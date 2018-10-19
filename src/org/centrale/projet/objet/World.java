/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

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
    List<Loup> loup;

    /**
     * Les objets a positionner dans le monde.
     */
    List<Soin> soin;
    List<Mana> magie;

    List<NuageToxique> nuage;

    List<Nourriture> nourriture;

    final int taille = 10;

    Joueur joueur;

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
        this.joueur = new Joueur();
    }

    public void creeMondeAlea() {
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
        nourriture.add(new Nourriture(pt.get(pt.size() - 3), true, 0, 5, 20));
        nourriture.add(new Nourriture(pt.get(pt.size() - 2), false, 1, 5, 20));
        creationJoueur(pt.get(pt.size() - 1));
        joueur.getPerso().affiche();

    }

    /**
     * Créé un monde et instancie automatiquement des entités aléatoires. Cette
     * fonction s'assure aussi du placement de ces entitées
     */
    public void creeMondeAlea(int nbarcher, int nbpaysan, int nblapin, int nbguerrier,
            int nbloup, int nbmage, int nbmagie, int nbsoin, int nbnuage) {
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
        nourriture.add(new Nourriture(pt.get(pt.size() - 3), true, 0, 2, 20));
        nourriture.add(new Nourriture(pt.get(pt.size() - 2), false, 1, 2, 20));
        creationJoueur(pt.get(pt.size() - 1));
        joueur.getPerso().affiche();
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
            nomClasse = sc.nextLine();

        }
        System.out.println("Vous avez choisi la classe " + nomClasse);
        System.out.println("Entrez le nom de votre personnage.");
        nomPerso = sc.nextLine();
        System.out.println("Vous incarnez " + nomPerso + ", un " + nomClasse + ".");

        Random rand = new Random();
        if (null == nomClasse) {
            joueur.setPerso(new Guerrier(nomPerso, 200, 0, rand.nextInt(50) + 50,
                    rand.nextInt(30) + 10, 0, rand.nextInt(35), rand.nextInt(50) + 40, 0,
                    rand.nextInt(1) + 1, pos));
        } else {
            switch (nomClasse) {
                case "Archer":
                    joueur.setPerso(new Archer(nomPerso, 100, 0, rand.nextInt(40) + 20,
                            rand.nextInt(30) + 10, 0, rand.nextInt(35), rand.nextInt(50) + 40, 0,
                            rand.nextInt(10) + 15, pos, rand.nextInt(10) + 10));
                    break;
                case "Guerrier":
                    joueur.setPerso(new Guerrier(nomPerso, 200, 0, rand.nextInt(50) + 50,
                            rand.nextInt(30) + 10, 0, rand.nextInt(35), rand.nextInt(50) + 40, 0,
                            rand.nextInt(1) + 1, pos));
                    break;
                default:
                    joueur.setPerso(new Mage(nomPerso, 100, rand.nextInt(50) + 50,
                            rand.nextInt(50) + 50, rand.nextInt(30) + 10, rand.nextInt(50) + 20,
                            rand.nextInt(35), rand.nextInt(50) + 40, rand.nextInt(50) + 50,
                            rand.nextInt(3) + 2, pos));
                    break;
            }
        }

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

        Personnage pJoueur = joueur.getPerso();

        personnage.forEach((c) -> {

            int index = -1;
            for (int i = 0; i < soin.size(); i++) {
                Soin s = soin.get(i);
                if (c.getPos().equal(s.getPos())) {
                    s.gueri(c);
                    System.out.println(c.getNom() + " vient de se regénérer.");
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
                    System.out.println(c.getNom() + " vient de récupérer du mana");
                    index = i;
                }
            }
            if (index != -1) {
                detruireElement(magie.get(index));
            }
        });

        nuage.forEach((c) -> {
            c.deplacer();
            List<Integer> aSupprimer = new ArrayList<Integer>();
            for (int i = 0; i < personnage.size(); i++) {
                Personnage p = personnage.get(i);
                if (p.getPos().equal(c.getPos())) {
                    c.combattre(p);
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
                if (m.getPos().equal(c.getPos())) {
                    c.combattre(m);
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

            if (c.getPos().equal(pJoueur.getPos())) {
                c.combattre(pJoueur);
            }
        });

        personnage.forEach((c) -> {
            c.deplacer();
        });

        monstre.forEach((c) -> {
            c.deplacer();
        });

        System.out.println("Vous vous situez en " + pJoueur.getPos().affiche() + ".");
        List<Creature> aPortee = new ArrayList<Creature>();
        List<Point2D> posAutour = new ArrayList<Point2D>();
        Point2D pos = pJoueur.getPos();
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
        int action = -1;
        while (action != 0 && action != 1) {
            System.out.println("Vous déplacez : 0 , combattre : 1");
            action = sc.nextInt();
        }
        if (action == 0) {
            int direction = -1;
            boolean test = false;
            Point2D initPos = new Point2D(pJoueur.getPos());
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
                test = test(posAutour, pJoueur.getPos());
                if (!test) {
                    System.out.println("La position est occupée.");
                    pJoueur.setPos(initPos);
                    direction = -1;
                }
            }
            System.out.println("Vous vous situez en " + pJoueur.getPos().affiche() + ".");
            Point2D newPos = pJoueur.getPos();
            int index = -1;
            for (int i = 0; i < soin.size(); i++) {
                Soin s = soin.get(i);
                if (pJoueur.getPos().equal(s.getPos())) {
                    s.gueri(pJoueur);
                    System.out.println(pJoueur.getNom() + " vient de se regénérer.");
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
                    System.out.println(pJoueur.getNom() + " vient de manger de la nourriture.");
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
                        System.out.println(pJoueur.getNom() + " vient de récupérer du mana");
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
        } else {
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
        pJoueur.verifierNourriture();
        personnage.forEach((c) -> {
            c.verifierNourriture();
        });
System.out.println();
    }

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

    private boolean test(List<Point2D> p, Point2D pos) {
        boolean test = true;
        if ((pos.getX() >= taille || pos.getX() < 0) || (pos.getY() >= taille || pos.getY() < 0)) {
            return false;
        }
        for (Point2D t : p) {
            if (pos != null && t.getX() == pos.getX() && t.getY() == pos.getY()) {
                test = false;
            }

        }
        return test;
    }

    private void creerarcher(int nbarcher, List<Point2D> pt) {
        Random rand = new Random();
        this.archer = new LinkedList<Archer>();
        for (int k = 0; k < nbarcher; k++) {
            this.archer.add(new Archer("Archer " + k, 100, 0, rand.nextInt(40) + 20,
                    rand.nextInt(30) + 10, 0, rand.nextInt(35), rand.nextInt(50) + 40, 0,
                    rand.nextInt(10) + 15, pt.get(k), rand.nextInt(10) + 10));
        }
    }

    private void creerpaysan(int nbpaysan, List<Point2D> pt, int pos) {
        Random rand = new Random();
        this.paysan = new LinkedList<Paysan>();
        for (int k = 0; k < nbpaysan; k++) {
            this.paysan.add(new Paysan("Paysan " + k, 100, 0, 0, rand.nextInt(10) + 5, 0, 0, 0, 0, 0, pt.get(k + pos)));
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
        Point2D p1 = new Point2D(rand.nextInt(taille), rand.nextInt(taille));
        pos.add(p1);
        Point2D p;
        int x = pos.get(rand.nextInt(pos.size())).getX() + (rand.nextInt(10) - 5);
        int y = pos.get(rand.nextInt(pos.size())).getY() + rand.nextInt(10) - 5;
        for (int k = 1; k < somme; k++) {
            p = new Point2D(x, y);
            while (!test(pos, p)) {
                x = pos.get(rand.nextInt(pos.size())).getX() + (rand.nextInt(10) - 5);
                y = pos.get(rand.nextInt(pos.size())).getY() + rand.nextInt(10) - 5;
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
            this.lapin.add(new Lapin(100, rand.nextInt(30), rand.nextInt(10), rand.nextInt(10) + 5, pt.get(i + k)));
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
            this.loup.add(new Loup(100, rand.nextInt(50) + 40, rand.nextInt(20) + 20, rand.nextInt(50) + 30, pt.get(i + k)));
        }
    }

    private void creerguerrier(int nbguerrier, List<Point2D> pt, int i) {
        Random rand = new Random();
        this.guerrier = new LinkedList<Guerrier>();
        for (int k = 0; k < nbguerrier; k++) {
            this.guerrier.add(new Guerrier("Guerrier " + k, 200, 0, rand.nextInt(50) + 50,
                    rand.nextInt(30) + 10, 0, rand.nextInt(35), rand.nextInt(50) + 40, 0,
                    rand.nextInt(1) + 1, pt.get(k + i)));
        }
    }

    private void creermage(int nbmage, List<Point2D> pt, int i) {
        Random rand = new Random();
        this.mage = new LinkedList<Mage>();
        for (int k = 0; k < nbmage; k++) {
            this.mage.add(new Mage("Mage " + k, 100, rand.nextInt(50) + 50,
                    rand.nextInt(50) + 50, rand.nextInt(30) + 10, rand.nextInt(50) + 20,
                    rand.nextInt(35), rand.nextInt(50) + 40, rand.nextInt(50) + 50,
                    rand.nextInt(3) + 2, pt.get(k + i)));
        }
    }

    private void creermagie(int nbmagie, List<Point2D> pt, int i) {
        Random rand = new Random();
        this.magie = new LinkedList<Mana>();
        for (int k = 0; k < nbmagie; k++) {
            this.magie.add(new Mana(rand.nextInt(90) + 10, pt.get(i + k)));
        }
    }

    private void creersoin(int nbsoin, List<Point2D> pt, int i) {
        Random rand = new Random();
        this.soin = new LinkedList<Soin>();
        for (int k = 0; k < nbsoin; k++) {
            this.soin.add(new Soin(rand.nextInt(90) + 10, pt.get(i + k)));
        }
    }

    private void creernuage(int nbnuage, List<Point2D> pt, int i) {
        Random rand = new Random();
        this.nuage = new LinkedList<NuageToxique>();
        for (int k = 0; k < nbnuage; k++) {
            this.nuage.add(new NuageToxique(pt.get(i + k)));
        }
    }

    private void detruireElement(ElementDeJeu e) {
        int index;
        if (e instanceof Objet) {
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
        } else {
            return;
        }
    }

}
