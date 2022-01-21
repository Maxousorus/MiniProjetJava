/*
MiniProjetJava | Création d'un programme de mini-jeu de mémoire.
@authors BOUDIER Maxime, BAYEN Maxime, CHAUNEY Célian
 */

import java.io.IOException;
import java.util.Locale;

public class MiniProjetJava {

    static void ecranChargement() { //Permet de gerer le chargement qunad on lance le code PS: il ne fonctionne pas bien
        //sur netbeans mais il fonctionne dans la console.

        String[] messages = {"0%  - Telechargement des methode",
            "10% - Mise en place des variables",
            "23% - Lecture intensive du cours de HM",
            "30% - Respect des règles sanitaire dans le code",
            "42% - Correction des bugs",
            "50% - Ce chargement est bien entendu inutile",
            "66% - Tapez 666 dans le menu des Statistiques !",
            "71% - Verification de l'indentation et des commentaires",
            "80% - ALT 243 correspond ¾ dans le code ASCII",
            "90% - Finalisation",
            "100% - Chargement terminé, amusez-vous bien !"};

        for (int i = 0; i < messages.length; i++) {
            supprConsole();
            afficherBarreChargement(messages.length, 3, i);
            System.out.println(messages[i]);
            Timer.attendreSecondes(3);
        }
    }

    static void afficherBarreChargement(int nbmessages, int multiplieur, int index) {
        nbmessages -= 1; //car le message d'index 0 ne compte pas dans la taille

        int taille = (nbmessages * multiplieur);
        int pas = index * multiplieur;

        System.out.print("╔");
        for (int i = 0; i < taille; i++) {
            System.out.print("═");
        }
        System.out.println("╗");

        System.out.print("║");
        for (int i = 0; i < pas; i++) {
            System.out.print("█");
        }
        for (int i = pas; i < taille; i++) {
            System.out.print(" ");
        }
        System.out.println("║");

        System.out.print("╚");
        for (int i = 0; i < taille; i++) {
            System.out.print("═");
        }
        System.out.println("╝");

    }

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

    static char[] supprAccent(char[] chartext) {
        for (int i = 0; i < chartext.length; i++) {
            char c = chartext[i];
            if (c == 'ê'
                    || c == 'ë'
                    || c == 'é'
                    || c == 'è') {
                chartext[i] = 'e';
            } else if (c == 'â'
                    || c == 'ä'
                    || c == 'à') {
                chartext[i] = 'a';
            } else if (c == 'ü'
                    || c == 'ù'
                    || c == 'û') {
                chartext[i] = 'u';
            } else if (c == 'ï'
                    || c == 'î') {
                chartext[i] = 'i';
            } else if (c == 'ö'
                    || c == 'ô') {
                chartext[i] = 'o';
            }
        }
        return chartext;
    }

    static String[] recupListeDeMots(String text) { //Version 2 de getWordList

        char[] chartext = text.toLowerCase().toCharArray();
        String[] listeMots = new String[0]; //Création de la liste de mot avec aucun élément

        chartext = supprAccent(chartext);

        String mot = "";//création d'un mot vide

        for (char c : chartext) { //cette boucle for permet de construire les mots et de les ajouter à la liste

            if ('a' <= c && c <= 'z') {
                mot += c;
            } //si c est une lettre, alors on l'ajoute au mot
            else {
                if (!mot.equals("")) {
                    listeMots = ajoutElementALaFin(listeMots, mot); // sinon on ajouter le mot à la liste s'il n'est pas vide
                }
                mot = ""; //on créer un nouveau mot où l'on peut ajouter des lettres.
            }
        }
        listeMots = supprMotsDeMoinsDe(listeMots, 2); //supprime les mots de moins de 2 lettre
        listeMots = supprDoublons(listeMots);        //supprime les doublons
        return listeMots;
    }

    static String[] supprDoublons(String[] tab) {//Methode permettant de supprimer les doublons

        for (int i = 0; i < tab.length; i++) { // i est le mot initial
            for (int j = 0; j < tab.length; j++) { //j parcoure la liste apres le mot initial
                if (i < j) {
                    if (tab[i].equals(tab[j])) { //Comparaison si un mot d'index j est egal au mot initial.
                        tab = supprElementParIndice(tab, j);
                    }
                }
            }
        }
        return tab; //retourne le tableau qui ne contient que des mots initial donc un liste sans doublons
    }

    static String[] supprMotsDeMoinsDe(String[] tab, int longueur) {//supprime les mots d'une longueur donnée et moins
        for (int i = 0; i < tab.length; i++) {
            if (tab[i].length() < longueur) {
                tab = supprElementParIndice(tab, i);
            }
        }
        return tab;

    }

