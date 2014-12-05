/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Case;
import Model.Grille;
import Model.Score;

/**
 *
 * @author dualshote
 */
public class GestionAgregation extends java.lang.Thread{
    
    public static int nombreDeThread = 0;
    public static boolean modifG = false; // True si l'utilisateur marque des point (modifie la grille)
    private static Score score = null;
    private final Case maCase;
    private static boolean init;
    
    public GestionAgregation(Case maCase, boolean init){
        this.maCase = maCase;
        this.init = init;
    }
    
    public static void setScore(Score score){
        GestionAgregation.score = score;
    }
    
    public static void addPoints(int points){
        if(init != true){
            if(score != null){
                GestionAgregation.score.addPoints(points);
            }
        }
    }
    
    public static synchronized void incrementThread(){
        nombreDeThread++;
    }
    
    public static synchronized void decrementThread(Grille g){
        nombreDeThread--;
        if(nombreDeThread == 0 && g != null && modifG){
            modifG = false;
            for(int i=0; i < g.getLargeur(); i++){
                if(init == true){
                    new GestionDeLaGravite(i, g, init).start();
                }
                else{
                    new GestionDeLaGravite(i, g, false).start();
                }
            }
        }
    }
    
    @SuppressWarnings("empty-statement")
    @Override
    public void run(){
//        if(init){
//            System.out.println("INITIALISATION");
//        }
//        else{
//            System.out.println("PAS INITIALISATION");
//        }
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
                for(int i = maCase.getY()-1; i >= 0; i--){
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

//                System.out.println("-- Haut : " + nbCaseHaut + " Bas : " + nbCaseBas);
//                System.out.println("   Droite : " + nbCaseDroite + " Gauche : " + nbCaseGauche);
                
                
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
                    GestionAgregation.addPoints(point);
                    modifG = true;
                }
            }
            decrementThread(maCase.getGrille());
        }
    }
}
