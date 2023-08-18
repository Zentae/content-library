package io.zentae.contentlibrary.io;

import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import io.zentae.contentlibrary.extension.Extension;

public abstract class AbstractReader<T> implements Reader<T> {

    private final Extension<T> extension;

    public AbstractReader(Extension<T> extension) {
        this.extension = extension;
    }

    protected Extension<T> extension() {
        return this.extension;
    }

    public <U> void addDeserializer(Class<U> uClass, StdDeserializer<U> stdDeserializer) {
        extension.registerDeserializer(uClass, stdDeserializer);
    }
}
