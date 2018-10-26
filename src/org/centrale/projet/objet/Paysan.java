/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Classe des paysans, personnages de base.
 *
 * @author Alban
 */
public class Paysan extends Personnage {

    public Paysan() {
        this("", 0, 0, 0, 0, 0, 0, 0, 0, 0,0, new Point2D());
    }

    /**
     * Créé un paysan avec les paramètres de bases
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
    public Paysan(String nom, int ptVie, int ptMana, int pourcentageAtt, int pourcentagePar, int pourcentageMag, int pourcentageResistMag, int degAtt, int degMag, int distAttMax,int parade, Point2D pos) {
        super(nom, ptVie, ptMana, pourcentageAtt, pourcentagePar, pourcentageMag, pourcentageResistMag, degAtt, degMag, distAttMax, pos,parade);
    }

    public Paysan(Paysan p) {
        super((Paysan) p);
    }

    /**
     * Affiche les informations du paysan.
     */
    @Override
    public void affiche() {
        System.out.print("Paysan, ");
        super.affiche();
    }
    
     @Override
    public String symbole(){return "P";}
}
