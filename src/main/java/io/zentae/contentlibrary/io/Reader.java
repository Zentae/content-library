package io.zentae.contentlibrary.io;

public interface Reader<T> {
    T read(String path);
}
