/* La classe Console fourni des méthodes permettant de lire des données au clavier
Méthode lireString () --> Lecture d'une chaine
Méthode lireFloat () --> Lecture d'une variable de type float
Méthode lireDouble () --> Lecture d'une variable de type double
Méthode lireInt () --> Lecture d'une variable de type int
Méthode lireLong () --> Lecture d'une variable de type long
Méthode lireShort () --> Lecture d'une variable de type short
Méthode lirebyte () --> Lecture d'une variable de type byte
Méthode lireChar () --> Lecture d'une variable de type char

 */
import java.io.* ;

public class Console{

public static boolean enErreur=false;	
	
// classe fournissant des fonctions de lecture au clavier -
 public static String lireString ()   // lecture d'une chaine
    { String ligne_lue = null ;
		enErreur=false;
        try
        { InputStreamReader lecteur = new InputStreamReader (System.in) ;
            BufferedReader entree = new BufferedReader (lecteur) ;
            ligne_lue = entree.readLine() ;
        }
        catch (IOException err)
        { enErreur=true;//System.exit(0) ;
        }
        return ligne_lue ;
    }

    public static float lireFloat ()   // lecture d'un float
    { float x=0 ;   // valeur a lire
		enErreur=false;
        try
        { String ligne_lue = lireString() ;
            x = Float.parseFloat(ligne_lue) ;
        }
        catch (NumberFormatException err)
        { System.out.println ("*** Erreur de donnee ***") ;
            enErreur=true;//System.exit(0) ;
        }
        return x ;
    }

    public static double lireDouble ()   // lecture d'un double
    { double x=0 ;   // valeur a lire
		enErreur=false;
        try
        { String ligne_lue = lireString() ;
            x = Double.parseDouble(ligne_lue) ;
        }
        catch (NumberFormatException err)
        { System.out.println ("*** Erreur de donnee ***") ;
            enErreur=true;//System.exit(0) ;
        }
        return x ;
    }

    public static int lireInt ()         // lecture d'un int
    { int n=0 ;   // valeur a lire
		enErreur=false;
        try
        { String ligne_lue = lireString() ;
            n = Integer.parseInt(ligne_lue) ;
        }
        catch (NumberFormatException err)
        { System.out.println ("*** Erreur de donnee ***") ;
            enErreur=true;//System.exit(0) ;
        }
        return n ;
    }

    public static long lireLong ()         // lecture d'un long
    { long l=0 ;   // valeur a lire
        enErreur=false;
		try		
        { String ligne_lue = lireString() ;
            l = Long.parseLong(ligne_lue) ;
        }
        catch (NumberFormatException err)
        { System.out.println ("*** Erreur de donnee ***") ;
            enErreur=true;//System.exit(0) ;
        }
        return l ;
    }

    public static short lireShort ()         // lecture d'un short
    { short s=0 ;   // valeur a lire
        enErreur=false;
		try
        { String ligne_lue = lireString() ;
            s = Short.parseShort(ligne_lue) ;
        }
        catch (NumberFormatException err)
        { System.out.println ("*** Erreur de donnee ***") ;
          enErreur=true;//System.exit(0) ;
        }
        return s ;
    }

    public static byte lireByte ()         // lecture d'un byte
    { byte b=0 ;   // valeur a lire
		enErreur=false;
        try
        { String ligne_lue = lireString() ;
            b = Byte.parseByte(ligne_lue) ;
        }
        catch (NumberFormatException err)
        { System.out.println ("*** Erreur de donnee ***") ;
            enErreur=true;//System.exit(0) ;
        }
        return b ;
    }

    public static char lireChar ()         // lecture d'un char
    { char c='\0';   // valeur a lire
        enErreur=false;
		try
        { String ligne_lue = lireString() ;
            c = ligne_lue.charAt(0);
        }
        catch (NumberFormatException err)
        { System.out.println ("*** Erreur de donnee ***") ;
            enErreur=true;//System.exit(0) ;
        }
        return c ;
    }
}

