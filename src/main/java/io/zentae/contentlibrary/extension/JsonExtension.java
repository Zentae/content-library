package io.zentae.contentlibrary.extension;

import com.fasterxml.jackson.core.JsonFactory;

public class JsonExtension<T> extends Extension<T> {

    public JsonExtension() {
        super(new JsonFactory());
    }
}
