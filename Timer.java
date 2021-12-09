/**
 * Classe Timer
 * @author Hervé Martinez
 * modified by Maxime BOUDIER long => double
 */

public class Timer {

    /**
     * Méthode de classe permettant de faire une pause en minutes
     * @param minutes nombre de minutes pendant lesquelles le programme attend
     * @return
     */
    public static boolean attendreMinutes(long minutes) {

        return attendreSecondes(minutes *60);
    }

    /**
     *  Méthode de classe permettant de faire une pause en secondes
     * @param secondes nombre de secondes pendant lesquelles le programme attend
     * @return
     */
    public static boolean attendreSecondes(long secondes) {
        return attendreMilli(secondes * 1000);
    }

    public static boolean attendreMilli(long milli){
        try {
            Thread.sleep(milli);
            return true;
        } catch (InterruptedException ex) {
            return false;

        }
    }
}


