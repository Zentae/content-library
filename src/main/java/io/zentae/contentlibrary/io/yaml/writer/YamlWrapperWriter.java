package io.zentae.contentlibrary.io.yaml.writer;

import io.zentae.contentlibrary.extension.YamlExtension;
import io.zentae.contentlibrary.file.AdvancedFile;
import io.zentae.contentlibrary.io.AbstractWriter;
import io.zentae.contentlibrary.wrapper.Wrapper;

import java.io.IOException;
import java.util.Objects;

public class YamlWrapperWriter extends AbstractWriter<Wrapper> {

    public YamlWrapperWriter() {
        super(new YamlExtension<>());
    }

    @Override
    public void write(Wrapper data, String path) {
        Objects.requireNonNull(path, "Path cannot be null !");
        Objects.requireNonNull(data, "Data cannot be null !");
        // retrieve and deserialize file.
        try {
           extension().serialize(new AdvancedFile(path), data);
        } catch (IOException exception) {
            throw new RuntimeException("Failed to write wrapper: " + path + " caused by: " + exception.getMessage());
        }
    }
}
