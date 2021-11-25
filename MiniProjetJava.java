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

    static String[] getWordList(String text){ //Version 1 de getWordList

        char[] texttochar = text.toCharArray();

        for(int i = 0; i < texttochar.length; i++){ //boucle permettant de remplacer la ponctuation par des espaces et d'enlever les accents

            char c = texttochar[i];
            switch(c){
                case 'ê':
                case 'ë':
                case 'é':
                case 'è': {c = 'e'; break;}
                case 'â':
                case 'ä':
                case 'à': {c = 'a'; break;}
                case 'ü':
                case 'ù':
                case 'û': {c = 'u'; break;}
                case 'ï':
                case 'î': {c = 'i'; break;}
                case 'ö':
                case 'ô': {c = 'o'; break;}
                case 'Ê':
                case 'Ë':
                case 'È':
                case 'É': {c = 'E'; break;}
                case 'Ä':
                case 'Â':
                case 'À': {c = 'A'; break;}
                case 'Ü':
                case 'Ù':
                case 'Û': {c = 'U'; break;}
                case 'Ï':
                case 'Î': {c = 'I'; break;}
                case 'Ö':
                case 'Ô': {c = 'O'; break;}
            }
            if(!(('A' <= c && c <= 'Z' ) || ('a' <= c && c <= 'z'))){
                c = ' ';
            }
            texttochar[i] = c;
        }

        for(int i = 0; i < texttochar.length; i++){ //suppression des espaces qui se suivent (mots séparé par un seul espace)
            if(texttochar[i] == ' ' && texttochar[i + 1] == ' '){
                texttochar = deleteTabElement(texttochar, i+1);
            }
        }

        String stringtext = "";
        for(char c : texttochar){ //creation d'un String contenant les mots séparés par un espace
            stringtext += c;
        }

        return stringtext.split(" "); //Retourne un Tableau de chaine contenant les mots 1 par 1
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
