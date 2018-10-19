/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.List;
import java.util.ArrayList;

/**
 * Classe des personnages du jeu, ceci inclut les entités guerrières et
 * passives.
 *
 * @author Alban
 */
public abstract class Personnage extends Creature {

    /**
     * Nom du personnage
     */
    private String nom;
    /**
     * Points de mana du personnage
     */
    private int ptMana;
    /**
     * Pourcentage de magie
     */
    private int pourcentageMag;
    /**
     * Pourcentage de la résistance à la magie
     */
    private int pourcentageResistMag;
    /**
     * Dégats des sorts
     */
    private int degMag;
    /**
     * Portée maximale d'une attaque
     */
    private int distAttMax;

    private List<Nourriture> nourriture;

    public Personnage() {
        this("", 0, 0, 0, 0, 0, 0, 0, 0, 0, new Point2D(),0);
    }

    /**
     *
     * @param nom = nom du personnage
     * @param ptVie = points de vie du personnage
     * @param ptMana = Point de mana
     * @param pourcentageAtt = pourcentage des attaques réussies
     * @param pourcentagePar = pourcentage des parades réussies
     * @param pourcentageMag = pourcentage de magie du personnage
     * @param pourcentageResistMag = pourcentage de résistance à la magie du
     * personnage
     * @param degAtt = dégats par attaque
     * @param degMag = dégats par sort lancé
     * @param distAttMax = portée des attaques
     * @param pos = postition du personnage
     */
    public Personnage(String nom, int ptVie, int ptMana, int pourcentageAtt, int pourcentagePar, int pourcentageMag, int pourcentageResistMag, int degAtt, int degMag, int distAttMax, Point2D pos,int parade) {
        super(ptVie, pourcentageAtt, pourcentagePar, degAtt, new Point2D(pos),parade);
        this.nom = nom;
        this.ptMana = ptMana;
        this.pourcentageMag = pourcentageMag;
        this.pourcentageResistMag = pourcentageResistMag;
        this.degMag = degMag;
        this.distAttMax = distAttMax;
        this.nourriture = new ArrayList<Nourriture>();
    }

    /**
     * Copie un personnage déjà créé placé en paramètre.
     *
     * @param p = personnage à copier.
     */
    public Personnage(Personnage p) {
        this(p.getNom(), p.getPtVie(), p.getPtMana(), p.getPourcentageAtt(), p.getPourcentagePar(), p.getPourcentageMag(), p.getPourcentageResistMag(), p.getDegAtt(), p.getDegMag(), p.getDistAttMax(), p.getPos(),p.getParade());
    }

    public void affiche() {
        System.out.print("Nom : " + this.nom + ", Pdvie : " + this.getPtVie() + ", Pdmana : " + this.ptMana);
        System.out.println(", position : " + this.getPos().affiche());

    }

    /**
     * @return the nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * @param nom the nom to set
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     * @return the ptMana
     */
    public int getPtMana() {
        return ptMana;
    }

    /**
     * @param ptMana the ptMana to set
     */
    public void setPtMana(int ptMana) {
        this.ptMana = ptMana;
    }

    /**
     * @return the pourcentageMag
     */
    public int getPourcentageMag() {
        return pourcentageMag;
    }

    /**
     * @param pourcentageMag the pourcentageMag to set
     */
    public void setPourcentageMag(int pourcentageMag) {
        this.pourcentageMag = pourcentageMag;
    }

    /**
     * @return the pourcentageResistMag
     */
    public int getPourcentageResistMag() {
        return pourcentageResistMag;
    }

    /**
     * @param pourcentageResistMag the pourcentageResistMag to set
     */
    public void setPourcentageResistMag(int pourcentageResistMag) {
        this.pourcentageResistMag = pourcentageResistMag;
    }

    /**
     * @return the degMag
     */
    public int getDegMag() {
        return degMag;
    }

    /**
     * @param degMag the degMag to set
     */
    public void setDegMag(int degMag) {
        this.degMag = degMag;
    }

    /**
     * @return the distAttMax
     */
    public int getDistAttMax() {
        return distAttMax;
    }

    /**
     * @param distAttMax the distAttMax to set
     */
    public void setDistAttMax(int distAttMax) {
        this.distAttMax = distAttMax;
    }

    /**
     * Enlève un tour d'effet aux nourritures.
     * Supprime les nourritures avariées.
     */
    public void verifierNourriture() {
        List<Integer> aSupprimer = new ArrayList<Integer>();
        this.nourriture.forEach((n) -> {
            n.setDuree(n.getDuree() - 1);
            if (n.getDuree() < 0) {
                n.normal(this);
                int index = nourriture.indexOf(n);
                aSupprimer.add(index);
                System.out.println(this.nom + " a fini de consommer une nourriture.");
            }
        });
        if (aSupprimer.size() > 0) {
            for (int i = aSupprimer.size() - 1; i == 0; i--) {
                nourriture.remove(aSupprimer.get(i));
            }
        }
    }

    /**
     * Ajoute une nourriture au personnage.
     * @param n = la nourriture à ajouter.
     */
    public void ajouterNourriture(Nourriture n) {
        nourriture.add(n);
    }

}
