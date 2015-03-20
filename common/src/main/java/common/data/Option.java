package common.data;

public abstract class Option<A> {

    // Option <A> = QuelqueChose <A> | Rien

    public interface Case<A, R> {

        R something(A a);

        R nothing();

    }

    abstract <R> R match(Case<A, R> cases);

    public static void main(final String[] args) {
        final Option<Integer> o1 = something(1);
        final Option<Integer> o2 = nothing();
        System.out.println(afficher(o1));
        System.out.println(afficher(o2));
    }

    public static <A> Option<A> something(final A a) {
        return new Option<A>() {
            @Override
            <R> R match(final Case<A, R> cases) {
                return cases.something(a);
            }
        };
    }

    public static <A> Option<A> nothing() {
        return new Option<A>() {
            @Override
            <R> R match(final Case<A, R> cases) {
                return cases.nothing();
            }
        };
    }

    static String afficher(final Option<Integer> os) {
        return os.match(new Case<Integer, String>() {
            @Override
            public String something(final Integer a) {
                return "Something(" + a + ")";
            }

            @Override
            public String nothing() {
                return "Nothing()";
            }
        });

    }
}
