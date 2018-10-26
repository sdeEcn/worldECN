/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 * Décrit le monde avec les entitées de personnages monstre et leur positions
 *
 * @author Alban
 */
public class World {

    

    /**
     * Archer initialement présent dans le monde.
     */
    List<Archer> archer;
    /**
     * Paysan initialement présent dans le monde.
     */
    List<Paysan> paysan;
    /**
     * Monstres de type lapin initialement présent dans le monde.
     */
    List<Lapin> lapin;
    /**
     * Guerriers présents dans le monde.
     */
    List<Guerrier> guerrier;
    /**
     * Mages présents dans le monde.
     */
    List<Mage> mage;
    
    /**
     * Loups présents dans le monde.
     */
    List<Loup> loup;

    /**
     * Soin a positionner dans le monde.
     */
    List<Soin> soin;
    
    /**
     * Potions de mana dans le monde.
     */
    List<Mana> magie;

    /**
     * Nuages toxiques dans le monde.
     */
    List<NuageToxique> nuage;
    
    /**
     * Nourritures dans le monde.
     */
    List<Nourriture> nourriture;

    /**
     * Taille du monde.
     */
    private int taille = 15;
    
    /**
     * Affichage textuel du monde.
     */
    String[] monde;
    /**
     * Le joueur.
     */
    private Joueur joueur;

    /**
     * Créer les listes d'entitées dans le monde, ces listes d'entitées entitées
     * sont vides.
     */
    public World() {
        this.archer = new LinkedList<Archer>();
        this.paysan = new LinkedList<Paysan>();
        this.lapin = new LinkedList<Lapin>();
        this.guerrier = new LinkedList<Guerrier>();
        this.loup = new LinkedList<Loup>();
        this.mage = new LinkedList<Mage>();
        this.magie = new LinkedList<Mana>();
        this.soin = new LinkedList<Soin>();
        this.nuage = new LinkedList<NuageToxique>();
        this.nourriture=new LinkedList<Nourriture>();
        this.joueur = new Joueur();
        this.monde = new String[taille * taille];
    }

    private void monde(){
        for(int i = 0; i < monde.length ; i+=1)
        {
            monde[i]= ".";
        }
        
        archer.forEach((p) -> {
            monde[p.getPos().getX() + p.getPos().getY() * getTaille()] = "A";
        });

        paysan.forEach((p) -> {
            monde[p.getPos().getX() + p.getPos().getY() * getTaille()] = "P";
        });
        
        guerrier.forEach((p) -> {
            monde[p.getPos().getX() + p.getPos().getY() * getTaille()] = "G";
        });
        
        mage.forEach((p) -> {
            monde[p.getPos().getX() + p.getPos().getY() * getTaille()] = "M";
        });
        
        loup.forEach((p) -> {
            monde[p.getPos().getX() + p.getPos().getY() * getTaille()] = "L";
        });
        
        lapin.forEach((p) -> {
            monde[p.getPos().getX() + p.getPos().getY() * getTaille()] = "l";
        });
        
        soin.forEach((p) -> {
            monde[p.getPos().getX() + p.getPos().getY() * getTaille()] = "s";
        });
        
        magie.forEach((p) -> {
            monde[p.getPos().getX() + p.getPos().getY() * getTaille()] = "m";
        });
        
        nuage.forEach((p) -> {
            monde[p.getPos().getX() + p.getPos().getY() * getTaille()] = "@";
        });
        
    }
    
    public void debutPartie() throws IOException{
        Scanner sc = new Scanner(System.in);
        System.out.println("Nouvelle Partie : 1, Chargez Sauvegarde : 2");
        int entree=-1;
        String nom;
        while(entree!=1 && entree !=2){
        entree=sc.nextInt();
        }
        if(entree==1){
            this.creeMondeAlea(3, 2, 2, 2, 2, 2, 5, 5, 3);
            
        }else{
           System.out.println("Entrez le nom de la sauvegarde :");
           
            nom = sc.next();
            ChargementRapide cr = new ChargementRapide(SauvegardePartie.REPOSITORY+ nom +SauvegardePartie.EXTENSION);
            cr.init();
            this.copie(cr.chargerPartie());
            cr.close();
        }
    }
    
