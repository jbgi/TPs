package common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 * Tests for ordering between two objects.
 */
@FunctionalInterface
public interface Ord<A> extends Equal<A>, BiFunction<A, A, Ordering> {

    /**
     * Returns an ordering for the given arguments.
     *
     * @param a1
     *            An instance to compare for ordering to another.
     * @param a2
     *            An instance to compare for ordering to another.
     * @return An ordering for the given arguments.
     */
    Ordering compare(final A a1, final A a2);

    @Override
    default boolean eq(final A a1, final A a2) {
        return compare(a1, a2) == Ordering.EQ;
    }

    @Override
    default Ordering apply(final A a1, final A a2) {
        return compare(a1, a2);
    }

    default Comparator<A> toComparator() {
        return (a1, a2) -> compare(a1, a2).toInt();
    }

    static <A> Ord<A> comparatorOrd(final Comparator<A> comparator) {
        return (a1, a2) -> Ordering.fromInt(comparator.compare(a1, a2));
    }

    /**
     * An order instance for the <code>Comparable</code> interface.
     *
     * @return An order instance for the <code>Comparable</code> interface.
     */
    public static <A extends Comparable<A>> Ord<A> comparableOrd() {
        return (a1, a2) -> Ordering.fromInt(a1.compareTo(a2));
    }

    /**
     * Maps the given function across this ord as a contra-variant functor.
     *
     * @param f
     *            The function to map.
     * @return A new ord.
     */
    default <B> Ord<B> comap(final Function<B, A> f) {
        return (b1, b2) -> compare(f.apply(b1), f.apply(b2));
    }

    /**
     * Returns <code>true</code> if the first given argument is less than the second given argument, <code>false</code>
     * otherwise.
     *
     * @param a1
     *            An instance to compare for ordering to another.
     * @param a2
     *            An instance to compare for ordering to another.
     * @return <code>true</code> if the first given argument is less than the second given argument, <code>false</code>
     *         otherwise.
     */
    default boolean isLessThan(final A a1, final A a2) {
        return compare(a1, a2) == Ordering.LT;
    }

    /**
     * Returns <code>true</code> if the first given argument is greater than the second given argument, <code>false</code>
     * otherwise.
     *
     * @param a1
     *            An instance to compare for ordering to another.
     * @param a2
     *            An instance to compare for ordering to another.
     * @return <code>true</code> if the first given argument is greater than the second given argument, <code>false</code>
     *         otherwise.
     */
    default boolean isGreaterThan(final A a1, final A a2) {
        return compare(a1, a2) == Ordering.GT;
    }

    /**
     * Returns a function that returns true if its argument is less than the argument to this method.
     *
     * @param a
     *            A value to compare against.
     * @return A function that returns true if its argument is less than the argument to this method.
     */
    default Predicate<A> isLessThan(final A a) {
        return a2 -> compare(a2, a) == Ordering.LT;
    }

    /**
     * Returns a function that returns true if its argument is greater than than the argument to this method.
     *
     * @param a
     *            A value to compare against.
     * @return A function that returns true if its argument is greater than the argument to this method.
     */
    default Predicate<A> isGreaterThan(final A a) {
        return a2 -> compare(a2, a) == Ordering.GT;
    }

    /**
     * Returns the greater of its two arguments.
     *
     * @param a1
     *            A value to compare with another.
     * @param a2
     *            A value to compare with another.
     * @return The greater of the two values.
     */
    default A max(final A a1, final A a2) {
        return isGreaterThan(a1, a2) ? a1 : a2;
    }

    /**
     * Returns the lesser of its two arguments.
     *
     * @param a1
     *            A value to compare with another.
     * @param a2
     *            A value to compare with another.
     * @return The lesser of the two values.
     */
    default A min(final A a1, final A a2) {
        return isLessThan(a1, a2) ? a1 : a2;
    }

    /**
     * An order instance for the <code>boolean</code> type.
     */
    static Ord<Boolean> booleanOrd = comparableOrd();

    /**
     * An order instance for the <code>byte</code> type.
     */
    static Ord<Byte> byteOrd = comparableOrd();

    /**
     * An order instance for the <code>char</code> type.
     */
    static Ord<Character> charOrd = comparableOrd();

    /**
     * An order instance for the <code>double</code> type.
     */
    static Ord<Double> doubleOrd = comparableOrd();

    /**
     * An order instance for the <code>float</code> type.
     */
    static Ord<Float> floatOrd = comparableOrd();

    /**
     * An order instance for the <code>int</code> type.
     */
    static Ord<Integer> intOrd = comparableOrd();

    /**
     * An order instance for the <code>BigInteger</code> type.
     */
    static Ord<BigInteger> bigintOrd = comparableOrd();

    /**
     * An order instance for the <code>BigDecimal</code> type.
     */
    static Ord<BigDecimal> bigdecimalOrd = comparableOrd();

    /**
     * An order instance for the <code>long</code> type.
     */
    static Ord<Long> longOrd = comparableOrd();

    /**
     * An order instance for the <code>short</code> type.
     */
    static Ord<Short> shortOrd = comparableOrd();

    /**
     * An order instance for the {@link Ordering} type.
     */
    static Ord<Ordering> orderingOrd = comparableOrd();

    /**
     * An order instance for the {@link String} type.
     */
    static Ord<String> stringOrd = comparableOrd();

    /**
     * An order instance for the {@link Option} type.
     *
     * @param oa
     *            Order across the element of the option.
     * @return An order instance for the {@link Option} type.
     */
    static <A> Ord<Optional<A>> optionOrd(final Ord<A> oa) {
        return (o1, o2) -> o1.isPresent() ?
                o2.isPresent() ?
                        oa.compare(o1.get(), o2.get()) : Ordering.GT
                : o2.isPresent() ? Ordering.LT : Ordering.GT;
    }

    /**
     * An order instance for the {@link List} type.
     *
     * @param oa
     *            Order across the elements of the list.
     * @return An order instance for the {@link List} type.
     */
    static <A> Ord<List<A>> listOrd(final Ord<A> oa) {
        return (l1, l2) -> {
            Ordering tmpOrdering = Ordering.EQ;
            for (int i = 0; (tmpOrdering == Ordering.EQ) && (i < l1.size()) && (i < l2.size()); i++) {
                tmpOrdering = oa.compare(l1.get(i), l2.get(i));
            }
            return tmpOrdering == Ordering.EQ ? intOrd.compare(l1.size(), l2.size()) : tmpOrdering;
        };
    }

    /**
     * An order instance for the {@link Unit} type.
     */
    static Ord<Unit> unitOrd = (u1, u2) -> Ordering.EQ;

    /**
     * An order instance for a product-1.
     *
     * @param oa
     *            Order across the produced type.
     * @return An order instance for a product-1.
     */
    static <P, A> Ord<P> p1Ord(final Function<P, A> getA, final Ord<A> oa) {
        return oa.comap(getA);
    }

    /**
     * An order instance for a product-2, with the first factor considered most significant.
     *
     * @param oa
     *            An order instance for the first factor.
     * @param ob
     *            An order instance for the second factor.
     * @return An order instance for a product-2, with the first factor considered most significant.
     */
    static <P, A, B> Ord<P> p2Ord(final Function<P, A> getA, final Ord<A> oa, final Function<P, B> getB, final Ord<B> ob) {
        return (p1, p2) -> {
            final Ordering aOrdering = oa.compare(getA.apply(p1), getA.apply(p2));
            return aOrdering == Ordering.EQ ? ob.compare(getB.apply(p1), getB.apply(p2)) : aOrdering;
        };
    }

}
