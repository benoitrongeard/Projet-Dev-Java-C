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
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dualshote
 */
public class MainView extends JFrame {
    private ScoreLab scoreLab = new ScoreLab();
    private ChronoLab chronoLab = new ChronoLab();
    private final int minutes, secondes;
    private final String POLICE = "Arial";
    private JPanel jpGrille;
    
    /* Objet pour la serialisation */
    private Score scoreSeria;
    private Chrono chronoSeria;
    private Grille grilleSeria;
    
    private int width;
    private int height;
    
    public MainView(final int width, final int height, final int minutes, final int secondes){
        
        this.minutes = minutes;
        this.secondes = secondes;
        this.width = width;
        this.height = height;
        
        /*  --------- Paramètres --------- */
        this.setTitle("CandyCrush Party");
        this.setSize(665,560);
        this.setLocationRelativeTo(null);
        //this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        /*  --------- Menu --------- */
        JMenuBar menu = new JMenuBar();
        JMenu partie = new JMenu("Partie");
        JMenuItem nouvellePartie = new JMenuItem("Nouvelle partie");
        JMenuItem sauegarderPartie = new JMenuItem("Sauvegarder une partie");
        JMenuItem chargerPartie = new JMenuItem("Charger une partie");
        partie.add(nouvellePartie);
        partie.add(sauegarderPartie);
        partie.add(chargerPartie);
        menu.add(partie);
        
        /*  --------- Fenetre de droite --------- */
        JPanel jpDroite = new JPanel();
        //jpDroite.setBackground(Color.yellow);
        jpDroite.setPreferredSize(new Dimension(130,498));
        
        /*  --------- Label sur la fenêtre de droite --------- */
        scoreLab.setFont(new Font(POLICE, 0, 18));
        jpDroite.add(scoreLab);
        chronoLab.setFont(new Font(POLICE, 0, 18));
        jpDroite.add(chronoLab);
        
        /*  --------- Grille, fenêtre de gauche --------- */
        jpGrille = new JPanel(new GridLayout(height,width));
        jpGrille.setPreferredSize(new Dimension(500,500));
        grilleSeria = new Grille(height, width);
        initialisation(width, height, grilleSeria, jpGrille, new GestionDeLaGrille(grilleSeria,this.minutes, this.secondes), 0, null, null);
        
        
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
                initialisation(width, height, grilleSeria, jpGrille, new GestionDeLaGrille(grilleSeria, minutes, secondes), 1, null, null);
                System.out.println("Partie renitialisée");
            }
        });
        
        sauegarderPartie.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    sauvegargerSeria();
                } catch (IOException ex) {
                    Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        chargerPartie.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                try { 
                    chargementSeria();
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(MainView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        
        
        /*  Paramètre pour rendre visible la fenêtre    */
        this.setJMenuBar(menu);
        this.setVisible(true);
    }
    
    
    /*  --------- Fonction pour initialiser la grille de jeu --------- */
    public void initialisation(int width, int height,Grille grille, JPanel jpGrille, MouseListener monMouseListener, int reset, Score scoreParam, Chrono chronoParam){
        
        boolean init = true;
        
        if(reset == 0){ //Si la partie n'est pas renitialisée
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
            
            
            /*  Definition objet pour la serialisation  */
            this.grilleSeria = grille;
            this.scoreSeria = score;
            this.chronoSeria = chrono;
            
        }
        else if(reset == 1){   //Si la partie est renitialisée
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
            GestionChrono.setDebutChrono(1); //On permet au chronomètre de se lancer à nouveau quand on  redémarre une nouvelle partie
        
            /*  Definition objet pour la serialisation  */
            this.grilleSeria = grille;
            this.scoreSeria = score;
            this.chronoSeria = chrono;
        }
        else if(reset == -1){
            System.out.println("Chargement....");
            for(int j = 0; j < height; j++){
                for(int i =0; i < width; i++){
                    grilleSeria.getCase(i, j).regenerer(grille.getCase(i, j));
                }
            }
            
            if(scoreParam != null){
                this.scoreSeria.addObserver(this.scoreLab);
                this.scoreSeria.setPoints(scoreParam.getPoints());
                GestionAgregation.setScore(this.scoreSeria);
            }
            else{
                Score score = new Score();
                score.addObserver(this.scoreLab);
                score.setPoints(0);
                GestionAgregation.setScore(score);
            }
            
            if(chronoParam != null){
                this.chronoSeria.addObserver(this.chronoLab);
                this.chronoSeria.setChrono(chronoParam.getTimeMinute(), chronoParam.getTimeSeconde());
                GestionChrono.setChrono(this.chronoSeria);
                GestionChrono.setDebutChrono(1); 
            }
            else{
                Chrono chrono = new Chrono(minutes, secondes);
                chrono.addObserver(this.chronoLab);
                chrono.setChrono(minutes, secondes);
                GestionChrono.setChrono(chrono);
                GestionChrono.setDebutChrono(1); 
            }
            
            System.out.println("grille apres chargement : " + grille);
        }
    }
    
    public void chargementSeria() throws FileNotFoundException{
        ObjectInputStream oisGrille = null;
        ObjectInputStream oisScore = null;
        ObjectInputStream oisChrono = null;
        Grille grille = null;
        Score score = null;
        Chrono chrono = null;
        
        try{
            final FileInputStream fichierGrille = new FileInputStream("grille.serial");
            final FileInputStream fichierScore = new FileInputStream("score.serial");
            final FileInputStream fichierChrono = new FileInputStream("chrono.serial");
            oisGrille = new ObjectInputStream(fichierGrille);
            oisScore = new ObjectInputStream(fichierScore);
            oisChrono = new ObjectInputStream(fichierChrono);
            grille = (Grille) oisGrille.readObject();
            score = (Score) oisScore.readObject();
            chrono = (Chrono) oisChrono.readObject();
        }
        catch(final java.io.IOException e){
             e.printStackTrace();
        }
        catch (final ClassNotFoundException e) {
            e.printStackTrace();
        }
        finally {
            try {
              if (oisGrille != null) {
                oisGrille.close();
              }
              if(oisScore != null){
                  oisScore.close();
              }
              if(oisChrono != null){
                  oisChrono.close();
              }
            }
            catch (final IOException ex) {
              ex.printStackTrace();
            }
        }
       
        if(grille != null){
            initialisation(this.width, this.height, grille, this.jpGrille, new GestionDeLaGrille(grille, minutes, secondes), -1, score, chrono);
        }
        System.out.println("Grille chargée  : " + grille);
        System.out.println("Score chargé : " + score);
        System.out.println("Temps chargé : " + chrono);
        System.out.println("Partie chargée ! ");
    }
    
    public void sauvegargerSeria() throws FileNotFoundException, IOException{
        Grille grille1 = this.grilleSeria;
        Score score1 = this.scoreSeria;
        Chrono chrono1 = this.chronoSeria;
        
        ObjectOutputStream oosGrille = null;
        ObjectOutputStream oosScore = null;
        ObjectOutputStream oosChrono = null;
        
        System.out.println("Grille avant seria  : " + grille1);
        System.out.println("Score avant seria : " + score1.getPoints());
        System.out.println("Temps avant seria : " + chrono1);
        try {
            final FileOutputStream fosGrille = new FileOutputStream("grille.serial");
            final FileOutputStream fosScore = new FileOutputStream("score.serial");
            final FileOutputStream fosChrono = new FileOutputStream("chrono.serial");
            
            oosGrille = new ObjectOutputStream(fosGrille);
            oosScore = new ObjectOutputStream(fosScore);
            oosChrono = new ObjectOutputStream(fosChrono);
            
            // Ecriture dans le flux de sortie
            oosGrille.writeObject(grille1);
            oosScore.writeObject(score1);
            oosChrono.writeObject(chrono1);

            // Vide le tampon
            oosGrille.flush();
            oosScore.flush();
            oosChrono.flush();

        } 
        catch (final java.io.IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(oosGrille != null){
                    oosGrille.close();
                }
                if(oosScore != null){
                    oosScore.close();
                }
                if(oosChrono != null){
                    oosChrono.close();
                }
            }
            catch (final IOException ex) {
                ex.printStackTrace();
            }
        }
        System.out.println("Partie sauvegardée ! ");
    }
}
