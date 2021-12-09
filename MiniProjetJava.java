/*
MiniProjetJava | Création d'un programme de mini-jeu de mémoire.
@authors BOUDIER Maxime, BAYEN Maxime, CHAUNEY Célian
 */

import java.io.IOException;

public class MiniProjetJava {

    static String recupText(String cheminFichier) { //Methode getText qui permet de récupéré le text d'un fichier .txt avec son chemin d'accès en paramètre.

        String text;

        try {
            text = Lecteur.LireTexte(cheminFichier); //Récupération du texte à partir du fichier qui peut provoquer une erreur
        } catch (IOException ex) {
            System.out.println("Erreur de la lecture du fichier.");
            text = "ERROR";
        }

        return text;
    }

    static String[] recupListeDeMots(String text) { //Version 2 de getWordList

        char[] chartext = text.toLowerCase().toCharArray();
        String[] listeMots = new String[0]; //Création de la liste de mot avec aucun élément

        String mot = "";//création d'un mot vide
        for (char c : chartext) { //cette boucle for permet de construire les mots et de les ajouter à la liste

            if (('a' <= c && c <= 'z') ||
                    c == 'ê' ||
                    c == 'ë' ||
                    c == 'é' ||
                    c == 'è' ||
                    c == 'â' ||
                    c == 'ä' ||
                    c == 'à' ||
                    c == 'ü' ||
                    c == 'ù' ||
                    c == 'û' ||
                    c == 'ï' ||
                    c == 'î' ||
                    c == 'ö' ||
                    c == 'ô') {
                mot += c;
            } //si c est une lettre, alors on l'ajoute au mot

            else {
                if (!mot.equals(""))
                    listeMots = ajoutElementALaFin(listeMots, mot); // sinon on ajouter le mot à la liste s'il n'est pas vide
                mot = "";
            } //on créer un nouveau mot où l'on peut ajouter des lettres.
        }
        for (int i = 0; i < listeMots.length; i++) //boucle for qui supprime les mots d'une lettre ou moins.
            if (listeMots[i].length() <= 1)
                listeMots = supprElementParIndice(listeMots, i);

        return listeMots;
    }

    static String[] supprElementParIndice(String[] tab, int indice) {

        String[] newtab = new String[tab.length - 1];

        for (int i = 0; i < indice; i++) {
            newtab[i] = tab[i];
        }
        for (int i = indice; i < newtab.length; i++) {
            newtab[i] = tab[i + 1];
        }
        return newtab;
    }

    static String[] ajoutElementALaFin(String[] tab, String s) {
        String[] newtab = new String[tab.length + 1];
        for (int i = 0; i < tab.length; i++)
            newtab[i] = tab[i];
        newtab[newtab.length - 1] = s;
        return newtab;
    }

    static void Menu(){
        System.out.println("Menu Principal");
        System.out.println("1 : Menu du jeu 1");
        System.out.println("2 : Menu du jeu 2");
        System.out.println("3 : Menu du jeu 3");
        System.out.println("4 : Statistiques");
        System.out.println("5 : Parametre");
        System.out.println("6 : Sortir du jeu");

        int choixMenu;
        do
        {    System.out.println ("Saisie un chiffre ") ;
            choixMenu = Console.lireInt() ;
            if (choixMenu > 6 || choixMenu < 1){
                System.out.println("La saisie est incorrect");;
            }
        }
        while (choixMenu > 6 || choixMenu < 1);

        System.out.println(choixMenu);

        switch(choixMenu){
            case 1:
                System.out.println("Jeu 1");
                break;
            case 2:
                System.out.println("Jeu 2");
                break;
            case 3:
                System.out.println("Jeu 3");
                break;
            case 4:
                System.out.println("Statistiques");
                break;
            case 5:
                System.out.println("Parametre");
                break;
            case 6:
                System.out.println("Sortir du jeu");
                break;
        }
    }

    public static void main(String[] args) { //Méthode main

        String text = recupText("src/Extrait_texte.txt");

        if (text.equals("ERROR")) return; //arrêt du programme en cas d'erreur de lecture.

        System.out.println(text);

        String[] listeDeMots = recupListeDeMots(text);

    }
}
