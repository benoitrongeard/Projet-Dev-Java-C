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
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author dualshote
 */
public class GestionDeLaGravite extends java.lang.Thread{
    public static int nombreDeThread = 0;
    
    private static Set<Case> setCaseMajAgreg = Collections.synchronizedSet(new HashSet());
    private Grille grille;
    private int numColonne = 0;
    private static boolean init;
    
    public GestionDeLaGravite(int numColonne, Grille grille, boolean init){
        this.numColonne = numColonne;
        this.grille = grille;
        this.init = init;
    }
    
    public static synchronized void incrementThread(){
        nombreDeThread++;
    }
    
    public static synchronized void decrementThread(){
        nombreDeThread--;
        if(nombreDeThread == 0){
            for(Case c : setCaseMajAgreg){
                try {
                    sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(GestionDeLaGravite.class.getName()).log(Level.SEVERE, null, ex);
                }
               if(init == true){
                    new GestionAgregation(c, init).start();
               }
               else{
                    new GestionAgregation(c, false).start();   
               }
            }
            setCaseMajAgreg.clear();
        }
    }
    
    @Override
    @SuppressWarnings("empty-statement")
    public void run(){
        incrementThread();
            synchronized(grille){
                int numCaseDepart = 0;
                for(numCaseDepart = grille.getHauteur()-1; numCaseDepart >= 0 && grille.getCase(numColonne, numCaseDepart).getForme() != null; numCaseDepart--);
                System.out.println("Colonne : " + numColonne + "  Case Y : " + numCaseDepart);
                if(numCaseDepart != -1){ // Si -1, il n'y a pas de case vide sur cette colonne, donc pas de gravité à vérifier
                    Case startCase = grille.getCase(numColonne, numCaseDepart);
                    for(int i = numCaseDepart; i >= 0; i--){
                        Case temporaireCase = grille.getCase(numColonne, i);
                        setCaseMajAgreg.add(temporaireCase);
                        if(temporaireCase.getForme() != null){
                            startCase.regenerer(temporaireCase);
                            temporaireCase.setForme(null);
                            numCaseDepart--;
                            startCase = grille.getCase(numColonne, numCaseDepart);
                        }
                    }
                    for(int i=numCaseDepart; i >= 0; i--){
                        Case temporaireCase = grille.getCase(numColonne, i);
                        temporaireCase.regenerer();
                        setCaseMajAgreg.add(temporaireCase);
                    }
                }

                
                
            }
        decrementThread();
    }
}
