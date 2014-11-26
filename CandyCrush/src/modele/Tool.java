/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

/**
 *
 * @author Neo
 */
public class Tool {

    public static int monRandom(int max ,int min){
        int n=(int)(Math.random() * (max-min)) + min;
        
        return n;
    }

    
}
