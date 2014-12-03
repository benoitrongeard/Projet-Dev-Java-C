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

    public void setSelected(){
        if(this.isSelected()){
            this.selected = false;
        }
        else{
            this.selected = true;
        }
        setChanged();
        notifyObservers();
    }
    
    
    public boolean isMarked() {
        return marked;
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
    
    public boolean equals(Case c){
        return this.maForme.equals(c.getForme());
    }
    
    public int aggregation(){
        int nbCaseDroite, nbCaseGauche, nbCaseHaut, nbCaseBas;
        int point = 0;
        
        //On parcour les cases à droite
        nbCaseDroite = 0;
        for(int i = this.x+1; i < maGrille.getLargeur(); i++){
            if(maGrille.getCase(i, this.y).getForme() == null){
                break;
            }
            if(maGrille.getCase(i, this.y).getForme().equals(this.maForme)){
                nbCaseDroite++;
            }
            else{
                break;
            }
        }
        System.out.println(this.maForme);
        
        
        //On parcour les cases à gauche
        nbCaseGauche = 0;
        for(int i = this.x-1; i >= 0; i--){
            if(maGrille.getCase(i, this.y).getForme() == null){
                break;
            }
            if(maGrille.getCase(i, this.y).getForme().equals(this.maForme)){
                nbCaseGauche++;
            }
            else{
                break;
            }
        }
        
        //On parcour les cases du bas
        nbCaseBas = 0;
        for(int i = this.y+1; i < maGrille.getHauteur(); i++){
            if(maGrille.getCase(this.x, i).getForme() == null){
                break;
            }
            if(maGrille.getCase(this.x, i).getForme().equals(this.maForme)){
                nbCaseBas++;
            }
            else{
                break;
            }
            
        }
        
        //On parcour les cases du haut
        nbCaseHaut = 0;
        for(int i = this.y-1; i >= 0; i--){
            if(maGrille.getCase(this.x, i).getForme() == null){
                break;
            }
            if(maGrille.getCase(this.x, i).getForme().equals(this.maForme)){
                nbCaseHaut++;
            }
            else{
                break;
            }
        }
        
        if((nbCaseDroite + nbCaseGauche + 1) >= 3){
            for(int i = this.x - nbCaseGauche; i < (this.x + nbCaseDroite+1); i++){
                maGrille.getCase(i, this.y).setForme(null);
            }
            point += (nbCaseDroite + nbCaseGauche);
        }
        else{
            nbCaseDroite = 0;
            nbCaseGauche = 0;
        }
        if((nbCaseHaut + nbCaseBas + 1) >= 3){
            for(int i = this.y - nbCaseHaut; i < (this.y + nbCaseBas+1); i++){
                maGrille.getCase(this.x, i).setForme(null);
            }
            point += (nbCaseHaut + nbCaseBas);
        }
        
        /*if(point > 0){
            new UpdateGravity(this).start();
            for(int i = this.x - nbCaseGauche; i < (this.x + nbCaseDroite + 1); i++){
                if(i != this.x){
                    new UpdateGravity(maGrille.getCase(i, this.y)).start();
                }
            }
        }*/
        return point;
    }
}
