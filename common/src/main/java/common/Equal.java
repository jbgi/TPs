package common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Tests for equality between two objects.
 *
 */
@FunctionalInterface
public interface Equal<A> extends BiPredicate<A, A> {

    /**
     * Returns <code>true</code> if the two given arguments are equal, <code>false</code> otherwise.
     *
     * @param a1
     *            An object to test for equality against another.
     * @param a2
     *            An object to test for equality against another.
     * @return <code>true</code> if the two given arguments are equal, <code>false</code> otherwise.
     */
    boolean eq(final A a1, final A a2);

    /**
     * Partially applied equality check.
     *
     * @param a
     *            An object to test for equality against another.
     * @return A predicate that returns <code>true</code> if the given argument equals the argument to this method.
     */
    default Predicate<A> eq(final A a1) {
        return a2 -> eq(a1, a2);
    }

    @Override
    @Deprecated
    default boolean test(final A a1, final A a2) {
        return eq(a1, a2);
    }

    /**
     * Returns an equal instance that uses the {@link Object#equals(Object)} method to test for equality.
     *
     * @return An equal instance that uses the {@link Object#equals(Object)} method to test for equality.
     */
    static <A> Equal<A> anyEqual() {
        return (a1, a2) -> Objects.equals(a1, a2);
    }

    /**
     * Helper method to implement {@link Object#equals(Object)} correctly
     *
     * @param classA
     *            the class in which the {@link Object#equals(Object)} is implemented
     * @param a
     *            a reference to 'this'
     * @param obj
     *            the other object of the comparison
     * @param eqA
     *            an equals instance for the type.
     * @return
     */
    @SuppressWarnings("unchecked")
    @Deprecated
    static <A> boolean objectEqualsImpl(final Class<? super A> classA, final A a, final Object obj, final Equal<A> eqA) {
        return a == obj ? true : (a != null) && classA.isInstance(obj) ? eqA.eq(a, (A) obj) : false;
    }

    /**
     * An equal instance for the <code>boolean</code> type.
     */
    Equal<Boolean> booleanEqual = anyEqual();

    /**
     * An equal instance for the <code>byte</code> type.
     */
    Equal<Byte> byteEqual = anyEqual();

    /**
     * An equal instance for the <code>char</code> type.
     */
    Equal<Character> charEqual = anyEqual();

    /**
     * An equal instance for the <code>double</code> type.
     */
    Equal<Double> doubleEqual = anyEqual();

    /**
     * An equal instance for the <code>float</code> type.
     */
    Equal<Float> floatEqual = anyEqual();

    /**
     * An equal instance for the <code>int</code> type.
     */
    Equal<Integer> intEqual = anyEqual();

    /**
     * An equal instance for the <code>BigInteger</code> type.
     */
    Equal<BigInteger> bigintEqual = anyEqual();

    /**
     * An equal instance for the <code>BigDecimal</code> type.
     */
    Equal<BigDecimal> bigdecimalEqual = anyEqual();

    /**
     * An equal instance for the <code>long</code> type.
     */
    Equal<Long> longEqual = anyEqual();

    /**
     * An equal instance for the <code>short</code> type.
     */
    Equal<Short> shortEqual = anyEqual();

    /**
     * An equal instance for the {@link String} type.
     */
    Equal<String> stringEqual = anyEqual();

    /**
     * An equal instance for an {@link Enum} type.
     */
    public static <E extends Enum<E>> Equal<E> enumEqual() {
        return (e1, e2) -> e1 == e2;
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
    public static <P, A, B> Equal<P> p2Equal(final Function<P, A> getA, final Equal<A> eqA, final Function<P, B> getB,
            final Equal<B> eqB) {
        return (p1, p2) -> eqA.eq(getA.apply(p1), getA.apply(p2)) && eqB.eq(getB.apply(p1), getB.apply(p2));
    }

    /**
     * An equal instance for a {@link List} type.
     */
    public static <A> Equal<List<A>> listEqual(final Equal<A> eqA) {
        return (l1, l2) -> {
            boolean r = l1.size() == l2.size();
            for (int i = 0; r && (i < l1.size()); i++) {
                r = eqA.eq(l1.get(i), l2.get(i));
            }
            return r;
        };
    }
}
