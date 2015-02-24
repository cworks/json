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
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.util.StdConverter;
import cworks.json.JsonArray;
import cworks.json.JsonContext;
import cworks.json.JsonElement;
import cworks.json.JsonException;
import cworks.json.JsonHandler;
import cworks.json.JsonNull;
import cworks.json.JsonObject;
import cworks.json.parser.JsonParser;
import cworks.json.streaming.StreamHandler;

import java.io.IOException;
import java.io.InputStream;
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

    public void read(final String json, final JsonHandler handler) throws JsonException {

        try {
            com.fasterxml.jackson.core.JsonParser jp = wrapper(json);

            jp.nextToken();

            while(jp.nextToken() == JsonToken.START_OBJECT) {
                final JsonObject thing = mapper.readValue(jp, JsonObject.class);
                if(thing == null) {
                    handler.complete(new JsonContext() { });
                    break;
                }
                handler.handle(thing, new JsonContext() {});
            }
        } catch (IOException ex) {
            throw new JsonException(ex);
        }
    }

    public <T> void read(InputStream in, Class<T> clazz, StreamHandler<T> handler) throws JsonException {
        
        try {
            com.fasterxml.jackson.core.JsonParser jp = wrapper(in);
            JsonToken firstToken = jp.nextToken();
            if(firstToken == JsonToken.START_ARRAY) {
                readArray(jp, clazz, handler);
            } else if(firstToken == JsonToken.START_OBJECT) {
                readObject(jp, clazz, handler);
            }
        } catch(IOException ex) {
            throw new JsonException(ex);
        }
    }
    
    public void read(InputStream in, StreamHandler<Object> handler) throws JsonException {

        try {
            com.fasterxml.jackson.core.JsonParser jp = wrapper(in);
            JsonToken firstToken = jp.getCurrentToken();
            if(firstToken == JsonToken.START_ARRAY) {
                readArray(jp, handler);
            } else if(firstToken == JsonToken.START_OBJECT) {
                readObject(jp, handler);
            }
        } catch(IOException ex) {
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

    private void readArray(com.fasterxml.jackson.core.JsonParser jp, StreamHandler<Object> handler)
        throws IOException {
        
        JsonToken token = jp.nextToken();
        while(token != null) {

            if(token == JsonToken.VALUE_FALSE || token == JsonToken.VALUE_TRUE) {
                Boolean value = mapper.readValue(jp, Boolean.class);
                handler.handle(value);
                continue;
            }
            
            if(token == JsonToken.VALUE_NUMBER_INT) {
                Integer value = mapper.readValue(jp, Integer.class);
                handler.handle(value);
                continue;
            }
            
            if(token == JsonToken.VALUE_NUMBER_FLOAT) {
                Double value = mapper.readValue(jp, Double.class);
                handler.handle(value);
                continue;
            }
            
            if(token == JsonToken.VALUE_STRING) {
                String value = mapper.readValue(jp, String.class);
                handler.handle(value);
                continue;
            }
            
            if(token == JsonToken.VALUE_NULL) {
                jp.nextToken(); // skip over null value
                handler.handle(new JsonNull());
                continue;
            }
            
            if(token == JsonToken.START_OBJECT) {
                JsonObject object = mapper.readValue(jp, JsonObject.class);
                handler.handle(object);
                continue;
            }
            
            // move to next token
            token = jp.nextToken();
        }
    }
    
    private <T> void readArray(com.fasterxml.jackson.core.JsonParser jp, Class<T> clazz, StreamHandler<T> handler) throws IOException {
        
        JsonToken token = jp.nextToken();
        while(token != null) {

            if (token == JsonToken.START_OBJECT ||
                token == JsonToken.VALUE_FALSE ||
                token == JsonToken.VALUE_TRUE ||
                token == JsonToken.VALUE_NUMBER_FLOAT ||
                token == JsonToken.VALUE_NUMBER_INT ||
                token == JsonToken.VALUE_STRING ||
                token == JsonToken.VALUE_NULL) {
                
                final T thing = mapper.readValue(jp, clazz);
                if(thing == null) {
                    //handler.handle(null);
                    break;
                }
                handler.handle(thing);
            } else {
                token = jp.nextToken();
            }
        }
        
//        while(jp.nextToken() == JsonToken.START_OBJECT) {
//            final T thing = mapper.readValue(jp, clazz);
//            if(thing == null) {
//                handler.handle(null);
//                break;
//            }
//            handler.handle(thing);
//        }
        
        
    }
    
    private <T> void readObject(com.fasterxml.jackson.core.JsonParser jp, Class<T> clazz, StreamHandler<T> handler) throws IOException {
        
        if(jp.getCurrentToken() == JsonToken.START_OBJECT) {
            final T thing = mapper.readValue(jp, clazz);
            handler.handle(thing);
        }
    }
    
    private void readObject(com.fasterxml.jackson.core.JsonParser jp, StreamHandler<Object> handler) throws IOException {
        //if(jp.getCurrentToken() == JsonToken.START_OBJECT) {
            final JsonObject thing = mapper.readValue(jp, JsonObject.class);
            handler.handle(thing);
        //}
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

        return new ParserDelegate(parser);
    }

    private com.fasterxml.jackson.core.JsonParser wrapper(InputStream json) throws IOException {
        JsonFactory factory = mapper.getFactory();
        com.fasterxml.jackson.core.JsonParser parser = factory.createParser(json);

        return new ParserDelegate(parser);
    }

}
