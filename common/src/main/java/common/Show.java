package common;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * Renders an object for display.
 */
public interface Show<A> extends Function<A, List<String>> {

    /**
     * Returns the display rendering of the given argument.
     *
     * @param a
     *            The argument to display.
     * @return The display rendering of the given argument.
     */
    List<String> show(final A a);

    /**
     * Returns the display rendering of the given argument as a <code>String</code>.
     *
     * @param a
     *            The argument to display.
     * @return The display rendering of the given argument as a <code>String</code>.
     */
    default String showS(final A a) {
        final StringBuilder sb = new StringBuilder();
        for (final String s : show(a)) {
            sb.append(s);
        }
        return sb.toString();
    }

    /**
     * Returns the transformation equivalent to this show.
     *
     * @return the transformation equivalent to this show.
     */
    default Function<A, String> showS_() {
        return a -> showS(a);
    }

    @Override
    default List<String> apply(final A a) {
        return show(a);
    }

    /**
     * Returns a show instance using the given function.
     *
     * @param f
     *            The function to use for the returned show instance.
     * @return A show instance.
     */
    public static <A> Show<A> showS(final Function<A, String> f) {
        return new Show<A>() {
            @Override
            public List<String> show(final A a) {
                return Collections.singletonList(f.apply(a));
            }

            @Override
            public String showS(final A a) {
                return f.apply(a);
            }
        };
    }

    /**
     * Returns a show instance that uses {@link Object#toString()} to perform the display rendering.
     *
     * @return A show instance that uses {@link Object#toString()} to perform the display rendering.
     */
    public static <A> Show<A> anyShow() {
        return showS(a -> String.valueOf(a));
    }

    /**
     * A show instance for the <code>boolean</code> type.
     */
    Show<Boolean> booleanShow = anyShow();

    /**
     * A show instance for the <code>byte</code> type.
     */
    Show<Byte> byteShow = anyShow();

    /**
     * A show instance for the <code>char</code> type.
     */
    Show<Character> charShow = anyShow();

    /**
     * A show instance for the <code>double</code> type.
     */
    Show<Double> doubleShow = anyShow();

    /**
     * A show instance for the <code>float</code> type.
     */
    Show<Float> floatShow = anyShow();

    /**
     * A show instance for the <code>int</code> type.
     */
    Show<Integer> intShow = anyShow();

    /**
     * A show instance for the <code>BigInteger</code> type.
     */
    Show<BigInteger> bigintShow = anyShow();

    /**
     * A show instance for the <code>BigDecimal</code> type.
     */
    Show<BigDecimal> bigdecimalShow = anyShow();

    /**
     * A show instance for the <code>long</code> type.
     */
    Show<Long> longShow = anyShow();

    /**
     * A show instance for the <code>short</code> type.
     */
    Show<Short> shortShow = anyShow();

    /**
     * A show instance for the {@link String} type.
     */
    Show<String> stringShow = anyShow();

    /**
     * @return A show instance for an {@link Enum} type.
     */
    static <E extends Enum<E>> Show<E> enumShow() {
        return anyShow();
    };

    /**
     * @return A show instance for an {@link List} type.
     */
    static <A> Show<List<A>> listShow(final Show<A> show) {
        return l -> wrapList("List(", showStringList(l.stream().map(show)), ")");
    };

    /**
     * @return A show instance for an {@link List} type.
     */
    static <A> Show<Stream<A>> streamShow(final Show<A> show) {
        return s -> wrapList("Stream(", showStringList(s.map(show)), ")");
    };

    /**
     * show content of a string stream
     */
    static <A> List<String> showStringList(final Stream<List<String>> stream) {
        return Monoid.<String> listMonoid().join(stream, Collections.singletonList(", "));
    };

    /**
     * Wrap a stream between two element
     */
    static <A> List<A> wrapList(final A prefix, final List<A> list, final A suffix) {
        return Monoid.<A> listMonoid().sum(
                Arrays.asList(Collections.singletonList(prefix), list, Collections.singletonList(suffix)));
    };

    /**
     * @return A show instance for an {@link Enum} type.
     */
    static <P, A, B> Show<P> p2Show(final String pName, final Function<P, A> getA, final Show<A> showA,
            final Function<P, B> getB,
            final Show<B> showB) {
        return p2 -> Monoid.<String> listMonoid().sum(Arrays.asList(
                Collections.singletonList(pName + "("),
                showA.show(getA.apply(p2)),
                Collections.singletonList(", "),
                showB.show(getB.apply(p2)),
                Collections.singletonList(")")
                ));
    };

}
