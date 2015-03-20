package cworks.json;

import cworks.json.builder.JsonArrayBuilder;
import cworks.json.builder.JsonObjectBuilder;
import cworks.json.io.JsonIO;
import cworks.json.io.JsonIOBuilder;
import cworks.json.streaming.StreamHandler;
import cworks.json.streaming.Token;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class JsonKit {

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

    /**
     * asStream 
     * @param input
     * @param handler
     * @throws JsonException
     */
    public static void asStream(InputStream input, StreamHandler<Token> handler) throws JsonException {
        throwIfNull(input);
        throwIfNull(handler);
        io().asStream(input, handler);
    }

    /**
     * asStream
     * @param input
     * @return
     * @throws JsonException
     */
    public static Stream<Token> asStream(InputStream input) throws JsonException {
        throwIfNull(input);
        return io().asStream(input);
    }

    /**
     * asStream
     * @param input
     * @param handler
     * @throws JsonException
     */
    public static void asStream(Reader input, StreamHandler<Token> handler) throws JsonException {
        throwIfNull(input);
        throwIfNull(handler);
        io().asStream(input, handler);
    }

    /**
     * asStream 
     * @param input
     * @return
     * @throws JsonException
     */
    public static Stream<Token> asStream(Reader input) throws JsonException {
        throwIfNull(input);
        return io().asStream(input);
    }

    /**
     * asStream 
     * @param input
     * @param handler
     * @throws JsonException
     */
    public static void asStream(File input, StreamHandler<Token> handler) throws JsonException {
        throwIfNull(input);
        throwIfNull(handler);
        io().asStream(input, handler);
    }

    /**
     * asStream 
     * @param input
     * @return
     * @throws JsonException
     */
    public static Stream<Token> asStream(File input) throws JsonException {
        throwIfNull(input);
        return io().asStream(input);
    }

    /**
     * asStream
     * @param input
     * @param handler
     * @throws JsonException
     */
    public static void asStream(Path input, StreamHandler<Token> handler) throws JsonException {
        throwIfNull(input);
        throwIfNull(handler);
        io().asStream(input, handler);
    }

    /**
     * asStream 
     * @param input
     * @return
     * @throws JsonException
     */
    public static Stream<Token> asStream(Path input) throws JsonException {
        throwIfNull(input);
        return io().asStream(input);
    }

    /**
     * asElement 
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonElement asElement(String input) throws JsonException {
        throwIfBad(input);
        return io().asElement(input);
    }

    /**
     * asObject 
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonObject asObject(String input) throws JsonException {
        throwIfBad(input);
        return io().asObject(input);
    }

    /**
     * asArray 
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonArray asArray(String input) throws JsonException {
        throwIfBad(input);
        return io().asArray(input);
    }

    /**
     * asObject 
     * @param input
     * @param objectType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> T asObject(String input, Class<T> objectType) throws JsonException {
        throwIfBad(input);
        throwIfNull(objectType);
        return io().asObject(input, objectType);
    }

    /**
     * asArray
     * @param input
     * @param arrayType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> T[] asArray(String input, Class<T> arrayType) throws JsonException {
        throwIfBad(input);
        throwIfNull(arrayType);
        return io().asArray(input, arrayType);
    }

    /**
     * asElement 
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonElement asElement(StringBuffer input) throws JsonException {
        throwIfNull(input);
        return io().asElement(input);
    }

    /**
     * asObject 
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonObject asObject(StringBuffer input) throws JsonException {
        throwIfNull(input);
        return io().asObject(input);
    }

    /**
     * asArray 
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonArray asArray(StringBuffer input) throws JsonException {
        throwIfNull(input);
        return io().asArray(input);
    }

    /**
     * asObject
     * @param input
     * @param objectType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> T asObject(StringBuffer input, Class<T> objectType) throws JsonException {
        throwIfNull(input);
        throwIfNull(objectType);
        return io().asObject(input, objectType);
    }

    /**
     * asArray
     * @param input
     * @param arrayType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> T[] asArray(StringBuffer input, Class<T> arrayType) throws JsonException {
        throwIfNull(input);
        throwIfNull(arrayType);
        return io().asArray(input, arrayType);
    }

    /**
     * asElement
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonElement asElement(StringBuilder input) throws JsonException {
        throwIfNull(input);
        return io().asElement(input);
    }

    /**
     * asObject
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonObject asObject(StringBuilder input) throws JsonException {
        throwIfNull(input);
        return io().asObject(input);
    }

    /**
     * asArray
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonArray asArray(StringBuilder input) throws JsonException {
        throwIfNull(input);
        return io().asArray(input);
    }

    /**
     * asObject
     * @param input
     * @param objectType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> T asObject(StringBuilder input, Class<T> objectType) throws JsonException {
        throwIfNull(input);
        throwIfNull(objectType);
        return io().asObject(input, objectType);
    }

    /**
     * asArray 
     * @param input
     * @param arrayType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> T[] asArray(StringBuilder input, Class<T> arrayType) throws JsonException {
        throwIfNull(input);
        throwIfNull(arrayType);
        return io().asArray(input, arrayType);
    }

    /**
     * asElement
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonElement asElement(Reader input) throws JsonException {
        throwIfNull(input);
        return io().asElement(input);
    }

    /**
     * asObject 
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonObject asObject(Reader input) throws JsonException {
        throwIfNull(input);
        return io().asObject(input);
    }

    /**
     * asArray
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonArray asArray(Reader input) throws JsonException {
        throwIfNull(input);
        return io().asArray(input);
    }

    /**
     * asObject
     * @param input
     * @param objectType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> T asObject(Reader input, Class<T> objectType) throws JsonException {
        throwIfNull(input);
        throwIfNull(objectType);
        return io().asObject(input, objectType);
    }

    /**
     * asArray
     * @param input
     * @param arrayType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> T[] asArray(Reader input, Class<T> arrayType) throws JsonException {
        throwIfNull(input);
        throwIfNull(arrayType);
        return io().asArray(input, arrayType);
    }

    /**
     * asElement 
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonElement asElement(File input) throws JsonException {
        throwIfNull(input);
        return io().asElement(input);
    }

    /**
     * asObject 
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonObject asObject(File input) throws JsonException {
        throwIfNull(input);
        return io().asObject(input);
    }

    /**
     * asArray
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonArray asArray(File input) throws JsonException {
        throwIfNull(input);
        return io().asArray(input);
    }

    /**
     * asObject
     * @param input
     * @param objectType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> T asObject(File input, Class<T> objectType) throws JsonException {
        throwIfNull(input);
        throwIfNull(objectType);
        return io().asObject(input, objectType);
    }

    /**
     * asArray 
     * @param input
     * @param arrayType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> T[] asArray(File input, Class<T> arrayType) throws JsonException {
        throwIfNull(input);
        throwIfNull(arrayType);
        return io().asArray(input, arrayType);
    }

    /**
     * asElement
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonElement asElement(InputStream input) throws JsonException {
        throwIfNull(input);
        return io().asElement(input);
    }

    /**
     * asObject
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonObject asObject(InputStream input) throws JsonException {
        throwIfNull(input);
        return io().asObject(input);
    }

    /**
     * asArray
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonArray asArray(InputStream input) throws JsonException {
        throwIfNull(input);
        return io().asArray(input);
    }

    /**
     * asObject
     * @param input
     * @param objectType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> T asObject(InputStream input, Class<T> objectType) throws JsonException {
        throwIfNull(input);
        throwIfNull(objectType);
        return io().asObject(input, objectType);
    }

    /**
     * asArray
     * @param input
     * @param arrayType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> T[] asArray(InputStream input, Class<T> arrayType) throws JsonException {
        throwIfNull(input);
        throwIfNull(arrayType);
        return io().asArray(input, arrayType);
    }

    /**
     * asElement
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonElement asElement(Path input) throws JsonException {
        throwIfNull(input);
        return io().asArray(input);
    }

    /**
     * asObject 
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonObject asObject(Path input) throws JsonException {
        throwIfNull(input);
        return io().asObject(input);
    }

    /**
     * asArray 
     * @param input
     * @return
     * @throws JsonException
     */
    public static JsonArray asArray(Path input) throws JsonException {
        throwIfNull(input);
        return io().asArray(input);
    }

    /**
     * asObject
     * @param input
     * @param objectType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> T asObject(Path input, Class<T> objectType) throws JsonException {
        throwIfNull(input);
        throwIfNull(objectType);
        return io().asObject(input, objectType);
    }

    /**
     * asArray 
     * @param input
     * @param arrayType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> T[] asArray(Path input, Class<T> arrayType) throws JsonException {
        throwIfNull(input);
        throwIfNull(arrayType);
        return io().asArray(input, arrayType);
    }

    /**
     * asList 
     * @param input
     * @return
     * @throws JsonException
     */
    public static List<JsonObject> asList(String input) throws JsonException {
        throwIfBad(input);
        return io().asList(input);
    }

    /**
     * asList
     * @param input
     * @return
     * @throws JsonException
     */
    public static List<JsonObject> asList(StringBuffer input) throws JsonException {
        throwIfNull(input);
        return io().asList(input);
    }

    /**
     * asList
     * @param input
     * @return
     * @throws JsonException
     */
    public static List<JsonObject> asList(StringBuilder input) throws JsonException {
        throwIfNull(input);
        return io().asList(input);
    }

    /**
     * asList
     * @param input
     * @return
     * @throws JsonException
     */
    public static List<JsonObject> asList(File input) throws JsonException {
        throwIfNull(input);
        return io().asList(input);
    }

    /**
     * asList
     * @param input
     * @return
     * @throws JsonException
     */
    public static List<JsonObject> asList(Path input) throws JsonException {
        throwIfNull(input);
        return io().asList(input);
    }

    /**
     * asList
     * @param input
     * @return
     * @throws JsonException
     */
    public static List<JsonObject> asList(Reader input) throws JsonException {
        throwIfNull(input);
        return io().asList(input);
    }

    /**
     * asList
     * @param input
     * @return
     * @throws JsonException
     */
    public static List<JsonObject> asList(InputStream input) throws JsonException {
        throwIfNull(input);
        return io().asList(input);
    }

    /**
     * asList
     * @param input
     * @param listType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> List<T> asList(String input, Class<T> listType) throws JsonException {
        throwIfBad(input);
        throwIfNull(listType);
        return io().asList(input, listType);
    }

    /**
     * asList
     * @param input
     * @param listType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> List<T> asList(StringBuffer input, Class<T> listType) throws JsonException {
        throwIfNull(input);
        throwIfNull(listType);
        return io().asList(input, listType);
    }

    /**
     * asList
     * @param input
     * @param listType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> List<T> asList(StringBuilder input, Class<T> listType) throws JsonException {
        throwIfNull(input);
        throwIfNull(listType);
        return io().asList(input, listType);
    }

    /**
     * asList
     * @param input
     * @param listType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> List<T> asList(File input, Class<T> listType) throws JsonException {
        throwIfNull(input);
        throwIfNull(listType);
        return io().asList(input, listType);
    }

    /**
     * asList
     * @param input
     * @param listType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> List<T> asList(Path input, Class<T> listType) throws JsonException {
        throwIfNull(input);
        throwIfNull(listType);
        return io().asList(input, listType);
    }

    /**
     * asList
     * @param input
     * @param listType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> List<T> asList(Reader input, Class<T> listType) throws JsonException {
        throwIfNull(input);
        throwIfNull(listType);
        return io().asList(input, listType);
    }

    /**
     * asList 
     * @param input
     * @param listType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> List<T> asList(InputStream input, Class<T> listType) throws JsonException {
        throwIfNull(input);
        throwIfNull(listType);
        return io().asList(input, listType);
    }

    /**
     * asMap
     * @param input
     * @return
     * @throws JsonException
     */
    public static Map<String, Object> asMap(String input) throws JsonException {
        throwIfBad(input);
        return io().asMap(input);
    }

    /**
     * asMap 
     * @param input
     * @return
     * @throws JsonException
     */
    public static Map<String, Object> asMap(StringBuffer input) throws JsonException {
        throwIfNull(input);
        return io().asMap(input);
    }

    /**
     * asMap 
     * @param input
     * @return
     * @throws JsonException
     */
    public static Map<String, Object> asMap(StringBuilder input) throws JsonException {
        throwIfNull(input);
        return io().asMap(input);
    }

    /**
     * asMap
     * @param input
     * @return
     * @throws JsonException
     */
    public static Map<String, Object> asMap(File input) throws JsonException {
        throwIfNull(input);
        return io().asMap(input);
    }

    /**
     * asMap 
     * @param input
     * @return
     * @throws JsonException
     */
    public static Map<String, Object> asMap(Path input) throws JsonException {
        throwIfNull(input);
        return io().asMap(input);
    }

    /**
     * asMap
     * @param input
     * @return
     * @throws JsonException
     */
    public static Map<String, Object> asMap(Reader input) throws JsonException {
        throwIfNull(input);
        return io().asMap(input);
    }

    /**
     * asMap
     * @param input
     * @return
     * @throws JsonException
     */
    public static Map<String, Object> asMap(InputStream input) throws JsonException {
        throwIfNull(input);
        return io().asMap(input);
    }

    /**
     * asMap 
     * @param input
     * @param mapType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> Map<String, T> asMap(String input, Class<T> mapType) throws JsonException {
        throwIfBad(input);
        throwIfNull(mapType);
        return io().asMap(input, mapType);
    }

    /**
     * asMap 
     * @param input
     * @param mapType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> Map<String, T> asMap(StringBuffer input, Class<T> mapType) throws JsonException {
        throwIfNull(input);
        throwIfNull(mapType);
        return io().asMap(input, mapType);
    }

    /**
     * asMap 
     * @param input
     * @param mapType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> Map<String, T> asMap(StringBuilder input, Class<T> mapType) throws JsonException {
        throwIfNull(input);
        throwIfNull(mapType);
        return io().asMap(input, mapType);
    }

    /**
     * asMap 
     * @param input
     * @param mapType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> Map<String, T> asMap(File input, Class<T> mapType) throws JsonException {
        throwIfNull(input);
        throwIfNull(mapType);
        return io().asMap(input, mapType);
    }

    /**
     * asMap 
     * @param input
     * @param mapType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> Map<String, T> asMap(Path input, Class<T> mapType) throws JsonException {
        throwIfNull(input);
        throwIfNull(mapType);
        return io().asMap(input, mapType);
    }

    /**
     * asMap 
     * @param input
     * @param mapType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> Map<String, T> asMap(Reader input, Class<T> mapType) throws JsonException {
        throwIfNull(input);
        throwIfNull(mapType);
        return io().asMap(input, mapType);
    }

    /**
     * asMap 
     * @param input
     * @param mapType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> Map<String, T> asMap(InputStream input, Class<T> mapType) throws JsonException {
        throwIfNull(input);
        throwIfNull(mapType);
        return io().asMap(input, mapType);
    }

    /**
     * asJson
     * @param object
     * @return
     * @throws JsonException
     */
    public static String asJson(Object object) throws JsonException {
        throwIfNull(object);
        return io().asJson(object);
    }

    /**
     * asJson 
     * @param object
     * @param objectType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> String asJson(Object object, Class<T> objectType) throws JsonException {
        throwIfNull(object);
        throwIfNull(objectType);
        return io().asJson(object, objectType);
    }

    /**
     * asJson 
     * @param objects
     * @return
     * @throws JsonException
     */
    public static String asJson(Object[] objects) throws JsonException {
        throwIfNull(objects);
        return io().asJson(objects);
    }

    /**
     * asJson
     * @param objects
     * @param arrayType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> String asJson(Object[] objects, Class<T> arrayType) throws JsonException {
        throwIfNull(objects);
        throwIfNull(arrayType);
        return io().asJson(objects, arrayType);
    }

    /**
     * asJson 
     * @param object
     * @return
     * @throws JsonException
     */
    public static String asJson(List object) throws JsonException {
        throwIfNull(object);
        return io().asJson(object);
    }

    /**
     * asJson
     * @param objects
     * @param listType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> String asJson(List objects, Class<T> listType) throws JsonException {
        throwIfNull(objects);
        throwIfNull(listType);
        return io().asJson(objects, listType);
    }

    /**
     * asJson
     * @param object
     * @return
     * @throws JsonException
     */
    public static String asJson(Map object) throws JsonException {
        throwIfNull(object);
        return io().asJson(object);
    }

    /**
     * asJson
     * @param object
     * @param mapType
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> String asJson(Map object, Class<T> mapType) throws JsonException {
        throwIfNull(object);
        throwIfNull(mapType);
        return io().asJson(object, mapType);
    }

    /**
     * asJson
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asJson(Object object, Writer output) throws JsonException {
        throwIfNull(object);
        throwIfNull(output);
        io().asJson(object, output);
    }

    /**
     * asJson
     * @param object
     * @param objectType
     * @param output
     * @param <T>
     * @throws JsonException
     */
    public static <T> void asJson(Object object, Class<T> objectType, Writer output) throws JsonException {
        throwIfNull(object);
        throwIfNull(objectType);
        throwIfNull(output);
        io().asJson(object, objectType, output);
    }

    /**
     * asJson
     * @param objects
     * @param output
     * @throws JsonException
     */
    public static void asJson(Object[] objects, Writer output) throws JsonException {
        throwIfNull(objects);
        throwIfNull(output);
        io().asJson(objects, output);
    }

    /**
     * asJson 
     * @param objects
     * @param arrayType
     * @param output
     * @param <T>
     * @throws JsonException
     */
    public static <T> void asJson(Object[] objects, Class<T> arrayType, Writer output) throws JsonException {
        throwIfNull(objects);
        throwIfNull(arrayType);
        throwIfNull(output);
        io().asJson(objects, arrayType, output);
    }

    /**
     * asJson
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asJson(Object object, File output) throws JsonException {
        throwIfNull(object);
        throwIfNull(output);
        io().asJson(object, output);
    }

    /**
     * asJson
     * @param object
     * @param objectType
     * @param output
     * @param <T>
     * @throws JsonException
     */
    public static <T> void asJson(Object object, Class<T> objectType, File output) throws JsonException {
        throwIfNull(object);
        throwIfNull(objectType);
        throwIfNull(output);
        io().asJson(object, objectType, output);
    }

    /**
     * asJson
     * @param objects
     * @param output
     * @throws JsonException
     */
    public static void asJson(Object[] objects, File output) throws JsonException {
        throwIfNull(objects);
        throwIfNull(output);
        io().asJson(objects, output);
    }

    /**
     * asJson
     * @param objects
     * @param arrayType
     * @param output
     * @param <T>
     * @throws JsonException
     */
    public static <T> void asJson(Object[] objects, Class<T> arrayType, File output) throws JsonException {
        throwIfNull(objects);
        throwIfNull(arrayType);
        throwIfNull(output);
        io().asJson(objects, arrayType, output);
    }

    /**
     * asJson 
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asJson(Object object, OutputStream output) throws JsonException {
        throwIfNull(object);
        throwIfNull(output);
        io().asJson(object, output);
    }

    /**
     * asJson
     * @param object
     * @param objectType
     * @param output
     * @param <T>
     * @throws JsonException
     */
    public static <T> void asJson(Object object, Class<T> objectType, OutputStream output) throws JsonException {
        throwIfNull(object);
        throwIfNull(objectType);
        throwIfNull(output);
        io().asJson(object, objectType, output);
    }

    /**
     * asJson
     * @param objects
     * @param output
     * @throws JsonException
     */
    public static void asJson(Object[] objects, OutputStream output) throws JsonException {
        throwIfNull(objects);
        throwIfNull(output);
        io().asJson(objects, output);
    }

    /**
     * asJson
     * @param objects
     * @param objectType
     * @param output
     * @param <T>
     * @throws JsonException
     */
    public static <T> void asJson(Object[] objects, Class<T> objectType, OutputStream output) throws JsonException {
        throwIfNull(objects);
        throwIfNull(objectType);
        throwIfNull(output);
        io().asJson(objects, objectType, output);
    }

    /**
     * asJson
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asJson(Object object, Path output) throws JsonException {
        throwIfNull(object);
        throwIfNull(output);
        io().asJson(object, output);
    }

    /**
     * asJson
     * @param object
     * @param objectType
     * @param output
     * @param <T>
     * @throws JsonException
     */
    public static <T> void asJson(Object object, Class<T> objectType, Path output) throws JsonException {
        throwIfNull(object);
        throwIfNull(objectType);
        throwIfNull(output);
        io().asJson(object, objectType, output);
    }

    /**
     * asJson 
     * @param objects
     * @param output
     * @throws JsonException
     */
    public static void asJson(Object[] objects, Path output) throws JsonException {
        throwIfNull(objects);
        throwIfNull(output);
        io().asJson(objects, output);
    }

    /**
     * asJson
     * @param objects
     * @param objectType
     * @param output
     * @param <T>
     * @throws JsonException
     */
    public static <T> void asJson(Object[] objects, Class<T> objectType, Path output) throws JsonException {
        throwIfNull(objects);
        throwIfNull(objectType);
        throwIfNull(output);
        io().asJson(objects, objectType, output);
    }

    /**
     * asJson
     * @param element
     * @return
     * @throws JsonException
     */
    public static String asJson(JsonElement element) throws JsonException {
        throwIfNull(element);
        return io().asJson(element);
    }

    /**
     * asJson
     * @param element
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonElement element, Writer output) throws JsonException {
        throwIfNull(element);
        throwIfNull(output);
        io().asJson(element, output);
    }

    /**
     * asJson 
     * @param element
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonElement element, File output) throws JsonException {
        throwIfNull(element);
        throwIfNull(output);
        io().asJson(element, output);
    }

    /**
     * asJson 
     * @param element
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonElement element, OutputStream output) throws JsonException {
        throwIfNull(element);
        throwIfNull(output);
        io().asJson(element, output);
    }

    /**
     * asJson
     * @param element
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonElement element, Path output) throws JsonException {
        throwIfNull(element);
        throwIfNull(output);
        io().asJson(element, output);
    }

    /**
     * asJson
     * @param object
     * @return
     * @throws JsonException
     */
    public static String asJson(JsonObject object) throws JsonException {
        throwIfNull(object);
        return io().asJson(object);
    }

    /**
     * asJson
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonObject object, Writer output) throws JsonException {
        throwIfNull(object);
        throwIfNull(output);
        io().asJson(object, output);
    }

    /**
     * asJson 
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonObject object, File output) throws JsonException {
        throwIfNull(object);
        throwIfNull(output);
        io().asJson(object, output);
    }

    /**
     * asJson 
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonObject object, OutputStream output) throws JsonException {
        throwIfNull(object);
        throwIfNull(output);
        io().asJson(object, output);
    }

    /**
     * asJson 
     * @param object
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonObject object, Path output) throws JsonException {
        throwIfNull(object);
        throwIfNull(output);
        io().asJson(object, output);
    }

    /**
     * asJson
     * @param array
     * @return
     * @throws JsonException
     */
    public static String asJson(JsonArray array) throws JsonException {
        throwIfNull(array);
        return io().asJson(array);
    }

    /**
     * asJson 
     * @param array
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonArray array, Writer output) throws JsonException {
        throwIfNull(array);
        throwIfNull(output);
        io().asJson(array, output);
    }

    /**
     * asJson 
     * @param array
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonArray array, File output) throws JsonException {
        throwIfNull(array);
        throwIfNull(output);
        io().asJson(array, output);
    }

    /**
     * asJson 
     * @param array
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonArray array, OutputStream output) throws JsonException {
        throwIfNull(array);
        throwIfNull(output);
        io().asJson(array, output);
    }

    /**
     * asJson
     * @param array
     * @param output
     * @throws JsonException
     */
    public static void asJson(JsonArray array, Path output) throws JsonException {
        throwIfNull(array);
        throwIfNull(output);
        io().asJson(array, output);
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
