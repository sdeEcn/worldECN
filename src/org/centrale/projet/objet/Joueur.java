/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 *
 * @author antoinehurard
 */
public class Joueur {

    private Personnage perso;

    public Joueur() {
    }

    /**
     * @return the perso
     */
    public Personnage getPerso() {
        return perso;
    }

    /**
     * @param perso the perso to set
     */
    public void setPerso(Personnage perso) {
        this.perso = perso;
    }
}
