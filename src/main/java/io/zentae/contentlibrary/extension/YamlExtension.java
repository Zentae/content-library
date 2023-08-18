package io.zentae.contentlibrary.extension;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

public class YamlExtension<T> extends Extension<T> {

    public YamlExtension() {
        super(new YAMLFactory());
    }
}
