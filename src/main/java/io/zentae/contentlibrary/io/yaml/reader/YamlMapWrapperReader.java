package io.zentae.contentlibrary.io.yaml.reader;

import io.zentae.contentlibrary.extension.YamlExtension;
import io.zentae.contentlibrary.file.AdvancedFile;
import io.zentae.contentlibrary.io.AbstractReader;
import io.zentae.contentlibrary.wrapper.MapWrapper;

import java.io.IOException;

public class YamlMapWrapperReader extends AbstractReader<MapWrapper> {


    public YamlMapWrapperReader() {
        super(new YamlExtension<>());
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
