import Graphe.Sommet;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main {

    public static ArrayList<String> MOTS = new ArrayList<String>();

    public static void arranger(){
        for (String mot: MOTS) {
            Main.MOTS.set(MOTS.indexOf(mot), mot.toUpperCase());
        }
        MOTS.sort(String.CASE_INSENSITIVE_ORDER);
    }

    public static void main(String[] args) {
        Main.MOTS.add("Cane");
        Main.MOTS.add("Ane");
        Main.MOTS.add("CaRTe");
        Main.MOTS.add("AnDeS");
        Main.MOTS.add("TENIr");
        Main.MOTS.add("CARtaBLE");
        Main.MOTS.add("TAblEaU");
        Main.MOTS.add("AS");
        Main.MOTS.add("ArTe");
        Main.MOTS.add("Rtt");
        // Initialisation de la liste de mots
        Main.arranger();
        System.out.println(Main.MOTS);
        // Ajout des sommets correspondants aux mots
        Sommet racine = new Sommet();
        for (String mot: Main.MOTS) {
            racine.ajoutMulti(mot);
        }
        System.out.println(racine);
        System.out.println(racine.recherche("CARTABLE"));
    }
}
