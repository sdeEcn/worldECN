/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.ArrayList;
import java.util.List;

/**
 * Exécution du monde pour tester les fonctionalités.
 *
 * @author Alban
 */
public class TestSeance2 {

    public static void main(String[] args) {
        World w = new World();
        w.creeMondeAlea(5, 0, 0, 0, 0, 0, 5, 5, 3);
        w.afficheWorld();
        for (int k = 0; k < 10; k++) {
            System.out.println("tour n°" + k);
            w.tourdejeu();
        }

    }

}