    public void creeMondeAlea() {
        for(int i = 0; i < monde.length ; i+=1)
        {
            monde[i]= ".";
        }
        Random rand = new Random();
        int nbarcher = rand.nextInt(5) + 3;
        int nbpaysan = rand.nextInt(5);
        int nblapin = rand.nextInt(5);
        int nbguerrier = rand.nextInt(5);
        int nbloup = rand.nextInt(5);
        int nbmage = rand.nextInt(5);
        int nbmagie = rand.nextInt(5);
        int nbsoin = rand.nextInt(5);
        int nbnuage = rand.nextInt(5);
        int somme = nbarcher + nbpaysan + nblapin + nbguerrier + nbloup + nbmage + nbmagie + nbsoin + nbnuage + 2;
        List<Point2D> pt = creerpos(somme + 1);
        Collections.shuffle(pt);
        creerarcher(nbarcher, pt);
        creerpaysan(nbpaysan, pt, nbarcher);
        creerlapin(nblapin, pt, nbarcher + nbpaysan);
        creerguerrier(nbguerrier, pt, nblapin + nbarcher + nbpaysan);
        creerloup(nbloup, pt, nbguerrier + nblapin + nbarcher + nbpaysan);
        creermage(nbmage, pt, nbguerrier + nblapin + nbarcher + nbpaysan + nbloup);
        creermagie(nbmagie, pt, nbguerrier + nblapin + nbarcher + nbpaysan + nbloup + nbmage);
        creersoin(nbsoin, pt, nbguerrier + nblapin + nbarcher + nbpaysan + nbloup + nbmage + nbmagie);
        nourriture = new ArrayList<Nourriture>();
        Point2D p = pt.get(pt.size() - 3);
        nourriture.add(new Nourriture(p, true, 0, 2, 20));
        monde[p.getX() + p.getY() * getTaille()] = "n";
        Point2D p2 = pt.get(pt.size() - 2);
        nourriture.add(new Nourriture(p2, false, 1, 2, 20));
        monde[p2.getX() + p2.getY() * getTaille()] = "n";
        creationJoueur(pt.get(pt.size() - 1));
        getJoueur().getPerso().affiche();
        
    }

    /**
     * Créé un monde et instancie automatiquement des entités aléatoires. Cette
     * fonction s'assure aussi du placement de ces entitées
     */
    public void creeMondeAlea(int nbarcher, int nbpaysan, int nblapin, int nbguerrier,
            int nbloup, int nbmage, int nbmagie, int nbsoin, int nbnuage) {
        for(int i = 0; i < monde.length ; i+=1)
        {
            monde[i]= ".";
        }
        int somme = nbarcher + nbpaysan + nblapin + nbguerrier + nbloup + nbmage + nbmagie + nbsoin + nbnuage + 2;
        List<Point2D> pt = creerpos(somme + 1);
        Collections.shuffle(pt);
        creerarcher(nbarcher, pt);
        creerpaysan(nbpaysan, pt, nbarcher);
        creerlapin(nblapin, pt, nbarcher + nbpaysan);
        creerguerrier(nbguerrier, pt, nblapin + nbarcher + nbpaysan);
        creerloup(nbloup, pt, nbguerrier + nblapin + nbarcher + nbpaysan);
        creermage(nbmage, pt, nbguerrier + nblapin + nbarcher + nbpaysan + nbloup);
        creermagie(nbmagie, pt, nbguerrier + nblapin + nbarcher + nbpaysan + nbloup + nbmage);
        creersoin(nbsoin, pt, nbguerrier + nblapin + nbarcher + nbpaysan + nbloup + nbmage + nbmagie);
        creernuage(nbnuage, pt, nbguerrier + nblapin + nbarcher + nbpaysan + nbloup + nbmage + nbmagie + nbsoin);
        nourriture = new ArrayList<Nourriture>();
        Point2D p = pt.get(pt.size() - 3);
        nourriture.add(new Nourriture(p, true, 0, 2, 20));
        monde[p.getX() + p.getY() * getTaille()] = "n";
        Point2D p2 = pt.get(pt.size() - 2);
        nourriture.add(new Nourriture(p2, false, 1, 2, 20));
        monde[p2.getX() + p2.getY() * getTaille()] = "n";
        creationJoueur(pt.get(pt.size() - 1));
        getJoueur().getPerso().affiche();
    }

