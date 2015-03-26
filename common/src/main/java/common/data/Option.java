package common.data;

import java.util.function.Function;
import java.util.function.Supplier;

public abstract class Option<A> {

    // Option <A> = QuelqueChose <A> | Rien

    abstract <R> R match(Function<A, R> something, Supplier<R> nothing);

    public static void main(final String[] args) {
        final Option<Integer> o1 = something(1);
        final Option<Integer> o2 = nothing();
        System.out.println(afficher(o1));
        System.out.println(afficher(o2));
    }

    public static <A> Option<A> something(final A a) {
        return new Option<A>() {
            @Override
            <R> R match(final Function<A, R> something, final Supplier<R> nothing) {
                return something.apply(a);
            }
        };
    }

    public static <A> Option<A> nothing() {
        return new Option<A>() {
            @Override
            <R> R match(final Function<A, R> something, final Supplier<R> nothing) {
                return nothing.get();
            }
        };
    }

    static String afficher(final Option<Integer> os) {
        return os.match(
                (Integer i) -> "Something(" + i + ")",
                () -> "Nothing()");

    }
}
