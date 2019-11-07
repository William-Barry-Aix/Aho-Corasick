package Graphe;

import java.util.HashMap;
import java.util.Map.Entry;

public class Sommet {
    private static Sommet racine;
    private static int nbrSommets = 0;
    private boolean visite;
    private int valeur;

    private HashMap<Character, Sommet> arcs;
    private String prefixe;

    /**
     *
     */
    public Sommet(){
        // Définit la racine si ce sommet est le premier à être instancié
        if (Sommet.racine == null){
            Sommet.racine = this;
        }
        this.visite = false;
        this.prefixe = "";
        this.valeur = nbrSommets++;
        arcs = new HashMap<Character, Sommet>();
    }

    /**
     * Ajoute une lettre à la liste d'arc du sommet
     * @param lettre
     * @return le nouvel arc
     */
    public Sommet ajouter(Character lettre){
        Sommet arc = null;
        //vérife que la lettre n'existe pas déjà
        if (!this.arcs.containsKey(lettre)){
            arc = new Sommet();
            arcs.put(lettre, arc);
        } else {
            arc = arcs.get(lettre);
        }
        return arc;
    }

    /**
     * Methode d'entrée pour l'appel récurcif d'ajout de prefixe
     * @param mot
     */
    public void ajoutMulti(String mot){
        ajoutMulti(mot, "");
    }

    /**
     * Méthode récurcive qui créé les arcs jusqu'au prefixe à ajouter
     * @param mot
     * @param prefix
     */
    private void ajoutMulti(String mot, String prefix){
        // mise à jour du préfixe à ajouter au sommet de la fin du prefixe
        prefix += mot.charAt(0);
        // ajoute un sommet s'il n'éxiste pas
        Sommet sommet = ajouter(mot.charAt(0));
        // si c'est le dernier caractère du prefixe, fixe le prefixe du sommet
        if (mot.length() <= 1){
            sommet.prefixe = prefix;
        }
        else {
            // l'ajout récurcif continue en enlevant la première lettre du prefixe
            sommet.ajoutMulti(mot.substring(1), prefix);
        }
    }

    public String toString(){
        String str = "";
        for(Entry<Character,Sommet> entry : arcs.entrySet()){
            str += entry.getKey()+"; ";
        }
        return str;
    }

    /**
     * Trouve le prefixe donné dans ses arcs
     * @param mot
     * @return
     */
    public Sommet recherche(String mot) {
        mot = mot.toUpperCase();
        Sommet trouve = null;
        if (mot.length() <= 0){
            System.out.println("tout le prefixe n'a pas été parcourus :'(");
            return null;
        }
        if (this.arcs.containsKey(mot.charAt(0))){
            trouve = this.arcs.get(mot.charAt(0)).rechercheSufixe(mot.substring(1), 0);
        } else {
            trouve = Sommet.racine.recherche(mot.substring(1));
        }
        return trouve;
    }

    private void affiche() {
        if (!visite){
            System.out.println("Trouvé: "+this.prefixe);
            this.visite = true;
        }
    }

    /**
     * Parcour les arcs à partir d'un sufixe
     * @param mot
     * @param position
     * @return
     */
    private Sommet rechercheSufixe(String mot, int position) {
        Sommet trouve = null;
        // si le sommet est le dernier d'un prefixe, afficher ce prefixe
        if (!this.prefixe.equals("")) {
            this.affiche();
        }
        // termine la recherche en retournant le dernier prefixe
        if (mot.length() == position){
            // Cherche les paternes
            if (this.prefixe.length() > 1){
                Sommet.racine.rechercheSufixe(this.prefixe.substring(1),0);
            }
            return this;
        }
        if (this.arcs.containsKey(mot.charAt(position))){
            trouve = this.arcs.get(mot.charAt(position)).rechercheSufixe(mot, position + 1);
        } else {
            if (mot.length() > 0){
                trouve = Sommet.racine.recherche(mot);
            }
        }
        return trouve;
    }


}
