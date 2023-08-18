package io.zentae.contentlibrary.extension.module;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import io.zentae.contentlibrary.wrapper.MapWrapper;

import java.io.IOException;
import java.util.Map;

public class MapWrapperSerializer extends StdSerializer<MapWrapper> {

    public MapWrapperSerializer() {
        super(MapWrapper.class);
    }

    @Override
    public void serialize(MapWrapper mapWrapper, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        // write the start of the object.
        jsonGenerator.writeStartObject();
        // loop through each entry.
        for(Map.Entry<String, Object> entrySet : mapWrapper.getValue().entrySet()) {
            jsonGenerator.writeObjectField(entrySet.getKey(), entrySet.getValue());
        }
        // write the end of the object.
        jsonGenerator.writeEndObject();
    }
}
