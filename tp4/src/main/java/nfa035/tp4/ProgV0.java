package nfa035.tp4;

import common.io.Terminal;

public class ProgV0 {

    /**
     * Gestion d'evenements d'une course (code a completer) - pas de menu - gestion d'erreurs avec codes d'erreurs (sans
     * exceptions) Version V0
     */

    /**
     * Affiche message d'erreur selon @param code avec numero dossard @param nd et nom d'operation erronee @param op
     */
    public static void afficheErreur(final int code, final int nd, final String op) {
        final String[] erreur = { "", "Dossard invalide.", "coureur deja arrive", "coureur ayant abandonne",
                "coureur deja disqualifie" };
        if (code != 0) {
            Terminal.ecrireStringln("Dossard no." + nd + " => " + op + " impossible: " + erreur[code]);
        }
    }

    public static void main(final String[] args) {

        // A completer

        final Course c = new Course();
        c.opInscritCoureur("A");
        c.opInscritCoureur("B");
        c.opInscritCoureur("C");

        c.afficheParticipants();
        afficheErreur(c.enregistreArrivee(3), 3, "Classement");
        c.afficheParticipants();
        afficheErreur(c.enregistreArrivee(3), 3, "Classement");
        c.afficheParticipants();

    }

}
