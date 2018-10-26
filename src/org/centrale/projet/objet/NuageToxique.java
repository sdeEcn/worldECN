/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;

/**
 * Définit un nuage toxique qui empoisonne les créatures.
 * @author antoinehurard
 */
public class NuageToxique extends Objet implements Deplacable, Combattant {

    /**
     * les dégats infligés par le nuage.
     */
    final int poison = 10;

    public NuageToxique() {
        super();
    }

    public NuageToxique(Point2D p) {
        super(p);
    }

    /**
     * Affiche les informations du nuage.
     */
    @Override
    public void affiche() {
        System.out.println("Nuage toxique, " + this.getPos().affiche());
    }

    /**
     * Déplace aléatoirement le nuage.
     */
    @Override
    public void deplacer() {
        Random rand = new Random();

        int mode = rand.nextInt(9);
        switch (mode) {
            case 0:
                this.getPos().translate(1, 1);
                break;
            case 1:
                this.getPos().translate(1, 0);
                break;
            case 2:
                this.getPos().translate(1, -1);
                break;
            case 3:
                this.getPos().translate(0, 1);
                break;
            case 5:
                this.getPos().translate(0, -1);
                break;
            case 6:
                this.getPos().translate(-1, 1);
                break;
            case 7:
                this.getPos().translate(-1, 0);
                break;
            case 4:
                this.getPos().translate(-1, -1);
                break;
            case 8:
                this.getPos().translate(0, 0);
                break;
        }
    }

    /**
     * Déplace le nuage selon une direction donnée.
     * @param d = entier représentant une direction.
     */
    @Override
    public void deplacer(int d) {
        switch (d) {
            case 0:
                this.getPos().translate(0, 1);
                break;
            case 1:
                this.getPos().translate(1, 1);
                break;
            case 2:
                this.getPos().translate(1, 0);
                break;
            case 3:
                this.getPos().translate(1, -1);
                break;
            case 4:
                this.getPos().translate(0, -1);
                break;
            case 5:
                this.getPos().translate(-1, -1);
                break;
            case 6:
                this.getPos().translate(-1, 0);
                break;
            case 7:
                this.getPos().translate(-1, 1);
                break;
            default:
                break;
        }
    }

    /**
     * Combat une créature donnée.
     * @param c = la créature à combattre.
    */
    @Override
    public void combattre(Creature c) {
        c.setPtVie(c.getPtVie() - poison);
        System.out.println("Le nuage toxique a infligé " + poison + " points de"
                + " dégats à l'entité.");
    }
    
    @Override
    public String symbole(){return "@";}
}