    static String[] supprMotsDePlusDe(String[] tab, int longueur) {//supprime les mots d'une longueur donnée et plus
        for (int i = 0; i < tab.length; i++) {
            if (tab[i].length() > longueur) {
                tab = supprElementParIndice(tab, i);
            }
        }
        return tab;
    }

    static String[] listeMotSelonLettres(String[] tab, int min, int max) { //retourne une liste avec des mots selon leur longueur
        String[] newtab = {}; //creation d'un nouveau tableau vide

        for (String s : tab) {
            if (estComprisEntre(s.length(), min, max)) //Quand la longueur du mots en dans l'intervalle
            {
                newtab = ajoutElementALaFin(newtab, s); //On l'ajoute dans le nouveau tableau
            }
        }

        return newtab; //Retourne le nouveau tableau
    }

    static String[] supprElementParIndice(String[] tab, int indice) {//Supprime un élément choisi dans un tableau par index

        String[] newtab = new String[tab.length - 1]; //Creation d'un tableau vide de longueur -1

        for (int i = 0; i < indice; i++) {
            newtab[i] = tab[i]; //Copie du tab dans newtab jusqu'à l'index (non compris)
        }
        for (int i = indice; i < newtab.length; i++) {
            newtab[i] = tab[i + 1]; // Copie des élements suivant l'élément choisi
        }
        return newtab; //Retourne donc la liste sans l'élément
    }

    static String[][] supprElementParIndice(String[][] tab, int indice) {
        for (int i = 0; i < tab.length; i++) {
            tab[i] = supprElementParIndice(tab[i], indice);
        }
        return tab;
    }

    static String[] ajoutElementALaFin(String[] tab, String s) { //Ajoute un élément à la fin d'un tableau
        String[] newtab = new String[tab.length + 1]; //Creation d'un tableau plus grand de 1

        for (int i = 0; i < tab.length; i++) {
            newtab[i] = tab[i]; //Copie du tableau entier
        }
        newtab[newtab.length - 1] = s; //Ajout de l'élément choisi

        return newtab; //Retourne la liste avec l'élément en plus
    }

    static String motAleatoire(String[] tab) {
        int random = genererUnEntier(tab.length);
        return tab[random - 1];
    }

    static boolean estDansLaListe(String[] tab, String s) {
        for (int i = 0; i < tab.length; i++) {
            if (tab[i] == s) {
                return true;
            }
        }
        return false;

    }

    static boolean estComprisEntre(int nombre, int limite1, int limite2) {
        if (limite1 < limite2) { //Si la limite1 est plus petite
            return limite1 <= nombre && nombre <= limite2; //On verif si le nombre est compris entre les deux
        } else //Si la limite2 est plus grande
        {
            return limite2 <= nombre && nombre <= limite2; //On verif si le nombre est compris entre les deux
        }
    }

    static int genererUnEntier(int max) {
        return (int) (Math.random() * max + 1);
    }

    public static void controleSaisie() {
        String memo = "";
        do // Boucle qui gere le controle de la saisie
        {
            System.out.println("Saisir la touche entrée une fois le chiffre mémoriser");
            memo = Console.lireString();   // Variable qui va stocker la saisie de l'utilisateur
            if (!memo.equals("")) {
                System.out.println("La saisie est incorrect");  // Affichage lorsque la saisie n'est pas valide
            }
        } while (!memo.equals(""));   // Condition de la boucle / tant que la saisie n'est pas égal a ""
    }   // Méthode qui vérifie la saisie de l'utilisateur

