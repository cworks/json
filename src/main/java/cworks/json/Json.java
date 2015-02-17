/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * Baked with love by corbett
 * Package: net.cworks.json
 * User: corbett
 * Created: 09/10/2013 13:30
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package cworks.json;

import cworks.json.builder.JsonArrayBuilder;
import cworks.json.builder.JsonObjectBuilder;
import cworks.json.parser.JsonParser;
import cworks.json.parser.JsonParserBuilder;
import cworks.json.parser.ParserType;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is a simple JSON string to Java Object and vice-versa
 * conversion utility.
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
    static JsonParser parser() {

        Map data = new HashMap<String, Object>();
        data.put("dateFormat", "yyyy-MM-dd'T'HH:mm:ssz");
        data.put("pretty", false);
        data.put("allowComments", true);
        JsonObject config = new JsonObject(data);

        return parser(config);
    }

    /**
     * Create a pretty print JsonParser which is same as default parser
     * except that it pretty prints Json text.
     *
     * @return JsonParser
     */
    static JsonParser prettyParser() {
        Map data = new HashMap<String, Object>();
        data.put("dateFormat", "yyyy-MM-dd'T'HH:mm:ssz");
        data.put("pretty", true);
        data.put("allowComments", true);
        JsonObject config = new JsonObject(data);

        return parser(config);
    }

    /**
     * Create a default JsonParser but with some extra config options.  By
     * default Jackson parser suite is used.
     *
     * @param config
     * @return JsonParser
     */
    static JsonParser parser(JsonObject config) {

        String name = System.getProperty("json.parser", "jackson");

        JsonParser parser = JsonParserBuilder.parser(ParserType.valueOf(name))
            .dateFormat(config.getString("dateFormat", "yyyy-MM-dd'T'HH:mm:ssz"))
            .pretty(config.getBoolean("pretty", false))
            .allowComments(config.getBoolean("allowComments", true))
            .make();

        return parser;
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
     * Convert the json text to a JsonArray
     * @param json text
     * @return JsonArray
     */
    public static JsonArray asArray(String json) {

        throwIfNotArray(json);

        JsonElement element = parser().toObject(json.trim());
        if (element.isArray()) {
            return (JsonArray) element;
        }

        return null;
    }

    /**
     * Convert the json text into an array of Java clazz instances.
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> T[] asArray(String json, final Class<T> clazz) throws JsonException {

        throwIfNotArray(json);
        
        return parser().toArray(json, clazz);
    }

    /**
     * Convert the json text file to a JsonArray
     * @param file containing json text
     * @return JsonArray
     */
    public static JsonArray asArray(File file) {
        
        if(file == null) {
            throw new IllegalArgumentException("File argument cannot be null.");
        }
        
        String buffer = bufferFile(file).toString();

        return asArray(buffer);
    }

    /**
     * Convert the json text file to a typed java array
     * @param file containing json text
     * @param clazz type of array elements
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> T[] asArray(File file, final Class<T> clazz) throws JsonException {
        
        if(file == null) {
            throw new IllegalArgumentException("File argument cannot be null.");
        }
        
        String buffer = bufferFile(file).toString();
        
        return asArray(buffer, clazz);
    }

    /**
     * Convert the json text file to a list of JsonElements
     * @param file
     * @return
     * @throws JsonException
     */
    public static List<JsonElement> asList(File file) throws JsonException {
        
        if(file == null) {
            throw new IllegalArgumentException("File argument cannot be null.");
        }
        
        String buffer = bufferFile(file).toString();
        return parser().toList(buffer);
    }

    /**
     * Convert the json text to a JsonObject
     * @param json text
     * @return JsonObject
     */
    public static JsonObject asObject(String json) {

        throwIfNotObject(json);

        JsonElement element = parser().toObject(json.trim());
        if (element.isObject()) {
            return (JsonObject) element;
        }

        return null;
    }

    /**
     * Convert the json text file to a JsonObject
     * @param file containing json text
     * @return JsonObject
     */
    public static JsonObject asObject(File file) {

        if(file == null) {
            throw new IllegalArgumentException("File argument cannot be null.");
        }
        
        String buffer = bufferFile(file).toString();

        return asObject(buffer);
    }

    /**
     * Convert the json text to a specific Java type
     * @param json
     * @param clazz
     * @param <T>
     * @return instance of T
     * @throws JsonException
     */
    public static <T> T asObject(String json, final Class<T> clazz) throws JsonException {

        throwIfNotObject(json);

        return parser().toObject(json.trim(), clazz);
    }

    /**
     * Convert the JsonElement to json text
     * @param element
     * @return json text
     */
    public static String asString(JsonElement element) {

        return parser().toJson(element);
    }

    /**
     * Convert the JsonElement to pretty json text
     * @param element
     * @return
     */
    public static String asPrettyString(JsonElement element) {
        return prettyParser().toJson(element);
    }

    /**
     * Convert the Object to json text
     * @param obj
     * @return json text
     * @throws JsonException
     */
    public static String asString(Object obj) throws JsonException {
        return parser().toJson(obj);
    }

    /**
     * Convert the Object to pretty json text
     * @param obj
     * @return
     * @throws JsonException
     */
    public static String asPrettyString(Object obj) throws JsonException {
        return prettyParser().toJson(obj);
    }

    /**
     * Internal class method to close a stream without a lot of drama
     * @param in
     */
    private static void closeQuietly(InputStream in) {
        if(in != null) {
            try { in.close(); } catch (IOException ignore) { }
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

    /**
     * This class method will buffer the whole file before being used to convert
     * the content to JsonObject, JsonArray or a Java Type.
     * 
     * TODO consider placing an upper limit on the buffer to protect against OoM
     * @param file
     */
    private static StringBuffer bufferFile(File file) {

        InputStream in = null;
        StringBuffer buffer = new StringBuffer();
        try {
            in = new BufferedInputStream(new FileInputStream(file));
            byte[] b = new byte[4096];
            for (int n; (n = in.read(b)) != -1; ) {
                buffer.append(new String(b, 0, n));
            }
        } catch (FileNotFoundException ex) {
            throw new JsonException("File "
                    + file.getPath() + " not found.", ex);
        } catch(IOException ex) {
            throw new JsonException("I/O error while reading from File "
                    + file.getPath(), ex);
        } finally {
            closeQuietly(in);
        }
        
        return buffer;
    }

}
