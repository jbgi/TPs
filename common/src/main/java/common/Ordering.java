package common;

/**
 * The comparison of two instances of a type may have one of three orderings; less than, equal or greater than.
 */
public enum Ordering {
    /**
     * Less than.
     */
    LT,

    /**
     * Equal.
     */
    EQ,

    /**
     * Greater than.
     */
    GT;

    public int toInt() {
        return ordinal() - 1;
    }

    public static Ordering fromInt(final int cmp) {
        return cmp == 0 ? EQ : cmp > 0 ? GT : LT;
    }
}
