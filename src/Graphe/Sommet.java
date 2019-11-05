package Graphe;

import java.util.HashMap;
import java.util.Map.Entry;

public class Sommet {
    private static Sommet racine;
    private static int nbrSommets = 0;
    private boolean etat;
    private int valeur;

    private HashMap<Character, Sommet> arcs;

    public Sommet(){
        if (Sommet.racine == null){
            Sommet.racine = this;
        }
        this.valeur = nbrSommets++;
        arcs = new HashMap<Character, Sommet>();
    }
    //TODO: Prendre en compte les echec
    public void ajouter(Character lettre){
        if(!this.arcs.containsKey(lettre)){
            arcs.put(lettre,new Sommet());
            
        }
    }

    public void ajoutMulti(String mot){
        if (mot.length() <= 1){
            ajouter(mot.charAt(0));
        } else {
            if(!this.arcs.containsKey(mot.charAt(0))) {
                this.arcs.put(mot.charAt(0), new Sommet());
            }
            arcs.get(mot.charAt(0)).ajoutMulti(mot.substring(1));
        }
    }

    public String toString(){
        String str = "";
        for(Entry<Character,Sommet> entry : arcs.entrySet()){
            str += entry.getKey()+"; ";
        }
        return str;
    }

}
