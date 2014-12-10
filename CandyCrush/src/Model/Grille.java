/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 *
 * @author Neo
 */
public class Grille implements Serializable{
    private Case t_case[][];
    private final int x,y;

    public Grille(int x, int y) {
        this.x = x;
        this.y = y;
        t_case = new Case[y][x];
    }
    
    public Case getCase(int x, int y){
        if(x < this.x && y < this.y){
            return t_case[y][x];
        }
        return null;
    }
    
    public void setCase(Case maCase){
        this.t_case[maCase.getY()][maCase.getX()] = maCase;
    }
    
    public Case[][] getTabCase(){
        return t_case;
    }
    
    public int getHauteur(){
        return y;
    }
    
    public int getLargeur(){
        return x;
    }
    
    @Override
    public String toString(){
        String chaine = "";
        for(int j = 0; j < this.y; j++){
            for(int i = 0; i < this.x; i++){
                chaine += " " + this.t_case[j][i].getNumCouleurForme();
            }
            chaine += "\n";
        }
        return "\n" + chaine;
    }
}
