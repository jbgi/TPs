package cardgame.domain.model;

import common.Equal;
import common.Hash;
import common.Show;

public enum Rang {
    DEUX, TROIS, QUATRE, CINQ, SIX, SEPT, HUIT, NEUF, DIX, VALET, DAME, ROI, AS;

    public static final Equal<Rang> equal = Equal.enumEqual();

    public static final Hash<Rang> hash = Hash.enumHash();

    public static final Show<Rang> show = Show.enumShow();
}
