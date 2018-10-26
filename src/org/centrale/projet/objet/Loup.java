/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;

/**
 * Définit un loup dans le jeu.
 * @author Alban
 */
public class Loup extends Monstre implements Combattant {

    public Loup() {
        super();
    }

    public Loup(int ptVie, int pourcentageAtt, int pourcentagePar, int degAtt,int parade, Point2D pos) {
        super(ptVie, pourcentageAtt, pourcentagePar, degAtt,parade, pos);
    }

    /**
     * permet d'attaquer un adversaire
     *
     * @param c cible de l'attaque.
     */
    @Override
    public void combattre(Creature c) {
        Random rand = new Random();
        int success = rand.nextInt(100), distance;
        distance = this.getPos().distance(c.getPos());
        if (distance <= 1) {//On vérifie que l'on peut toucher l'adversaire
            if (success < this.getPourcentageAtt()) { // L'attaque réussie
                int parade = rand.nextInt(100);
                int degats;
                if (parade <= c.getPourcentagePar()) {
                    degats = this.getDegAtt() - parade;
                } else {
                    degats = this.getDegAtt();
                }
                c.setPtVie(c.getPtVie() - degats); //on inflige des dégats à l'adversaire.
                System.out.println("Attaque réussi : vous avez infligé " + degats + " de dégats à l'adversaire");
            }
        }
    }
    
    /**
     * Affiche les informations du loup.
     */
    @Override
    public void affiche() {
        System.out.print("Loup");
        super.affiche();
    }
    
    @Override
    public String symbole(){return "L";}
}
