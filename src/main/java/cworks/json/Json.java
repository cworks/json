package cworks.json;

import cworks.json.builder.JsonArrayBuilder;
import cworks.json.builder.JsonObjectBuilder;
import cworks.json.io.JsonIO;
import cworks.json.io.JsonIOBuilder;
import cworks.json.streaming.StreamHandler;
import cworks.json.streaming.Token;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * This class is a simple JSON toolkit that prides itself on simplicity of use.
 *
 * @author corbett
 */
public final class Json {

    /**
     * Create the default JsonParser which does not pretty print but allows
     * comments in JSON and has yyyy-MM-dd'T'HH:mm:ssz formatted date strings.
     *
     * @return JsonParser
     */
    static JsonIO io() {

        Map data = new HashMap<String, Object>();
        data.put("dateFormat", "yyyy-MM-dd'T'HH:mm:ssz");
        data.put("pretty", false);
        data.put("allowComments", true);
        JsonObject config = new JsonObject(data);

        return io(config);
    }

    /**
     * Create a pretty print JsonParser which is same as default io
     * except that it pretty prints Json text.
     *
     * @return JsonParser
     */
    static JsonIO prettyIO() {
        Map data = new HashMap<String, Object>();
        data.put("dateFormat", "yyyy-MM-dd'T'HH:mm:ssz");
        data.put("pretty", true);
        data.put("allowComments", true);
        JsonObject config = new JsonObject(data);

        return io(config);
    }

    /**
     * Create a default JsonParser but with some extra config options.  By
     * default Jackson io suite is used.
     *
     * @param config
     * @return JsonParser
     */
    static JsonIO io(JsonObject config) {

        String name = System.getProperty("json.io", "JACKSON").toUpperCase();

        JsonIO io = JsonIOBuilder.io(JsonLib.valueOf(name))
            .dateFormat(config.getString("dateFormat", "yyyy-MM-dd'T'HH:mm:ssz"))
            .pretty(config.getBoolean("pretty", false))
            .allowComments(config.getBoolean("allowComments", true))
            .build();

        return io;
    }

    /**
     * Builder method for quickly creating a JsonObject
     *
     * @return JsonObjectBuilder
     */
    public static JsonObjectBuilder object() {
        return new JsonObjectBuilder();
    }

    /**
     * Builder method for quickly creating a JsonArray
     *
     * @return JsonArrayBuilder
     */
    public static JsonArrayBuilder array() {
        return new JsonArrayBuilder();
    }
    
    // Read methods

    public static JsonElement asElement(String input) throws JsonException {
        throwIfBad(input);
        return io().asElement(input);
    }
    
    public static JsonObject asObject(String input) throws JsonException {
        throwIfNotObject(input);
        return io().asObject(input);
    }
    
    public static JsonArray asArray(String input) throws JsonException {
        throwIfNotArray(input);
        return io().asArray(input);
    }

    public static JsonElement asElement(StringBuffer input) throws JsonException {
        throwIfBad(input.toString());
        return io().asElement(input);
    }
    
    public static JsonObject asObject(StringBuffer input) throws JsonException {
        throwIfNotObject(input.toString());
        return io().asObject(input);
    }
    
    public static JsonArray asArray(StringBuffer input) throws JsonException {
        throwIfNotArray(input.toString());
        return io().asArray(input);
    }

    public static JsonElement asElement(StringBuilder input) throws JsonException {
        throwIfBad(input.toString());
        return io().asElement(input);
    }
    
    public static JsonObject asObject(StringBuilder input) throws JsonException {
        throwIfNotObject(input.toString());
        return io().asObject(input);
    }
    
    public static JsonArray asArray(StringBuilder input) throws JsonException {
        throwIfNotArray(input.toString());
        return io().asArray(input);
    }

    public static JsonElement asElement(Reader input) throws JsonException {
        throwIfNull(input);
        return io().asElement(input);
    }
    
