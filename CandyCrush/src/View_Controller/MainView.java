/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author dualshote
 */
public class MainView extends JFrame{
    
    
    public MainView(){
        /*  Fenetre */
        this.setTitle("CandyCrush Party");
        this.setSize(500,300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        /*  Menu  */
        JMenuBar menu = new JMenuBar();
        JMenu partie = new JMenu("Partie");
        JMenuItem nouvellePartie = new JMenuItem("Nouvelle partie");
        partie.add(nouvellePartie);
        menu.add(partie);
        
        
        /*  Grille  */
        JPanel jpGrille = new JPanel(new GridLayout(9,9));
        for(int i = 0; i < 9; i++){
            for(int j =0; j < 9; j++){
                Random rand = new Random();
                float r = rand.nextFloat();
                float g = rand.nextFloat();
                float b = rand.nextFloat();
                Color randomColor = new Color(r, g, b);
                
                JPanel panel = new JPanel();
                panel.setBackground(randomColor);

                jpGrille.add(panel);
            }
        }
        
        
        
        /*  Affichage   */
        this.setJMenuBar(menu);
        this.add(jpGrille);
        this.setVisible(true);
    }
}
