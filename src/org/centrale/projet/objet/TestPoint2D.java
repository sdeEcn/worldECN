/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.centrale.projet.objet;

/**
 *
 * @author Alban
 */
public class TestPoint2D {
    
    public static void main(String[] args){
        Point2D p1 = new Point2D(-1,1);
        Point2D p2= new Point2D(p1);
        
        System.out.println("p2 est à la position : "+p2.affiche());
        System.out.println("p1 est à la position : "+p1.affiche());
        p1.setPosition(2, 4);// On translate le point p1 de dx=2 et dy=4
        System.out.println("p1 est à la position : "+p1.affiche());
        p2.setPosition(3,8);
        System.out.println("p2 est à la position : "+p2.affiche());
        
        System.out.println(" la distance entre p1 et p2 est de : "+ p1.distance(p2));
    }
    
}
