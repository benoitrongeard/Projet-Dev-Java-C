/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import java.util.Observable;
import java.util.Observer;
import javax.swing.JLabel;

/**
 *
 * @author dualshote
 */
public class Score extends JLabel implements Observer{

    public Score(){
        
    }
    
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
