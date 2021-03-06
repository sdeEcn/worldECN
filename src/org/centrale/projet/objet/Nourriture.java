/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Définit une nourriture dans le jeu, c'est un bonus ou un malus pour un 
 * personnage qui le ramasse.
 * @author antoinehurard
 */
public class Nourriture extends Objet {

    /**
     * vrai si bonus, faux si malus.
     */
    private boolean bonus;
    
    /**
     * entier définissant la caractéristique à modifier.
     */
    private int caracInfluence;
    
    /**
     * le nombre de tour restant.
     */
    private int duree;
    
    /**
     * le nombre de points d'influence.
     */
    private int pouvoir;

    public Nourriture() {
        super();
        this.bonus = false;
        this.caracInfluence = 0;
        this.duree = 0;
        this.pouvoir = 0;
    }

    public Nourriture(int x, int y) {
        super(x, y);
        this.bonus = false;
        this.caracInfluence = 0;
        this.duree = 0;
        this.pouvoir = 0;
    }

    public Nourriture(Point2D p) {
        super(p);
        this.bonus = false;
        this.caracInfluence = 0;
        this.duree = 0;
        this.pouvoir = 0;
    }

    public Nourriture(int x, int y, boolean bonus, int caracInfluence, int duree,
            int pouvoir) {
        super(x, y);
        this.bonus = bonus;
        this.caracInfluence = caracInfluence;
        this.duree = duree;
        this.pouvoir = pouvoir;
    }

    public Nourriture(Point2D p, boolean bonus, int caracInfluence, int duree,
            int pouvoir) {
        super(p);
        this.bonus = bonus;
        this.caracInfluence = caracInfluence;
        this.duree = duree;
        this.pouvoir = pouvoir;
    }

    /**
     * Affiche les informations de la nourriture.
     */
    @Override
    public void affiche() {
        System.out.println("Nourriture, " + this.getPos().affiche());
    }

    /**
     * Modifie les caractéristiques d'un personnage.
     * @param p = le personnage à influencer.
     */
    public void pouvoir(Personnage p) {
        int i = this.isBonus() ? 1 : -1;
        switch (this.getCaracInfluence()) {
            case 0:
                p.setPourcentageAtt(p.getPourcentageAtt() + i * this.getPouvoir());
                break;
            case 1:
                p.setPourcentagePar(p.getPourcentagePar() + i * this.getPouvoir());
                break;
            case 2:
                p.setPourcentageMag(p.getPourcentageMag() + i * this.getPouvoir());
                break;
            case 3:
                p.setPourcentageResistMag(p.getPourcentageResistMag() + i * this.getPouvoir());
                break;
            case 4:
                p.setDegAtt(p.getDegAtt() + i * this.getPouvoir());
                break;
            case 5:
                p.setDegMag(p.getDegMag() + i * this.getPouvoir());
                break;
            case 6:
                p.setDistAttMax(p.getDistAttMax() + i * this.getPouvoir());
                break;
            default:
                break;
        }

    }

    /**
     * Enlève l'effet sur le personnage.
     * @param p = le personnage à influencer.
     */
    public void normal(Personnage p) {
        int i = this.isBonus() ? -1 : 1;

        switch (this.getCaracInfluence()) {
            case 0:
                p.setPourcentageAtt(p.getPourcentageAtt() + i * this.getPouvoir());
                break;
            case 1:
                p.setPourcentagePar(p.getPourcentagePar() + i * this.getPouvoir());
                break;
            case 2:
                p.setPourcentageMag(p.getPourcentageMag() + i * this.getPouvoir());
                break;
            case 3:
                p.setPourcentageResistMag(p.getPourcentageResistMag() + i * this.getPouvoir());
                break;
            case 4:
                p.setDegAtt(p.getDegAtt() + i * this.getPouvoir());
                break;
            case 5:
                p.setDegMag(p.getDegMag() + i * this.getPouvoir());
                break;
            case 6:
                p.setDistAttMax(p.getDistAttMax() + i * this.getPouvoir());
                break;
            default:
                break;
        }

    }

    /**
     * @return the bonus
     */
    public boolean isBonus() {
        return bonus;
    }

    /**
     * @param bonus the bonus to set
     */
    public void setBonus(boolean bonus) {
        this.bonus = bonus;
    }

    /**
     * @return the caracInfluence
     */
    public int getCaracInfluence() {
        return caracInfluence;
    }

    /**
     * @param caracInfluence the caracInfluence to set
     */
    public void setCaracInfluence(int caracInfluence) {
        this.caracInfluence = caracInfluence;
    }

    /**
     * @return the duree
     */
    public int getDuree() {
        return duree;
    }

    /**
     * @param duree the duree to set
     */
    public void setDuree(int duree) {
        this.duree = duree;
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
    public String symbole(){return "n";}
}