    /**
     * Crée un personnage joueur, en donnant à l'utilisateur le choix de la
     * classe et du nom.
     *
     * @param pos = la position à donner au joueur
     */
    public void creationJoueur(Point2D pos) {
        Scanner sc = new Scanner(System.in);
        String nomClasse = "";
        String nomPerso;
        while (!"Archer".equals(nomClasse) && !"Guerrier".equals(nomClasse) && !"Mage".equals(nomClasse)) {
            System.out.println("Entrez la classe de votre personnage.");
            System.out.println("Les classes disponibles sont : Archer, Guerrier et Mage.");
            try{
                nomClasse = sc.nextLine();
            }
            catch(InputMismatchException exception)
            {
                  System.out.println("Entrez une chaîne de caractère.");      
            }

        }
        System.out.println("Vous avez choisi la classe " + nomClasse);
        System.out.println("Entrez le nom de votre personnage.");
        nomPerso = sc.nextLine();
        System.out.println("Vous incarnez " + nomPerso + ", un " + nomClasse + ".");

        Random rand = new Random();
        if (null == nomClasse) {
            getJoueur().setPerso(new Guerrier(nomPerso, 200, 0, rand.nextInt(50) + 50,
                    rand.nextInt(30) + 10, 0, rand.nextInt(35), rand.nextInt(50) + 40, 0,
                    rand.nextInt(1) + 1,10, pos));
        } else {
            switch (nomClasse) {
                case "Archer":
                    getJoueur().setPerso(new Archer(nomPerso, 100, 0, rand.nextInt(40) + 20,
                            rand.nextInt(30) + 10, 0, rand.nextInt(35), rand.nextInt(50) + 40, 0,
                            rand.nextInt(10) + 15,10, pos, rand.nextInt(10) + 10));
                    break;
                case "Guerrier":
                    getJoueur().setPerso(new Guerrier(nomPerso, 200, 0, rand.nextInt(50) + 50,
                            rand.nextInt(30) + 10, 0, rand.nextInt(35), rand.nextInt(50) + 40, 0,
                            rand.nextInt(1) + 1,10, pos));
                    break;
                default:
                    getJoueur().setPerso(new Mage(nomPerso, 100, rand.nextInt(50) + 50,
                            rand.nextInt(50) + 50, rand.nextInt(30) + 10, rand.nextInt(50) + 20,
                            rand.nextInt(35), rand.nextInt(50) + 40, rand.nextInt(50) + 50,
                            rand.nextInt(3) + 2,10, pos));
                    break;
            }
        }
        monde[pos.getX() + pos.getY() * getTaille()] = "J";
    }

    /**
     * Détermine le barycentre des positions des créatures du monde, en
     * parcourant les listes grâce à leurs tailles.
     */
    public void barycentre() {
        List<Creature> crea = new ArrayList<Creature>();
        crea.addAll(this.archer);
        crea.addAll(this.paysan);
        crea.addAll(this.guerrier);
        crea.addAll(this.mage);
        crea.addAll(this.loup);
        crea.addAll(this.lapin);

        int x = crea.get(0).getPos().getX();
        int y = crea.get(0).getPos().getY();
        long debutN = System.nanoTime();

        for (int i = 1; i < crea.size(); i++) {
            x += crea.get(i).getPos().getX();
            x = x / 2;
            y += crea.get(i).getPos().getY();
            y = y / 2;
        }
        long finN = System.nanoTime();

        System.out.println("Le Barycentre est : [ " + x + " ; " + y + " ]");
        System.out.println("le Temps écoulé en s est " + (finN - debutN) * Math.pow(10, -9));
    }

    /**
     * Détermine le barycentre des positions des créatures du monde, en
     * parcourant les listes grâce à des itérateurs.
     */
    public void barycentre1() {
        List<Creature> crea = new ArrayList<Creature>();
        crea.addAll(this.archer);
        crea.addAll(this.paysan);
        crea.addAll(this.guerrier);
        crea.addAll(this.mage);
        crea.addAll(this.loup);
        crea.addAll(this.lapin);

        Iterator<Creature> listIt = crea.iterator();
        int x = 0;
        int y = 0;
        long debutN = System.nanoTime();

        while (listIt.hasNext()) {
            Creature c = listIt.next();
            x += c.getPos().getX();
            x = x / 2;
            y += c.getPos().getY();
            y = y / 2;
        }
        long finN = System.nanoTime();

        System.out.println("Le Barycentre 1 est : [ " + x + " ; " + y + " ]");
        System.out.println("le Temps écoulé en s est " + (finN - debutN) * Math.pow(10, -9));
    }

