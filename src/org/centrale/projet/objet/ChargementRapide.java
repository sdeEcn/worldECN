/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.io.*;
import static java.lang.Integer.parseInt;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe permettant la récupération d'une sauvegarde.
 *
 * @author Alban
 */
public class ChargementRapide {

    private BufferedReader buffer;
    private String fichier;

    public ChargementRapide(String fichier) {
        this.fichier = fichier;
    }

    /**
     * Initialise le bufferReader.
     */
    public void init() {
        
        try {
            this.buffer = new BufferedReader(new FileReader(this.fichier));
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Erreur... fichier non trouvé");
           
        }
    }

    /**
     * Charge une partie.
     * @return un monde complété par la
     * @throws IOException 
     */
    public World chargerPartie() throws IOException {
        World world = new World();
        String line;
        try{
            line = buffer.readLine();
            line=buffer.readLine();
            StringInt param= nextspace(0,line);
            StringInt taille = nextspace(param.getI(),line);
            world.setTaille(parseInt(taille.getString()));
            line=buffer.readLine();
            while (line != null) {
                creerElementJeu(line, world);
                line = buffer.readLine();
            }
        }catch(Exception e){
            e.printStackTrace();
        }

        return (world);
    }

    
    /**
     * Créé un element du jeu et l'insère avec toutes ses caractéristiques dans le monde chargé.
     * @param chaine = la ligne à décoder
     * @param world = le monde où on instancie
     */
    public void creerElementJeu(String chaine, World world) {
        StringInt init = nextspace(0, chaine);
        String type = init.getString();
        String nom;
        if (type.equals("Joueur")) {
            world.setJoueur(initJoueur(chaine));
        } else {
            int[] carac;
            switch (type) {
                case "Guerrier":
                    init= nextspace(init.getI(), chaine);
                    nom = init.getString();
                    carac = liredonnees(init);
                    world.guerrier.add(new Guerrier(nom, carac[0], carac[1], carac[2], carac[3], carac[4], carac[5], carac[6], carac[7], carac[8], carac[9], new Point2D(carac[10], carac[11])));
                    break;
                case "Mage":
                    init= nextspace(init.getI(), chaine);
                    nom = init.getString();
                    carac = liredonnees(init);
                    world.mage.add(new Mage(nom, carac[0], carac[1], carac[2], carac[3], carac[4], carac[5], carac[6], carac[7], carac[8], carac[9], new Point2D(carac[10], carac[11])));
                    break;
                case "Voleur":
                    System.out.println("Le voleur est mort");
                    break;
                case "Archer":
                    init= nextspace(init.getI(), chaine);
                    nom = init.getString();
                    carac = liredonnees(init);
                    world.archer.add(new Archer(nom, carac[0], carac[1], carac[2], carac[3], carac[4], carac[5], carac[6], carac[7], carac[8], carac[9], new Point2D(carac[10], carac[11]), 10));
                    break;
                case "Paysan":
                   init= nextspace(init.getI(), chaine);
                    nom = init.getString();
                    carac = liredonnees(init);
                    world.paysan.add(new Paysan(nom, carac[0], carac[1], carac[2], carac[3], carac[4], carac[5], carac[6], carac[7], carac[8], carac[9], new Point2D(carac[10], carac[11])));
                    break;
                case "Loup":
                    carac = liredonnees(init);
                    world.loup.add(new Loup(carac[0], carac[1], carac[2], carac[3], carac[4], new Point2D(carac[5], carac[6])));
                    break;
                case "Lapin":
                    carac = liredonnees(init);
                    world.lapin.add(new Lapin(carac[0], carac[1], carac[2], carac[3], carac[4], new Point2D(carac[5], carac[6])));
                    break;
                case "NuageToxique":
                    carac = liredonnees(init);
                    world.nuage.add(new NuageToxique(new Point2D(carac[2], carac[3])));
                    break;
                case "Soin":
                    carac = liredonnees(init);
                    world.soin.add(new Soin(carac[0], new Point2D(carac[1], carac[2])));
                    break;
                case "Mana":
                    carac = liredonnees(init);
                    world.magie.add(new Mana(carac[0], new Point2D(carac[1], carac[2])));
                    break;
                default:
                    System.out.println("Erreur de lecture" + chaine);
                    break;
            }
        }
    }

    /**
     * Initialise le joueur
     * @param chaine = la ligne à décoder
     * @return le personnage joueur
     */
    private Joueur initJoueur(String chaine) {
        Joueur joueur= new Joueur();
        String properties = chaine.substring(7);
        StringInt init = nextspace(0, properties);
        String type = init.getString();
        String nom;
        init= nextspace(init.getI(), properties);
        nom = init.getString();
         int[] carac;
            switch (type) {
                case "Guerrier":
                    
                    carac = liredonnees(init);
                    joueur.setPerso(new Guerrier(nom, carac[0], carac[1], carac[2], carac[3], carac[4], carac[5], carac[6], carac[7], carac[8], carac[9], new Point2D(carac[10], carac[11])));
                    break;
                case "Mage":
                    
                    carac = liredonnees(init);
                    joueur.setPerso(new Mage(nom, carac[0], carac[1], carac[2], carac[3], carac[4], carac[5], carac[6], carac[7], carac[8], carac[9], new Point2D(carac[10], carac[11])));
                    break;
                case "Voleur":
                    System.out.println("Le voleur est mort");
                    break;
                case "Archer":
                    
                    carac = liredonnees(init);
                    joueur.setPerso(new Archer(nom, carac[0], carac[1], carac[2], carac[3], carac[4], carac[5], carac[6], carac[7], carac[8], carac[9], new Point2D(carac[10], carac[11]), 10));
                    break;
                case "Paysan":
               
                    carac = liredonnees(init);
                    joueur.setPerso(new Paysan(nom, carac[0], carac[1], carac[2], carac[3], carac[4], carac[5], carac[6], carac[7], carac[8], carac[9], new Point2D(carac[10], carac[11])));
                    break;
            }
            return joueur;
    }

    /**
     * Retourne un tableau d'entier contenant les paramètres suivant le type du
     * personnages.
     *
     * @param init index
     * @return tableau de caractéristique
     */
    public static int[] liredonnees(StringInt init) {
        int[] result = null;
        int i = 0;
        result = new int[20];
        while (init.isSuite()) {
            init = nextspace(init.getI(), init.getChaine());

            try {
                result[i] = parseInt(init.getString());
                i++;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return (result);
    }

    /**
     * Retourne le premier mot après l'index indiqué
     *
     * @param i index
     * @param chaine chaine à analyser
     * @return Objet contenant le mot sous forme de caractère et un entier qui
     * est l'index pour le mot suivant
     */
    public static StringInt nextspace(int i, String chaine) {

        StringBuffer tab = new StringBuffer(chaine);
        int length = tab.length();
        while (i < length && tab.charAt(i) == ' ') {
            i++;
        }
        int y = i;
        while (y < length && tab.charAt(y) != ' ') {
            y++;
        }
        String string = tab.substring(i, y);
        StringInt result;
        result = new StringInt(string, Math.min(y, length), y < length - 1, chaine);
        return (result);
    }

    /**
     * @return the fichier
     */
    public String getFichier() {
        return fichier;
    }

    /**
     * @param fichier the fichier to set
     */
    public void setFichier(String fichier) {
        this.fichier = fichier;
    }
    
    public void close() throws IOException{
        this.buffer.close();
    }

}
