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
        String fichier = null;

        try {
            System.out.println("veuillez sélectionner le nom du fichier dont vous voulez trouver le ou les mots souhaités : ");
            Scanner s = new Scanner(System.in);
            fichier = s.nextLine() + ".txt";

            File file = new File(fichier);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;
            System.out.println("Chargement du fichier "+ fichier);
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
            System.err.println("Le fichier \""+fichier+"\" doit se trouver dans le répertoire "+
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
