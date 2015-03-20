package nfa035.tp4;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class Exercice4 {

    public static void main(final String[] args) {

        final ArrayList<String> ls = new ArrayList<String>();

        ls.add("A");
        ls.add("B");
        ls.add("C");
        ls.add("D");
        ls.add("E");
        // ls.add(5, "F");

        // ls.set(3, "element replace");
        System.out.println(afficheList(ls));
        ls.remove(1);
        System.out.println(afficheList(ls));
        // ls.remove(20);
        // ls.add(25, "Z");

        System.out.println(ls.contains(23)); // compile mais ça n'a pas de sens (bug)
        // System.out.println(safeContains(ls, 23)); : ne compile pas
        System.out.println("Contient A :" + safeContains(ls, "A"));
        final int indexOfD = ls.indexOf("D");
        if (indexOfD == -1) {
            System.out.println("ne contient pas D");
        }
        else {
            System.out.println("Contient D à l'index:" + indexOfD);
        }
        ls.add("D");
        System.out.println("index dernière occurence de D :" + ls.lastIndexOf("D"));
    }

    static boolean safeContains(final Collection<Integer> collection, final Integer e) {
        return collection.contains(e);
    }

    static boolean safeContains(final Collection<Long> collection, final Long e) {
        return collection.contains(e);
    }

    static <E> boolean safeContains(final Collection<E> collection, final E e) {
        return collection.contains(e);
    }

    static String afficheList(final List<String> l) {
        final StringBuilder str = new StringBuilder();
        for (int i = 0; i < l.size(); i++) {
            str.append(l.get(i));
            str.append(" ");
        }
        return str.toString();
    }

    static String afficheList_for_each(final Iterable<String> l) {
        final StringBuilder str = new StringBuilder();
        for (final String s : l) {
            str.append(s);
            str.append(" ");
        }
        return str.toString();
    }

    static String afficheList_iterator(final Iterable<String> l) {
        final StringBuilder str = new StringBuilder();
        final Iterator<String> it = l.iterator();
        while (it.hasNext()) {
            final String s = it.next();
            str.append(s);
            str.append(" ");
        }
        return str.toString();
    }

    static String afficheList_stream(final Collection<String> l) {
        final StringBuilder str = new StringBuilder();
        l.stream().forEachOrdered(s -> {
            str.append(s);
            str.append(" ");
        });
        return str.toString();
    }

    static ArrayList<String> replaceStrInLs(final ArrayList<String> l, final int pos, final String str) {
        l.set(pos, str);
        return l;
    }

}
