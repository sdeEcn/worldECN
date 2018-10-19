/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Interface permettant aux entités de se déplacer.
 * @author antoinehurard
 */
public interface Deplacable {

    /**
     * Déplacement sans paramètre.
     */
    public void deplacer();

    /**
     * Déplacement avec paramètre de dircetion.
     * @param d = entier représentant une direction.
     */
    public void deplacer(int d);

}
