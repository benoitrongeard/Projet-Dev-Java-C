/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Neo
 */
public class Grille {
    private Case t_case[][];
    private int x,y;

    public Grille(int x, int y) {
        this.x = x;
        this.y = y;
        t_case = new Case[x][y];
    }
  
    public void build(){
        for(int i = 0; i < x; i++){
            for(int j = 0; j < y; j++){
                Case c = new Case(x, y,this);
                t_case[x][y] = c;
            }
        }
    }
    
    
}
