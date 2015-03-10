package cworks.json.impl.jackson;

import com.fasterxml.jackson.databind.module.SimpleModule;
import cworks.json.JsonArray;
import cworks.json.JsonObject;

public class JacksonIOModule extends SimpleModule {
    
    public JacksonIOModule() {
        super("JacksonIOModule");
        addDeserializer(JsonObject.class, new JacksonJsonObjectDeserializer());
        addDeserializer(JsonArray.class, new JacksonJsonArrayDeserializer());
        addSerializer(JsonObject.class, new JacksonJsonObjectSerializer());
        addSerializer(JsonArray.class, new JacksonJsonArraySerializer());
    }
}
