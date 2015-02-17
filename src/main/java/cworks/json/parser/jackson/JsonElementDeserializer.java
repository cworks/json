package cworks.json.parser.jackson;

import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.util.Converter;
import cworks.json.Json;
import cworks.json.JsonElement;

import java.util.Map;

public class JsonElementDeserializer extends StdDelegatingDeserializer<JsonElement> {

    public JsonElementDeserializer(Converter<Map, JsonElement> converter) {
        super(converter);
    }
    
    protected JsonElement convert(Object object) {

        System.out.println(object.toString());
        return Json.object().build();
    }
}