    /**
     * Passe un tour dans le jeu.
     */
    public void tourdejeu() {
        Scanner sc = new Scanner(System.in);
        List<Personnage> personnage = new ArrayList<Personnage>();
        personnage.addAll(this.archer);
        personnage.addAll(this.paysan);
        personnage.addAll(this.guerrier);
        personnage.addAll(this.mage);

        List<Monstre> monstre = new ArrayList<Monstre>();
        monstre.addAll(this.loup);
        monstre.addAll(this.lapin);

        Personnage pJoueur = getJoueur().getPerso();
        
       initialisationTour(personnage,monstre,pJoueur);

        dessineMonde();
        System.out.println("Vos statistiques : ");
        pJoueur.affiche();
        System.out.println();
        String nom;
        List<Creature> aPortee = new ArrayList<Creature>();
        List<Point2D> posAutour = new ArrayList<Point2D>();
        Point2D pos = pJoueur.getPos();
        afficherAutour(personnage,monstre,aPortee,pJoueur,posAutour,pos);
        int action = -1;
        while (action != 0 && action != 1 && action != 2) {
            System.out.println("Vous déplacez : 0 , combattre : 1, sauvegarder : 2");
            action = sc.nextInt();
        }
        if (action == 0) {
            deplacer(pJoueur,sc,posAutour);
        } else if (action ==1) {
            combat(aPortee, sc, pJoueur);
        }else if( action==2){
            System.out.println("Entrez le nom de la sauvegarde : ");
            try{
                nom = sc.next();
            }
            catch(InputMismatchException exception)
            {
                  nom="test";
                  System.out.println("Entrez une chaîne de caractère.");      
            }
            SauvegardePartie sv= new SauvegardePartie(nom);
            sv.init();
            sv.sauvegarder(this);
        }
        pJoueur.verifierNourriture();
        personnage.forEach((c) -> {
            c.verifierNourriture();
        });

    }
    
    public void deplacer(Personnage pJoueur,Scanner sc,List<Point2D> posAutour){
        int direction = -1;
            boolean test = false;
            Point2D initPos = new Point2D(pJoueur.getPos());
            monde[initPos.getX() + initPos.getY()*getTaille()] = ".";
            while (!test) {
                while (direction < 0 || direction > 8) {
                    System.out.println("Entrez le nombre correspondant à la direction souhaitée."
                            + " 8 pour ne rien faire : ");
                    System.out.println("7 0 1");
                    System.out.println("6 j 2");
                    System.out.println("5 4 3");
                    direction = sc.nextInt();
                }
                pJoueur.deplacer(direction);
                Point2D newPos = pJoueur.getPos();
                System.out.println(test(posAutour,newPos));
                if(test(posAutour,newPos))
                {
                    test = true;
                }
                else
                {
                    System.out.println("Vous ne pouvez aller ici.");
                    direction = -1;
                    pJoueur.setPos(initPos);
                } 
            }
            System.out.println("Vous vous situez en " + pJoueur.getPos().affiche() + ".");
            Point2D newPos = pJoueur.getPos();
            monde[newPos.getX() + newPos.getY()*getTaille()] = "J";
            int index = -1;
            for (int i = 0; i < soin.size(); i++) {
                Soin s = soin.get(i);
                if (pJoueur.getPos().equal(s.getPos())) {
                    s.gueri(pJoueur);
                    System.out.println("Vous avez pris une potion de soin. "
                            + "Vous avez maintenant " + pJoueur.getPtVie() + " PV.");
                    index = i;
                }
            }
            if (index != -1) {
                detruireElement(soin.get(index));
                index = -1;
            }

            for (int i = 0; i < nourriture.size(); i++) {
                Nourriture n = nourriture.get(i);
                if (pJoueur.getPos().equal(n.getPos())) {
                    n.pouvoir(pJoueur);
                    System.out.println("Vous venez de manger.");
                    index = i;
                    pJoueur.ajouterNourriture(n);
                }
            }
            if (index != -1) {
                detruireElement(nourriture.get(index));
                index = -1;
            }

            if (pJoueur instanceof Mage) {
                for (int i = 0; i < magie.size(); i++) {
                    Mana m = magie.get(i);
                    if (pJoueur.getPos().equal(m.getPos())) {
                        m.magie(pJoueur);
                        System.out.println("Vous avez pris une potion de mana. "
                            + "Vous avez maintenant " + pJoueur.getPtMana() + " PdMana.");
                        index = i;
                    }
                }
                if (index != -1) {
                    detruireElement(magie.get(index));
                }
            }

            this.nuage.forEach((n) -> {
                if (newPos.equal(n.getPos())) {
                    n.combattre(pJoueur);
                }
            });
    }

