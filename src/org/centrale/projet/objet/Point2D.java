/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Classe décrivant un point possédant 2 coordonnées (x,y).
 *
 * @author Alban
 */
public class Point2D {

    private int x;
    private int y;

    public Point2D() {
        this(0, 0);
    }

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point2D(Point2D p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     *
     * @return chaine de caractère pour l'appel println ou print
     */
    public String affiche() {
        return ("[ " + x + " , " + y + " ]");
    }

    public void translate(int dx, int dy) {
        x += dx;
        y += dy;
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    public void setPosition(int X, int Y) {
        x = X;
        y = Y;
    }

    /**
     * calcule la distance au point pos
     *
     * @param pos point distant.
     * @return distance entre les deux points.
     */
    public int distance(Point2D pos) {
        int dis;
        dis = (int) Math.round((double) Math.sqrt((Math.pow((double) (this.x - pos.getX()), 2) + Math.pow((double) (this.y - pos.getY()), 2))));
        return (dis);
    }

    public boolean equal(Point2D p) {
        return (this.x == p.getX() && this.y == p.getY());
    }

}
