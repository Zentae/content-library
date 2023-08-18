package io.zentae.contentlibrary.cache;

import java.util.*;

public class MapSet<T> extends AbstractSet<T> {

    private final Map<Integer, T> mapCache = new HashMap<>();

    public MapSet() {}

    public MapSet(Collection<? extends T> c) {
        addAll(c);
    }

    @Override
    public boolean add(T t) {
        mapCache.put(hash(t), t);
        return true;
    }

    @Override
    public boolean remove(Object o) {
        mapCache.remove(hash(o));
        return true;
    }

    public T get(Object o) {
        return mapCache.get(hash(o));
    }

    @Override
    public void clear() {
        mapCache.clear();
    }

    @Override
    public boolean contains(Object o) {
        return mapCache.get(hash(o)) != null;
    }

    @Override
    public Iterator<T> iterator() {
        return mapCache.values().iterator();
    }

    @Override
    public int size() {
        return mapCache.size();
    }

    private int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }
}
