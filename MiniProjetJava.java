/*
MiniProjetJava | Création d'un programme de mini-jeu de mémoire.
BOUDIER Maxime, BAYEN Maxime, CHAUNEY Célian
 */

import java.io.IOException;

public class MiniProjetJava {

    public static void main(String[] args){

        String text = getText("src/Extrait_texte.txt");

        System.out.println(text);
    }

    static String getText(String filepath){

        String text;
        try {
            text = Lecteur.LireTexte(filepath);
        }
        catch (IOException ex){
            System.out.println("Erreur de la lecture du fichier.");
            text = "ERROR";
        }

        return text;
    }
}
