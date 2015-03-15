package nfa035.tp2;

/** On représente une date avec un tableau de 3 entiers.
 * <ul>
 * <li>case 0 : numéro du jour
 * <li>case 1 : numéro du mois
 * <li>case 2 : numéro de l'année
 * </ul>
 *
 * @author rosmord */
public class Dates {

    /** Retourne le nom du mois numéro numero.
     *
     * @param numero
     *            le numéro du mois (commence à  1)
     * @return le nom du mois.
     * @throws IllegalArgumentException
     *             si mois n'est pas entre 1 et 12. */
    public static String nomMois(final int numero) {
        if ((numero < 1) || (numero > 12)) {
            throw new IllegalArgumentException();
        }
        final String[] mois = new String[] { "janvier", "février", "mars", "avril", "mai", "juin", "juillet", "aout",
                "septembre", "octobre", "novembre", "décembre" };

        return mois[numero - 1];
    }

    /** Renvoie vrai si une année est bissextile.
     *
     * @param annee
     * @return */
    public static boolean estBissextile(final int annee) {
        final boolean bissextile;
        if ((annee % 4) == 0) {
            if ((annee % 100) == 0) {
                bissextile = (annee % 400) == 0;
            } else {
                bissextile = true;
            }
        } else {
            bissextile = false;
        }
        return bissextile;

    }

    /** retourne la représentation de la date sous forme de String.
     *
     * @param date
     * @return
     * @throws IllegalArgumentException
     *             si la date n'est pas correcte. */
    public static String commeString(final int[] date) {
        if (!estDateCorrecte(date)) {
            throw new IllegalArgumentException();
        }
        return date[0] + " " + nomMois(date[1]) + " " + date[2];
    }

    /** Retourne vrai si une date est correcte (Ã  vous de préciser ce que Ã§a signifie).
     *
     * @param date
     * @return vrai si les trois paramÃ¨tres correspondent Ã  une vraie date. */
    public static boolean estDateCorrecte(final int[] date) {
        boolean formatOK = false;
        if ((date[1] >= 1) && (date[1] <= 12) && (date[0] >= 0) && (date[0] <= nombreDeJoursDansMois(date[1], date[2]))) {
            formatOK = true;
        }
        return formatOK;
    }

    /** Retourne le nombre de jour dans un mois pour une année donnée.
     *
     * @param mois
     * @param annee
     * @return
     * @throws IllegalArgumentException
     *             si le numéro du mois n'est pas correct. */
    public static int nombreDeJoursDansMois(final int mois, final int annee) {
        if ((mois < 1) || (mois > 12)) {
            throw new IllegalArgumentException();
        }

        int[] tabjours = new int[12];

        if (estBissextile(annee)) {
            tabjours = new int[] { 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        } else {
            tabjours = new int[] { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        }
        return tabjours[mois - 1];
    }

    /** Retourne la date du jour suivant une date donnée
     *
     * @param date
     *            une date
     * @return la date du jour suivant.
     * @throws IllegalArgumentException
     *             si la date n'est pas correcte. */
    public static int[] dateSuivante(final int[] date) {
        if (!estDateCorrecte(date)) {
            throw new IllegalArgumentException();
        }

        int jour = date[0] + 1;
        int mois = date[1];
        int annee = date[2];

        if (jour > nombreDeJoursDansMois(date[1], date[2])) {
            jour = 1;
            mois = date[1] + 1;
        }
        if (mois > 12) {
            mois = 1;
            annee = annee + 1;
        }

        return new int[] { jour, mois, annee };
    }
}
