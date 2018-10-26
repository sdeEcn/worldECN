/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Définit une potion de mana.
 * @author Alban
 */
public class Mana extends Potion {

    /**
     * Le nombre de points de mana rendus par la potion.
     */
    private int pouvoir;

    public Mana(int pouvoir) {
        this.pouvoir = pouvoir;
    }

    public Mana(int pouvoir, int x, int y) {
        super(x, y);
        this.pouvoir = pouvoir;
    }

    public Mana(int pouvoir, Point2D p) {
        super(p);
        this.pouvoir = pouvoir;
    }

    public Mana() {
        super();
        this.pouvoir = 0;
    }

    public Mana(int x, int y) {
        super(x, y);
        this.pouvoir = 0;
    }

    public Mana(Point2D p) {
        super(p);
        this.pouvoir = 0;
    }

    /**
     * Affiche les informations de la potion.
     */
    @Override
    public void affiche() {
        System.out.print("Mana, ");
        super.affiche();
    }

    /**
     * Augmente la mana d'un personnage.
     *
     * @param c personnage qui boit la potion.
     */
    public void magie(Personnage c) {
        c.setPtMana(c.getPtMana() + pouvoir);
    }

    /**
     * @return the pouvoir
     */
    public int getPouvoir() {
        return pouvoir;
    }

    /**
     * @param pouvoir the pouvoir to set
     */
    public void setPouvoir(int pouvoir) {
        this.pouvoir = pouvoir;
    }
    
    @Override
    public String symbole(){return "m";}
}
