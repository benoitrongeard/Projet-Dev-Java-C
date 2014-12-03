/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Grille;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author dualshote
 */
public class GestionDeLaGrille implements MouseListener{
    
    
    
    public GestionDeLaGrille(Grille maGrille){
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        System.out.println("bonjour");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
