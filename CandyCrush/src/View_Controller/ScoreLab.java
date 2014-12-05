/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Score;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

/**
 *
 * @author dualshote
 */
public class ScoreLab extends JLabel implements Observer{

    public ScoreLab(){
    }
    
    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Score){
            Score score = (Score)o;
            if(score.getPoints() == 0){
                this.setText(score.toString(true)); 
            }
            else{
                this.setText(score.toString(false));   
            }
        }
    }
    
}
