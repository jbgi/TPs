package nfa035.tp2;

public class Exo1Puissance {

    /**
     * Calcule a puissance n. Si a est 0 et n est négatif, doit lancer une {@link ArithmeticException}
     *
     * @param a
     *            un réel
     * @param n
     *            un entier quelconque
     * @return a puissance n
     * @throws ArithmeticException
     *             si a == 0 et n est strictement négatif.
     */
    public static double puissance(final double a, final int n) {

        return puissanceTest3(a, n);
    }

    public static double puissanceTest1(final double a, final int n) {

        double result = 1.0;
        for (int i = 0; i < n; i++) {
            result = a * result;
        }
        return result;
    }

    public static double puissanceTest2(final double a, final int n) {
        if ((a == 0.0) && (n < 0)) {
            throw new ArithmeticException("a == 0 et n est strictement négatif");
        }
        double result = 1.0;
        for (int i = 0; i < n; i++) {
            result = a * result;
        }
        return result;
    }

    public static double puissanceTest3(final double a, final int n) {
        if ((a == 0.0) && (n < 0)) {
            throw new ArithmeticException("a == 0 et n est strictement négatif");
        }
        double result;
        if (n < 0) {
            result = puissanceHelper(1 / a, 0 - n);
        }
        else {
            result = puissanceHelper(a, n);
        }
        return result;
    }

    private static double puissanceHelper(final double a, final int n) {
        double result = 1.0;
        for (int i = 0; i < n; i++) {
            result = a * result;
        }
        return result;
    }
}
