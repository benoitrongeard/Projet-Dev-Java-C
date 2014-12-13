/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import View_Controller.GestionAgregation;
import java.awt.Color;
import java.io.Serializable;
import java.util.Observable;

/**
 *
 * @author Neo
 */

public class Case extends Observable implements Serializable{
    
    private final int x,y;
    private int nombreAlea;
    public Grille maGrille;
    private Forme maForme;

    public Case(int x, int y, Grille g) {
        this.x = x;
        this.y = y;
        nombreAlea = Tool.monRandom(0, 3);
        maForme = new Forme(nombreAlea);
        this.maGrille = g;
    }
   
    //permet de regenerer une couleur aleatoire
    public void regenerer(){
        this.nombreAlea = Tool.monRandom(0, 3);
        this.maForme = new Forme(nombreAlea);
        setChanged();   //A faire pour prévenir l'observer avant d'appliquer notify
        notifyObservers();
    }
    
    public void regenerer(Case maCase){ //Permet de regénerer une couleur en fonction d'une autre case
        this.maForme = maCase.getForme();
        setChanged();   //A faire pour prévenir l'observer avant d'appliquer notify
        notifyObservers();
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Grille getGrille(){
        return this.maGrille;
    }
    
    public Forme getForme(){
        return maForme;
    }
    
    public void setForme(Forme maForme){
        this.maForme = maForme;
        setChanged();
        notifyObservers();
    }
    
    public void changeForme(Forme maForme){
        this.maForme = maForme;
        setChanged();
        notifyObservers();
    }

    
    public Color getCouleurForme(){
        return this.maForme.getCouleur();
    }
    
    public int getNumCouleurForme(){
        return this.maForme.getForme();
    }
    
    public boolean equals(Case c){  //Pour comparer des cases
        return this.maForme.equals(c.getForme());
    }
    
    public int aggregation(boolean init){ //Agregation pour l'initialisation
        if(this.getForme() != null){
            new GestionAgregation(this, init).start();
        }
        return 1;
    }
    
    public int aggregation(){
        GestionAgregation gestionAgregation = new GestionAgregation(this, false);
        gestionAgregation.start();
        return 1;
    }
}
