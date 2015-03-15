package cardgame.domain.model;

import java.util.Objects;

import common.Equal;
import common.Hash;
import common.Show;

public final class Carte {

    public static final Equal<Carte> equal = Equal.p2Equal(Carte::rang, Rang.equal, Carte::couleur, Couleur.equal);

    public static final Hash<Carte> hash = Hash.p2Hash(Carte::rang, Rang.hash, Carte::couleur, Couleur.hash);

    public static final Show<Carte> show = Show.p2Show("Carte", Carte::rang, Rang.show, Carte::couleur, Couleur.show);

    private final Rang rang;

    private final Couleur couleur;

    public Carte(final Rang rang, final Couleur couleur) {
        super();
        this.rang = Objects.requireNonNull(rang);
        this.couleur = Objects.requireNonNull(couleur);
    }

    public Couleur couleur() {
        return couleur;
    }

    public Rang rang() {
        return rang;
    }

    @Override
    @Deprecated
    public int hashCode() {
        return hash.hash(this);
    }

    @Override
    @Deprecated
    public boolean equals(final Object obj) {
        return Equal.objectEqualsImpl(Carte.class, this, obj, equal);
    }

    @Override
    @Deprecated
    public String toString() {
        return show.showS(this);
    }

}
