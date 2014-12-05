/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;
import Model.Case;
import Model.Chrono;
import Model.Grille;
import Model.Score;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
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
    private ScoreLab scoreLab = new ScoreLab();
    private ChronoLab chronoLab = new ChronoLab();
    private final int minutes, secondes;
    
    public MainView(final int width, final int height, final int minutes, final int secondes){
        
        this.minutes = minutes;
        this.secondes = secondes;
        
        /*  --------- Paramètres --------- */
        this.setTitle("CandyCrush Party");
        this.setSize(650,560);
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        /*  --------- Menu --------- */
        JMenuBar menu = new JMenuBar();
        JMenu partie = new JMenu("Partie");
        JMenuItem nouvellePartie = new JMenuItem("Nouvelle partie");
        partie.add(nouvellePartie);
        menu.add(partie);
        
        /*  --------- Fenetre de droite --------- */
        JPanel jpDroite = new JPanel();
        //jpDroite.setBackground(Color.yellow);
        jpDroite.setPreferredSize(new Dimension(130,498));
        
        /*  --------- Label sur la fenêtre de droite --------- */
        jpDroite.add(scoreLab);
        jpDroite.add(chronoLab);
        
        /*  --------- Grille, fenêtre de gauche --------- */
        final JPanel jpGrille = new JPanel(new GridLayout(height,width));
        jpGrille.setPreferredSize(new Dimension(500,500));
        final Grille grille = new Grille(height, width);
        initialisation(width, height, grille, jpGrille, new GestionDeLaGrille(grille,this.minutes, this.secondes), false);
        
        
        /*  --------- Gestion affichage --------- */
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
        
        
        /*  --------- Action sur le menu --------- */
        nouvellePartie.addActionListener(new ActionListener() {
            boolean reset = true;
            @Override
            public void actionPerformed(ActionEvent e) {
                initialisation(width, height, grille, jpGrille, new GestionDeLaGrille(grille, minutes, secondes), reset);
                System.out.println("Partie renitialisée");
            }
        });
        
        this.setJMenuBar(menu);
        this.setVisible(true);
    }
    
    
    /*  --------- Fonction pour initialiser la grille de jeu --------- */
    public void initialisation(int width, int height,Grille grille, JPanel jpGrille, MouseListener monMouseListener, boolean reset){
        
        boolean init = true;
        
        if(reset == false){
            for(int j = 0; j < height; j++){
                for(int i =0; i < width; i++){
                    Case maCase = new Case(i,j,grille);
                    grille.setCase(maCase);
                    CaseGrille maCaseGrille = new CaseGrille();
                    maCaseGrille.initialisation(maCase.getX(), maCase.getY(), maCase.getForme());
                    maCase.addObserver(maCaseGrille);
                    maCaseGrille.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                    maCaseGrille.addMouseListener(monMouseListener);
                    jpGrille.add(maCaseGrille);
                }
            }

            for(int j = 0; j < height; j++){
                for(int i =0; i < width; i++){
                    grille.getCase(i, j).aggregation(init);
                }
            }
            Score score = new Score();
            score.addObserver(this.scoreLab);
            score.setPoints(0);
            GestionAgregation.setScore(score);
            
            Chrono chrono = new Chrono(minutes, secondes);
            chrono.addObserver(this.chronoLab);
            chrono.setChrono(minutes, secondes);
            GestionChrono.setChrono(chrono);
            
            
        }
        else{
            for(int j = 0; j < height; j++){
                for(int i =0; i < width; i++){
                    grille.getCase(i, j).regenerer();
                    grille.getCase(i, j).aggregation(init);
                }
            }
            Score score = new Score();
            score.addObserver(this.scoreLab);
            score.setPoints(0);
            GestionAgregation.setScore(score);
            
            Chrono chrono = new Chrono(minutes, secondes);
            chrono.addObserver(this.chronoLab);
            chrono.setChrono(minutes, secondes);
            GestionChrono.setChrono(chrono);
            GestionChrono.setDebutChrono(1); //On permet au chronomètre de se lancer à nouveau quand on démarre une nouvelle partie
        }
    }
}