    public void combat(List<Creature> aPortee,Scanner sc,Personnage pJoueur){
        if (aPortee.size() > 0) {
                int combat = -1;
                while (combat < 0 || combat >= aPortee.size()) {
                    Iterator<Creature> listIt = aPortee.iterator();
                    int k = 0;
                    while (listIt.hasNext()) {
                        Creature c = listIt.next();
                        System.out.println("Entrez " + k + " pour attaquer");
                        c.affiche();
                        k += 1;
                    }
                    combat = sc.nextInt();
                }
                pJoueur.affiche();

                if (pJoueur instanceof Combattant) {
                    Creature c = aPortee.get(combat);
                    ((Combattant) pJoueur).combattre(c);
                    if (c.getPtVie() <= 0) {
                        System.out.println("L'adversaire est mort.");
                        detruireElement(c);
                    } else {
                        if (c instanceof Combattant) {
                            ((Combattant) c).combattre(pJoueur);
                            if (pJoueur.getPtVie() <= 0) {
                                System.out.println("Vous êtes mort.");
                            }
                        }
                    }
                }
                pJoueur.affiche();

            } else {
                System.out.println("Aucune créature à portée. Vous ne faites rien.");
            }
    }
    
    
    /**
     * Affiche les informations du monde.
     */
    public void afficheWorld() {
        List<Creature> crea = new ArrayList<Creature>();
        crea.addAll(this.archer);
        crea.addAll(this.paysan);
        crea.addAll(this.guerrier);
        crea.addAll(this.mage);
        crea.addAll(this.loup);
        crea.addAll(this.lapin);

        crea.forEach((c) -> {
            c.affiche();
        });

        this.soin.forEach((c) -> {
            c.affiche();
        });

        this.magie.forEach((c) -> {
            c.affiche();
        });

        this.nuage.forEach((n) -> {
            n.affiche();
        });

        this.nourriture.forEach((n) -> {
            n.affiche();
        });
    }

    /**
     * Vérifie que la position souhaitée n'est pas occupée.
     * @param p = liste de positions à vérifier.
     * @param pos = la position à tester.
     * @return true si pos n'est pas occupée dans p.
     */
    private boolean test(List<Point2D> p, Point2D pos) {
        boolean test = true;
        if ((pos.getX() >= getTaille() || pos.getX() < 0) || (pos.getY() >= getTaille() || pos.getY() < 0)) {
            return false;
        }
        for (Point2D t : p) {
            if (pos != null && t.equal(pos) /*|| pos.distance(t)<=2 */) {
                test = false;
            }

        }
        return test;
    }

    private void creerarcher(int nbarcher, List<Point2D> pt) {
        Random rand = new Random();
        this.archer = new LinkedList<Archer>();
        for (int k = 0; k < nbarcher; k++) {
            Point2D p = pt.get(k);
            this.archer.add(new Archer("Archer " + k, 100, 0, rand.nextInt(40) + 20,
                    rand.nextInt(30) + 10, 0, rand.nextInt(35), rand.nextInt(50) + 40, 0,
                    rand.nextInt(10) + 15,10, p , rand.nextInt(10) + 10));
            monde[p.getX() + p.getY() * getTaille()] = "A";
        }
    }

    private void creerpaysan(int nbpaysan, List<Point2D> pt, int pos) {
        Random rand = new Random();
        this.paysan = new LinkedList<Paysan>();
        for (int k = 0; k < nbpaysan; k++) {
            Point2D p = pt.get(k + pos);
            this.paysan.add(new Paysan("Paysan " + k, 100, 0, 0, rand.nextInt(10) + 5, 0, 0, 0, 0, 0,0, p));
            monde[p.getX() + p.getY() * getTaille()] = "P";
        }
    }
    
    /**
     * Créé une liste de position aléatoire toutes différentes.
     *
     * @param somme nombre d'entitées dans le monde
     * @return liste de position de taille somme et toutes différentes.
     */
    private ArrayList<Point2D> creerpos(int somme) {
        Random rand = new Random();
        ArrayList<Point2D> pos = new ArrayList<Point2D>();
        Point2D p1 = new Point2D(rand.nextInt(getTaille()), rand.nextInt(getTaille()));
        pos.add(p1);
        Point2D p;
        int x = rand.nextInt(getTaille());
        int y = rand.nextInt(getTaille());
        for (int k = 1; k < somme; k++) {
            p = new Point2D(x, y);
            while (!test(pos, p)) {
                x = rand.nextInt(getTaille());
                y = rand.nextInt(getTaille());
                p.setX(x);
                p.setY(y);
            }
            pos.add(p);

        }

        return (pos);
    }

