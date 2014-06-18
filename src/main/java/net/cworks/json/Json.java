/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * Baked with love by corbett
 * Package: net.cworks.json
 * User: corbett
 * Created: 09/10/2013 13:30
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package net.cworks.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import net.cworks.json.builder.JsonArrayBuilder;
import net.cworks.json.builder.JsonObjectBuilder;

import java.util.Map;

public final class Json {

    /**
     * The more conservative mapper
     */
    private final static ObjectMapper mapper = new ObjectMapper();

    /**
     * The liberal mapper
     */
    private final static ObjectMapper prettyMapper = new ObjectMapper();

    /**
     * configure mappers
     */
    static {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS, true);

        prettyMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        prettyMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        prettyMapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS, true);
    }

    /**
     * Builder method for quickly creating a JsonObject
     * @return Json Object builder
     */
    public static JsonObjectBuilder object() {
        return new JsonObjectBuilder();
    }

    /**
     * Builder method for quickly creating a JsonArray
     * @return
     */
    public static JsonArrayBuilder array() {
        return new JsonArrayBuilder();
    }

    /**
     * convert JSON string to a Java type
     * @param json JSON string
     * @param clazz the Java type to convert into
     * @return an instance of the clazz type
     * @throws JsonException
     */
    @SuppressWarnings("unchecked")
    public static <T> T asType(final String json, final Class<T> clazz)
        throws JsonException {

        try {
            //JsonFactory jsonFactory = new JsonFactory();
            //com.fasterxml.jackson.core.JsonParser jp = jsonFactory.createParser(json);
            return (T)mapper.readValue(json, clazz);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }
    }

    /**
     * convert JSON string to assigned type
     * @param json JSON string
     * @return an instance of the assigned type
     */
    public static Map asMap(final String json) {
        if (json == null) {
            return null;
        }

        try {
            // Untyped List/Map
            return mapper.readValue(json, Map.class);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }
    }

    /**
     * convert Java type to JSON string
     * @param obj Java type to convert
     * @return a JSON string that represents the obj parameter
     * @throws JsonException
     */
    public static String asString(final Object obj) throws JsonException {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }
    }

    /**
     * Just a convenience method for cases where its easier to call this static method over
     * instance methods
     * @param element
     * @return
     */
    public static String asString(final JsonElement element) {

        if(element.isObject()) {
            return ((JsonObject)element).asString();
        }

        if(element.isArray()) {
            return ((JsonArray)element).asString();
        }

        return "";
    }

    /**
     * convert Java type to JSON string
     * @param obj Java type to convert
     * @return a JSON pretty string that represents the obj parameter
     * @throws JsonException
     */
    public static String asPrettyString(final Object obj) throws JsonException {
        try {
            return prettyMapper.writeValueAsString(obj);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }
    }

    /**
     * convert JsonElement to a pretty JSON string
     * @param element
     * @return the pretty Json string
     */
    public static String asPrettyString(final JsonElement element) {
        if(element.isObject()) {
            return ((JsonObject)element).asPrettyString();
        }

        if(element.isArray()) {
            return ((JsonArray)element).asPrettyString();
        }

        return "";
    }

    /**
     * convert Json string into JsonArray instance
     * @param json
     * @return
     */
    public static JsonArray asArray(final String json) {
        JsonArray ja = new JsonArray(json);
        return ja;
    }

    /**
     * convert Json string into JsonObject instance
     * @param json
     * @return
     */
    public static JsonObject asObject(final String json) {
        JsonObject jo = new JsonObject(json);
        return jo;
    }

    /**
     * convert Map of data into JsonObject instance
     * @param data
     * @return
     */
    public static JsonObject asObject(final Map data) {
        if(data == null) {
            return new JsonObject();
        }
        JsonObject jo = new JsonObject(data);
        return jo;
    }
}
