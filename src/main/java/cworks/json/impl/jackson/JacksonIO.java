package cworks.json.impl.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.StdConverter;
import cworks.json.*;
import cworks.json.spi.JsonReader;
import cworks.json.spi.JsonWriter;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class JacksonIO extends JsonIO {

    private final ObjectMapper mapper;

    public JacksonIO() {
        super(JsonLib.JACKSON);
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS, true);

        SimpleModule module = new SimpleModule("JacksonIO");
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
    public JsonReader getReader() {
        return null;
    }

    @Override
    public JsonWriter getWriter() {
        return null;
    }

    @Override
    public JsonElement asElement(String input) throws JsonException {
        return null;
    }

    @Override
    public JsonObject asObject(String input) throws JsonException {
        return null;
    }

    @Override
    public JsonArray asArray(String input) throws JsonException {
        return null;
    }

    @Override
    public <T> T asObject(String input, Type objectType) throws JsonException {
        return null;
    }

    @Override
    public <T> T asObject(String input, Class<T> objectType) throws JsonException {
        return null;
    }

    @Override
    public <T> T[] asArray(String input, Type arrayType) throws JsonException {
        return null;
    }

    @Override
    public <T> T[] asArray(String input, Class<T> arrayType) throws JsonException {
        return null;
    }

    @Override
    public JsonElement asElement(StringBuffer input) throws JsonException {
        return null;
    }

    @Override
    public JsonObject asObject(StringBuffer input) throws JsonException {
        return null;
    }

    @Override
    public JsonArray asArray(StringBuffer input) throws JsonException {
        return null;
    }

    @Override
    public <T> T asObject(StringBuffer input, Type objectType) throws JsonException {
        return null;
    }

    @Override
    public <T> T asObject(StringBuffer input, Class<T> objectType) throws JsonException {
        return null;
    }

    @Override
    public <T> T[] asArray(StringBuffer input, Type arrayType) throws JsonException {
        return null;
    }

    @Override
    public <T> T[] asArray(StringBuffer input, Class<T> arrayType) throws JsonException {
        return null;
    }

    @Override
    public JsonElement asElement(StringBuilder input) throws JsonException {
        return null;
    }

    @Override
    public JsonObject asObject(StringBuilder input) throws JsonException {
        return null;
    }

    @Override
    public JsonArray asArray(StringBuilder input) throws JsonException {
        return null;
    }

    @Override
    public <T> T asObject(StringBuilder input, Type objectType) throws JsonException {
        return null;
    }

    @Override
    public <T> T asObject(StringBuilder input, Class<T> objectType) throws JsonException {
        return null;
    }

    @Override
    public <T> T[] asArray(StringBuilder input, Type arrayType) throws JsonException {
        return null;
    }

    @Override
    public <T> T[] asArray(StringBuilder input, Class<T> arrayType) throws JsonException {
        return null;
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
        return null;
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

    @Override
    public String asJson(Object object) throws JsonException {
        return null;
    }

    @Override
    public String asJson(Object object, Type objectType) throws JsonException {
        return null;
    }

    @Override
    public String asJson(Object[] objects) throws JsonException {
        return null;
    }

    @Override
    public String asJson(Object[] objects, Type arrayType) throws JsonException {
        return null;
    }

    @Override
    public String asJson(List object) throws JsonException {
        return null;
    }

    @Override
    public String asJson(List objects, Type listType) throws JsonException {
        return null;
    }

    @Override
    public String asJson(Map object) throws JsonException {
        return null;
    }

    @Override
    public String asJson(Map object, Type mapType) throws JsonException {
        return null;
    }

    @Override
    public void asJson(Object object, Writer output) throws JsonException {

    }

    @Override
    public void asJson(Object object, Type objectType, Writer output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, Writer output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, Type arrayType, Writer output) throws JsonException {

    }

    @Override
    public void asJson(Object object, File output) throws JsonException {

    }

    @Override
    public void asJson(Object object, Type objectType, File output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, File output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, Type arrayType, File output) throws JsonException {

    }

    @Override
    public void asJson(Object object, OutputStream output) throws JsonException {

    }

    @Override
    public void asJson(Object object, Type objectType, OutputStream output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, OutputStream output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, Type objectType, OutputStream output) throws JsonException {

    }

    @Override
    public void asJson(Object object, Path output) throws JsonException {

    }

    @Override
    public void asJson(Object object, Type objectType, Path output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, Path output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, Type objectType, Path output) throws JsonException {

    }

    @Override
    public String asJson(JsonElement element) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonElement element, Writer output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonElement element, File output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonElement element, OutputStream output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonElement element, Path output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonObject object) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonObject object, Writer output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonObject object, File output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonObject object, OutputStream output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonObject object, Path output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonArray array) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonArray array, Writer output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonArray array, File output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonArray array, OutputStream output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonArray array, Path output) throws JsonException {
        return null;
    }
}
