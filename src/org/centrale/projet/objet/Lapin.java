/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Classe définissant les lapins dans le jeu.
 *
 * @author Alban
 */
public class Lapin extends Monstre {

    /**
     * Instancie un lapin qui est un type de monstre basique.
     *
     * @param ptVie = points de vie du monstre
     * @param pourcentageAtt = pourcentage des attaques réussies
     * @param pourcentagePar = pourcentage des esquives réussies
     * @param degAtt = dégats par attaques
     * @param pos = position du monstre
     */
    public Lapin(int ptVie, int pourcentageAtt, int pourcentagePar, int degAtt,int parade, Point2D pos) {
        super(ptVie, pourcentageAtt, pourcentagePar, degAtt,parade, pos);
    }

    public Lapin() {
        super();
    }

    public Lapin(Lapin l) {
        super((Monstre) l);
    }
    
    /**
     * Affiche les informations du lapin.
     */
    @Override
    public void affiche() {
        System.out.print("Lapin");
        super.affiche();
    }
}
