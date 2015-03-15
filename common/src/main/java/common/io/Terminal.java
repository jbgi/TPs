package common.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

/**
 * Interactions avec le terminal
 */
public final class Terminal {

    private Terminal() {
        throw new IllegalStateException();
    }

    // Lire un String
    public static String lireString() {
        return readLine().run();
    }

    // Lire un entier
    public static int lireInt() {
        return readLine().map(Integer::parseInt).run();
    }

    // Lire un boolean
    public static boolean lireBoolean() {
        return readLine().map(Boolean::parseBoolean).run();
    }

    // Lire un double
    public static double lireDouble() {
        return readLine().map(Double::parseDouble).run();
    }

    // Lire un caractere
    public static char lireChar() {
        return readLine().map(s -> s.isEmpty() ? '\n' : s.charAt(0)).run();
    }

    // Afficher un String
    public static void ecrireString(final String s) {
        print(s).run();
    }

    // Afficher un String avec saut de ligne
    public static void ecrireStringln(final String s) {
        printNewLine(s).run();
    }

    // Afficher un entier
    public static void ecrireInt(final int i) {
        print(Integer.toString(i)).run();
    }

    // Afficher un entier puis saut de ligne
    public static void ecrireIntln(final int i) {
        printNewLine(Integer.toString(i)).run();
    }

    // Afficher un boolean
    public static void ecrireBoolean(final boolean b) {
        print(Boolean.toString(b)).run();
    }

    // Afficher un boolean puis saut de ligne
    public static void ecrireBooleanln(final boolean b) {
        printNewLine(Boolean.toString(b)).run();
    }

    // Afficher un double
    public static void ecrireDouble(final double d) {
        print(Double.toString(d)).run();
    }

    // Afficher un double puis saut de ligne
    public static void ecrireDoubleln(final double d)
    {
        printNewLine(Double.toString(d)).run();
    }

    // Afficher un caractere
    public static void ecrireChar(final char c)
    {
        print(Character.toString(c)).run();
    }

    // Afficher un caractere puis saut de ligne
    public static void ecrireCharln(final char c)
    {
        printNewLine(Character.toString(c)).run();
    }

    // saut de ligne
    public static void sautDeLigne() {
        newLine().run();
    }

    /**
     * Lecture du terminal
     *
     * @return Une operation de lecture d'une ligne tapée dans le terminal
     */
    public static IO<String> readLine() {
        return () -> new BufferedReader(new InputStreamReader(System.in))
                .readLine();
    }

    /**
     * Écriture dans le terminal
     *
     * @param msg
     *            un message à afficher dans le terminal
     * @return Une operation d'écriture du message dans le terminal
     */
    public static IO<Unit> print(final String msg) {
        return () -> {
            System.out.print(msg);
            return Unit.unit();
        };
    }

    /**
     * Passer à la ligne dans le terminal
     *
     * @return une opération de passage à la ligne dans le terminal
     */
    public static IO<Unit> newLine() {
        return () -> {
            System.out.println();
            return Unit.unit();
        };
    }

    /**
     * Écriture + à la ligne dans le terminal
     *
     * @param msg
     *            un message à afficher dans le terminal
     * @return une opération d'écriture du message suivit d'un saut de ligne dans le terminal
     */
    public static IO<Unit> printNewLine(final String msg) {
        return print(msg).flatMap(u -> newLine());
    }
}

/**
 * Type utilisé pour représenter le résulat d'un pur effet de bord : c'est à dire qui ne produit aucune valeur exploitable par
 * le programme (par exemple écrire quelque chose dans le terminal). Analogue au type {@link Void}, mais évite les
 * {@link NullPointerException}.
 *
 */
final class Unit {

    private static final Unit unit = new Unit();

    private Unit() {
        super();
    }

    /**
     * @return unit
     */
    public static Unit unit() {
        return unit;
    }

}

/**
 * Classe utilisé pour représenté les opération d'entrée/sortie (Input/Ouput), tels que les interractions avec le terminal.
 *
 * @param <A>
 *            le type de la valeur produite par l'opération d'entrée/sortie lorsqu'elle est exécutée (méthode {@link IO#run()})
 */
@FunctionalInterface
interface IO<A> {

    /**
     * Exécute l'opération d'entré/sortie.
     *
     * @return la valeur produite par l'exécution
     * @throws IOException
     *             en cas d'erreur lors de l'exécution
     */
    A runChecked() throws IOException;

    /**
     * Idem {@link #runChecked()()} en enveloppant l'exception dans une exception non-checké.
     *
     * @return la valeur produite par l'exécution
     * @throws RuntimeException
     *             en cas d'erreur lors de l'exécution
     */
    default A run() {
        final A result;
        try {
            result = runChecked();
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    /**
     * Créer une nouvelle opération d'entrée sortie avec transformation de la valeur de retour.
     *
     * @param f
     *            la fonction de transformation de la valeur de retour
     * @return une nouvelle opération d'entrée sortie avec transformation de la valeur de retour
     */
    default <B> IO<B> map(final Function<A, B> f) {
        return () -> f.apply(runChecked());
    }

    /**
     * Créer une nouvelle opération d'entrée sortie qui utilise de la valeur de retour originale pour une opération IO suivante.
     *
     * @param f
     *            la fonction de transformation de la valeur de retour
     * @return une nouvelle opération d'entrée sortie qui utilise de la valeur de retour originale pour une opération IO
     *         suivante.
     */
    default <B> IO<B> flatMap(final Function<A, IO<B>> f) {
        return () -> f.apply(runChecked()).runChecked();
    }

    /**
     * Créer une opération IO à partir d'une valeur simple : garentie de retourné cette même valeur lors de l'exécution (méthode
     * {@link #run()})
     *
     * @param a
     *            valeur à transformé en opération IO
     * @return une opération IO qui produit toujours la valeur a.
     */
    public static <A> IO<A> pure(final A a) {
        return () -> a;
    }

}
