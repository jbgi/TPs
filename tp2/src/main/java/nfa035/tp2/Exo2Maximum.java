package nfa035.tp2;

public class Exo2Maximum {
    /**
     * Renvoie le maximum d'un tableau.
     *
     * @param tab
     *            un tableau non vide
     * @return le maximum d'un tableau
     * @throws IllegalArgumentException
     *             si le tableau est vide
     * @throws NullPointerException
     *             si le tab vaut null
     */
    public static double maximum(final double[] tab) {
        if (tab.length == 0) {
            throw new IllegalArgumentException("tableau vide");
        }
        double m = tab[0];
        for (int i = 1; i < tab.length; i++) {
            if (tab[i] > m) {
                m = tab[i];
            }
        }
        return m;
    }

    public static double maximum(final double premierElement, final double[] elementSuivants) {
        double m = premierElement;
        for (final double d : elementSuivants) {
            if (d > m) {
                m = d;
            }
        }
        return m;
    }
}
