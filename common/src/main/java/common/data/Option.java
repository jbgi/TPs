package common.data;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import common.Unit;

public abstract class Option<A> {

    // Option <A> = QuelqueChose <A> | Rien

    public abstract <R> R match(Function<A, R> something, Supplier<R> nothing);

    public <U> Option<U> map(final Function<A, U> mapper) {
        return this.match(
                (final A a) -> Option.something(mapper.apply(a)),
                () -> Option.<U> nothing());
    }

    public void forEach(final Consumer<A> consummer) {
        this.match(
                (final A a) -> {
                    consummer.accept(a);
                    return Unit.unit();
                },
                Unit::unit);
    }

    public A orElse(final A other) {
        return match(
                a -> a,
                () -> other);
    }

    public static <A> Option<A> something(final A a) {
        return new Something<>(a);
    }

    public static <A> Option<A> nothing() {
        return new Nothing<>();
    }

    private static final class Nothing<A> extends Option<A> {
        @Override
        public <R> R match(final Function<A, R> something, final Supplier<R> nothing) {
            return nothing.get();
        }
    }

    private static final class Something<A> extends Option<A> {

        private final A a;

        private Something(final A a) {
            this.a = a;
        }

        @Override
        public <R> R match(final Function<A, R> something, final Supplier<R> nothing) {
            return something.apply(a);
        }

    }
}
