/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.GroupLayout;
import javax.swing.JButton;
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
    
    
    public MainView(int width, int height){
        /*  Fenetre */
        this.setTitle("CandyCrush Party");
        this.setSize(630,545);
        //this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        /*  Menu  */
        JMenuBar menu = new JMenuBar();
        JMenu partie = new JMenu("Partie");
        JMenuItem nouvellePartie = new JMenuItem("Nouvelle partie");
        partie.add(nouvellePartie);
        menu.add(partie);
        
        
        /*  Fenetre de droite   */
        JPanel jpDroite = new JPanel();
        jpDroite.setBackground(Color.yellow);
        jpDroite.setPreferredSize(new Dimension(130,498));
        
        /*  Bouton fenetre de droite    */
        JButton JB1 = new JButton();
        JButton JB2 = new JButton();
        JButton JB3 = new JButton();
        jpDroite.add(JB1);
        jpDroite.add(JB2);
        jpDroite.add(JB3);
        
        /*  Grille  */
        JPanel jpGrille = new JPanel(new GridLayout(height,width));
        jpGrille.setPreferredSize(new Dimension(500,500));
        for(int i = 0; i < width; i++){
            for(int j =0; j < height; j++){
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
        GroupLayout groupeLayout = new GroupLayout(this.getContentPane());
        this.setLayout(groupeLayout);
        groupeLayout.setHorizontalGroup(
                groupeLayout.createSequentialGroup()
                            .addComponent(jpGrille, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpDroite, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );
        groupeLayout.setVerticalGroup(
                groupeLayout.createSequentialGroup()
                        .addGroup(groupeLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(jpGrille, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                            .addComponent(jpDroite, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
        );
        
        this.setJMenuBar(menu);
        this.setVisible(true);
    }
}
