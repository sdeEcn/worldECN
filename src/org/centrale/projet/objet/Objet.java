/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Caractérise tout les objets du jeu.
 *
 * @author Alban
 */
public abstract class Objet extends ElementDeJeu {

    /**
     * position de l'objet.
     */
    private Point2D pos;

    public Objet() {
        this.pos = new Point2D();
    }

    public Objet(Point2D p) {
        this.pos = new Point2D(p);
    }

    public Objet(int x, int y) {
        this.pos = new Point2D(x, y);
    }

    /**
     * @return the pos
     */
    public Point2D getPos() {
        return pos;
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    public abstract void affiche();
    
    @Override
    public abstract String symbole();
}
