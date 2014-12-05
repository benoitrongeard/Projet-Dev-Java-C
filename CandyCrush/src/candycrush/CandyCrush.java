/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candycrush;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import View_Controller.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

/**
 *
 * @author dualshote
 */
public class CandyCrush implements ActionListener{

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.lang.InstantiationException
     * @throws java.lang.IllegalAccessException
     * @throws javax.swing.UnsupportedLookAndFeelException
     */
     private int timeMinute;
      private int timeSeconde;
 
      public CandyCrush(int initMinute, int initSeconde) {
            super();
            this.timeMinute = initMinute;
            this.timeSeconde = initSeconde;
      }
      
     @Override
      public void actionPerformed(ActionEvent e) {
            if (this.timeSeconde == 0) {
                  this.timeMinute--;
                  this.timeSeconde = 59;
            } else
                  this.timeSeconde--;
            System.out.println(this.timeMinute + " : " + this.timeSeconde);
      }
    
    public static void main (String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        
        //MainView fenetre = new MainView(9,9);
        Timer timer = new Timer(1000, new CandyCrush(0,10));
        timer.start();

        try 
        {
          Thread.sleep(1000000); // 10000 ms, ça s'affiche 10-1sec fois
          // Le timer dure 10sec et s'affiche tous les 1sec
        } catch (InterruptedException e) 
        {
        }
        timer.stop();
    }
}
