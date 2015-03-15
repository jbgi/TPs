package cardgame.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class Jeux {

    private final List<Carte> pioche;

    private final List<Joueur> joueurs;

    private Jeux(final List<Carte> pioche, final List<Joueur> joueurs) {
        this.pioche = pioche;
        this.joueurs = joueurs;
    }

    public List<Carte> pioche() {
        return pioche;
    }

    public List<Joueur> joueurs() {
        return joueurs;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((joueurs == null) ? 0 : joueurs.hashCode());
        result = (prime * result) + ((pioche == null) ? 0 : pioche.hashCode());
        return result;
    }

    @Override
    @Deprecated
    public boolean equals(final Object obj) {
        return obj instanceof Jeux ? equals((Jeux) obj) : false;
    }

    public boolean equals(final Jeux autreJeux) {
        return this.pioche.equals(autreJeux.pioche) && this.joueurs.equals(autreJeux.joueurs);
    }

    public static Jeux creerJeux(final List<Carte> pioche, final List<Joueur> joueurs) {
        return new Jeux(Collections.unmodifiableList(new ArrayList<>(pioche)), Collections.unmodifiableList(new ArrayList<>(
                joueurs)));
    }

    public static DistribuerCarte distribuerCarte() {
        return (final List<Carte> pioche) -> {
            // TODO;
            throw new UnsupportedOperationException();
        };
    }

    public static ChoisirCarte choisirCarte() {
        return (final List<Carte> main, final Carte carte) -> {
            // TODO;
            throw new UnsupportedOperationException();
        };
    }
}
