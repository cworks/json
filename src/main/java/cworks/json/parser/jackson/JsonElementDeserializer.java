package cworks.json.parser.jackson;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import cworks.json.JsonElement;

import java.io.IOException;

public class JsonElementDeserializer extends StdDeserializer<JsonElement> {


    protected JsonElementDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public JsonElement deserialize(JsonParser parser, DeserializationContext context)
        throws IOException, JsonProcessingException {

        return null;
    }
}