    /**
     * Créé une liste de lapin aléatoire
     *
     * @param nblapin nombre d'entitée Lapin
     * @param pt liste de positions
     * @param i
     */
    private void creerlapin(int nblapin, List<Point2D> pt, int i) {
        Random rand = new Random();
        this.lapin = new LinkedList<Lapin>();
        for (int k = 0; k < nblapin; k++) {
            Point2D p = pt.get(k + i);
            this.lapin.add(new Lapin(100, rand.nextInt(30), rand.nextInt(10), rand.nextInt(10) + 5,1, p));
            monde[p.getX() + p.getY() * getTaille()] = "l";
        }
    }

    /**
     * Créé une liste de loup aléatoire
     *
     * @param nbloup nombre d'entitée Loup
     * @param pt liste de positions
     * @param i
     */
    private void creerloup(int nbloup, List<Point2D> pt, int i) {
        Random rand = new Random();
        this.loup = new LinkedList<Loup>();
        for (int k = 0; k < nbloup; k++) {
            Point2D p = pt.get(k + i);
            this.loup.add(new Loup(100, rand.nextInt(50) + 40, rand.nextInt(20) + 20, rand.nextInt(50) + 30,1, p));
            monde[p.getX() + p.getY() * getTaille()] = "L";
        }
    }

    private void creerguerrier(int nbguerrier, List<Point2D> pt, int i) {
        Random rand = new Random();
        this.guerrier = new LinkedList<Guerrier>();
        for (int k = 0; k < nbguerrier; k++) {
            Point2D p = pt.get(k + i);
            this.guerrier.add(new Guerrier("Guerrier " + k, 200, 0, rand.nextInt(50) + 50,
                    rand.nextInt(30) + 10, 0, rand.nextInt(35), rand.nextInt(50) + 40, 0,
                    rand.nextInt(1) + 1,15, p));
            monde[p.getX() + p.getY() * getTaille()] = "G";
        }
    }

    private void creermage(int nbmage, List<Point2D> pt, int i) {
        Random rand = new Random();
        this.mage = new LinkedList<Mage>();
        for (int k = 0; k < nbmage; k++) {
            Point2D p = pt.get(k + i);
            this.mage.add(new Mage("Mage " + k, 100, rand.nextInt(50) + 50,
                    rand.nextInt(50) + 50, rand.nextInt(30) + 10, rand.nextInt(50) + 20,
                    rand.nextInt(35), rand.nextInt(50) + 40, rand.nextInt(50) + 50,
                    rand.nextInt(3) + 2,5, p));
            monde[p.getX() + p.getY() * getTaille()] = "M";
        }
    }

    private void creermagie(int nbmagie, List<Point2D> pt, int i) {
        Random rand = new Random();
        this.magie = new LinkedList<Mana>();
        for (int k = 0; k < nbmagie; k++) {
            Point2D p = pt.get(k + i);
            this.magie.add(new Mana(rand.nextInt(90) + 10, p));
            monde[p.getX() + p.getY() * getTaille()] = "m";
        }
    }

    private void creersoin(int nbsoin, List<Point2D> pt, int i) {
        Random rand = new Random();
        this.soin = new LinkedList<Soin>();
        for (int k = 0; k < nbsoin; k++) {
            Point2D p = pt.get(k + i);
            this.soin.add(new Soin(rand.nextInt(90) + 10, p));
            monde[p.getX() + p.getY() * getTaille()] = "s";
        }
    }

    private void creernuage(int nbnuage, List<Point2D> pt, int i) {
        Random rand = new Random();
        this.nuage = new LinkedList<NuageToxique>();
        for (int k = 0; k < nbnuage; k++) {
            Point2D p = pt.get(k + i);
            this.nuage.add(new NuageToxique(p));
            monde[p.getX() + p.getY() * getTaille()] = "@";
        }
    }

