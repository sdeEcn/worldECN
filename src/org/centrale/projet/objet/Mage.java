/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;

/**
 *
 * @author Alban
 */
public class Mage extends Personnage implements Combattant {

    public Mage() {
        super();
    }

    public Mage(String nom, int ptVie, int ptMana, int pourcentageAtt, int pourcentagePar, int pourcentageMag, int pourcentageResistMag, int degAtt, int degMag, int distAttMax, Point2D pos) {
        super(nom, ptVie, ptMana, pourcentageAtt, pourcentagePar, pourcentageMag, pourcentageResistMag, degAtt, degMag, distAttMax, pos);
    }

    /**
     * Permet d'attaquer un adversaire c.
     *
     * @param c cible de l'attaque.
     */
    @Override
    public void combattre(Creature c) {
        if (this.getPtMana() > 0) {
            this.setPtMana(this.getPtMana() - 1);
            Random rand = new Random();
            int success = rand.nextInt(100), distance;
            distance = this.getPos().distance(c.getPos());
            if (distance <= this.getDistAttMax()) {//On vérifie que l'on peut toucher l'adversaire
                if (success < this.getPourcentageMag()) { // L'attaque réussie
                    int parade = rand.nextInt(100);
                    int degats;
                    degats = this.getDegMag();
                    c.setPtVie(c.getPtVie() - degats); //on inflige des dégats à l'adversaire.
                    System.out.println("Attaque réussie : vous avez infligé " + degats + " de dégats à l'adversaire");
                } else {
                    System.out.println("l'attaque échoue");
                }
            } else {
                System.out.println("Trop loin");
            }
        } else {
            System.out.println("Pas assez de mana");
        }
    }

    public void affiche() {
        System.out.print("Mage, pdmana : " + getPtMana() + ", ");
        super.affiche();
    }

}