    static int[] creerStats() { //Creation des statistiques
        int[] stats = {
            0, //[0]Nombre de partie jouees au total

            0, //[1]Nombre de partie jouees au jeu 1 toutes diffcultés confondues
            0, //[2]Nombre de partie jouees au jeu 1 niveau 1
            0, //[3]Nombre de partie jouees au jeu 1 niveau 2
            0, //[4]Nombre de partie jouees au jeu 1 niveau 3

            0, //[5]Nombre de partie jouees au jeu 2 toutes difficultés confondues
            0, //[6]Nombre de partie jouees au jeu 2 niveau 1
            0, //[7]Nombre de partie jouees au jeu 2 niveau 2
            0, //[8]Nombre de partie jouees au jeu 2 niveau 3

            0, //[9]Nombre de partie jouees au jeu 3 toutes difficultés confondues
            0, //[10]Nombre de partie jouees au jeu 3 niveau 1
            0, //[11]Nombre de partie jouees au jeu 3 niveau 2
            0, //[12]Nombre de partie jouees au jeu 3 niveau 3

            0, //[13]Meilleur score jeu 1 niveau 1
            0, //[14]Moyenne score jeu 1 niveau 1
            0, //[15]Meilleur score jeu 1 niveau 2
            0, //[16]Moyenne score jeu 1 niveau 2
            0, //[17]Meilleur score jeu 1 niveau 3
            0, //[18]Moyenne score jeu 1 niveau 3

            0, //[19]Meilleur score jeu 2 niveau 1
            0, //[20]Moyenne score jeu 2 niveau 1
            0, //[21]Meilleur score jeu 2 niveau 2
            0, //[22]Moyenne score jeu 2 niveau 2
            0, //[23]Meilleur score jeu 2 niveau 3
            0, //[24]Moyenne score jeu 2 niveau 3

            0, //[25]Meilleur score jeu 3 niveau 1
            0, //[26]Moyenne score jeu 3 niveau 1
            0, //[27]Meilleur score jeu 3 niveau 2
            0, //[28]Moyenne score jeu 3 niveau 2
            0, //[29]Meilleur score jeu 3 niveau 3
            0, //[30]Moyenne score jeu 3 niveau 3

            0, //[31]Total des scores jeu 1 niveau 1 (pour moyennes)
            0, //[32]Total des scores jeu 1 niveau 2 (pour moyennes)
            0, //[33]Total des scores jeu 1 niveau 3 (pour moyennes)

            0, //[34]Total des scores jeu 2 niveau 1 (pour moyennes)
            0, //[35]Total des scores jeu 2 niveau 2 (pour moyennes)
            0, //[36]Total des scores jeu 2 niveau 3 (pour moyennes)

            0, //[37]Total des scores jeu 3 niveau 1 (pour moyennes)
            0, //[38]Total des scores jeu 3 niveau 2 (pour moyennes)
            0 //[39]Total des scores jeu 3 niveau 3 (pour moyennes)
        };

        return stats;

    }

    static String[] creerNomStats() {
        String[] nomstats = {
            "Nombre de partie jouees au total",
            "Nombre de partie jouees au jeu 1 toutes diffcultés confondues",
            "Nombre de partie jouees au jeu 1 niveau 1",
            "Nombre de partie jouees au jeu 1 niveau 2",
            "Nombre de partie jouees au jeu 1 niveau 3",
            "Nombre de partie jouees au jeu 2 toutes difficultés confondues",
            "Nombre de partie jouees au jeu 2 niveau 1",
            "Nombre de partie jouees au jeu 2 niveau 2",
            "Nombre de partie jouees au jeu 2 niveau 3",
            "Nombre de partie jouees au jeu 3 toutes difficultés confondues",
            "Nombre de partie jouees au jeu 3 niveau 1",
            "Nombre de partie jouees au jeu 3 niveau 2",
            "Nombre de partie jouees au jeu 3 niveau 3",
            "Meilleur score jeu 1 niveau 1",
            "Moyenne score jeu 1 niveau 1",
            "Meilleur score jeu 1 niveau 2",
            "Moyenne score jeu 1 niveau 2",
            "Meilleur score jeu 1 niveau 3",
            "Moyenne score jeu 1 niveau 3",
            "Meilleur score jeu 2 niveau 1",
            "Moyenne score jeu 2 niveau 1",
            "Meilleur score jeu 2 niveau 2",
            "Moyenne score jeu 2 niveau 2",
            "Meilleur score jeu 2 niveau 3",
            "Moyenne score jeu 2 niveau 3",
            "Meilleur score jeu 3 niveau 1",
            "Moyenne score jeu 3 niveau 1",
            "Meilleur score jeu 3 niveau 2",
            "Moyenne score jeu 3 niveau 2",
            "Meilleur score jeu 3 niveau 3",
            "Moyenne score jeu 3 niveau 3"
        };

        return nomstats;
    }

    static void calculMoyenneStats(int[] stats) {

        try {
            stats[14] = stats[31] / stats[2];
        } catch (ArithmeticException e) {
        }

        try {
            stats[16] = stats[32] / stats[3];
        } catch (ArithmeticException e) {
        }
        try {
            stats[18] = stats[33] / stats[4];
        } catch (ArithmeticException e) {
        }

        try {
            stats[20] = stats[34] / stats[6];
        } catch (ArithmeticException e) {
        }
        try {
            stats[22] = stats[35] / stats[7];
        } catch (ArithmeticException e) {
        }
        try {
            stats[24] = stats[36] / stats[8];
        } catch (ArithmeticException e) {
        }

        try {
            stats[26] = stats[37] / stats[10];
        } catch (ArithmeticException e) {
        }
        try {
            stats[28] = stats[38] / stats[11];
        } catch (ArithmeticException e) {
        }
        try {
            stats[30] = stats[39] / stats[12];
        } catch (ArithmeticException e) {
        }

    }

