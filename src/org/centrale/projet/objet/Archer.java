/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.Random;

/**
 * Classe qui hérite de personnage et qui définit un archer. Personnage ayant en
 * plus un arc et des flèches.
 *
 * @author Alban
 */
public class Archer extends Personnage implements Combattant {

    /**
     * Nombre de flèches de l'archer.
     */
    private int nbFleche;

    private void init(int nbfleche) {
        this.nbFleche = nbfleche;
    }

    public Archer() {
        super();
        init(0);
    }

    public Archer(Archer a) {
        super((Personnage) a);
        this.nbFleche = a.getNbFleche();
    }

    /**
     * Instancie un archer avec ses flèches.
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
     * @param nbFleche = nombre de flèches disponible dans le carquois de
     * l'archer
     */
    public Archer(String nom, int ptVie, int ptMana, int pourcentageAtt, int pourcentagePar, int pourcentageMag, int pourcentageResistMag, int degAtt, int degMag, int distAttMax,int parade, Point2D pos, int nbFleche) {
        super(nom, ptVie, ptMana, pourcentageAtt, pourcentagePar, pourcentageMag, pourcentageResistMag, degAtt, degMag, distAttMax, pos,parade);
        init(nbFleche);
    }

    public int getNbFleche() {
        return nbFleche;
    }

    public void setNbFleche(int nbFleche) {
        this.nbFleche = nbFleche;
    }
    
    /**
     * Affiche les informations utiles du personnage.
     */
    @Override
    public void affiche() {
        System.out.print("Archer, nbfleches : " + nbFleche + ", ");
        super.affiche();
    }

    /**
     * Permet d'infliger des dégats à son adversaire c.
     * @param c adversaire du guerrier.
     */
    @Override
    public void combattre(Creature c) {
        System.out.println("Tour de combat de " + getNom() + " :");
        if (this.nbFleche > 0) {
            this.setNbFleche(this.nbFleche - 1);
            Random rand = new Random();
            int success = rand.nextInt(100), distance;
            distance = this.getPos().distance(c.getPos());
            if (distance <= this.getDistAttMax()) {//On vérifie que l'on peut toucher l'adversaire
                if (distance > 1) {
                    if (success < this.getPourcentageAtt()) { // L'attaque réussie
                        c.setPtVie(c.getPtVie() - this.getDegAtt()); //on inflige des dégats à l'adversaire.
                        System.out.println("Attaque réussie de " + getNom() + " : " + this.getDegAtt() + " dégats infligés.");
                    } else {
                        System.out.println("Dans le vide!");
                    }
                } else {
                    System.out.println("Trop près.");
                }
            } else {
                System.out.println("Trop loin.");
            }
        } else {
            System.out.println("Oups... plus de flèches.");
        }
    }
    
    @Override
    public String symbole(){return "A";}
}
