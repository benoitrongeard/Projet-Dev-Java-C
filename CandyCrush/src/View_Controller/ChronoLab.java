/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Chrono;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

/**
 *
 * @author Benoit
 */
public class ChronoLab extends JLabel implements Observer{

    public ChronoLab(){
        
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Chrono){
            Chrono chrono = (Chrono)o;
            
            if(chrono.getTimeMinute() == 0 && chrono.getTimeSeconde() <= 5){
                this.setText(chrono.toString()); 
                this.setForeground(Color.red); 
            }
            else{
                if(chrono.getTimeMinute() < 0){
                    chrono.setChronoNull();
                    chrono.setPartieTerminee();
                    this.setText(chrono.toString()); 
                    this.setForeground(Color.red);
                }
                else{
                    this.setText(chrono.toString());  
                    this.setForeground(Color.black); 
                }
            }       
        }
    }    
    
}
