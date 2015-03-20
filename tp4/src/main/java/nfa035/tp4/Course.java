package nfa035.tp4;

import java.util.ArrayList;

import common.io.Terminal;

/**
 * Gestion d'evenement d'une course
 *
 **/
public class Course {
    private final ArrayList<Coureur> participants = new ArrayList<Coureur>();
    private final ArrayList<Coureur> arrives = new ArrayList<Coureur>();

    public int getNbParticipants() {
        return participants.size();
    }

    /**
     * Enregistre un nouveau coureur de no @param nc parmi les participants.
     **/
    public void opInscritCoureur(final String nc) {
        final Coureur c = new Coureur(nc, participants.size() + 1);
        participants.add(c);
    }

    /**
     * Teste si le numero de dossard @param nd est compris entre [1..N] avec N nombre de participants.
     **/
    public boolean dossardValide(final int nd) {
        return ((nd > 0) && (nd <= participants.size()));
    }

    /**
     * Enregistre le coureur de dossard @param nd dans la liste des arrivees. pre: suppose @param nd correspond a un dossard
     * valide dans [1..N]
     * 
     * @return code d'erreur: 0 si pas d'erreur 2 s'il est deja dans les arrives 3 s'il a deja abandonne 4 s'il est deja
     *         disqualifie
     **/
    public int enregistreArrivee(final int nd) {
        final int codeErr = participants.get(nd - 1).changeStatus(Statut.Arrive);
        if (codeErr == 0) {
            arrives.add(participants.get(nd - 1));
        }
        return codeErr;
    }

    /**
     * Enregistre abandon pour le dossard @param nd pre: Suppose que le numero @param nd correspond a un dossard valide.
     * 
     * @return code d'erreur si abandon impossible.
     */
    public int enregistreAbandon(final int nd) {
        // A modifier
        return 0;
    }

    /**
     * Enregistre disqualification dossard @param nd. pre: @param nd numero de dossard valide.
     * 
     * @return code d'erreur si disqualification impossible.
     **/
    public int enregistreDisqual(final int nd) {
        // A modifier
        return 0;
    }

    /**** Operations d'affichage *****/
    /**
     * Affiche le message @param m suivi de la liste de coureurs dans @param lc
     **/
    private static void afficheListe(final String m, final ArrayList<Coureur> lc) {
        Terminal.ecrireStringln(m);
        Terminal.ecrireStringln("-----------------------");
        if (lc.size() == 0) {
            Terminal.ecrireStringln("  ==> aucun");
            return;
        }
        for (int i = 0; i < lc.size(); i++) {
            lc.get(i).affiche();
        }
        Terminal.sautDeLigne();
    }

    public void afficheParticipants() {
        afficheListe("Participants a la course", participants);
    }

    /**
     * Affiche le message @param m suivi de la liste de tous les coureurs de @param lc qui ont le @param statut s
     **/
    private static void afficheListeStat(final String m, final ArrayList<Coureur> lc, final Statut s) {
        // Remplacer le throw par votre code!!!
        throw new Error("Remplacer ce throw par votre implantation.");
    }

    /** Affiche la liste de coureurs classes */
    public void afficheClasses() {
        // Remplacer le throw par votre code!!!
        throw new Error("Remplacer ce throw par votre implantation.");
    }

    /** Affiche la liste de coureurs ayant abandonne */
    public void afficheAbandons() {
        // Remplacer le throw par votre code!!!
        throw new Error("Remplacer ce throw par votre implantation.");
    }

    /** Affiche la liste de coureurs disqualifies */
    public void afficheDisqualifications() {
        // Remplacer le throw par votre code!!!
        throw new Error("Remplacer ce throw par votre implantation.");
    }

    /**
     * Affiche le ganant (s'y il en a un) ou un message si pas de gagnant
     */
    public void afficheGagant() {
        // Remplacer le throw par votre code!!!
        throw new Error("Remplacer ce throw par votre implantation.");
    }
}
