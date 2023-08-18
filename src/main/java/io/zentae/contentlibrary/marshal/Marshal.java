package io.zentae.contentlibrary.marshal;

public interface Marshal<K, V> {

    K serialize(V data) throws Exception;
    V deserialize(K data) throws Exception;
}
