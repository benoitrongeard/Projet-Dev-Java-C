/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.util.Observable;




/**
 *
 * @author Neo
 */

public class Case extends Observable{
    
    private final int x,y;
    private boolean selected,marked;
    private int nombreAlea;
    public Grille maGrille;
    private Forme maForme;

    public Case(int x, int y, Grille g) {
        this.x = x;
        this.y = y;
        this.selected = false;
        this.marked = false;
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
    
    public void regenerer(Case maCase){
        this.maForme = maCase.getForme();
        setChanged();   //A faire pour prévenir l'observer avant d'appliquer notify
        notifyObservers();
    }
    
    public void changeForme(Forme maForme){
        this.maForme = maForme;
        setChanged();
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
    
    public boolean isSelected() {
        return selected;
    }

    public boolean isMarked() {
        return marked;
    }
    
    public Forme getForme(){
        return maForme;
    }
    
    public Color getCouleurForme(){
        return this.maForme.getCouleur();
    }
    
    public int getNumCouleurForme(){
        return this.maForme.getForme();
    }
    
    public boolean equals(Case c){
        return this.maForme.equals(c.getForme());
    }
}
