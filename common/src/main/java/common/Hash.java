package common;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.ToIntFunction;

/**
 * Produces a hash code for an object which should attempt uniqueness.
 */
@FunctionalInterface
public interface Hash<A> extends ToIntFunction<A>, Function<A, Integer> {

    /**
     * Compute the hash of the given value.
     *
     * @param a
     *            The value to compute the hash value for.
     * @return The hash value.
     */
    int hash(A a);

    /**
     * A hash that uses {@link Object#hashCode()}.
     *
     * @return A hash that uses {@link Object#hashCode()}.
     */
    static <A> Hash<A> anyHash() {
        return a -> Objects.hashCode(a);
    }

    /**
     * A hash instance for the <code>boolean</code> type.
     */
    Hash<Boolean> booleanHash = anyHash();

    /**
     * A hash instance for the <code>byte</code> type.
     */
    Hash<Byte> byteHash = anyHash();

    /**
     * A hash instance for the <code>char</code> type.
     */
    Hash<Character> charHash = anyHash();

    /**
     * A hash instance for the <code>double</code> type.
     */
    Hash<Double> doubleHash = anyHash();

    /**
     * A hash instance for the <code>float</code> type.
     */
    Hash<Float> floatHash = anyHash();

    /**
     * A hash instance for the <code>int</code> type.
     */
    Hash<Integer> intHash = anyHash();

    /**
     * A hash instance for the <code>long</code> type.
     */
    Hash<Long> longHash = anyHash();

    /**
     * A hash instance for the <code>short</code> type.
     */
    Hash<Short> shortHash = anyHash();

    /**
     * A hash instance for the <code>String</code> type.
     */
    Hash<String> stringHash = anyHash();

    /**
     * @return A hash instance for an {@link Enum} type.
     */
    static <E extends Enum<E>> Hash<E> enumHash() {
        return anyHash();
    };

    /**
     * A hash instance for a product-2.
     *
     * @param ha
     *            A hash for the first element of the product.
     * @param hb
     *            A hash for the second element of the product.
     * @return A hash instance for a product-2.
     */
    public static <P, A, B> Hash<P> p2Hash(final Function<P, A> getA, final Hash<A> ha, final Function<P, B> getB,
            final Hash<B> hb) {
        return p2 -> {
            final int p = 419;
            int r = 239;

            r = (p * r) + ha.hash(getA.apply(p2));
            r = (p * r) + hb.hash(getB.apply(p2));

            return r;
        };
    }

    /**
     * A hash instance for a list.
     *
     * @param ha
     *            A hash for the elements of the list.
     * @param hb
     *            A hash for the second element of the product.
     * @return A hash instance for a list.
     */
    public static <A> Hash<List<A>> listHash(final Hash<A> ha) {
        return as -> {
            final int p = 419;
            int r = 239;
            for (final A a : as) {
                r = (p * r) + ha.hash(a);
            }
            return r;
        };
    }

    @Override
    default int applyAsInt(final A a) {
        return hash(a);
    }

    @Override
    default Integer apply(final A a) {
        return hash(a);
    }

}
