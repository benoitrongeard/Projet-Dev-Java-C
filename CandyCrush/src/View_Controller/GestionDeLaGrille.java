/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View_Controller;

import Model.Case;
import Model.Forme;
import Model.Grille;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;

/**
 *
 * @author dualshote
 */
public class GestionDeLaGrille implements MouseListener{
    
    private Grille maGrille;
    private CaseGrille caseSelected;
    private int point;
    
    public GestionDeLaGrille(Grille maGrille){
        this.maGrille = maGrille;
        this.caseSelected = null;
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        CaseGrille maCaseGrille = (CaseGrille) e.getSource();
        if(caseSelected == null){
            caseSelected = maCaseGrille;
            maCaseGrille.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        }
        else if(maCaseGrille != this.caseSelected && 
                ((maCaseGrille.x == caseSelected.x + 1 || maCaseGrille.x == caseSelected.x - 1) && maCaseGrille.y == caseSelected.y) ||
                ((maCaseGrille.y == caseSelected.y + 1 || maCaseGrille.y == caseSelected.y - 1) && maCaseGrille.x == caseSelected.x)){
            
            Case maCase1 = maGrille.getCase(caseSelected.x, caseSelected.y);
            Case maCase2 = maGrille.getCase(maCaseGrille.x, maCaseGrille.y);
            //System.out.println(maCase1);
            //System.out.println(maCase2);
            
            Forme formeTmp = maCase1.getForme();
            maCase1.setForme(maCase2.getForme());
            maCase2.setForme(formeTmp);
            
            int pointsAdd = maCase1.aggregation();
            if(maCase2.getForme() != null){
                pointsAdd +=  maCase2.aggregation();
            }
            if(pointsAdd > 0){
                point += pointsAdd;
                maCase1.changeForme(maCase1.getForme());
                maCase2.changeForme(maCase2.getForme());
            }
            else{
                maCase2.changeForme(maCase1.getForme());
                maCase1.changeForme(formeTmp);
            }
            
            this.caseSelected.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            caseSelected = null;
        }
        else{
            this.caseSelected.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            caseSelected = null;
        }
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
