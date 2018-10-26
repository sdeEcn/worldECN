/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Permet de sauvegarder une partie.
 * @author Alban
 */
public class SauvegardePartie {
    
    private String nom;
    private BufferedWriter buffer=null;
    /**
     * Si le fichier existe déjà afin de créer un autre fichier unique
     */
    public static final String REPOSITORY="resources/";
    public static final String EXTENSION=".txt";
    
    /**
     * Test d'existence d'un fichier
     * @param s nom du fichier à tester
     * @return 
     */
    public static boolean fichierExiste(String s){
        File tmpDir = new File(REPOSITORY+s+EXTENSION);
        boolean exists = tmpDir.exists();
        return exists;
    }

    /**
     * Constructeur évitant la redondance du nom.
     * @param nom nom initial du fichier
     */
    public SauvegardePartie(String nom) {
        this.nom = nom;
        int i=0;
        while(fichierExiste(this.nom)){
            this.nom= nom+"("+i+")";
            i++;
        }      
    }
    
    /**
     * Initialise le buffer.
     */
    public void init(){
        try{
            this.buffer= new BufferedWriter(new FileWriter(REPOSITORY+this.nom+EXTENSION));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Sauvegarde le monde en cours.
     * @param world = le monde à sauvegarder
     * @return succès de l'opération
     */
    public boolean sauvegarder(World world){
        boolean success =true;
        try{
            buffer.write("Largeur "+world.getTaille());
            buffer.newLine();
            buffer.write("Hauteur "+world.getTaille());
            buffer.newLine();
            writeGuerriers(world.guerrier);
            writeArchers(world.archer);
            writeMages(world.mage);
            writePaysans(world.paysan);
            writeLapins(world.lapin);
            writeLoups(world.loup);
            writePotion(world.soin);
            writeMana(world.magie);
            writeNuage(world.nuage);
            writeJoueur(world.getJoueur());
            
        }catch(Exception e){
            e.printStackTrace();
            success=false;
        }finally {
      try {
        if (buffer != null) {
          // je force l'écriture dans le fichier
          buffer.flush();
          // puis je le ferme
          buffer.close();
        }
      }
      // on attrape l'exception potentielle 
      catch (IOException ex) {
        ex.printStackTrace();
      }
    }
        return(success);
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
     * Sauvegarde les guerriers.
     * @param guerrier 
     */
    private void writeGuerriers(List<Guerrier> guerrier){
        guerrier.forEach((n)->{
            try {
                buffer.write("Guerrier "+n.getNom()+" "+n.getPtVie()+" "+n.getPtMana()+" "+n.getPourcentageAtt()
                        +" "+n.getPourcentagePar()+" "+n.getPourcentageMag()+" "+n.getPourcentageResistMag()
                        +" "+n.getDegAtt()+" "+n.getDegMag()+" "+n.getDistAttMax()+" "+n.getParade()
                        +" "+n.getPos().getX()+" "+n.getPos().getY());
                buffer.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }
    
    /**
     * Sauvegarde les archers.
     * @param archer 
     */
    private void writeArchers(List<Archer> archer) {
        archer.forEach((n)->{
            try {
                buffer.write("Archer "+n.getNom()+" "+n.getPtVie()+" "+n.getPtMana()+" "+n.getPourcentageAtt()
                        +" "+n.getPourcentagePar()+" "+n.getPourcentageMag()+" "+n.getPourcentageResistMag()
                        +" "+n.getDegAtt()+" "+n.getDegMag()+" "+n.getDistAttMax()+" "+n.getParade()
                        +" "+n.getPos().getX()+" "+n.getPos().getY());
                buffer.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Sauvegarde les mages.
     * @param mage 
     */
    private void writeMages(List<Mage> mage) {
        mage.forEach((n)->{
            try {
                buffer.write("Mage "+n.getNom()+" "+n.getPtVie()+" "+n.getPtMana()+" "+n.getPourcentageAtt()
                        +" "+n.getPourcentagePar()+" "+n.getPourcentageMag()+" "+n.getPourcentageResistMag()
                        +" "+n.getDegAtt()+" "+n.getDegMag()+" "+n.getDistAttMax()+" "+n.getParade()
                        +" "+n.getPos().getX()+" "+n.getPos().getY());
                buffer.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Sauvegarde les paysans.
     * @param paysan 
     */
    private void writePaysans(List<Paysan> paysan) {
       paysan.forEach((n)->{
            try {
                buffer.write("Paysan "+n.getNom()+" "+n.getPtVie()+" "+n.getPtMana()+" "+n.getPourcentageAtt()
                        +" "+n.getPourcentagePar()+" "+n.getPourcentageMag()+" "+n.getPourcentageResistMag()
                        +" "+n.getDegAtt()+" "+n.getDegMag()+" "+n.getDistAttMax()+" "+n.getParade()
                        +" "+n.getPos().getX()+" "+n.getPos().getY());
                buffer.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Sauvegarde les lapins.
     * @param lapin 
     */
    private void writeLapins(List<Lapin> lapin) {
        lapin.forEach((n)->{
            try {
                buffer.write("Lapin "+n.getPtVie()+" "+n.getPourcentageAtt()
                        +" "+n.getPourcentagePar()+" " +                  
                        n.getDegAtt()+" "+n.getParade()
                        +" "+n.getPos().getX()+" "+n.getPos().getY());
                buffer.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Sauvegarde les loups.
     * @param loup 
     */
    private void writeLoups(List<Loup> loup) {
        loup.forEach((n)->{
            try {
                buffer.write("Loup "+n.getPtVie()+" "+n.getPourcentageAtt()
                        +" "+n.getPourcentagePar()+ " "+                    
                        n.getDegAtt()+" "+n.getParade()
                        +" "+n.getPos().getX()+" "+n.getPos().getY());
                buffer.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Sauvegarde les potions de soins.
     * @param soin 
     */
    private void writePotion(List<Soin> soin) {
        soin.forEach((n)->{
            try {
                buffer.write("Soin "+n.getPouvoir()
                        +" "+n.getPos().getX()+" "+n.getPos().getY());
                buffer.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Sauvegarde les potions de mana.
     * @param magie 
     */
    private void writeMana(List<Mana> magie) {
         magie.forEach((n)->{
            try {
                buffer.write("Mana "+n.getPouvoir()
                        +" "+n.getPos().getX()+" "+n.getPos().getY());
                buffer.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Sauvegarde les nuages toxiques.
     * @param nuage 
     */
    private void writeNuage(List<NuageToxique> nuage) {
         nuage.forEach((n)->{
            try {
                buffer.write("NuageToxique "+50
                        +" "+5 +" "+n.getPos().getX()+" "+n.getPos().getY());
                buffer.newLine();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
    }

    /**
     * Sauvegarde le joueur.
     * @param joueur 
     */
    private void writeJoueur(Joueur joueur) {
        String classe = joueur.getPerso().getClass().getSimpleName();
        Personnage n= joueur.getPerso();
        
        try {
                buffer.write("Joueur "+classe+" "+n.getNom()+" "+n.getPtVie()+" "+n.getPtMana()+" "+n.getPourcentageAtt()
                        +" "+n.getPourcentagePar()+" "+n.getPourcentageMag()+" "+n.getPourcentageResistMag()
                        +" "+n.getDegAtt()+" "+n.getDegMag()+" "+n.getDistAttMax()+" "+n.getParade()
                        +" "+n.getPos().getX()+" "+n.getPos().getY());
            } catch (IOException ex) {
                ex.printStackTrace();
            }
    }
}