    static void menuPrincipal(String[] listeDeMots, int[] stats, String[] nomstats) {
        int choixMenu;

        do {
            supprConsole();
            System.out.println("+==========================+"); //Affichage du Menu
            System.out.println("||     Menu Principal     ||");
            System.out.println("+==========================+");
            System.out.println("+==========================+");
            System.out.println("||   1 : Menu du jeu 1    ||");
            System.out.println("+==========================+");
            System.out.println("||   2 : Menu du jeu 2    ||");
            System.out.println("+==========================+");
            System.out.println("||   3 : Menu du jeu 3    ||");
            System.out.println("+==========================+");
            System.out.println("||   4 : Statistiques     ||");
            System.out.println("+==========================+");
            System.out.println("||   5 : Sortir du jeu    ||");
            System.out.println("+==========================+");

            do {
                System.out.println("Saisie un chiffre ");
                choixMenu = Console.lireInt();
                if (!estComprisEntre(choixMenu, 1, 5)) {
                    System.out.println("La saisie est incorrect");
                }
            } while (!estComprisEntre(choixMenu, 1, 5)); //Vérification de saisie.

            switch (choixMenu) {
                case 1 -> {
                    System.out.println("Jeu 1");
                    menuJeu1(listeDeMots, stats); //Lancement Jeu 1
                }
                case 2 -> {
                    System.out.println("Jeu 2");
                    menuJeu2(stats); //Lancement Jeu 2
                }
                case 3 -> {
                    System.out.println("Jeu 3");
                    menuJeu3(listeDeMots, stats); //Lancement Jeu 3
                }
                case 4 -> {
                    System.out.println("Statistiques");
                    menuStatistiques(stats, nomstats); //Affichage des Statistiques
                }
                case 5 ->
                    System.out.println("Vous sortez du jeu");
            }
        } while (choixMenu != 5);
        /*Cette boucle permet de terminer le programme
                                  lorsque l'utilisateur le souhaite */
    }

    static void menuJeu1(String[] listeDeMots, int[] stats) {

        supprConsole();
        System.out.println("+==========================+"); //Affichage du menu
        System.out.println("||     Menu du jeu 1      ||");
        System.out.println("+==========================+");
        System.out.println("+==========================+");
        System.out.println("||      1 : Niveau 1      ||");
        System.out.println("+==========================+");
        System.out.println("||      2 : Niveau 2      ||");
        System.out.println("+==========================+");
        System.out.println("||      3 : Niveau 3      ||");
        System.out.println("+==========================+");
        System.out.println("||       4 : Sortir       ||");
        System.out.println("+==========================+");

        int choixMenu;
        do {
            System.out.println("Saisie un chiffre ");
            choixMenu = Console.lireInt();
            if (!estComprisEntre(choixMenu, 1, 4)) {
                System.out.println("La saisie est incorrect");
            }
        } while (!estComprisEntre(choixMenu, 1, 4)); //Vérification de saisie

        switch (choixMenu) {
            case 1,2,3 -> {
                System.out.println("Vous allez commencer a jouer");
                startJeu1(choixMenu, listeDeMots, stats);
            }
            //----> aller au code du jeu 1
            case 4 -> {
                System.out.println("Vous allez sortir du jeu");
                return; //Retourne au Menu Principal
            }
        }
    }

    static void menuJeu2(int[] stats) {

        supprConsole();
        System.out.println("+==========================+"); //Affichage du menu
        System.out.println("||     Menu du jeu 2      ||");
        System.out.println("+==========================+");
        System.out.println("+==========================+");
        System.out.println("||      1 : Niveau 1      ||");
        System.out.println("+==========================+");
        System.out.println("||      2 : Niveau 2      ||");
        System.out.println("+==========================+");
        System.out.println("||      3 : Niveau 3      ||");
        System.out.println("+==========================+");
        System.out.println("||       4 : Sortir       ||");
        System.out.println("+==========================+");

        int choixMenu;
        do {
            System.out.println("Saisie un chiffre ");
            choixMenu = Console.lireInt();
            if (!estComprisEntre(choixMenu, 1, 4)) {
                System.out.println("La saisie est incorrect");
            }
        } while (!estComprisEntre(choixMenu, 1, 4)); //Vérification de saisie

        switch (choixMenu) {
            case 1,2,3 -> {
                System.out.println("Vous allez commencer a jouer");
                startJeu2(choixMenu, stats);
            }
            //----> aller au code du jeu 1
            case 4 -> {
                System.out.println("Vous allez sortir du jeu");
                return; //Retourne au Menu Principal
            }
        }
    }

