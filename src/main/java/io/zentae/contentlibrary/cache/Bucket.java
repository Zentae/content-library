package io.zentae.contentlibrary.cache;

import java.util.ArrayList;
import java.util.Collection;

public abstract class Bucket<K, V> {

    // the actual cache.
    private final Collection<V> cache;

    public Bucket(Collection<V> cache) {
        this.cache = cache;
    }

    public Bucket() {
        this.cache = new ArrayList<>();
    }

    /**
     * Register the value into the cache.
     * @param value the value to register.
     */
    public void register(V value) {
        this.cache.add(value);
    }

    public void unregister(V value) {
        cache.remove(value);
    }

    /**
     * If present, return the value corresponding to the given key.
     * @param key the given key.
     * @return the value corresponding to the given key.
     */
    public abstract Collection<V> of(K key);

    /**
     * @return the cache.
     */
    public Collection<V> getCache() {
        return this.cache;
    }
}
