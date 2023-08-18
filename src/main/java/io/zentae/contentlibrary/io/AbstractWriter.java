package io.zentae.contentlibrary.io;

import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.zentae.contentlibrary.extension.Extension;

public abstract class AbstractWriter<T> implements Writer<T> {

    private final Extension<T> extension;

    public AbstractWriter(Extension<T> extension) {
        this.extension = extension;
    }

    protected Extension<T> extension() {
        return this.extension;
    }

    public void addSerializer(StdSerializer<?> stdSerializer) {
        extension.registerSerializer(stdSerializer);
    }
}