    static void menuJeu3(String[] listeDeMots, int[] stats) {

        supprConsole();
        System.out.println("+==========================+"); //Affichage du menu
        System.out.println("||     Menu du jeu 3      ||");
        System.out.println("+==========================+");
        System.out.println("+==========================+");
        System.out.println("||      1 : Niveau 1      ||");
        System.out.println("+==========================+");
        System.out.println("||      2 : Niveau 2      ||");
        System.out.println("+==========================+");
        System.out.println("||      3 : Niveau 3      ||");
        System.out.println("+==========================+");
        System.out.println("||       4 : Sortir       ||");
        System.out.println("+==========================+");

        int choixMenu;
        do {
            System.out.println("Saisie un chiffre ");
            choixMenu = Console.lireInt();
            if (!estComprisEntre(choixMenu, 1, 4)) {
                System.out.println("La saisie est incorrect");
            }
        } while (!estComprisEntre(choixMenu, 1, 4)); //Vérification de saisie

        switch (choixMenu) {
            case 1,2,3 -> {
                System.out.println("Vous allez commencer a jouer");
                startJeu3(choixMenu, listeDeMots, stats);
            }
            //----> aller au code du jeu 1
            case 4 -> {
                System.out.println("Vous allez sortir du jeu");
                return; //Retourne au Menu Principal
            }
        }
    }

    static void menuStatistiques(int[] stats, String[] nomstats) {

        supprConsole();
        calculMoyenneStats(stats);
        System.out.println("+===========================+"); //Affichage des stats
        System.out.println("||      Statistiques       ||");
        System.out.println("+===========================+\n");

        System.out.println("Total Parties Jouées : " + stats[0] + "\n");

        System.out.println("----- Jeu 1 ----- (" + stats[1] + " parties jouées)\n");
        System.out.println("Niveau 1 - Parties jouées : " + stats[2] + " / Meilleur score : " + stats[13] + " / Moyenne score : " + stats[14]);
        System.out.println("Niveau 2 - Parties jouées : " + stats[3] + " / Meilleur score : " + stats[15] + " / Moyenne score : " + stats[17]);
        System.out.println("Niveau 3 - Parties jouées : " + stats[4] + " / Meilleur score : " + stats[17] + " / Moyenne score : " + stats[18] + "\n");

        System.out.println("----- Jeu 2 ----- (" + stats[5] + " parties jouées)\n");
        System.out.println("Niveau 1 - Parties jouées : " + stats[6] + " / Meilleur score : " + stats[19] + " / Moyenne score : " + stats[20]);
        System.out.println("Niveau 2 - Parties jouées : " + stats[7] + " / Meilleur score : " + stats[21] + " / Moyenne score : " + stats[22]);
        System.out.println("Niveau 3 - Parties jouées : " + stats[8] + " / Meilleur score : " + stats[23] + " / Moyenne score : " + stats[24] + "\n");

        System.out.println("----- Jeu 3 ----- (" + stats[9] + " parties jouées)\n");
        System.out.println("Niveau 1 - Parties jouées : " + stats[10] + " / Meilleur score : " + stats[25] + " / Moyenne score : " + stats[26]);
        System.out.println("Niveau 2 - Parties jouées : " + stats[11] + " / Meilleur score : " + stats[27] + " / Moyenne score : " + stats[28]);
        System.out.println("Niveau 3 - Parties jouées : " + stats[12] + " / Meilleur score : " + stats[29] + " / Moyenne score : " + stats[30] + "\n");

        System.out.println("+===========================+");
        System.out.println("||       1 : Sortir        ||");
        System.out.println("+===========================+");

        int choixMenu;
        do {
            System.out.println("Saisie un chiffre ");
            choixMenu = Console.lireInt();
            if (!(choixMenu == 1 || choixMenu == 666)) {
                System.out.println("La saisie est incorrect");
            }
        } while (!(choixMenu == 1 || choixMenu == 666));

        System.out.println(choixMenu);

        switch (choixMenu) {
            case 1 -> {
                System.out.println("Vous allez sortir des paramètres");
                return;
            }
            case 666 ->
                _34ST3R_3GG();
        }
    }

    static boolean recommencer(int niveau_actuel) { //Permet de recommencer ou non un niveau.
        supprConsole();

        System.out.println("Voulez vous recommencer le niveau " + niveau_actuel + ".(Tapez \"oui\" ou \"non\")");
        String rep = "oui";
        do {
            if ((!rep.equals("oui")) && (!rep.equals("non"))) {
                System.out.println("Veuillez ecrire \"oui\" ou \"non\".");
            }

            rep = Console.lireString().toLowerCase();

        } while ((!rep.equals("oui")) && (!rep.equals("non")));

        if (rep.equals("oui")) {
            return true;
        } else {
            return false;
        }
    }

