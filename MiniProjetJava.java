/*
MiniProjetJava | Création d'un programme de mini-jeu de mémoire.
BOUDIER Maxime, BAYEN Maxime, CHAUNEY Célian
 */

import java.io.IOException;

public class MiniProjetJava {

     static String getText(String filepath){ //Methode getText qui permet de récupéré le text d'un fichier .txt avec son chemin d'accès en paramètre.

        String text;

        try {
            text = Lecteur.LireTexte(filepath); //Récupération du texte à partir du fichier qui peut provoquer une erreur
        }
        catch (IOException ex){
            System.out.println("Erreur de la lecture du fichier.");
            text = "ERROR";
        }

        return text;
    }

    static String[] getWordList(String text){ //Version 2 de getWordList

        char[] chartext = text.toLowerCase().toCharArray();
        String[] wordList = new String[0]; //Création de la liste de mot avec aucun élément

        String mot = "";//création d'un mot vide
        for(char c : chartext ) { //cette boucle for permet de construire les mots et de les ajouter à la liste

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
                 mot += c;} //si c est une lettre, alors on l'ajoute au mot

            else{if(!mot.equals("")) wordList = addTabElementToEnd(wordList, mot); // sinon on ajouter le mot à la liste s'il n'est pas vide
                mot = "";} //on créer un nouveau mot où l'on peut ajouter des lettres.
        }
        for(int i = 0; i < wordList.length; i++) //boucle for qui supprime les mots d'une lettre ou moins.
            if(wordList[i].length() <= 1)
                wordList = deleteTabElement(wordList, i);

        return wordList;
    }

    static char[] deleteTabElement(char[] tab, int elementPos){

        char[] newtab = new char[tab.length-1];

        for(int i = 0; i < elementPos; i++){
            newtab[i] = tab[i];
        }
        for(int i = elementPos; i < newtab.length; i++){
            newtab[i] = tab[i + 1];
        }
        return newtab;
    }

    static String[] deleteTabElement(String[] tab, int elementPos){

        String[] newtab = new String[tab.length-1];

        for(int i = 0; i < elementPos; i++){
            newtab[i] = tab[i];
        }
        for(int i = elementPos; i < newtab.length; i++){
            newtab[i] = tab[i + 1];
        }
        return newtab;
    }

    static String[] addTabElementToEnd(String[] tab, String s){
        String[] newtab = new String[tab.length+1];
        for(int i = 0; i < tab.length; i++)
            newtab[i] = tab[i];
        newtab[newtab.length-1] = s;
        return newtab;
    }

    public static void main(String[] args){ //Méthode main

        String text = getText("src/Extrait_texte.txt");
        if(text.equals("ERROR")) return; //arrêt du programme en cas d'erreur de lecture.

        System.out.println(text);

        String[] wordlist = getWordList(text);

        for (String s : wordlist) {
            System.out.print(s);
        }
    }
}
