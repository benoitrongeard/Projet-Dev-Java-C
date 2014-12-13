/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Chrono;
import candycrush.CandyCrush;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Timer;

/**
 *
 * @author Benoit
 */
public class GestionChrono extends java.lang.Thread{
    private static int minutes, secondes;
    private static Chrono chrono = null;
    private static int debutChrono;
    
    private static Timer timer;
    
    
    public GestionChrono(int minutes, int secondes){
        GestionChrono.minutes = minutes;
        GestionChrono.secondes = secondes;
    }
    
    public static void setChrono(Chrono chrono){
        GestionChrono.chrono = chrono;
    }
    
    public static int getMinuteChrono(){
        return GestionChrono.minutes;
    }
    
     public static int getSecondeChrono(){
        return GestionChrono.secondes;
    }
    
    public static void setDebutChrono(int debutChrono){
        GestionChrono.debutChrono = debutChrono;
    }
    
    public static int getDebutChrono(){
        return GestionChrono.debutChrono;
    }
    
    public Chrono getChrono(){
        return GestionChrono.chrono;
    }
    
    @Override
    public void run(){  //Permet de lancer un nouveau thread qui appelle Timer, avec le chronomètre
        timer = new Timer(1000, chrono);
        timer.start();
        int minuteEnSeconde = this.minutes * 60;
        int totalSeconde = minuteEnSeconde + this.secondes;
        int totalMilliseconde = totalSeconde * 1000 + 1000; //On calcul le temps exacte pour endormir le thread pendant que le timer s'execute et décompte
        //System.out.println("MinuteEnSeconde : " + minuteEnSeconde + " et total seconde : " + totalSeconde);

        try 
        {
          Thread.sleep(totalMilliseconde); 
        } catch (InterruptedException e) 
        {
            Logger.getLogger(GestionChrono.class.getName()).log(Level.SEVERE, null, e);
        }
        timer.stop();
    }
}
