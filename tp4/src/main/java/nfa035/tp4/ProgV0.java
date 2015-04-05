package nfa035.tp4;

import common.io.Terminal;

public class ProgV0 {

    /**
     * Gestion d'evenements d'une course (correction) - pas de menu - gestion d'erreurs avec codes d'erreurs (sans exceptions)
     * Version V0
     */

    /**
     * Affiche message correspondant au @param code d'erreur et au nom de l'operation passee
     */
    public static void afficheErreur(final int code, final int nd, final String op) {
        final String[] erreur = { "", "Dossard invalide.", "coureur deja arrive", "coureur ayant abandonne",
                "coureur deja disqualifie" };
        if (code != 0) {
            Terminal.ecrireStringln("Dossard no." + nd + " => " + op + " impossible: " + erreur[code]);
        }
    }

    public static void main(final String[] args) {

        final Course c = new Course();
        c.opInscritCoureur("A");
        c.opInscritCoureur("B");
        c.opInscritCoureur("C");
        c.opInscritCoureur("D");
        c.opInscritCoureur("E");
        c.afficheParticipants();

        afficheErreur(c.enregistreDisqual(2), 2, "Disqualification");
        Terminal.sautDeLigne();
        Terminal.ecrireStringln("Apres disqual 2");
        Terminal.sautDeLigne();

        c.afficheParticipants();

        afficheErreur(c.enregistreArrivee(5), 5, "Classement");
        afficheErreur(c.enregistreArrivee(3), 3, "Classement");
        afficheErreur(c.enregistreArrivee(4), 4, "Classement");
        c.afficheClasses();

        afficheErreur(c.enregistreAbandon(4), 4, "Abandon");
        Terminal.sautDeLigne();
        Terminal.ecrireStringln("Apres abandon impossible 4");
        Terminal.sautDeLigne();

        c.afficheAbandons();
        c.afficheParticipants();
        afficheErreur(c.enregistreAbandon(1), 1, "Abandon");

        Terminal.sautDeLigne();
        Terminal.ecrireStringln("Apres abandon 1");
        Terminal.sautDeLigne();

        c.afficheAbandons();
        c.afficheParticipants();

        afficheErreur(c.enregistreDisqual(5), 5, "Disqualification");

        Terminal.sautDeLigne();
        Terminal.ecrireStringln("Apres disqual 5");
        Terminal.sautDeLigne();

        c.afficheParticipants();
        c.afficheDisqualifications();
        c.afficheClasses();
    }

}