    static void startJeu1(int niveau, String[] listeDeMots, int[] stats) { //Lance le niveau choisi
        boolean restart = false;
        do {
            switch (niveau) {
                case 1 ->
                    jeu1Niveau1(listeDeMots, stats); //Lance le niveau 1
                case 2 ->
                    jeu1Niveau2(listeDeMots, stats); //Lance le niveau 2
                case 3 ->
                    jeu1Niveau3(listeDeMots, stats); //Lance le niveau 3
            }

            stats[1] += 1;

            restart = recommencer(niveau);

        } while (restart);
    }

    static void jeu1Niveau1(String[] listeDeMots, int[] stats) { //Initialisation des param de difficulté
        int nombreDeMots = 5;
        int longueurMotMin = 3;
        int longueurMotMax = 6;
        corpsJeu1(listeDeMots, stats, nombreDeMots, longueurMotMin, longueurMotMax);
        stats[2] += 1;
    }

    static void jeu1Niveau2(String[] listeDeMots, int[] stats) { //Initialisation des param de difficulté
        int nombreDeMots = 10;
        int longueurMotMin = 3;
        int longueurMotMax = 6;
        corpsJeu1(listeDeMots, stats, nombreDeMots, longueurMotMin, longueurMotMax);
        stats[3] += 1;
    }

    static void jeu1Niveau3(String[] listeDeMots, int[] stats) { //Initialisation des param de difficulté
        int nombreDeMots = 15;
        int longueurMotMin = 3;
        int longueurMotMax = 6;
        corpsJeu1(listeDeMots, stats, nombreDeMots, longueurMotMin, longueurMotMax);
        stats[4] += 1;
    }

    static String[] creationListeMotsJeu1(String[] listeDeMots, int nbMots, int longueurMotMin, int longueurMotMax) {

        String[] listeMotsJeu1 = new String[nbMots];
        String[] listeSelonLgr = listeMotSelonLettres(listeDeMots, longueurMotMin, longueurMotMax);

        String s;

        for (int i = 0; i < nbMots; i++) {
            do {
                s = motAleatoire(listeSelonLgr);

            } while (estDansLaListe(listeMotsJeu1, s));

            listeMotsJeu1[i] = s;
        }

        return listeMotsJeu1;

    }

    static void afficherListeMots(String[] tab) {

        supprConsole();
        System.out.println("+=================================+");
        System.out.println("||      Affichage des Mots       ||");
        System.out.println("+=================================+");

        for (int i = 0; i < tab.length; i++) {
            System.out.println(tab[i]); //affichage des mots un par un.
        }
        System.out.println("");
    }

    static void corpsJeu1(String[] listeDeMots, int[] stats, int nombreDeMots, int longueurMotMin, int longueurMotMax) {

        String[] listeMotsJeu1 = creationListeMotsJeu1(listeDeMots, nombreDeMots, longueurMotMin, longueurMotMax);

        supprConsole();

        int score = 0;

        boolean haveFail = false;
        boolean isWin = false;

        String s;

        for (int i = 0; i < nombreDeMots; i++) {
            System.out.println("Memorisez ce(s) mot(s) ! Appuyez sur une touche quand c'est bon !");
            for (int j = 0; j <= i; j++) {
                System.out.print(listeMotsJeu1[j] + " / ");
            }

            Console.lireInt();

            supprConsole();

            System.out.println("Restituer les mots (appuyer sur entrée à la fin de chaque mot).");

            for (int j = 0; j <= i; j++) {
                s = Console.lireString().toLowerCase();
                if (!s.equals(listeMotsJeu1[j])) {
                    System.out.println("Mauvais mot !");
                    haveFail = true;
                    break;
                } else {
                    System.out.println("Mot Correct");
                }
                if (j == nombreDeMots - 1) {
                    isWin = true;
                }
            }

            if (haveFail) {
                break;
            }

            score += 1;
        }

        if (isWin) {
            System.out.println("\n Vous etes arrive au bout !\n");
        }
        Timer.attendreSecondes(1);
        System.out.println("Vous avez restitue " + score + " mots !");

        if (nombreDeMots == 5) {
            stats[31] += score;
            if (stats[13] < score) {
                stats[13] = score;
            }
        }
        if (nombreDeMots == 10) {
            stats[32] += score;
            if (stats[15] < score) {
                stats[15] = score;
            }
        }
        if (nombreDeMots == 15) {
            stats[33] += score;
            if (stats[17] < score) {
                stats[17] = score;
            }
        }

        Timer.attendreSecondes(3);
    }

    static void startJeu2(int niveau, int[] stats) { //Lance le niveau choisi
        boolean restart = false;
        do {
            switch (niveau) {
                case 1 ->
                    jeu2Niveau1(stats); //Lance le niveau 1
                case 2 ->
                    jeu2Niveau2(stats); //Lance le niveau 2
                case 3 ->
                    jeu2Niveau3(stats); //Lance le niveau 3
            }

            stats[5] += 1;

            restart = recommencer(niveau);

        } while (restart);
    }

