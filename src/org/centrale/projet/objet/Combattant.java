/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Interface permettant aux entités de combattre.
 * @author antoinehurard
 */
public interface Combattant {

    /**
     * Permet à l'entité de combattre.
     * @param c = la créature que combat l'entité.
    */
    public void combattre(Creature c);

}
