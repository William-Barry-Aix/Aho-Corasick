import Graphe.Sommet;

import java.io.*;
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
        System.out.println(args[0]);
        File file = new File(args[0]);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            while ((st = br.readLine()) != null) {
                st = st.replaceAll("[,?;.:/!()-]", " ");
                for (String mot : st.toUpperCase().split(" ")) {
                    if (mot.length() != 0) {
                        System.out.println(mot.trim());
                        Main.MOTS.add(mot.trim());
                    }
                }
            }


        } catch (IOException ex) {
            ex.printStackTrace();
        }
        System.out.println(Main.MOTS);
        // Ajout des sommets correspondants aux mots
        Sommet racine = new Sommet();
        for (String mot: Main.MOTS) {
            racine.ajoutMulti(mot);
        }
        //System.out.println(racine.recherche("CARTABLEAU"));
        racine.recherche("Théâtre");
    }
}
