/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;

/**
 * Définit un guerrier : combat au corps à corps.
 *
 * @author Alban
 */
public class Guerrier extends Personnage implements Combattant {

    public Guerrier() {
        super();
    }

    public Guerrier(String nom, int ptVie, int ptMana, int pourcentageAtt, int pourcentagePar, int pourcentageMag, int pourcentageResistMag, int degAtt, int degMag, int distAttMax,int parade, Point2D pos) {
        super(nom, ptVie, ptMana, pourcentageAtt, pourcentagePar, pourcentageMag, pourcentageResistMag, degAtt, degMag, distAttMax, pos,parade);
    }

    /**
     * Permet d'infliger des dégats à son adversaire c.
     *
     * @param c adversaire du guerrier.
     */
    @Override
    public void combattre(Creature c) {
        Random rand = new Random();
        int success = rand.nextInt(100), distance;
        distance = this.getPos().distance(c.getPos());
        if (distance <= this.getDistAttMax()) {//On vérifie que l'on peut toucher l'adversaire
            if (success < this.getPourcentageAtt()) { // L'attaque réussie
                int parade = rand.nextInt(100);
                int degats;
                if (parade <= c.getPourcentagePar()) {
                    degats = this.getDegAtt() - parade;
                    System.out.println("Parade de l'adversaire.");
                } else {
                    degats = this.getDegAtt();
                    System.out.println("Aucune parade de l'adversaire.");
                }
                c.setPtVie(c.getPtVie() - degats); //on inflige des dégats à l'adversaire.
                System.out.println("Attaque réussie de " + getNom() + " : " + degats + " dégats infligés.");
            } else {
                System.out.println("Dans le vide!");
            }
        } else {
            System.out.println("Oups! loupé!!");
        }
    }
    
    /**
     * Affiche les informations du guerrier.
     */
    @Override
    public void affiche() {
        System.out.print("Guerrier, ");
        super.affiche();
    }
    
    @Override
    public String symbole(){return "G";}
}
