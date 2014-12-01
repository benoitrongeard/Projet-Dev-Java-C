/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Forme;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.JPanel;

/**
 *
 * @author dualshote
 */
public class CaseGrille extends JPanel implements Observer{
    
    public CaseGrille(Forme forme){
        
        this.addMouseListener(new MouseListener() {
            
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                System.out.println("Case cliqué");
                CaseGrille.this.setBackground(Color.PINK);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
            }
        }); 
        
        this.setBackground(forme.getCoueleur());
    }
    
    

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
