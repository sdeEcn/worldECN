/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Classe définissant les monstres du jeu
 *
 * @author Alban
 */
public abstract class Monstre extends Creature {

    /**
     * Instancie un monstre basique.
     *
     * @param ptVie = points de vie du monstre
     * @param pourcentageAtt = pourcentage des attaques réussies
     * @param pourcentagePar = pourcentage des esquives réussies
     * @param degAtt = dégats par attaques
     * @param pos = position du monstre
     */
    public Monstre(int ptVie, int pourcentageAtt, int pourcentagePar, int degAtt, Point2D pos) {
        super(ptVie, pourcentageAtt, pourcentagePar, degAtt, pos);
    }

    public Monstre(Monstre m) {
        super(m);
    }

    public Monstre() {
        super();
    }

    public void affiche() {
        System.out.println(", Pdvie : " + this.getPtVie() + ", position : " + this.getPos().affiche());
    }

}

