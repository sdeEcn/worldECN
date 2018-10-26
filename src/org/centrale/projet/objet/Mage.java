/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;

/**
 * Définit un mage dans le jeu.
 * @author Alban
 */
public class Mage extends Personnage implements Combattant {

    public Mage() {
        super();
    }

    public Mage(String nom, int ptVie, int ptMana, int pourcentageAtt, int pourcentagePar, int pourcentageMag, int pourcentageResistMag, int degAtt, int degMag, int distAttMax,int parade, Point2D pos) {
        super(nom, ptVie, ptMana, pourcentageAtt, pourcentagePar, pourcentageMag, pourcentageResistMag, degAtt, degMag, distAttMax, pos,parade);
    }

    /**
     * Permet d'attaquer un adversaire c.
     *
     * @param c cible de l'attaque.
     */
    @Override
    public void combattre(Creature c) {
        System.out.println("Tour de combat de " + getNom() + " :");
        if (this.getPtMana() > 0) {
            this.setPtMana(this.getPtMana() - 1);
            Random rand = new Random();
            int success = rand.nextInt(100), distance;
            distance = this.getPos().distance(c.getPos());
            if (distance <= this.getDistAttMax()) {//On vérifie que l'on peut toucher l'adversaire
                if (success < this.getPourcentageMag()) { // L'attaque réussie
                    c.setPtVie(c.getPtVie() - this.getDegAtt()); //on inflige des dégats à l'adversaire.
                    System.out.println("Attaque réussie de " + getNom() + " : " + this.getDegAtt() + " dégats infligés.");
                } else {
                    System.out.println("Dans le vide!");
                }
            } else {
                System.out.println("Trop loin.");
            }
        } else {
            System.out.println("Oups... plus de mana.");
        }
    }
    
    /**
     * Affiche les informations du mage.
     */
    @Override
    public void affiche() {
        System.out.print("Mage, pdmana : " + getPtMana() + ", ");
        super.affiche();
    }
    
    @Override
    public String symbole(){return "M";}

}
