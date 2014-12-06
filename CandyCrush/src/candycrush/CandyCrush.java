/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candycrush;

import View_Controller.MainView;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.util.Scanner;

/**
 *
 * @author dualshote
 */
public class CandyCrush{
    private static int minutes, secondes;

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws javax.swing.UnsupportedLookAndFeelException
     */
    
    public static void main (String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
        /*System.out.println("------------------Menu-------------------");
        System.out.println("1 - Facile (Temps de jeu : 1min30)");
        System.out.println("2 - Moyen (Temps de jeu : 1min)");
        System.out.println("3 - Difficile (Temps de jeu : 30s)");
        System.out.println("4 - Exit");
        System.out.println("-----------------------------------------");
        
        System.out.println("Choisissez votre niveau pour démarer le jeu...");
        
        Scanner sc = new Scanner(System.in);
        int resultatScan = sc.nextInt();
        
        switch(resultatScan){
            case 1: 
                minutes = 1;
                secondes = 30;
                break;
            case 2: 
                minutes = 1;
                secondes = 0;
                break;
            case 3:
                minutes = 0;
                secondes = 30;
                break;
            case 4:
                minutes = 0;
                secondes = 0;
                break;
            default:
                minutes = 1;
                secondes = 30;
                break;
        }
        
        if(minutes !=0 || secondes != 0){
            MainView fenetre = new MainView(9,9,minutes,secondes);
        }*/
        
        MainView fenetre = new MainView(9,9,0,30);
    }
}
