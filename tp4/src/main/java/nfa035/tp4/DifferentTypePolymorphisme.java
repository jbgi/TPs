package nfa035.tp4;

import java.util.Arrays;

import common.io.Terminal;

public class DifferentTypePolymorphisme {

    private static final Affich<Statut> affichStatus = s -> Terminal.ecrireStringln(s.name());

    // Le polymorphisme paramétrique est plus puissant. Par ex. je peut définir une instance d'Affich pour les tableaux de
    // String, par contre je ne peut pas faire hériter les tableaux de string de l'interface Affichable
    private static final Affich<String[]> affichStringTab = t -> Terminal.ecrireStringln(Arrays.toString(t));

    public static void main(final String[] args) {
        aff("polymorphisme par sous-typage", Statut.Abandon);
        aff("polymorphisme par paramétricité", affichStatus, Statut.Abandon);
    }

    private static void aff(final String msg, final Affichable affichable) {
        Terminal.ecrireStringln(msg);
        affichable.afficher();
    }

    private static <T> void aff(final String msg, final Affich<T> affich, final T t) {
        Terminal.ecrireStringln(msg);
        affich.afficher(t);
    }

}
