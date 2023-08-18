package io.zentae.contentlibrary.io;

public interface Writer<T> {

    void write(T data, String path);
}
