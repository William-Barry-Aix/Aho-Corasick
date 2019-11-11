import Graphe.Sommet;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static ArrayList<String> MOTS = new ArrayList<String>();

    public static void arranger(){
        for (String mot: MOTS) {
            Main.MOTS.set(MOTS.indexOf(mot), mot.toUpperCase());
        }
        MOTS.sort(String.CASE_INSENSITIVE_ORDER);
    }

    public static void main(String[] args) {

        try {
            File file = new File("source.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            System.out.println("Chargement du fichier...");
            while ((st = br.readLine()) != null) {
                st = st.replaceAll("[,?;.:/!()-]", " ");
                for (String mot : st.toUpperCase().split(" ")) {
                    if (mot.length() != 0) {
                        Main.MOTS.add(mot.trim());
                    }
                }
            }
            // Ajout des sommets correspondants aux mots
            Sommet racine = new Sommet();
            for (String mot: Main.MOTS) {
                racine.ajoutMulti(mot);
            }
            System.out.println("Fichier chargé !");
            recherche(racine);

        } catch (FileNotFoundException ex){
            System.err.println("Un fichier \"source.txt\" doit se trouver dans le répertoire "+
                    System.getProperty("user.dir"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    public static void recherche(Sommet racine){
        while (true){
            Scanner myObj = new Scanner(System.in);  // Create a Scanner object
            System.out.println("Entrez un mot à chercher :");
            String mot = myObj.nextLine();  // Read user input
            racine.recherche(mot);
        }
    }
}
