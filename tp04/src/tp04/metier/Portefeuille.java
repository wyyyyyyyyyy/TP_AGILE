/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tp04.metier;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author perussel
 */
public class Portefeuille {

    Map<Action, LignePortefeuille> mapLignes; //it's the list of all actions.
    private static final int year = Calendar.getInstance().
        get(Calendar.YEAR);//the year of sysdate.
    private static final int nbday = 
        Calendar.getInstance().get(Calendar.DAY_OF_YEAR);//day number of sysdate
    private static final Jour today = 
            new Jour(year,nbday);//Object Jour for sysdate.
    
    /**
     * private object class for the protefeuille line.
     */
    private class LignePortefeuille {

        private Action action; // the action in a line of portefeuille.

        private int qte; //the quantity of this added action.

        /**
         * @return get quantity of action.
         */
        public int getQte() {
            return qte;
        }

        /**
         * set the quantity.
         * @param qte quantity of actions.
         */
        public void setQte(int qte) {
            this.qte = qte;
        }

        /**
         * @return the action in a line of portefeuille.
         */
        public Action getAction() {
            return this.action;
        }

        /**
         * constructor of object LignePortefeuille.
         * @param action
         * @param qte 
         */
        public LignePortefeuille(Action action, int qte) {
            this.action = action;
            this.qte = qte;
        }

        /**
         * @return string of a object LignePortefeuille: libelle + valeur.
         */
        public String toString() {
            return "||"+Integer.toString(qte) + "  Valeur : " + this.action.valeur(today) ;
        }
    }

    /**
     * consultor of class Portefeuille.
     */
    public Portefeuille() {
        this.mapLignes = new HashMap();
    }

    /**
     * function for buy an action.
     * @param a action object.
     * @param q quantity of action.
     */
    public void acheter(Action a, int q) {
        if (this.mapLignes.containsKey(a) == false) {
            this.mapLignes.put(a, new LignePortefeuille(a, q));
        } else {
            this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() + q);
        }
    }

    /**
     * function for sale an action.
     * @param a action object.
     * @param q quantity of action.
     */
    public void vendre(Action a, int q) {
        if (this.mapLignes.containsKey(a) == true) {
            if (this.mapLignes.get(a).getQte() > q) {
                this.mapLignes.get(a).setQte(this.mapLignes.get(a).getQte() - q);
            } else if (this.mapLignes.get(a).getQte() == q) {
                this.mapLignes.remove(a);
            }
        }        
    }
    
    /**
     * function for read my protefuille.
     * @return String for protefeuille.
     */
    public String toString() {
        return "Valeur de portefeuille : "+this.valeur(today)+"||"+this.mapLignes.toString();
    }

    /**
     * function for get today's price of portefeuille .
     * @param j today.
     * @return today's price of portefeuille .
     */
    public float valeur(Jour j) {
        float total = 0;
        for (LignePortefeuille lp : this.mapLignes.values()) {
            total = total + (lp.getQte() * lp.getAction().valeur(j));
        }
        return total;
    }
    

}
