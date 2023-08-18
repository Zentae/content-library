package io.zentae.contentlibrary.wrapper;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MapWrapper extends Wrapper<Map<String, Object>> implements Cloneable {

    // Wrapper's identifier.
    private final String mapWrapperIdentifier;

    public MapWrapper() {
        this("root", new LinkedHashMap<>());
    }

    public MapWrapper(String mapWrapperIdentifier, Map<String, Object> map) {
        super(map);
        this.mapWrapperIdentifier = mapWrapperIdentifier;
    }

    public static MapWrapper empty() { return new MapWrapper(); }

    /**
     * @return {@link MapWrapper}'s identifier.
     */
    public String getIdentifier() {
        return this.mapWrapperIdentifier;
    }

    /**
     * Push an {@link Object} into the {@link Map}.
     * @param value the {@link Object} that needs to be stored.
     * @return {@link MapWrapper this}.
     */
    public MapWrapper putParameter(Object value) {
        putParameter(value.getClass().getSimpleName(), value);
        return this;
    }

    /**
     * Push an {@link Object} into the {@link Map} linked with a key.
     * @param key the key paired with the {@link Object}.
     * @param value the {@link Object} that needs to be stored.
     * @return {@link MapWrapper this}.
     */
    @JsonAnySetter
    public MapWrapper putParameter(String key, Object value) {
        getValue().put(key, value);
        return this;
    }

    /**
     * Replaces the entry for the specified key only if it is
     * currently mapped to some value.
     *
     * @implSpec
     * The default implementation is equivalent to, for this {@code map}:
     *
     * <pre> {@code
     * if (map.containsKey(key)) {
     *     return map.put(key, value);
     * } else
     *     return null;
     * }</pre>
     *
     * <p>The default implementation makes no guarantees about synchronization
     * or atomicity properties of this method. Any implementation providing
     * atomicity guarantees must override this method and document its
     * concurrency properties.
     *
     * @param key key with which the specified value is associated
     * @param value value to be associated with the specified key
     * @return this instance of {@link MapWrapper}.
     * @throws UnsupportedOperationException if the {@code put} operation
     *         is not supported by this map
     *         (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws ClassCastException if the class of the specified key or value
     *         prevents it from being stored in this map
     *         (<a href="{@docRoot}/java.base/java/util/Collection.html#optional-restrictions">optional</a>)
     * @throws NullPointerException if the specified key or value is null,
     *         and this map does not permit null keys or values
     * @throws IllegalArgumentException if some property of the specified key
     *         or value prevents it from being stored in this map
     * @since 1.8
     */
    public MapWrapper replaceParameter(String key, Object value) {
        getValue().replace(key, value);
        return this;
    }

    /**
     * @param key the key paired to the {@link Object}.
     * @return a parameter {@link Object} from its key.
     */
    public Object getParameter(String key) {
        return super.getValue().get(key);
    }

    /**
     * @param key the key paired to the {@link Object}.
     * @param adapter the {@link Function adapter} to transform the object.
     * @param <T> the target type.
     * @return a parameter by its key.
     */
    public <T> T getParameter(String key, Function<Object, T> adapter) {
        return adapter.apply(getParameter(key));
    }

    /**
     * @param tClass {@link Object}'s {@link Class}.
     * @param <T> {@link Object}'s type.
     * @return stored {@link Object} from the {@link Map} by its {@link Class}.
     */
    public <T> T getParameter(Class<T> tClass) {
        return getParameter(tClass.getSimpleName(), tClass);
    }

    /**
     * @param key {@link Object}'s paired key.
     * @param tClass {@link Object}'s {@link Class}.
     * @param <T> {@link Object}'s type.
     * @return stored {@link Object} from the {@link Map} by its {@link Class} and key.
     */
    public <T> T getParameter(String key, Class<T> tClass) {
        return tClass.cast(getValue().get(key));
    }

    public <T> T getOrDefault(String key, Class<T> tClass, T value) {
        return getValue().get(key) == null ? value : tClass.cast(getValue().get(key));
    }

    /**
     * @return all the parameters contained in this {@link MapWrapper}.
     */
    public Collection<Object> getParameters() {
        return super.getValue().values();
    }

    /**
     * Removes a key from the {@link MapWrapper}.
     * @param key the key to remove.
     */
    public void remove(String key) {
        super.getValue().remove(key);
    }

    /**
     * @return all the keys contained in this {@link MapWrapper}.
     */
    public Collection<String> getKeys() {
        return super.getValue().keySet();
    }

    /**
     * @param keyClass the key class to match.
     * @return all the keys corresponding to the given class in this {@link MapWrapper}.
     */
    public Collection<String> getKeys(Class<?> keyClass) {
        return super.getValue().keySet().stream().filter(s ->
                getParameter(s).getClass().equals(keyClass)).collect(Collectors.toList());
    }

    /**
     * @param key the key to match.
     * @return whether the given key exists in this {@link MapWrapper}.
     */
    public boolean containsKey(String key) {
        return super.getValue().containsKey(key);
    }

    /**
     * @param key the identifier corresponding to the {@link MapWrapper}.
     * @return the specified {@link MapWrapper} by its identifier.
     */
    public MapWrapper getSubMapWrapper(String key) {
        // Will throw class cast exception.
        Map<String, Object> subWrapper = getParameter(key, Map.class);
        // Check if not found.
        if(subWrapper == null)
            throw new NoSuchElementException("Unknown sub-wrapper: " + key);
        // Return sub-wrapper
        return new MapWrapper(key, subWrapper);
    }

    /**
     * @return all the {@link MapWrapper} contained in this {@link MapWrapper}.
     */
    public Collection<MapWrapper> getSubMapWrappers() {
        List<MapWrapper> mapWrapperList = new ArrayList<>();
        // Loop through each key and value.
        for(Map.Entry<String, Object> entrySet : getValue().entrySet()) {
            Object value = entrySet.getValue();
            // Check if we have a sub wrapper.
            if(!(value instanceof Map<?,?> map))
                continue;
            // Add our sub-wrapper into our result.
            mapWrapperList.add(new MapWrapper(entrySet.getKey(), (Map<String, Object>)map));
        }
        // Return our query result.
        return mapWrapperList;
    }

    @Override
    public String toString() {
        return "MapWrapper@" + this.hashCode() + "{" +
                "wrappedValues=" + getValue() +
                "}";
    }

    /**
     * Please note that this is a shallow copy ONLY not a deep-copy.
     * <br>
     * It means that only the reference of the {@link Map} is changed not its internals.
     * @return a shallow copy of this {@link MapWrapper}.
     */
    @Override
    public MapWrapper clone() {
        try {
            MapWrapper clone = (MapWrapper) super.clone();
            // clone ONLY the map.
            Map<String, Object> wrapper = clone.getValue();
            clone.setValue(new HashMap<>(wrapper));
            // return the cloned object.
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