    public static JsonObject asObject(Reader input) throws JsonException {
        throwIfNull(input);
        return io().asObject(input);
    }
    
    public static JsonArray asArray(Reader input) throws JsonException {
        throwIfNull(input);
        return io().asArray(input);
    }

    public static JsonElement asElement(File input) throws JsonException {
        throwIfNull(input);    
        return io().asElement(input);
    }
    
    public static JsonObject asObject(File input) throws JsonException {
        throwIfNull(input);
        return io().asObject(input);
    }
    
    public static JsonArray asArray(File input) throws JsonException {
        throwIfNull(input);
        return io().asArray(input);
    }

    public static JsonElement asElement(InputStream input) throws JsonException {
        throwIfNull(input);
        return io().asElement(input);
    }
    
    public static JsonObject asObject(InputStream input) throws JsonException {
        throwIfNull(input);
        return io().asObject(input);
    }
    
    public static JsonArray asArray(InputStream input) throws JsonException {
        throwIfNull(input);
        return io().asArray(input);
    }

    public static JsonElement asElement(Path input) throws JsonException {
        throwIfNull(input);
        return io().asElement(input);
        
    }
    
    public static JsonObject asObject(Path input) throws JsonException {
        throwIfNull(input);
        return io().asObject(input);
    }
    
    public static JsonArray asArray(Path input) throws JsonException {
        throwIfNull(input);
        return io().asArray(input);
    }    

    public static <T> T asObject(File input, Class<T> objectType) throws JsonException {
        throwIfNull(input);
        return io().asObject(input, objectType);
    }
    
    public static <T> T[] asArray(File input, Class<T> arrayType) throws JsonException {
        throwIfNull(input);
        return io().asArray(input, arrayType);
    }    

    public static <T> T asObject(String input, Class<T> objectType) throws JsonException {
        throwIfNotObject(input);
        return io().asObject(input, objectType);
    }
    
    public static <T> T[] asArray(String input, Class<T> arrayType) throws JsonException {
        throwIfNotArray(input);
        return io().asArray(input, arrayType);
    }

    public static <T> T asObject(StringBuffer input, Class<T> objectType) throws JsonException {
        throwIfNotObject(input.toString());
        return io().asObject(input, objectType);
    }
    
    public static <T> T[] asArray(StringBuffer input, Class<T> arrayType) throws JsonException {
        throwIfNotArray(input.toString());
        return io().asArray(input.toString(), arrayType);
    }

    public static <T> T asObject(StringBuilder input, Class<T> objectType) throws JsonException {
        throwIfNotObject(input.toString());
        return io().asObject(input, objectType);
    }
    
    public static <T> T[] asArray(StringBuilder input, Class<T> arrayType) throws JsonException {
        throwIfNotArray(input.toString());
        return io().asArray(input, arrayType);
    }

    public static <T> T asObject(Reader input, Class<T> objectType) throws JsonException {
        throwIfNull(input);
        return io().asObject(input, objectType);
    }
    
    public static <T> T[] asArray(Reader input, Class<T> arrayType) throws JsonException {
        throwIfNull(input);
        return io().asArray(input, arrayType);
    }

    public static <T> T asObject(InputStream input, Class<T> objectType) throws JsonException {
        throwIfNull(input);
        return io().asObject(input, objectType);
    }
    
    public static <T> T[] asArray(InputStream input, Class<T> arrayType) throws JsonException {
        throwIfNull(input);
        return io().asArray(input, arrayType);
    }

    public static <T> T asObject(Path input, Class<T> objectType) throws JsonException {
        throwIfNull(input);
        return io().asObject(input, objectType);
    }
    
    public static <T> T[] asArray(Path input, Class<T> arrayType) throws JsonException {
        throwIfNull(input);
        return io().asArray(input, arrayType);
    }

    public static List<JsonObject> asList(String input) throws JsonException {
        throwIfBad(input);
        return io().asList(input);
    }
    
