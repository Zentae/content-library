package io.zentae.contentlibrary.extension;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.zentae.contentlibrary.extension.module.MapWrapperSerializer;

import java.io.File;
import java.io.IOException;

public abstract class Extension<T> {

    private final ObjectMapper mapper;

    public Extension(JsonFactory factory) {
        this.mapper = new ObjectMapper(factory);
        registerModule(new SimpleModule().addSerializer(new MapWrapperSerializer()));
    }

    protected void registerModule(SimpleModule module) {
        if(module == null)
            throw new NullPointerException("module cannot be null");
        mapper.registerModule(module);
    }

    public void registerSerializer(StdSerializer<?> stdSerializer) {
        mapper.registerModule(new SimpleModule().addSerializer(stdSerializer));
    }

    public <K> void registerDeserializer(Class<K> clazz, StdDeserializer<K> stdDeserializer) {
        mapper.registerModule(new SimpleModule().addDeserializer(clazz, stdDeserializer));
    }

    /**
     * Serialize the given {@link Object} into the given {@link File}.
     * @param file the {@link File} where the {@link Object} needs to be stored.
     * @param data the {@link Object} to be stored.
     * @throws IOException read / write exception.
     */
    public void serialize(File file, T data) throws IOException {
        if(file == null)
            throw new RuntimeException("The file cannot be null when serializing.");
        this.mapper.writeValue(file, data);
    }

    /**
     * Deserialize the {@link Object data} from the given {@link File}.
     * @param file the {@link File} where the {@link Object data} is stored.
     * @param tClass the type of {@link Object data}.
     * @return the {@link Object data}.
     * @throws IOException read / write exception.
     */
    public T deserialize(File file, Class<T> tClass) throws IOException {
        if(file == null)
            throw new RuntimeException("The file cannot be null when deserializing.");
        return mapper.readValue(file, tClass);
    }

    public static Extension<?> YAML() {
        return new YamlExtension<>();
    }

    public static Extension<?> JSON() {
        return new JsonExtension<>();
    }
}
