/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Baked with love by corbett
 * Project: json
 * Package: net.cworks.json.parser
 * Class: JacksonParser
 * Created: 8/1/14 3:40 PM
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package net.cworks.json.parser.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import net.cworks.json.*;
import net.cworks.json.parser.JsonParser;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JacksonParser extends JsonParser {

    private final ObjectMapper mapper;

    public JacksonParser() {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS, true);
    }

    @Override
    public JsonElement toObject(String json) throws JsonException {

        JsonElement element = null;

        try {
            if (json.startsWith("[")) {
                List list = toObject(json, List.class);
                element = new JsonArray(list);
            } else if (json.startsWith("{")) {
                Map map = toObject(json, Map.class);
                element = new JsonObject(map);
            }
        } catch(Exception ex) {
            throw new JsonException(ex);
        }

        if(element == null) {
            throw new JsonException("json argument isn't valid Json");
        }

        return element;
    }

    @Override
    public <T> T toObject(String json, Class<T> clazz) throws JsonException {

        T object;
        try {
            JsonFactory factory = mapper.getFactory();
            com.fasterxml.jackson.core.JsonParser parser = factory.createParser(json);
            com.fasterxml.jackson.core.JsonParser wrapper = new JsonParserDelegate(parser) {
                public String getCurrentName() throws IOException {
                    return JsonJavaUtils.toMethodName(delegate.getCurrentName());
                }
            };
            object = mapper.readValue(wrapper, clazz);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return object;
    }

    @Override
    public String toJson(JsonElement element) throws JsonException {
        if(element.isObject()) {
            Map data = ((JsonObject)element).toMap();
            return toJson(data);
        } else if(element.isArray()) {
            Object[] data = ((JsonArray)element).toArray();
            return toJson(data);
        }

        return "";
    }

    @Override
    public String toJson(Object obj) throws JsonException {

        String json;
        try {
            if(pretty) {
                mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                json = mapper.writeValueAsString(obj);
            } else {
                mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
                json = mapper.writeValueAsString(obj);
            }
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return json;
    }


}