    static void jeu2Niveau1(int[] stats) { //Initialisation des param de difficulté
        int nombreDeNombres = 5;
        int nombreMax = 10;
        corpsJeu2(nombreDeNombres, nombreMax, stats);
        stats[6] += 1;
    }

    static void jeu2Niveau2(int[] stats) { //Initialisation des param de difficulté
        int nombreDeNombres = 10;
        int nombreMax = 100;
        corpsJeu2(nombreDeNombres, nombreMax, stats);
        stats[7] += 1;
    }

    static void jeu2Niveau3(int[] stats) { //Initialisation des param de difficulté
        int nombreDeNombres = 15;
        int nombreMax = 1000;
        corpsJeu2(nombreDeNombres, nombreMax, stats);
        stats[8] += 1;
    }

    static void corpsJeu2(int nombreDeNombres, int nombreMax, int[] stats) {

        int tab[] = new int[nombreDeNombres];
        for (int i = 0; i < tab.length; i++) {
            boolean verifDoublon = false;
            do {
                verifDoublon = false;
                tab[i] = genererUnEntier(nombreMax);
                for (int j = 0; j < i; j++) {
                    if (tab[i] == tab[j]) {
                        verifDoublon = true;
                    }
                }
            } while (verifDoublon);
        }
        int[] tabverif = new int[nombreDeNombres];
        for (int y = 0; y < tab.length; y++) {
            supprConsole();
            System.out.println("Les chiffre sont : ");
            for (int x = 0; x <= y; x++) {
                System.out.println(tab[x]);
            }
            controleSaisie();
            supprConsole();
            for (int a = 0; a <= y; a++) {
                System.out.print("Saisir le chiffre numéro " + a + " mémoriser : ");
                tabverif[a] = Console.lireInt();
                if (tab[a] != tabverif[a]) {
                    System.out.println("Vous avez mémoriser " + y + " chiffres");
                    if (nombreDeNombres == 5) {
                        stats[34] += y;
                        if (stats[19] < y) {
                            stats[19] = y;
                        }
                    }
                    if (nombreDeNombres == 10) {
                        stats[35] += y;
                        if (stats[21] < y) {
                            stats[21] = y;
                        }
                    }
                    if (nombreDeNombres == 15) {
                        stats[36] += y;
                        if (stats[23] < y) {
                            stats[23] = y;
                        }
                    }
                    return;
                }
            }
        }

        supprConsole();

    }

    static void startJeu3(int niveau, String[] listeDeMots, int[] stats) { //Lance le niveau choisi

        boolean restart = false;
        do {
            switch (niveau) {
                case 1 ->
                    jeu3Niveau1(listeDeMots, stats); //Lance le niveau 1
                case 2 ->
                    jeu3Niveau2(listeDeMots, stats); //Lance le niveau 2
                case 3 ->
                    jeu3Niveau3(listeDeMots, stats); //Lance le niveau 3
            }

            stats[9] += 1;

            restart = recommencer(niveau);

        } while (restart);
    }

    static void jeu3Niveau1(String[] listeDeMots, int[] stats) { //Initialisation des param de difficulté
        int nombreDePaires = 5;
        int longueurMotMin = 3;
        int longueurMotMax = 6;
        corpsJeu3(listeDeMots, stats, nombreDePaires, longueurMotMin, longueurMotMax);
        stats[10] += 1;
    }

    static void jeu3Niveau2(String[] listeDeMots, int[] stats) { //Initialisation des param de difficulté
        int nombreDePaires = 10;
        int longueurMotMin = 5;
        int longueurMotMax = 8;
        corpsJeu3(listeDeMots, stats, nombreDePaires, longueurMotMin, longueurMotMax);
        stats[11] += 1;
    }

    static void jeu3Niveau3(String[] listeDeMots, int[] stats) { //Initialisation des param de difficulté
        int nombreDePaires = 15;
        int longueurMotMin = 7;
        int longueurMotMax = 11;
        corpsJeu3(listeDeMots, stats, nombreDePaires, longueurMotMin, longueurMotMax);
        stats[12] += 1;
    }

