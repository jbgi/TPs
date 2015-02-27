package utils.io;

import java.util.stream.IntStream;

/**
 * Méthodes utilitaires pour les tableaux
 *
 * @author jbgi
 *
 */
public class TabUtils {

    /**
     * Demande à l'utilisateur un certains d'entier à taper dans le terminal et renvoi ces entier dans un tableau
     *
     * @param nbElements
     *            le nombre d'entier à taper dans le terminal
     * @return un tableau d'entier de taille nbElements
     */
    public static int[] saisirTabInt(final int nbElements) {
        final int[] tab = new int[nbElements];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = Terminal.lireInt();
        }
        return tab;
    }

    /**
     * Affiche un tableau d'entier avec la représention donné par {@link #representationString(int[])}
     *
     * @param tab
     *            le tableau à afficher
     */
    public static void afficherTableau(final int[] tab) {
        final String representation = representationString(tab);

        System.out.println(representation);

        // for (final int i : tab) {
        // System.out.println(i);
        // }
        // for (int i = 0; i <= tab.length; i++) {
        // System.out.println(tab[i]);
        // }
    }

    /**
     * Renvoie la représentation sous forme de string du tableau: |el1|el2|...|eln|
     *
     * @param tab
     *            tableau dont on veut la représentation
     * @return représentation du tableau sous forme de String
     */
    public static String representationString(final int[] tab) {
        return "|" +
                IntStream.of(tab)
                        .mapToObj(Integer::toString)
                        .reduce((i1, i2) -> i1 + "|" + i2).orElse("")
                + "|";
    }

    public static void main(final String[] args) {

        // étant donné (given):
        final int[] tabTest = new int[] { 1, 2, 3 };

        // lorsque (when)
        final String representation = representationString(tabTest);

        // alors (then)
        System.out.println("|1|2|3|".equals(representation));
    }

}
