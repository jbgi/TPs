package common;

/**
 * A product-2.
 */
public abstract class P2<A, B> {

    /**
     * Access the first element of the product.
     *
     * @return The first element of the product.
     */
    public abstract A _1();

    /**
     * Access the second element of the product.
     *
     * @return The second element of the product.
     */
    public abstract B _2();

    @Override
    @Deprecated
    public final boolean equals(final Object obj) {
        return Equal.objectEqualsImpl(P2.class, this, obj, equal(Equal.<A> anyEqual(), Equal.<B> anyEqual()));
    }

    /**
     * An equal instance for a product-2.
     *
     * @param eqA
     *            Equality across the first element of the product.
     * @param eqB
     *            Equality across the second element of the product.
     * @return An equal instance for a product-2.
     */
    public static <A, B> Equal<P2<A, B>> equal(final Equal<A> eqA, final Equal<B> eqB) {
        return Equal.p2Equal(P2::_1, eqA, P2::_2, eqB);
    }

    /**
     * A hash instance for a product-2.
     *
     * @param ha
     *            A hash for the first element of the product.
     * @param hb
     *            A hash for the second element of the product.
     * @return A hash instance for a product-2.
     */
    public static <A, B> Hash<P2<A, B>> p2Hash(final Hash<A> ha, final Hash<B> hb) {
        return Hash.p2Hash(P2::_1, ha, P2::_2, hb);
    }
}
