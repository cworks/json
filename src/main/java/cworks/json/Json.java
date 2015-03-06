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
import cworks.json.io.JsonIO;
import cworks.json.io.JsonIOBuilder;
import cworks.json.parser.JsonParser;
import cworks.json.parser.jackson.JacksonParser;
import cworks.json.streaming.JsonStreamHandler;
import cworks.json.streaming.StreamHandler;
import cworks.json.streaming.Token;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * This class is a simple JSON toolkit that prides itself on simplicity
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
    static JsonIO prettyParser() {
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

        String name = System.getProperty("json.io", "jackson");

        JsonIO parser = JsonIOBuilder.io(JsonLib.valueOf(name))
            .dateFormat(config.getString("dateFormat", "yyyy-MM-dd'T'HH:mm:ssz"))
            .pretty(config.getBoolean("pretty", false))
            .allowComments(config.getBoolean("allowComments", true))
            .build();

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

        JsonElement element = io().toObject(json.trim());
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
        
        return io().toArray(json, clazz);
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
     * Convert the json text into a list of JsonElements 
     * @param json
     * @return
     */
    public static List<JsonObject> asList(String json) {
        
        throwIfNotArray(json);
        
        return io().toList(json);
    }

    /**
     * Convert the json text into a typed java array
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> List<T> asList(String json, final Class<T> clazz) throws JsonException {
        
        throwIfNotArray(json);
        
        return io().toList(json, clazz);
    }

    /**
     * Convert the json text file to a list of JsonElements
     * @param file
     * @return
     * @throws JsonException
     */
    public static List<JsonObject> asList(File file) throws JsonException {
        
        if(file == null) {
            throw new IllegalArgumentException("File argument cannot be null.");
        }
        
        String buffer = bufferFile(file).toString();
        return io().toList(buffer);
    }

    /**
     * Convert the json text file to a list of java instances 
     * @param file
     * @param clazz
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> List<T> asList(File file, final Class<T> clazz) throws JsonException {
        
        if(file == null) {
            throw new IllegalArgumentException("File argument cannot be null.");
        }
        
        String buffer = bufferFile(file).toString();
        return io().toList(buffer, clazz);
    }

    /**
     * Convert the json text to a JsonObject
     * @param json text
     * @return JsonObject
     */
    public static JsonObject asObject(String json) {

        throwIfNotObject(json);

        JsonElement element = io().toObject(json.trim());
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
     * Convert the json text file to a java instance 
     * @param file
     * @param clazz
     * @param <T>
     * @return
     * @throws JsonException
     */
    public static <T> T asObject(File file, final Class<T> clazz) throws JsonException {
        
        if(file == null) {
            throw new IllegalArgumentException("File argument cannot be null.");
        }
        
        String buffer = bufferFile(file).toString();
        return asObject(buffer, clazz);
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

        return io().toObject(json.trim(), clazz);
    }

    /**
     * Stream the rendered content from InputStream to the handler.
     * @param in
     * @param handler
     * @param <T>
     * @throws JsonException
     */
    @SuppressWarnings("unchecked")
    public static <T> void asStream(InputStream in, final StreamHandler<T> handler) throws JsonException {

        try {
            Class<T> genericType = getGenericType(handler);
            if (genericType == null) {
                throw new JsonException("Cannot determine Generic Type information for StreamHandler implementation.");
            }

            if (genericType == Object.class) {
                io().read(in, (StreamHandler<Token>) handler);
            } else {
                io().read(in, genericType, handler);
            }
        } finally {
            closeQuietly(in);
        }
    }

    /**
     * Stream the rendered content from InputStream to the handler
     * @param in
     * @param handler
     * @param <T>
     * @throws JsonException
     */
    public static <T> void asStream(InputStream in, final JsonStreamHandler handler) throws JsonException {

        try {
            io().read(in, handler);
        } finally {
            closeQuietly(in);
        }
    }

    public static Stream<Token> asStream(InputStream in) throws IOException {
        JacksonParser parser = new JacksonParser();
        return parser.read(in);
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
     * Convert the JsonElement to json text
     * @param element
     * @return json text
     */
    public static String toString(JsonElement element) {

        return io().toJson(element);
    }

    /**
     * Convert the JsonElement to pretty json text
     * @param element
     * @return
     */
    public static String toPrettyString(JsonElement element) {
        return prettyParser().toJson(element);
    }

    /**
     * Convert the Object to json text
     * @param obj
     * @return json text
     * @throws JsonException
     */
    public static String toString(Object obj) throws JsonException {
        return io().toJson(obj);
    }

    /**
     * Convert the Object to pretty json text
     * @param obj
     * @return
     * @throws JsonException
     */
    public static String toPrettyString(Object obj) throws JsonException {
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

    /**
     * Convert a Closeable to a Runnable by converting checked IOException
     * to UncheckedIOException
     */
    private static Runnable asUncheckedRunnable(Closeable c) {
        return () -> {
            try {
                c.close();
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }
}
