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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.JsonParserDelegate;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.util.StdConverter;
import cworks.json.*;
import cworks.json.parser.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
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

        
        SimpleModule module = new SimpleModule("cworksJacksonModule");
        module.addDeserializer(JsonObject.class,
            new StdDelegatingDeserializer<>(
                new StdConverter<Map, JsonObject>() {
                    @Override
                    public JsonObject convert(Map map) {
                        return new JsonObject(map);
                    }
                }
            ));

        mapper.registerModule(module);
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
            object = mapper.readValue(wrapper(json), clazz);
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
            array = mapper.readValue(wrapper(json), arrayType);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }
        
        return array;
    }
    
    @Override
    public List<JsonObject> toList(String json) throws JsonException {
        
        List<JsonObject> list;
        
        try {
            CollectionType collectionType = mapper.getTypeFactory()
                .constructCollectionType(List.class, JsonObject.class);
            list = mapper.readValue(wrapper(json), collectionType);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }
        
        return list;
    }
    
    public void read(final InputStream json, final JsonHandler handler) throws JsonException {

        try {

            //MapType mapType = mapper.getTypeFactory().constructMapType(LinkedHashMap.class, String.class, Object.class);
            //CollectionType collectionType = mapper.getTypeFactory().constructCollectionType(List.class, mapType);
            
            MappingIterator<Map> it = mapper.reader(HashMap.class).readValues(json);
            
            while(it.hasNextValue()) {
                Map value = it.nextValue();
                if(value == null) {
                    //handler.complete(new JsonContext() {});
                }
                //handler.handle(value, new JsonContext() {});
            }
            handler.complete(new JsonContext() {});
        } catch (IOException ex) {
            throw new JsonException(ex);
        }
    }

    @Override
    public <T> List<T> toList(String json, Class<T> clazz) throws JsonException {

        List<T> list;

        try {
            CollectionType collectionType = mapper.getTypeFactory()
                .constructCollectionType(List.class, clazz);
            list = mapper.readValue(wrapper(json), collectionType);
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
    private com.fasterxml.jackson.core.JsonParser wrapper(String json) throws IOException {
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

    private com.fasterxml.jackson.core.JsonParser wrapper(InputStream json) throws IOException {
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