    public static List<JsonObject> asList(StringBuffer input) throws JsonException {
        throwIfBad(input.toString());
        return io().asList(input);
    }
    
    public static List<JsonObject> asList(StringBuilder input) throws JsonException {
        throwIfBad(input.toString());
        return io().asList(input);
    }
    
    public static List<JsonObject> asList(File input) throws JsonException {
        throwIfNull(input);
        return io().asList(input);
    }
    
    public static List<JsonObject> asList(Path input) throws JsonException {
        throwIfNull(input);
        return io().asList(input);
    }
    
    public static List<JsonObject> asList(Reader input) throws JsonException {
        throwIfNull(input);
        return io().asList(input);
    }
    
    public static List<JsonObject> asList(InputStream input) throws JsonException {
        throwIfNull(input);
        return io().asList(input);
    }

    public static <T> List<T> asList(String input, Class<T> listType) throws JsonException {
        throwIfBad(input);
        return io().asList(input, listType);
    }
    
    public static <T> List<T> asList(StringBuffer input, Class<T> listType) throws JsonException {
        throwIfBad(input.toString());
        return io().asList(input, listType);
    }
    
    public static <T> List<T> asList(StringBuilder input, Class<T> listType) throws JsonException {
        throwIfBad(input.toString());
        return io().asList(input.toString(), listType);
    }
    
    public static <T> List<T> asList(File input, Class<T> listType) throws JsonException {
        throwIfNull(input);
        return io().asList(input, listType);
    }
    
    public static <T> List<T> asList(Path input, Class<T> listType) throws JsonException {
        throwIfNull(input);
        return io().asList(input, listType);
    }
    
    public static <T> List<T> asList(Reader input, Class<T> listType) throws JsonException {
        throwIfNull(input);
        return io().asList(input, listType);
    }
    
    public static <T> List<T> asList(InputStream input, Class<T> listType) throws JsonException {
        throwIfNull(input);
        return io().asList(input, listType);
    }

    public static Map<String, Object> asMap(String input) throws JsonException {
        throwIfBad(input);
        return io().asMap(input);
    }
    
    public static Map<String, Object> asMap(StringBuffer input) throws JsonException {
        throwIfBad(input.toString());
        return io().asMap(input);
    }
    
    public static Map<String, Object> asMap(StringBuilder input) throws JsonException {
        throwIfBad(input.toString());
        return io().asMap(input);
    }
    
    public static Map<String, Object> asMap(File input) throws JsonException {
        throwIfNull(input);
        return io().asMap(input);
    }
    
    public static Map<String, Object> asMap(Path input) throws JsonException {
        throwIfNull(input);
        return io().asMap(input);
    }
    
    public static Map<String, Object> asMap(Reader input) throws JsonException {
        throwIfNull(input);
        return io().asMap(input);
    }
    
    public static Map<String, Object> asMap(InputStream input) throws JsonException {
        throwIfNull(input);
        return io().asMap(input);
    }

    public static <T> Map<String, T> asMap(String input, Class<T> mapType) throws JsonException {
        throwIfBad(input);
        return io().asMap(input, mapType);
    }
    
    public static <T> Map<String, T> asMap(StringBuffer input, Class<T> mapType) throws JsonException {
        throwIfBad(input.toString());
        return io().asMap(input.toString(), mapType);
    }
    
    public static <T> Map<String, T> asMap(StringBuilder input, Class<T> mapType) throws JsonException {
        throwIfBad(input.toString());
        return io().asMap(input, mapType);
    }
    
    public static <T> Map<String, T> asMap(File input, Class<T> mapType) throws JsonException {
        throwIfNull(input);
        return io().asMap(input, mapType);
    }
    
    public static <T> Map<String, T> asMap(Path input, Class<T> mapType) throws JsonException {
        throwIfNull(input);
        return io().asMap(input, mapType);
    }
    
    public static <T> Map<String, T> asMap(Reader input, Class<T> mapType) throws JsonException {
        throwIfNull(input);
        return io().asMap(input, mapType);
    }
    
