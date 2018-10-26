/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Exécution du monde pour tester les fonctionalités.
 *
 * @author Alban
 */
public class TestSeance2 {

    public static void main(String[] args) throws IOException {
        World w = new World();
        w.debutPartie();
        for (int k = 0; k < 10; k++) {
            System.out.println("tour n°" + k);
            w.tourdejeu();
        }
        ChargementRapide cr = new ChargementRapide("resources/Sauvegarde-WoE.txt");
        cr.init();
        
        w.afficheWorld();
        
        SauvegardePartie sv= new SauvegardePartie("test");
        sv.init();
        sv.sauvegarder(w);

    }

}
