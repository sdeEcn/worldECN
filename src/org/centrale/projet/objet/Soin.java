/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Définit une potion de soin.
 * @author Alban
 */
public class Soin extends Potion {

    /**
     * le nombre de points de vie récupérés grâce à la potion.
     */
    private int pouvoir;

    public Soin() {
        super();
        this.pouvoir = 0;
    }

    public Soin(int pouvoir, int x, int y) {
        super(x, y);
        this.pouvoir = pouvoir;
    }

    public Soin(int pouvoir, Point2D p) {
        super(p);
        this.pouvoir = pouvoir;
    }

    public Soin(int x, int y) {
        super(x, y);
        this.pouvoir = 0;
    }

    public Soin(Point2D p) {
        super(p);
        this.pouvoir = 0;
    }

    /**
     * Permet de guérir un monstre ou un personnage.
     *
     * @param c Créature à guérir.
     */
    public void gueri(Personnage c) {

        c.setPtVie(c.getPtVie() + pouvoir);
        System.out.println();
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

    /**
     * Affiche les informations de la potion de soin.
     */
    @Override
    public void affiche() {
        System.out.print("Soin, ");
        super.affiche();
    }
    
    @Override
    public String symbole(){return "s";}
}
