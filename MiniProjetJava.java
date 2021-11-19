/*
MiniProjetJava | Création d'un programme de mini-jeu de mémoire.
BOUDIER Maxime, BAYEN Maxime, CHAUNEY Célian
 */

import java.io.IOException;

public class MiniProjetJava {

    public static void main(String[] args){

        String text;
        try {
            text = Lecteur.LireTexte("src/Extrait_texte.txt");
        }
        catch (IOException ex){
            System.out.println("Erreur de la lecture du fichier.");
            text = "ERROR";
        }

        System.out.println(text);


    }
}
