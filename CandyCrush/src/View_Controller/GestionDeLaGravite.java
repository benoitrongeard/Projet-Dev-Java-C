/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Case;
import Model.Grille;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author dualshote
 */
public class GestionDeLaGravite extends java.lang.Thread{
    public static int nombreDeThread = 0;
    public static Lock mutex = new ReentrantLock();
    
    private static Set<Case> setCaseMajAgreg = Collections.synchronizedSet(new HashSet());
    private Case maCase;
    
    public GestionDeLaGravite(Case maCase){
        this.maCase = maCase;
    }
    
    @Override
    @SuppressWarnings("empty-statement")
    public void run(){
        mutex.lock();
        try{
            nombreDeThread++;
        }
        finally{
            mutex.unlock();
        }
        
        synchronized(maCase.getGrille()){
            Grille maGrille = maCase.getGrille();
            int numCaseDepart;
            for(numCaseDepart = maCase.getY(); numCaseDepart < maGrille.getHauteur(); numCaseDepart++);
            if(numCaseDepart != 0){
                numCaseDepart--;
            }
            for(int i = numCaseDepart; i >= 0; i--){
                setCaseMajAgreg.add(maGrille.getCase(maCase.getX(), i));
            }
            
            Case startCase = maGrille.getCase(maCase.getX(), numCaseDepart);
            for(int i = numCaseDepart; i >= 0; i--){
                Case temporaireCase = maGrille.getCase(maCase.getX(), i);
                startCase.regenerer(temporaireCase);
                temporaireCase.setForme(null);
                int j;
                for(j = numCaseDepart; j >= i; j--){
                    startCase = maGrille.getCase(maCase.getX(), j);
                    numCaseDepart = j;
                }
            }
        }
        
        mutex.lock();
        nombreDeThread--;
        if(nombreDeThread == 0){
            for(Case maCase : setCaseMajAgreg){
                new GestionAgregation(maCase).start();
            }
            setCaseMajAgreg.clear();
            mutex.unlock();
        }
        else{
            mutex.unlock();
        }
    }
}
