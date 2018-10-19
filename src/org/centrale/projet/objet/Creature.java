/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;

/**
 *
 * @author Alban
 */
public abstract class Creature extends ElementDeJeu implements Deplacable {

    /**
     * points de vie
     */
    private int ptVie;
    /**
     * Pourcentage des attaques réussies
     */
    private int pourcentageAtt;
    /**
     * Pourcentage d'esquives réussies
     */
    private int pourcentagePar;
    /**
     * Dégats des attaques
     */
    private int degAtt;
    /**
     * position en 2D du personnage
     */
    private Point2D pos;
    
    private int parade;

    /**
     *
     * @param ptVie points de vie de la créature
     * @param pourcentageAtt proportion d'attaques réussies
     * @param pourcentagePar proportion d'esquives réussies
     * @param degAtt dégats par attaques
     * @param pos position de l'entité
     */
    public Creature(int ptVie, int pourcentageAtt, int pourcentagePar, int degAtt, Point2D pos,int parade) {
        this.ptVie = ptVie;
        this.pourcentageAtt = pourcentageAtt;
        this.pourcentagePar = pourcentagePar;
        this.degAtt = degAtt;
        this.pos = new Point2D(pos);
        this.parade= parade;
    }

    public Creature(Creature c) {
        this(c.getPtVie(), c.getPourcentageAtt(), c.getPourcentagePar(), c.getDegAtt(), c.getPos(),c.getParade());
    }

    public Creature() {
        this(0, 0, 0, 0, new Point2D(),0);
    }

    /**
     * Déplace aléatoirement le monstre sur l'une des 8 cases adjacentes. Ces
     * déplacements ne prennent pas en compte les positions des autres
     * personnages.
     */
    @Override
    public void deplacer() {
        Random rand = new Random();

        int mode = rand.nextInt(9);
        switch (mode) {
            case 0:
                this.pos.translate(1, 1);
                break;
            case 1:
                this.pos.translate(1, 0);
                break;
            case 2:
                this.pos.translate(1, -1);
                break;
            case 3:
                this.pos.translate(0, 1);
                break;
            case 5:
                this.pos.translate(0, -1);
                break;
            case 6:
                this.pos.translate(-1, 1);
                break;
            case 7:
                this.pos.translate(-1, 0);
                break;
            case 4:
                this.pos.translate(-1, -1);
                break;
            case 8:
                this.pos.translate(0, 0);
                break;
        }
    }

    @Override
    public void deplacer(int d) {
        switch (d) {
            case 0:
                this.pos.translate(0, 1);
                break;
            case 1:
                this.pos.translate(1, 1);
                break;
            case 2:
                this.pos.translate(1, 0);
                break;
            case 3:
                this.pos.translate(1, -1);
                break;
            case 4:
                this.pos.translate(0, -1);
                break;
            case 5:
                this.pos.translate(-1, -1);
                break;
            case 6:
                this.pos.translate(-1, 0);
                break;
            case 7:
                this.pos.translate(-1, 1);
                break;
            default:
                break;
        }
    }

    /**
     * @return the ptVie
     */
    public int getPtVie() {
        return ptVie;
    }

    /**
     * @param ptVie the ptVie to set
     */
    public void setPtVie(int ptVie) {
        this.ptVie = ptVie;
    }

    /**
     * @return the pourcentageAtt
     */
    public int getPourcentageAtt() {
        return pourcentageAtt;
    }

    /**
     * @param pourcentageAtt the pourcentageAtt to set
     */
    public void setPourcentageAtt(int pourcentageAtt) {
        this.pourcentageAtt = pourcentageAtt;
    }

    /**
     * @return the pourcentagePar
     */
    public int getPourcentagePar() {
        return pourcentagePar;
    }

    /**
     * @param pourcentagePar the pourcentagePar to set
     */
    public void setPourcentagePar(int pourcentagePar) {
        this.pourcentagePar = pourcentagePar;
    }

    /**
     * @return the degAtt
     */
    public int getDegAtt() {
        return degAtt;
    }

    /**
     * @param degAtt the degAtt to set
     */
    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
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

    /**
     * @return the parade
     */
    public int getParade() {
        return parade;
    }

    /**
     * @param parade the parade to set
     */
    public void setParade(int parade) {
        this.parade = parade;
    }
}
