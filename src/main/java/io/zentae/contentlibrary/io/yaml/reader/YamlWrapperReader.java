package io.zentae.contentlibrary.io.yaml.reader;

import io.zentae.contentlibrary.extension.YamlExtension;
import io.zentae.contentlibrary.file.AdvancedFile;
import io.zentae.contentlibrary.io.AbstractReader;
import io.zentae.contentlibrary.wrapper.Wrapper;

import java.io.IOException;

public class YamlWrapperReader extends AbstractReader<Wrapper> {

    public YamlWrapperReader() {
        super(new YamlExtension<>());
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
