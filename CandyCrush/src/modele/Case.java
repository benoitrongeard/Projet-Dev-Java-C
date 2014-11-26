/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import View_Controller.Tool;
import java.util.Observable;




/**
 *
 * @author Neo
 */

public class Case extends Observable{
    
    private int x,y;
    private boolean selected,marked;
    private int nombreAlea;
    Grille g;
    public Forme forme;

    public Case(int x, int y,Grille g) {
        this.x = x;
        this.y = y;
        this.selected = false;
        this.marked = false;
        nombreAlea = Tool.monRandom(0, 2);
        forme = new Forme(nombreAlea);
    }
    

    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }



    public boolean isSelected() {
        return selected;
    }

    public boolean isMarked() {
        return marked;
    }
    
    


    
    
    
    
    
}
