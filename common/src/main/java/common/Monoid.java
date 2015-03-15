package common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A monoid abstraction to be defined across types of the given type argument. Implementations must follow the monoidal laws:
 * <ul>
 * <li><em>Left Identity</em>; forall x. sum(zero(), x) == x</li>
 * <li><em>Right Identity</em>; forall x. sum(x, zero()) == x</li>
 * <li><em>Associativity</em>; forall x. forall y. forall z. sum(sum(x, y), z) == sum(x, sum(y, z))</li>
 * </ul>
 *
 * @version %build.number%
 */
public interface Monoid<A> extends Semigroup<A> {

    /**
     * The zero value for this monoid.
     *
     * @return The zero value for this monoid.
     */
    A zero();

    /**
     * Sums the given values.
     *
     * @param as
     *            The values to sum.
     * @return The sum of the given values.
     */
    public default A sum(final Collection<A> as) {
        return sum(as.stream());
    }

    /**
     * Sums the given values.
     *
     * @param as
     *            The values to sum.
     * @return The sum of the given values.
     */
    public default A sum(final Stream<A> as) {
        return as.reduce(zero(), this);
    }

    /**
     * Intersperses the given value between each two elements of the stream, and sums the result.
     *
     * @param as
     *            An stream of values to sum.
     * @param a
     *            The value to intersperse between values of the given iterable.
     * @return The sum of the given values and the interspersed value.
     */
    public default A join(final Stream<A> as, final A a) {
        return as.reduce((a1, a2) -> sum(a1, sum(a, a2))).orElse(zero());
    }

    /**
     * Intersperses the given value between each two elements of the collection, and sums the result.
     *
     * @param as
     *            An stream of values to sum.
     * @param a
     *            The value to intersperse between values of the given iterable.
     * @return The sum of the given values and the interspersed value.
     */
    public default A join(final Collection<A> as, final A a) {
        return join(as.stream(), a);
    }

    /**
     * Constructs a monoid from the given sum function and zero value, which must follow the monoidal laws.
     *
     * @param sum
     *            The sum function for the monoid.
     * @param zero
     *            The zero for the monoid.
     * @return A monoid instance that uses the given sun function and zero value.
     */
    public static <A> Monoid<A> monoid(final BinaryOperator<A> sum, final A zero) {
        return new Monoid<A>() {

            @Override
            public A sum(final A a1, final A a2) {
                return sum.apply(a1, a2);
            }

            @Override
            public A zero() {
                return zero;
            }
        };
    }

    /**
     * A monoid that adds integers.
     */
    public static final Monoid<Integer> intAdditionMonoid = monoid(Semigroup.intAdditionSemigroup, 0);

    /**
     * A monoid that multiplies integers.
     */
    public static final Monoid<Integer> intMultiplicationMonoid = monoid(Semigroup.intMultiplicationSemigroup, 1);

    /**
     * A monoid that adds doubles.
     */
    public static final Monoid<Double> doubleAdditionMonoid = monoid(Semigroup.doubleAdditionSemigroup, 0.0);

    /**
     * A monoid that multiplies doubles.
     */
    public static final Monoid<Double> doubleMultiplicationMonoid = monoid(Semigroup.doubleMultiplicationSemigroup, 1.0);

    /**
     * A monoid that adds big integers.
     */
    public static final Monoid<BigInteger> bigintAdditionMonoid = monoid(Semigroup.bigintAdditionSemigroup, BigInteger.ZERO);

    /**
     * A monoid that multiplies big integers.
     */
    public static final Monoid<BigInteger> bigintMultiplicationMonoid =
            monoid(Semigroup.bigintMultiplicationSemigroup, BigInteger.ONE);

    /**
     * A monoid that adds big decimals.
     */
    public static final Monoid<BigDecimal> bigdecimalAdditionMonoid =
            monoid(Semigroup.bigdecimalAdditionSemigroup, BigDecimal.ZERO);

    /**
     * A monoid that multiplies big decimals.
     */
    public static final Monoid<BigDecimal> bigdecimalMultiplicationMonoid =
            monoid(Semigroup.bigdecimalMultiplicationSemigroup, BigDecimal.ONE);

    /**
     * A monoid that adds longs.
     */
    public static final Monoid<Long> longAdditionMonoid = monoid(Semigroup.longAdditionSemigroup, 0L);

    /**
     * A monoid that multiplies longs.
     */
    public static final Monoid<Long> longMultiplicationMonoid = monoid(Semigroup.longMultiplicationSemigroup, 1L);

    /**
     * A monoid that ORs booleans.
     */
    public static final Monoid<Boolean> disjunctionMonoid = monoid(Semigroup.disjunctionSemigroup, false);

    /**
     * A monoid that XORs booleans.
     */
    public static final Monoid<Boolean> exclusiveDisjunctionMonoid = monoid(Semigroup.exclusiveDisjunctionSemiGroup, false);

    /**
     * A monoid that ANDs booleans.
     */
    public static final Monoid<Boolean> conjunctionMonoid = monoid(Semigroup.conjunctionSemigroup, true);

    /**
     * A monoid that appends strings.
     */
    public static final Monoid<String> stringMonoid = monoid(Semigroup.stringSemigroup, "");

    /**
     * A monoid for functions.
     *
     * @param mb
     *            The monoid for the function codomain.
     * @return A monoid for functions.
     */
    public static <A, B> Monoid<Function<A, B>> functionMonoid(final Monoid<B> mb) {
        return monoid(Semigroup.functionSemigroup(mb), f -> mb.zero());
    }

    /**
     * A monoid for lists.
     *
     * @return A monoid for lists.
     */
    public static <A> Monoid<List<A>> listMonoid() {
        return new Monoid<List<A>>() {
            @Override
            public List<A> sum(final List<A> a1, final List<A> a2) {
                return Semigroup.<A> listSemigroup().sum(a1, a2);
            }

            @Override
            public List<A> zero() {
                return Collections.emptyList();
            }

            @Override
            public List<A> sum(final Stream<List<A>> ll) {
                return ll.flatMap(l -> l.stream()).collect(Collectors.toList());
            }

            @Override
            public List<A> sum(final Collection<List<A>> ll) {
                final List<A> r = new ArrayList<>(ll.size());
                for (final List<A> l : ll) {
                    r.addAll(l);
                }
                return r;
            }
        };
    }

    /**
     * A monoid for options (that take the first available value).
     *
     * @return A monoid for options (that take the first available value).
     */
    public static <A> Monoid<Optional<A>> optionMonoid() {
        return monoid(Semigroup.optionSemigroup(), Optional.empty());
    }

    /**
     * A monoid for options that take the last available value.
     *
     * @return A monoid for options that take the last available value.
     */
    public static <A> Monoid<Optional<A>> lastOptionMonoid() {
        return monoid(Semigroup.lastOptionSemigroup(), Optional.empty());
    }

}