    static String[][] creationListePaires(String[] listeDeMots, int nombreDePaires, int longueurMotMin, int longueurMotMax) {
        //creation d'une liste de paires pour le jeu 3
        String[][] listeDePaires = new String[2][nombreDePaires];

        String[] listeSelonLgr = listeMotSelonLettres(listeDeMots, longueurMotMin, longueurMotMax);

        String s;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < nombreDePaires; j++) {
                do {
                    s = motAleatoire(listeSelonLgr);

                } while (estDansLaListe(listeDePaires[0], s)
                        || estDansLaListe(listeDePaires[1], s));

                listeDePaires[i][j] = s;
            }

        }

        return listeDePaires;
    }

    static void afficherListePaires(String[][] tab) {

        supprConsole();
        System.out.println("+=================================+");
        System.out.println("||     Affichage des Paires      ||");
        System.out.println("+=================================+");

        for (int i = 0; i < tab[0].length; i++) {
            System.out.println(tab[0][i] + " / " + tab[1][i]); //affichage des paires une par une.
        }
        System.out.println("");
    }

    static void corpsJeu3(String[] listeDeMots, int[] stats, int nombreDePaires, int longueurMotMin, int longueurMotMax) {

        String[][] listePaires = creationListePaires(listeDeMots, nombreDePaires, longueurMotMin, longueurMotMax);

        afficherListePaires(listePaires);

        System.out.println("");
        System.out.println("Mémorisez les paires ! \n\n Appuyez sur Entrée lorsque que vous etes pret !");

        Console.lireString();

        supprConsole();

        int score = 0;

        boolean isWin = false;

        for (int i = 0; i < nombreDePaires; i++) {
            int random = genererUnEntier(listePaires[0].length) - 1;

            System.out.print("Complétez la paire : " + listePaires[0][random] + " / ");
            String proposition = Console.lireString();
            if (!proposition.equals(listePaires[1][random])) {
                System.out.println("Mauvais mot\n");
                break;
            } else {
                System.out.println("Bien joué");
                score += 1;
                listePaires = supprElementParIndice(listePaires, random);
            }
            if (i == nombreDePaires - 1) {
                isWin = true;
            }
        }
        if (isWin) {
            System.out.println("\n Vous etes arrive au bout !\n");
        }
        Timer.attendreSecondes(1);
        System.out.println("Vous avez restitue " + score + " paires !");

        if (nombreDePaires == 5) {
            stats[37] += score;
            if (stats[25] < score) {
                stats[25] = score;
            }
        }
        if (nombreDePaires == 10) {
            stats[38] += score;
            if (stats[27] < score) {
                stats[27] = score;
            }
        }
        if (nombreDePaires == 15) {
            stats[39] += score;
            if (stats[29] < score) {
                stats[29] = score;
            }
        }

        Timer.attendreSecondes(3);

    }

    static void _34ST3R_3GG() {
        supprConsole();
        System.out.println("                    ____     ____\n" + "                  /'    |   |    \\\n" + "                /    /  |   | \\   \\\n" + "              /    / |  |   |  \\   \\\n" + "             (   /   |  \"\"\"\"   |\\   \\ \n" + "             | /   / /^\\    /^\\  \\  _|\n" + "              ~   | |   |  |   | | ~\n" + "                  | |__O|__|O__| |\n" + "                /~~      \\/     ~~\\\n" + "               /   (      |      )  \\\n" + "         _--_  /,   \\____/^\\___/'   \\  _--_\n" + "       /~    ~\\ / -____-|_|_|-____-\\ /~    ~\\\n" + "     /________|___/~~~~\\___/~~~~\\ __|________\\\n" + "--~~~          ^ |     |   |     |  -     :  ~~~~~:~-_     ___-----~~~~~~~~|\n" + "   /             `^-^-^'   `^-^-^'                  :  ~\\ /'   ____/--------|\n" + "       --                                            ;   |/~~~------~~~~~~~~~|\n" + " ;                                    :              :    |----------/--------|\n" + ":                     ,                           ;    .  |---\\\\--------------|\n" + " :     -                          .                  : : |______________-__|\n" + "  :              ,                 ,                :   /'~----___________|\n" + "__  \\\\\\        ^                          ,, ;; ;; ;._-~\n" + "  ~~~-----____________________________________----~~~");
        Timer.attendreSecondes(5);
    }

    static void supprConsole() {
        for (int i = 0; i < 66; i++) {
            System.out.println("");
        }
    }

    public static void main(String[] args) { //Méthode main

        //ecranChargement();
        String text = recupText("./Extrait_texte.txt"); //Ceci fonctionne quand on execute avec Windows PowerShell
        if (text.equals("ERROR")) {
            text = recupText("src/Extrait_texte.txt");  //Ceci fonctionne avec Netbeans
            if (text.equals("ERROR")) {
                return;
            }
        } //arrêt du programme en cas d'erreur de lecture.

        String[] listeDeMots = recupListeDeMots(text); //Transforme le texte en tableau de mots.

        int[] stats = creerStats(); //Création des statistiques
        String[] nomstats = creerNomStats(); //Création des noms des statistiques

        menuPrincipal(listeDeMots, stats, nomstats); //Lance le menu principal.
    }
}
