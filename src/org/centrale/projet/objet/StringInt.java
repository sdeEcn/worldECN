/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 * Permet de diviser une chaîne de caractère en sous-chaînes de caractères, en
 * divisant à chaque espace.
 * @author Alban
 */
public class StringInt {
    private String string;
    private int i;
    private boolean suite;
    private String chaine;

    public StringInt(String string, int i,boolean suite,String chaine) {
        this.string = string;
        this.i = i;
        this.suite = suite;
        this.chaine= chaine;
    }
    
    

    /**
     * @return the string
     */
    public String getString() {
        return string;
    }

    /**
     * @param string the string to set
     */
    public void setString(String string) {
        this.string = string;
    }

    /**
     * @return the i
     */
    public int getI() {
        return i;
    }

    /**
     * @param i the i to set
     */
    public void setI(int i) {
        this.i = i;
    }

    /**
     * @return the suite
     */
    public boolean isSuite() {
        return suite;
    }

    /**
     * @param suite the suite to set
     */
    public void setSuite(boolean suite) {
        this.suite = suite;
    }

    /**
     * @return the chaine
     */
    public String getChaine() {
        return chaine;
    }

    /**
     * @param chaine the chaine to set
     */
    public void setChaine(String chaine) {
        this.chaine = chaine;
    }
    
    
    
}
