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
public class Jeu {

    public static void main(String[] args) throws IOException {
        World w = new World();
        w.debutPartie();
        int k = 0;
        boolean jeu = true;
        while(jeu){
            System.out.println();
            System.out.println("Tour " + k + " :");
            jeu = w.tourdejeu();
        }
    }

}
