/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Baked with love by corbett
 * Project: json
 * Package: cworks.json.parser
 * Class: JacksonParser
 * Created: 8/1/14 3:40 PM
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package cworks.json.parser.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import cworks.json.*;
import cworks.json.parser.JsonParser;

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
            object = mapper.readValue(toWrapper(json), clazz);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return object;
    }

    @Override
    public <T> T[] toArray(String json, Class<T> clazz) throws JsonException {
        
        T[] array;
        
        try {
            ArrayType arrayType = mapper.getTypeFactory().constructArrayType(clazz);
            array = mapper.readValue(toWrapper(json), arrayType);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }
        
        return array;
    }
    
    @Override
    public List<JsonElement> toList(String json) throws JsonException {
        
        List<JsonElement> list;
        
        try {
            Map map = toObject(json, Map.class);
            //element = new JsonObject(map);

            JsonFactory factory = mapper.getFactory();

            com.fasterxml.jackson.core.JsonParser parser = factory.createParser(json);
            com.fasterxml.jackson.core.JsonParser wrapper = new JsonParserDelegate(parser) {
                public String getCurrentName() throws IOException {
                    return JsonJavaUtils.toMethodName(delegate.getCurrentName());
                }
                
                
                

            };
            
            
            
        } catch (Exception ex) {
            throw new JsonException(ex);
        }
        
        return null;
    }
    
    @Override
    public <T> List<T> toList(String json, Class<T> clazz) throws JsonException {

        List<T> list;

        try {
            CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
            list = mapper.readValue(toWrapper(json), collectionType);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return list;
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

    /**
     * Create a wrapper around the Jackson parser so that we can tweak some features
     * to our liking
     * *
     * @param json json text
     * @return jackson parser
     * @throws IOException
     */
    private com.fasterxml.jackson.core.JsonParser toWrapper(String json) throws IOException {
        JsonFactory factory = mapper.getFactory();
        com.fasterxml.jackson.core.JsonParser parser = factory.createParser(json);

        return new JsonParserDelegate(parser) {
            public String getCurrentName() throws IOException {
                //
                // implements flex field name mapping (i.e. some_property == someProperty)
                //
                return JsonJavaUtils.toMethodName(delegate.getCurrentName());
            }
            

        };
    }


}
