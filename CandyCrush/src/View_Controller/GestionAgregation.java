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
public class GestionAgregation extends java.lang.Thread{
    
    public static int nombreDeThread = 0;
    public static Lock mutex = new ReentrantLock();
    private static Set<Case> setCaseMajGrav = Collections.synchronizedSet(new HashSet());

    
    private Case maCase;
    
    public GestionAgregation(Case maCase){
        this.maCase = maCase;
    }
    
    public static synchronized void incrementThread(){
        nombreDeThread++;
    }
    
    public static synchronized void decrementThread(){
        nombreDeThread--;
        if(nombreDeThread == 0){
            for(Case c : setCaseMajGrav){
               // new GestionDeLaGravite(c).start();
            }
            setCaseMajGrav.clear();
        }
    }
    
    @SuppressWarnings("empty-statement")
    @Override
    public void run(){
        if(maCase != null && maCase.getForme() != null){
            incrementThread();
            synchronized(maCase.getGrille()){
                Grille maGrille = maCase.getGrille();
                int nbCaseDroite, nbCaseGauche, nbCaseHaut, nbCaseBas;
                int point = 0;

                //On parcour les cases à droite
                nbCaseDroite = 0;
                for(int i = maCase.getX()+1; i < maGrille.getLargeur(); i++){
                    if(maGrille.getCase(i, maCase.getY()).getForme() == null){
                        break;
                    }
                    if(maGrille.getCase(i, maCase.getY()).getForme().equals(maCase.getForme())){
                        nbCaseDroite++;
                    }
                    else{
                        break;
                    }
                }
        
        
                //On parcour les cases à gauche
                nbCaseGauche = 0;
                for(int i = maCase.getX()-1; i >= 0; i--){
                    if(maGrille.getCase(i, maCase.getY()).getForme() == null){
                        break;
                    }
                    if(maGrille.getCase(i, maCase.getY()).getForme().equals(maCase.getForme())){
                        nbCaseGauche++;
                    }
                    else{
                        break;
                    }
                }

                //On parcour les cases du bas
                nbCaseBas = 0;
                for(int i = maCase.getY()+1; i < maGrille.getHauteur(); i++){
                    if(maGrille.getCase(maCase.getX(), i).getForme() == null){
                        break;
                    }
                    if(maGrille.getCase(maCase.getX(), i).getForme().equals(maCase.getForme())){
                        nbCaseBas++;
                    }
                    else{
                        break;
                    }

                }

                //On parcour les cases du haut
                nbCaseHaut = 0;
                for(int i = maCase.getY(); i >= 0; i--){
                    if(maGrille.getCase(maCase.getX(), i).getForme() == null){
                        break;
                    }
                    if(maGrille.getCase(maCase.getX(), i).getForme().equals(maCase.getForme())){
                        nbCaseHaut++;
                    }
                    else{
                        break;
                    }
                }

                if((nbCaseDroite + nbCaseGauche + 1) >= 3){
                    for(int i = maCase.getX() - nbCaseGauche; i < (maCase.getX() + nbCaseDroite+1); i++){
                        maGrille.getCase(i, maCase.getY()).setForme(null);
                    }
                    point += (nbCaseDroite + nbCaseGauche);
                }
                else{
                    nbCaseDroite = 0;
                    nbCaseGauche = 0;
                }
                if((nbCaseHaut + nbCaseBas + 1) >= 3){
                    for(int i = maCase.getY() - nbCaseHaut; i < (maCase.getY() + nbCaseBas+1); i++){
                        maGrille.getCase(maCase.getX(), i).setForme(null);
                    }
                    point += (nbCaseHaut + nbCaseBas);
                }
                
                if(point > 0){
                    setCaseMajGrav.add(maCase);
                    
                    for(int j = maCase.getX() - nbCaseGauche; j < (maCase.getX() + nbCaseDroite + 1); j++){
                        if(j != maCase.getX()){
                            setCaseMajGrav.add(maGrille.getCase(j, maCase.getY()));
                        }
                    }
                }
            }
            decrementThread();
        }
    }
}
