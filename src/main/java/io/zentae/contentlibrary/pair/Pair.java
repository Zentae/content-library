package io.zentae.contentlibrary.pair;

public class Pair {

    private static final Pair EMPTY = new Pair(new Object(), new Object());
    private final Object a;
    private final Object b;

    private Pair(Object a, Object b) {
        this.a = a;
        this.b = b;
    }

    public Object getA() {
        return this.a;
    }

    public Object getB() {
        return this.b;
    }

    @Override
    public int hashCode() {
        // makes the pair unique & comparable
        // via the #equals method.
        return a.hashCode() + b.hashCode();
    }

    public static Pair empty() {
        return EMPTY;
    }

    public static Pair of(Object a, Object b) {
        return new Pair(a, b);
    }
}
