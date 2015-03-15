package cardgame.domain.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import common.Equal;
import common.Hash;
import common.Show;

public final class Joueur {

    public static final Equal<Joueur> equal = Equal.p2Equal(Joueur::nom, Equal.stringEqual, Joueur::main,
            Equal.listEqual(Carte.equal));

    public static final Hash<Joueur> hash = Hash.p2Hash(Joueur::nom, Hash.stringHash, Joueur::main, Hash.listHash(Carte.hash));

    public static final Show<Joueur> show = Show.p2Show("Joueur", Joueur::nom, Show.stringShow, Joueur::main,
            Show.listShow(Carte.show));

    private final String nom;

    private final List<Carte> main;

    private Joueur(final String nom, final List<Carte> main) {
        super();
        this.nom = nom;
        this.main = main;
    }

    public String nom() {
        return nom;
    }

    public List<Carte> main() {
        return main;
    }

    public static Joueur creerJoueur(final String nom, final List<Carte> main) {
        return new Joueur(nom, Collections.unmodifiableList(new ArrayList<>(main)));
    }

    @Override
    @Deprecated
    public int hashCode() {
        return hash.hash(this);
    }

    @Override
    @Deprecated
    public boolean equals(final Object obj) {
        return Equal.objectEqualsImpl(Joueur.class, this, obj, equal);
    }

    @Override
    @Deprecated
    public String toString() {
        return show.showS(this);
    }
}
