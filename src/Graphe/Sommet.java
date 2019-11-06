package Graphe;

import java.util.HashMap;
import java.util.Map.Entry;

public class Sommet {
    private static Sommet racine;
    private static int nbrSommets = 0;
    private boolean etat;
    private int valeur;

    private HashMap<Character, Sommet> arcs;
    private String mot;

    /**
     *
     */
    public Sommet(){
        // Définit la racine si ce sommet est le premier à être instancié
        if (Sommet.racine == null){
            Sommet.racine = this;
        }
        this.mot = "";
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
        if(!this.arcs.containsKey(lettre)){
            arc = new Sommet();
            arcs.put(lettre, arc);
        } else {
            arc = arcs.get(lettre);
        }
        return arc;
    }

    /**
     * Methode d'entrée pour l'appel récurcif d'ajout de mot
     * @param mot
     */
    public void ajoutMulti(String mot){
        ajoutMulti(mot, "");
    }

    /**
     * Méthode récurcive qui créé les arcs jusqu'au mot à ajouter
     * @param mot
     * @param prefix
     */
    private void ajoutMulti(String mot, String prefix){
        // mise à jour du préfixe à ajouter au sommet de la fin du mot
        prefix += mot.charAt(0);
        // ajoute un sommet s'il n'éxiste pas
        Sommet sommet = ajouter(mot.charAt(0));
        // si c'est le dernier caractère du mot, fixe le mot du sommet
        if (mot.length() <= 1){
            sommet.mot = prefix;
        }
        else {
            // l'ajout récurcif continue en enlevant la première lettre du mot
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
     * Trouve le mot donné dans ses arcs
     * @param mot
     * @return
     */
    public Sommet recherche(String mot) {
        Sommet trouve = null;
        if (mot.length() <= 0){
            System.out.println("tout le mot n'a pas été parcourus :'(");
            return null;
        }
        if (this.arcs.containsKey(mot.charAt(0))){
            trouve = this.arcs.get(mot.charAt(0)).rechercheSufixe(mot.substring(1), 0);
        }
        return trouve;
    }

    /**
     * Parcour les arcs à partir d'un sufixe
     * @param mot
     * @param position
     * @return
     */
    private Sommet rechercheSufixe(String mot, int position) {
        Sommet trouve = null;
        // si le sommet est le dernier d'un mot, afficher ce mot
        if (!this.mot.equals("")) {
            System.out.println(this.mot);
        }
        // termine la recherche en retournant le dernier mot
        if (mot.length() == position){
            System.out.println("tout le mot a été parcourus !!!");
            return this;
        }
        if (this.arcs.containsKey(mot.charAt(position))){
            trouve = this.arcs.get(mot.charAt(position)).rechercheSufixe(mot, position + 1);
        } else {
            if (mot.length() > 0){
                // TODO: ajouter une méthode de recherche de transition
                System.out.println("shit");
                System.out.println(mot);
                trouve = Sommet.racine.recherche(mot);
            }
        }
        return trouve;
    }


}
