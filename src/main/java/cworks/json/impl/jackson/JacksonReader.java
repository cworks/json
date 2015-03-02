package cworks.json.impl.jackson;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import cworks.json.*;
import cworks.json.parser.jackson.ParserDelegate;
import cworks.json.spi.JsonReader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class JacksonReader implements JsonReader {

    private final ObjectMapper mapper;
    
    public JacksonReader(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public JsonElement asElement(String input) throws JsonException {

        JsonElement element = null;

        try {
            if (input.startsWith("[")) {
                List list = asObject(input, List.class);
                element = new JsonArray(list);
            } else if (input.startsWith("{")) {
                Map map = asObject(input, Map.class);
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
    public JsonObject asObject(String input) throws JsonException {
        
        if(!input.trim().startsWith("{")) {
            throw new JsonException("Json input isn't valid json object.");
        }
        
        Map map = asObject(input, Map.class);
        
        return new JsonObject(map);
    }

    @Override
    public JsonArray asArray(String input) throws JsonException {
        
        if(!input.trim().startsWith("[")) {
            throw new JsonException("Json input isn't valid json array.");
        }
        
        List list = asObject(input, List.class);
        
        return new JsonArray(list);
    }

    @Override
    public <T> T asObject(String input, Type objectType) throws JsonException {
        
        Class clazz = JsonJavaUtils.getRawType(objectType);
        
        return (T) asObject(input, clazz);

    }

    @Override
    public <T> T asObject(String input, Class<T> objectType) throws JsonException {
        T object;
        try {
            object = mapper.readValue(wrapper(input), objectType);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return object;
    }

    @Override
    public <T> T[] asArray(String input, Type arrayType) throws JsonException {
        return null;
    }

    @Override
    public <T> T[] asArray(String input, Class<T> arrayType) throws JsonException {
        T[] array;

        try {
            ArrayType arrt = mapper.getTypeFactory().constructArrayType(arrayType);
            array = mapper.readValue(wrapper(input), arrt);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return array;
    }

    @Override
    public JsonElement asElement(StringBuffer input) throws JsonException {
        
        JsonElement element = asElement(input.toString());
        
        return element;
    }

    @Override
    public JsonObject asObject(StringBuffer input) throws JsonException {
        
        JsonObject object = asObject(input.toString());
        
        return object;
    }

    @Override
    public JsonArray asArray(StringBuffer input) throws JsonException {
        
        JsonArray array = asArray(input.toString());
        
        return array;
    }

    @Override
    public <T> T asObject(StringBuffer input, Type objectType) throws JsonException {
        
        
        
        return null;
    }

    @Override
    public <T> T asObject(StringBuffer input, Class<T> objectType) throws JsonException {
        
        T object = asObject(input.toString(), objectType);
        
        return object;
    }

    @Override
    public <T> T[] asArray(StringBuffer input, Type arrayType) throws JsonException {
        return null;
    }

    @Override
    public <T> T[] asArray(StringBuffer input, Class<T> arrayType) throws JsonException {
        
        T[] array = asArray(input.toString(), arrayType);
        
        return array;
    }

    @Override
    public JsonElement asElement(StringBuilder input) throws JsonException {
        
        JsonElement element = asElement(input.toString());
        
        return element;
    }

    @Override
    public JsonObject asObject(StringBuilder input) throws JsonException {
        
        JsonObject object = asObject(input.toString());
        
        return object;
    }

    @Override
    public JsonArray asArray(StringBuilder input) throws JsonException {
        
        JsonArray array = asArray(input.toString());
        
        return array;
    }

    @Override
    public <T> T asObject(StringBuilder input, Type objectType) throws JsonException {
        
        
        
        return null;
    }

    @Override
    public <T> T asObject(StringBuilder input, Class<T> objectType) throws JsonException {
        
        T object = asObject(input.toString(), objectType);
        
        return object;
    }

    @Override
    public <T> T[] asArray(StringBuilder input, Type arrayType) throws JsonException {
        return null;
    }

    @Override
    public <T> T[] asArray(StringBuilder input, Class<T> arrayType) throws JsonException {
        
        T[] array = asArray(input.toString(), arrayType);
        
        return array;
    }

    @Override
    public JsonElement asElement(Reader input) throws JsonException {
        
        
        
        return null;
    }

    @Override
    public JsonObject asObject(Reader input) throws JsonException {
        return null;
    }

    @Override
    public JsonArray asArray(Reader input) throws JsonException {
        return null;
    }

    @Override
    public <T> T asObject(Reader input, Type objectType) throws JsonException {
        return null;
    }

    @Override
    public <T> T asObject(Reader input, Class<T> objectType) throws JsonException {
        return null;
    }

    @Override
    public <T> T[] asArray(Reader input, Type arrayType) throws JsonException {
        return null;
    }

    @Override
    public <T> T[] asArray(Reader input, Class<T> arrayType) throws JsonException {
        return null;
    }

    @Override
    public JsonElement asElement(File input) throws JsonException {
        return null;
    }

    @Override
    public JsonObject asObject(File input) throws JsonException {
        return null;
    }

    @Override
    public JsonArray asArray(File input) throws JsonException {
        return null;
    }

    @Override
    public <T> T asObject(File input, Type objectType) throws JsonException {
        return null;
    }

    @Override
    public <T> T asObject(File input, Class<T> objectType) throws JsonException {
        return null;
    }

    @Override
    public <T> T[] asArray(File input, Type arrayType) throws JsonException {
        return null;
    }

    @Override
    public <T> T[] asArray(File input, Class<T> arrayType) throws JsonException {
        return null;
    }

    @Override
    public JsonElement asElement(InputStream input) throws JsonException {
        return null;
    }

    @Override
    public JsonObject asObject(InputStream input) throws JsonException {
        return null;
    }

    @Override
    public JsonArray asArray(InputStream input) throws JsonException {
        return null;
    }

    @Override
    public <T> T asObject(InputStream input, Type objectType) throws JsonException {
        return null;
    }

    @Override
    public <T> T asObject(InputStream input, Class<T> objectType) throws JsonException {
        return null;
    }

    @Override
    public <T> T[] asArray(InputStream input, Type arrayType) throws JsonException {
        return null;
    }

    @Override
    public <T> T[] asArray(InputStream input, Class<T> arrayType) throws JsonException {
        return null;
    }

    @Override
    public JsonElement asElement(Path input) throws JsonException {
        return null;
    }

    @Override
    public JsonObject asObject(Path input) throws JsonException {
        return null;
    }

    @Override
    public JsonArray asArray(Path input) throws JsonException {
        return null;
    }

    @Override
    public <T> T asObject(Path input, Type objectType) throws JsonException {
        return null;
    }

    @Override
    public <T> T asObject(Path input, Class<T> objectType) throws JsonException {
        return null;
    }

    @Override
    public <T> T[] asArray(Path input, Type arrayType) throws JsonException {
        return null;
    }

    @Override
    public <T> T[] asArray(Path input, Class<T> arrayType) throws JsonException {
        return null;
    }

    @Override
    public List<JsonObject> asList(String input) throws JsonException {
        List<JsonObject> list;

        try {
            CollectionType collectionType = mapper.getTypeFactory()
                    .constructCollectionType(List.class, JsonObject.class);
            list = mapper.readValue(wrapper(input), collectionType);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return list;
    }

    @Override
    public List<JsonObject> asList(StringBuffer input) throws JsonException {
        return null;
    }

    @Override
    public List<JsonObject> asList(StringBuilder input) throws JsonException {
        return null;
    }

    @Override
    public List<JsonObject> asList(File input) throws JsonException {
        return null;
    }

    @Override
    public List<JsonObject> asList(Path input) throws JsonException {
        return null;
    }

    @Override
    public List<JsonObject> asList(Reader input) throws JsonException {
        return null;
    }

    @Override
    public List<JsonObject> asList(InputStream input) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(String input, Type listType) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(StringBuffer input, Type listType) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(StringBuilder input, Type listType) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(File input, Type listType) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(Path input, Type listType) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(Reader input, Type listType) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(InputStream input, Type listType) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(String input, Class<T> listType) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(StringBuffer input, Class<T> listType) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(StringBuilder input, Class<T> listType) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(File input, Class<T> listType) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(Path input, Class<T> listType) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(Reader input, Class<T> listType) throws JsonException {
        return null;
    }

    @Override
    public <T> List<T> asList(InputStream input, Class<T> listType) throws JsonException {
        return null;
    }

    @Override
    public Map<String, ? extends JsonElement> asMap(String input) throws JsonException {
        return null;
    }

    @Override
    public Map<String, ? extends JsonElement> asMap(StringBuffer input) throws JsonException {
        return null;
    }

    @Override
    public Map<String, ? extends JsonElement> asMap(StringBuilder input) throws JsonException {
        return null;
    }

    @Override
    public Map<String, ? extends JsonElement> asMap(File input) throws JsonException {
        return null;
    }

    @Override
    public Map<String, ? extends JsonElement> asMap(Path input) throws JsonException {
        return null;
    }

    @Override
    public Map<String, ? extends JsonElement> asMap(Reader input) throws JsonException {
        return null;
    }

    @Override
    public Map<String, ? extends JsonElement> asMap(InputStream input) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, ? extends T> asMap(String input, Type mapType) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, ? extends T> asMap(StringBuffer input, Type mapType) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, ? extends T> asMap(StringBuilder input, Type mapType) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, ? extends T> asMap(File input, Type mapType) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, ? extends T> asMap(Path input, Type mapType) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, ? extends T> asMap(Reader input, Type mapType) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, ? extends T> asMap(InputStream input, Type mapType) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, ? extends T> asMap(String input, Class<T> mapType) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, ? extends T> asMap(StringBuffer input, Class<T> mapType) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, ? extends T> asMap(StringBuilder input, Class<T> mapType) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, ? extends T> asMap(File input, Class<T> mapType) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, ? extends T> asMap(Path input, Class<T> mapType) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, ? extends T> asMap(Reader input, Class<T> mapType) throws JsonException {
        return null;
    }

    @Override
    public <T> Map<String, ? extends T> asMap(InputStream input, Class<T> mapType) throws JsonException {
        return null;
    }

    /**
     * Create a wrapper around the Jackson parser so that we can tweak some features
     * to our liking
     * *
     * @param json json text
     * @return jackson parser
     * @throws java.io.IOException
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
