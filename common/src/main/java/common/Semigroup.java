package common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

/**
 * Implementations must satisfy the law of associativity:
 * <ul>
 * <li><em>Associativity</em>; forall x. forall y. forall z. sum(sum(x, y), z) == sum(x, sum(y, z))</li>
 * </ul>
 */
public interface Semigroup<A> extends BinaryOperator<A> {

    /**
     * Sums the two given arguments.
     *
     * @param a1
     *            A value to sum with another.
     * @param a2
     *            A value to sum with another.
     * @return The of the two given arguments.
     */
    A sum(final A a1, final A a2);

    /**
     * Returns a function that sums the given value according to this semigroup.
     *
     * @param a1
     *            The value to sum.
     * @return A function that sums the given value according to this semigroup.
     */
    default UnaryOperator<A> sum(final A a1) {
        return a2 -> sum(a1, a2);
    }

    @Override
    @Deprecated
    default A apply(final A a1, final A a2) {
        return sum(a1, a2);
    }

    /**
     * A semigroup that adds integers.
     */
    public static final Semigroup<Integer> intAdditionSemigroup = (i1, i2) -> i1 + i2;

    /**
     * A semigroup that adds doubles.
     */
    public static final Semigroup<Double> doubleAdditionSemigroup = (d1, d2) -> d1 + d2;

    /**
     * A semigroup that multiplies integers.
     */
    public static final Semigroup<Integer> intMultiplicationSemigroup = (i1, i2) -> i1 * i2;

    /**
     * A semigroup that multiplies doubles.
     */
    public static final Semigroup<Double> doubleMultiplicationSemigroup = (d1, d2) -> d1 * d2;

    /**
     * A semigroup that yields the maximum of integers.
     */
    public static final Semigroup<Integer> intMaximumSemigroup = Ord.intOrd::max;

    /**
     * A semigroup that yields the minimum of integers.
     */
    public static final Semigroup<Integer> intMinimumSemigroup = Ord.intOrd::min;

    /**
     * A semigroup that adds big integers.
     */
    public static final Semigroup<BigInteger> bigintAdditionSemigroup =
            (i1, i2) -> i1.add(i2);

    /**
     * A semigroup that multiplies big integers.
     */
    public static final Semigroup<BigInteger> bigintMultiplicationSemigroup =
            (i1, i2) -> i1.multiply(i2);

    /**
     * A semigroup that yields the maximum of big integers.
     */
    public static final Semigroup<BigInteger> bigintMaximumSemigroup = Ord.bigintOrd::max;

    /**
     * A semigroup that yields the minimum of big integers.
     */
    public static final Semigroup<BigInteger> bigintMinimumSemigroup = Ord.bigintOrd::min;

    /**
     * A semigroup that adds big decimals.
     */
    public static final Semigroup<BigDecimal> bigdecimalAdditionSemigroup =
            (i1, i2) -> i1.add(i2);

    /**
     * A semigroup that multiplies big decimals.
     */
    public static final Semigroup<BigDecimal> bigdecimalMultiplicationSemigroup =
            (i1, i2) -> i1.multiply(i2);

    /**
     * A semigroup that yields the maximum of big decimals.
     */
    public static final Semigroup<BigDecimal> bigDecimalMaximumSemigroup = Ord.bigdecimalOrd::max;

    /**
     * A semigroup that yields the minimum of big decimals.
     */
    public static final Semigroup<BigDecimal> bigDecimalMinimumSemigroup = Ord.bigdecimalOrd::min;

    /**
     * A semigroup that adds longs.
     */
    public static final Semigroup<Long> longAdditionSemigroup = (x, y) -> x + y;

    /**
     * A semigroup that multiplies longs.
     */
    public static final Semigroup<Long> longMultiplicationSemigroup = (x, y) -> x * y;

    /**
     * A semigroup that yields the maximum of longs.
     */
    public static final Semigroup<Long> longMaximumSemigroup = Ord.longOrd::max;

    /**
     * A semigroup that yields the minimum of longs.
     */
    public static final Semigroup<Long> longMinimumSemigroup = Ord.longOrd::min;

    /**
     * A semigroup that ORs booleans.
     */
    public static final Semigroup<Boolean> disjunctionSemigroup = (b1, b2) -> b1 || b2;

    /**
     * A semigroup that XORs booleans.
     */
    public static final Semigroup<Boolean> exclusiveDisjunctionSemiGroup = (p, q) -> (p ^ q);

    /**
     * A semigroup that ANDs booleans.
     */
    public static final Semigroup<Boolean> conjunctionSemigroup = (b1, b2) -> b1 && b2;

    /**
     * A semigroup that appends strings.
     */
    public static final Semigroup<String> stringSemigroup = (s1, s2) -> s1.concat(s2);

    /**
     * A semigroup for functions.
     *
     * @param sb
     *            The semigroup for the codomain.
     * @return A semigroup for functions.
     */
    public static <A, B> Semigroup<Function<A, B>> functionSemigroup(final Semigroup<B> sb) {
        return (a1, a2) -> a -> sb.sum(a1.apply(a), a2.apply(a));
    }

    /**
     * A semigroup for lists.
     *
     * @return A semigroup for lists.
     */
    public static <A> Semigroup<List<A>> listSemigroup() {
        return (l1, l2) -> {
            final List<A> sumList;
            if (l1.isEmpty()) {
                sumList = l2;

            } else if (l2.isEmpty()) {
                sumList = l1;

            } else {
                sumList = new ArrayList<>(l1.size() + l2.size());
                sumList.addAll(l1);
                sumList.addAll(l2);
            }
            return sumList;
        };
    }

    /**
     * A semigroup for optional values (that take the first available value).
     **
     * @return A semigroup for optional values (that take the first available value).
     */
    public static <A> Semigroup<Optional<A>> optionSemigroup() {
        return (a1, a2) -> a1.isPresent() ? a1 : a2;
    }

    /**
     * A semigroup for optional values that take the last available value.
     *
     * @return A semigroup for optional values that take the last available value.
     */
    public static <A> Semigroup<Optional<A>> lastOptionSemigroup() {
        return (a1, a2) -> a2.isPresent() ? a2 : a1;
    }

    /**
     * A semigroup for streams.
     *
     * @return A semigroup for streams.
     */
    public static <A> Semigroup<Stream<A>> streamSemigroup() {
        return (a1, a2) -> Stream.concat(a1, a2);
    }

    /**
     * A semigroup for unary products.
     *
     * @param sa
     *            A semigroup for the product's type.
     * @return A semigroup for unary products.
     */
    public static <P, A> Semigroup<P> p1Semigroup(final Function<P, A> getA, final Semigroup<A> sa, final Function<A, P> toP) {
        return (p1, p2) -> toP.apply(sa.sum(getA.apply(p1), getA.apply(p2)));
    }

    /**
     * A semigroup for binary products.
     *
     * @param sa
     *            A semigroup for the product's first type.
     * @param sb
     *            A semigroup for the product's second type.
     * @return A semigroup for binary products.
     */
    public static <P, A, B> Semigroup<P> p2Semigroup(final Function<P, A> getA, final Semigroup<A> sa,
            final Function<P, B> getB, final Semigroup<B> sb, final BiFunction<A, B, P> toP) {
        return (p1, p2) -> toP.apply(sa.sum(getA.apply(p1), getA.apply(p2)), sb.sum(getB.apply(p1), getB.apply(p2)));
    }

    /**
     * A semigroup for the Unit value.
     */
    public static final Semigroup<Unit> unitSemigroup = (u1, u2) -> Unit.unit();

}
