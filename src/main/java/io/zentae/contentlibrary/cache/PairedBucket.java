package io.zentae.contentlibrary.cache;

import java.util.HashMap;
import java.util.Map;

public abstract class PairedBucket<K, V> {

    // the actual cache.
    private final Map<K, V> cache;

    public PairedBucket() {
        this.cache = new HashMap<>();
    }

    public PairedBucket(Map<K, V> cache) {
        this.cache = cache;
    }

    /**
     * Register the value into the cache paired to the key.
     * @param key the key to register.
     * @param value the value to register.
     */
    public void register(K key, V value) {
        this.cache.put(key, value);
    }

    public void unregister(K key, V value) {
        cache.remove(key, value);
    }

    public void unregister(K key) {
        cache.remove(key);
    }

    /**
     * @param key the key to check for.
     * @return whether this {@link PairedBucket} contains or not the given key.
     */
    public boolean containsKey(K key) {
        return cache.containsKey(key);
    }

    /**
     * If present, return the value paired with its key.
     * @param key the key paired to the value.
     * @return the value paired with its key.
     */
    public V of(K key) {
        return cache.get(key);
    }

    /**
     * @return the cache.
     */
    public Map<K, V> getCache() {
        return this.cache;
    }
}
