/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candycrush;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import View_Controller.*;

/**
 *
 * @author dualshote
 */
public class CandyCrush {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
        MainView fenetre = new MainView(9,9);
    }
    
}
