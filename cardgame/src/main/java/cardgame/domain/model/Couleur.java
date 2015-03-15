package cardgame.domain.model;

import common.Equal;
import common.Hash;
import common.Show;

public enum Couleur {
    TREFLE, COEUR, CARREAU, PIQUE;

    public static final Equal<Couleur> equal = Equal.enumEqual();

    public static final Hash<Couleur> hash = Hash.enumHash();

    public static final Show<Couleur> show = Show.enumShow();
}
