/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candycrush;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import View_Controller.*;
import Model.*;
import java.awt.Button;
import java.awt.FlowLayout;

/**
 *
 * @author dualshote
 */
public class CandyCrush {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws javax.swing.UnsupportedLookAndFeelException
     */
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
        MainView fenetre = new MainView(9,9);
//        Grille g = new Grille(8,8);
//        Case c1 = new Case(1,2,g);
//        Case c2 = new Case(2,3,g);
//        
//        System.out.println(c1.getCouleurForme());
//        System.out.println(c1.getNumCouleurForme());
//        System.out.println(c2.getCouleurForme());
//        System.out.println(c2.getNumCouleurForme());
//        
//        
//        if(c1.equals(c2)){
//            System.out.println("vrai");
//        }
//        else{
//            System.out.println("false");
//        }
    }
}
