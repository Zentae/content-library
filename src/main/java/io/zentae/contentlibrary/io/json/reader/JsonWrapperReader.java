package io.zentae.contentlibrary.io.json.reader;

import io.zentae.contentlibrary.extension.JsonExtension;
import io.zentae.contentlibrary.file.AdvancedFile;
import io.zentae.contentlibrary.io.AbstractReader;
import io.zentae.contentlibrary.wrapper.Wrapper;

import java.io.IOException;

public class JsonWrapperReader extends AbstractReader<Wrapper> {

    public JsonWrapperReader() {
        super(new JsonExtension<>());
    }

    @Override
    public Wrapper read(String path) {
        // retrieve and deserialize file.
        try {
            return extension().deserialize(new AdvancedFile(path), Wrapper.class);
        } catch (IOException exception) {
            throw new RuntimeException("Failed to read wrapper: " + path + " caused by: " + exception.getMessage());
        }
    }
}
