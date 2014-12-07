/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Case;
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
    
    public int x;
    public int y;
    
    public CaseGrille(){
        this.x = 0;
        this.y = 0;
    }
    
    public void initialisation(int x, int y, Forme maForme){
        this.x = x;
        this.y = y;
        this.setBackground(maForme.getCouleur());
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof Case){
            Case maCase = (Case)o;
            if(maCase.getForme() != null){
                this.setBackground(maCase.getCouleurForme());
            }
            else{
                this.setBackground(Color.white);
            }
        }
    }        
}