    public static <T> Map<String, T> asMap(InputStream input, Class<T> mapType) throws JsonException {
        throwIfNull(input);
        return io().asMap(input, mapType);
    }

    public static void asStream(InputStream input, StreamHandler<Token> handler) throws JsonException {
        throwIfNull(input);
        // TODO wrap handler
        io().asStream(input, handler);
    }
    
    public static Stream<Token> asStream(InputStream input) throws JsonException {
        throwIfNull(input);
        return io().asStream(input);
    }
    
    public static void asStream(Reader input, StreamHandler<Token> handler) throws JsonException {
        throwIfNull(input);
        io().asStream(input, handler);
    }
    
    public static Stream<Token> asStream(Reader input) throws JsonException {
        throwIfNull(input);
        return io().asStream(input);
    }
    
    public static void asStream(File input, StreamHandler<Token> handler) throws JsonException {
        throwIfNull(input);
        io().asStream(input, handler);
    }
    
    public static Stream<Token> asStream(File input) throws JsonException {
        throwIfNull(input);
        return io().asStream(input);
    }
    
    public static void asStream(Path input, StreamHandler<Token> handler) throws JsonException {
        throwIfNull(input);
        io().asStream(input, handler);
    }
    
    public static Stream<Token> asStream(Path input) throws JsonException {
        throwIfNull(input);
        return io().asStream(input);
    }

    /**
     * Convert the JsonElement to json text
     * @param element
     * @return json text
     */
    public static String asJson(JsonElement element) {

        return io().asJson(element);
    }

    // Writer methods
    
    /**
     * Convert the JsonElement to pretty json text
     * @param element
     * @return
     */
    public static String asPrettyJson(JsonElement element) {
        return prettyIO().asJson(element);
    }

    /**
     * Convert the Object to json text
     * @param obj
     * @return json text
     * @throws JsonException
     */
    public static String asJson(Object obj) throws JsonException {
        return io().asJson(obj);
    }

    /**
     * Convert the Object to pretty json text
     * @param obj
     * @return
     * @throws JsonException
     */
    public static String asPrettyJson(Object obj) throws JsonException {
        return prettyIO().asJson(obj);
    }
    
    @SuppressWarnings("unchecked")
    private static <T> Class<T> getGenericType(final StreamHandler<T> handler) {
        Class<T> genericType = null;
        Method[] methods = handler.getClass().getDeclaredMethods();
        
        for(Method m : methods) {
            if("handle".equals(m.getName())) {
                genericType = (Class<T>) m.getParameterTypes()[0];
                break;
            }
        }

        return genericType;
    }
    
    /**
     * Internal class method that will throw an IllegalArgumentException if input
     * argument is null.
     * @param input
     */
    private static void throwIfNull(Object input) {
        if(input == null) {
            throw new IllegalArgumentException("input argument cannot be null.");
        }
    }

    /**
     * Internal class method that will throw an IllegalArgumentException if json is jacked up
     * @param json
     */
    private static void throwIfBad(String json) {
        if(json == null) {
            throw new IllegalArgumentException("Json argument cannot be null.");
        }
        
        if(json.trim().length() == 0) {
            throw new IllegalArgumentException("Json argument cannot be an empty-string.");
        }
    }

    /**
     * Internal class method that will throw an IllegalArgumentException if json is not an array 
     * @param json a Json array
     */
    private static void throwIfNotArray(String json) {
        throwIfBad(json);
        if(json.trim().charAt(0) != '[') {
            throw new IllegalArgumentException("Json argument is not an array.");
        }
    }

    /**
     * Internal class method that will throw an IllegalArgumentException if json is not an object 
     * @param json a Json object
     */
    private static void throwIfNotObject(String json) {
        throwIfBad(json);
        if(json.trim().charAt(0) != '{') {
            throw new IllegalArgumentException("Json argument is not an object.");
        }
    }
}
