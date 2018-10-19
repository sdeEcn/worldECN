/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Définit une potion.
 * @author Alban
 */
public abstract class Potion extends Objet {

    public Potion() {
        super();
    }

    public Potion(int x, int y) {
        super(x, y);
    }

    public Potion(Point2D p) {
        super(p);
    }

    /**
     * Affiche les informations de la potion.
     */
    @Override
    public void affiche() {
        System.out.println(this.getPos().affiche());
    }
}
