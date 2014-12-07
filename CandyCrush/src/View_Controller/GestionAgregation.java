/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Case;
import Model.Forme;
import Model.Grille;
import Model.Score;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

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
    private static Set<Integer> setColumnToUpdGrav = Collections.synchronizedSet(new HashSet());
    
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
    
    public static synchronized void decrementThread(Case c){
        nombreDeThread--;
        if(nombreDeThread == 0){
            for (Integer it: setColumnToUpdGrav) {
                if(init == true){
                    new GestionDeLaGravite(it, c.getGrille(), init).start();
                }
                else{
                    new GestionDeLaGravite(it, c.getGrille(), false).start();
                }
            }
            setColumnToUpdGrav.clear();
        }
    }
    
    public boolean testCase(Case maCase2){
        incrementThread();
        incrementThread();
        boolean compatible = false;

        Forme formeTmp = this.maCase.getForme();
        this.maCase.setForme(maCase2.getForme());
        maCase2.setForme(formeTmp);
        
        if(this.agregation(this.maCase) != 0){
            compatible = true;
        }
        if(this.agregation(maCase2) != 0){
            compatible = true;
        }
        if(compatible == true){
            this.maCase.changeForme(this.maCase.getForme());
            maCase2.changeForme(maCase2.getForme());
        }
        else{
            maCase2.changeForme(this.maCase.getForme());
            this.maCase.changeForme(formeTmp);
        }
        decrementThread(this.maCase);
        decrementThread(maCase2);
        if(compatible == false){
            System.out.println("Impossible d'intervertir !");
        }
        return compatible;
    }
    
    public int agregation(Case maCase){
        int point = 0;
        if(maCase != null && maCase.getForme() != null){
            synchronized(maCase.getGrille()){
                Grille maGrille = maCase.getGrille();
                int nbCaseDroite, nbCaseGauche, nbCaseHaut, nbCaseBas;

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

                System.out.println("-- Haut : " + nbCaseHaut + " Bas : " + nbCaseBas);
                System.out.println("   Droite : " + nbCaseDroite + " Gauche : " + nbCaseGauche);
                
                
                if((nbCaseDroite + nbCaseGauche + 1) >= 3){
                    for(int i = maCase.getX() - nbCaseGauche; i < (maCase.getX() + nbCaseDroite+1); i++){
                        maGrille.getCase(i, maCase.getY()).setForme(null);
                    }
                    point += (nbCaseDroite + nbCaseGauche) - 1;
                    System.out.println("largeur : " +   point);
                }
                else{
                    nbCaseDroite = 0;
                    nbCaseGauche = 0;
                }
                if((nbCaseHaut + nbCaseBas + 1) >= 3){
                    for(int i = maCase.getY() - nbCaseHaut; i < (maCase.getY() + nbCaseBas+1); i++){
                        maGrille.getCase(maCase.getX(), i).setForme(null);
                    }
                    point += (nbCaseHaut + nbCaseBas) - 1;
                    System.out.println("hauteur : " +   point);
                }

                if(point > 0){
                    GestionAgregation.addPoints(point);
                    setColumnToUpdGrav.add(maCase.getX());
                    for(int j = maCase.getX() - nbCaseGauche; j < (maCase.getX() + nbCaseDroite + 1); j++){
                        if(j != maCase.getX()){
                            setColumnToUpdGrav.add(j);
                        }
                    }
                }
            }
        }
        return point;
    }
    
    @Override
    public void run(){
        incrementThread();
        agregation(maCase);
        decrementThread(maCase);
    }
}
