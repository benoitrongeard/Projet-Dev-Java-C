/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;

/**
 *
 * @author dualshote
 */
public class Forme {
    
    private Color c;
    private int numColor;
    
    public Forme(int numeroAleatoire){
        switch(numeroAleatoire){
            case 0: this.c = Color.blue;
                this.numColor = 0;
                break;
            case 1: this.c = Color.yellow;
                this.numColor = 1;
                break;
            case 2: this.c = Color.red;
                this.numColor = 2;
                break;
            case 3: this.c = Color.green;
                this.numColor = 2;
                break;
            default: this.c = Color.green;
                this.numColor = 0;
                break;
        }
    }
    
    public int getForme(){
        return numColor;
    }
    
    public Color getCouleur(){
        return c;
    }
    
    public boolean equals(Forme forme){
        if(forme == null){
            return false;
        }
        if(this.numColor == forme.getForme()){
            return true;
        }
        else{
            return false;
        }
    }
}
