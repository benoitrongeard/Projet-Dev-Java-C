/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.Observable;

/**
 *
 * @author Sylvio
 */
public class Score extends Observable{
    private int points;
    
    public Score() {
        this.points = 0;
    }
    
    public void setPoints(int points){
        this.points = points;
        setChanged();
        notifyObservers();
    }
    
    public void addPoints(int points){
        this.points += points;
        setChanged();
        notifyObservers();
    }

    public int getPoints() {
        return points;
    }
    
    
    public String toString(boolean scoreNull){
        if(scoreNull == true){
            return "Score : " + 0;
        }
        else{
            return "Score : " + this.points;
        }
    }
    
}