    /**
     * Supprime l'élément donné dans le jeu.
     * @param e = l'élément à supprimer.
     */
    private void detruireElement(ElementDeJeu e) {
        int index;
        if (e instanceof Objet) {
            Point2D p = ((Objet) e).getPos();
            if (e instanceof Potion) {
                if (e instanceof Soin) {
                    index = soin.indexOf(e);
                    soin.remove(index);
                } else {
                    index = magie.indexOf(e);
                    magie.remove(index);
                }

            } else {
                index = nourriture.indexOf(e);
                nourriture.remove(e);
            }
        } else if (e instanceof Creature) {
            Point2D p = ((Creature) e).getPos();
            if (e instanceof Personnage) {
                if (e instanceof Archer) {
                    index = archer.indexOf(e);
                    archer.remove(index);
                } else if (e instanceof Guerrier) {
                    index = guerrier.indexOf(e);
                    guerrier.remove(index);
                } else if (e instanceof Mage) {
                    index = mage.indexOf(e);
                    mage.remove(index);
                } else {
                    index = paysan.indexOf(e);
                    paysan.remove(index);
                }
            } else {
                if (e instanceof Lapin) {
                    index = lapin.indexOf(e);
                    lapin.remove(index);
                } else {
                    index = loup.indexOf(e);
                    loup.remove(index);
                }
            }
            monde[p.getX() + p.getY() * getTaille()] = ".";
        } else {
            return;
        }
    }
    
    public void dessineMonde()
    {
        for(int j = getTaille() - 1; j >= 0; j--)
        {
            for(int i = 0; i < getTaille(); i++)
            {
                System.out.print(" ");
                System.out.print(monde[i + j*getTaille()]);
            }
            System.out.println();
        }
        System.out.println("J : Joueur, A : archer, G : guerrier, M : mage, P : paysan");
        System.out.println("l : lapin, L : loup, s : soin, m : magie, @ : nuage, n : nourriture.");
    }
    
    public void deplacer(ElementDeJeu e)
    {
        if (e instanceof NuageToxique) {
            Point2D p = new Point2D(((NuageToxique) e).getPos());
            String marque = monde[p.getX() + p.getY()*getTaille()];
            monde[p.getX() + p.getY()*getTaille()] = ".";
            boolean moved = false;
            while(!moved)
            {
                ((NuageToxique) e).deplacer();
                Point2D p2 = ((NuageToxique) e).getPos();
                int x = p2.getX();
                int y = p2.getY();
                if(x >= 0 && x<getTaille() && y>=0 && y<getTaille())
                {
                    if(monde[x + y*getTaille()] != "@")
                    {
                        moved = true;
                        monde[x + y*getTaille()] = marque;
                    }
                    else
                    {
                        ((NuageToxique) e).setPos(p);
                    }
                }
                else
                {
                    ((NuageToxique) e).setPos(p);
                }
            }
        }
        else if(e instanceof Creature)
        {
            Point2D p = new Point2D(((Creature) e).getPos());
            String marque = monde[p.getX() + p.getY()*getTaille()];
            monde[p.getX() + p.getY()*getTaille()] = ".";
            boolean moved = false;
            while(!moved)
            {
                ((Creature) e).deplacer();
                Point2D p2 = ((Creature) e).getPos();
                int x = p2.getX();
                int y = p2.getY();
                if(x >= 0 && x<getTaille() && y>=0 && y<getTaille())
                {
                    String pos = monde[x + y*getTaille()];
                    if(pos == "." || pos =="m" || pos=="s"||pos=="n" || pos=="@")
                    {
                        moved = true;
                        monde[x + y*getTaille()] = marque;
                    }
                    else
                    {
                        ((Creature) e).setPos(p);
                    }
                }
                else
                {
                    ((Creature) e).setPos(p);
                }
            }
        }
        else
        {
            return;
        }
    }

    /**
     * @param joueur the joueur to set
     */
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
    
    /**
     * @return the joueur
     */
    public Joueur getJoueur() {
        return joueur;
    }
    
    public void setTaille(int taille){
        this.taille=taille;
    }

    /**
     * @return the taille
     */
    public int getTaille() {
        return taille;
    }

    private void afficherAutour(List<Personnage> personnage, List<Monstre> monstre, List<Creature> aPortee, Personnage pJoueur, List<Point2D> posAutour, Point2D pos) {
        int portee = pJoueur.getDistAttMax();
        personnage.forEach((c) -> {
            if (c.getPos().distance(pos) <= portee) {
                aPortee.add(c);
                posAutour.add(c.getPos());
            }
        });
        monstre.forEach((c) -> {
            if (c.getPos().distance(pos) <= portee) {
                aPortee.add(c);
                posAutour.add(c.getPos());
            }
        });
        if (aPortee.size() > 0) {
            System.out.println("Vous pouvez attaquer :");
            aPortee.forEach((c) -> {
                c.affiche();
            });
        } else {
            System.out.println("Aucune créature à portée.");
        }
    }

