package io.zentae.contentlibrary.io.json.reader;

import io.zentae.contentlibrary.extension.JsonExtension;
import io.zentae.contentlibrary.file.AdvancedFile;
import io.zentae.contentlibrary.io.AbstractReader;
import io.zentae.contentlibrary.wrapper.MapWrapper;

import java.io.IOException;

public class JsonMapWrapperReader extends AbstractReader<MapWrapper> {

    public JsonMapWrapperReader() {
        super(new JsonExtension<>());
    }

    @Override
    public MapWrapper read(String path) {
        // retrieve and deserialize file.
        try {
            return extension().deserialize(new AdvancedFile(path), MapWrapper.class);
        } catch (IOException exception) {
            throw new RuntimeException("Failed to read wrapper: " + path + " caused by: " + exception.getMessage());
        }
    }
}
