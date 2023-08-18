package io.zentae.contentlibrary.cache;

import java.util.*;

/*
Abstract list partial implementation.
It is basically a map disguised in a list;

Performances:
#contains() -> performs better than ArrayList both in large & small collections (1000 / 100.000 elements).
#add() -> performs worse than ArrayList both in large & small collections (1000 elements)
#get() -> performs slightly worse than ArrayList on small collections (1000 elements)
 */
public class MapList<T> extends AbstractList<T> {

    private final Map<Integer, T> mapCache = new HashMap<>();
    // keeps the index bound to the object's hashcode.
    private final List<T> indexKeeper = new ArrayList<>();

    public MapList() {}

    public MapList(Collection<? extends T> c) {
        addAll(c);
    }

    @Override
    public boolean add(T t) {
        int h = hash(t);
        indexKeeper.add(t);
        mapCache.put(h, t);
        return true;
    }

    @Override
    public T remove(int index) {
        T t = get(index);
        int h = indexKeeper.get(index).hashCode();
        indexKeeper.remove(index);
        mapCache.remove(h);
        return t;
    }

    @Override
    public T get(int index) {
        return indexKeeper.get(index);
    }

    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }

    @Override
    public boolean contains(Object o) {
        return mapCache.get(hash(o)) != null;
    }

    @Override
    public int size() {
        return mapCache.size();
    }
}