    private void initialisationTour(List<Personnage> personnage, List<Monstre> monstre, Personnage pJoueur) {
        
        
         personnage.forEach((c) -> {
            deplacer(c);
        });

        monstre.forEach((c) -> {
            deplacer(c);
        });
        
        personnage.forEach((c) -> {

            int index = -1;
            for (int i = 0; i < soin.size(); i++) {
                Soin s = soin.get(i);
                if (c.getPos().equal(s.getPos())) {
                    s.gueri(c);
                    System.out.println(c.getNom() + " vient de se regénérer. "
                            + "Il a maintenant " + c.getPtVie() + ".");
                    index = i;
                }
            }
            if (index != -1) {
                detruireElement(soin.get(index));
                index = -1;
            }

            for (int i = 0; i < nourriture.size(); i++) {
                Nourriture n = nourriture.get(i);
                if (c.getPos().equal(n.getPos())) {
                    n.pouvoir(c);
                    System.out.println(c.getNom() + " vient de manger de la nourriture.");
                    index = i;
                    c.ajouterNourriture(n);
                }
            }
            if (index != -1) {
                detruireElement(nourriture.get(index));
            }
        });

        this.mage.forEach((c) -> {
            int index = -1;
            for (int i = 0; i < magie.size(); i++) {
                Mana m = magie.get(i);
                if (c.getPos().equal(m.getPos())) {
                    m.magie(c);
                    System.out.println(c.getNom() + " vient de récupérer du mana. "
                            + "Il a maintenant " + c.getPtMana());
                    index = i;
                }
            }
            if (index != -1) {
                detruireElement(magie.get(index));
            }
        });

        nuage.forEach((n) -> {
            deplacer(n);
            List<Integer> aSupprimer = new ArrayList<Integer>();
            for (int i = 0; i < personnage.size(); i++) {
                Personnage p = personnage.get(i);
                if (p.getPos().equal(n.getPos())) {
                    n.combattre(p);
                    if (p.getPtVie() <= 0) {
                        aSupprimer.add(i);
                    }
                }
            }
            if (aSupprimer.size() >= 0) {
                for (int i = aSupprimer.size() - 1; i == 0; i--) {
                    personnage.remove(aSupprimer.get(i));
                }
            }

            aSupprimer = new ArrayList<Integer>();
            for (int i = 0; i < monstre.size(); i++) {
                Monstre m = monstre.get(i);
                if (m.getPos().equal(n.getPos())) {
                    n.combattre(m);
                    if (m.getPtVie() <= 0) {
                        aSupprimer.add(i);
                    }
                }
            }
            if (aSupprimer.size() >= 0) {
                for (int i = aSupprimer.size() - 1; i == 0; i--) {
                    monstre.remove(aSupprimer.get(i));
                }
            }

            if (n.getPos().equal(pJoueur.getPos())) {
                n.combattre(pJoueur);
            }
        });
        
        soin.forEach((s) ->{
            Point2D p = s.getPos();
            if(monde[p.getX() + p.getY()*getTaille()] == ".")
            {
                monde[p.getX() + p.getY()*getTaille()] = "s";
            }
        });
        
        magie.forEach((m) ->{
            Point2D p = m.getPos();
            if(monde[p.getX() + p.getY()*getTaille()] == ".")
            {
                monde[p.getX() + p.getY()*getTaille()] = "m";
            }
        });
        
        nourriture.forEach((n) ->{
            Point2D p = n.getPos();
            if(monde[p.getX() + p.getY()*getTaille()] == ".")
            {
                monde[p.getX() + p.getY()*getTaille()] = "n";
            }
        });
    }

    private void copie(World w) {
        this.archer.addAll(w.archer);
        this.paysan.addAll(w.paysan);
        this.lapin.addAll(w.lapin);
        this.guerrier.addAll(w.guerrier);
        this.loup.addAll(w.loup);
        this.mage.addAll(w.mage);
        this.magie.addAll(w.magie);
        this.soin.addAll(w.soin);
        this.nuage.addAll(w.nuage);
        this.joueur = w.getJoueur();
        this.taille=w.taille;
        this.monde = new String[taille * taille];
        monde();
    }
}
