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
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Json {

    private final static ObjectMapper mapper = new ObjectMapper();
    private final static ObjectMapper prettyMapper = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS, true);

        prettyMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
        prettyMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    /**
     * convert JSON string to a Java type
     * @param json JSON string
     * @param clazz the Java type to convert into
     * @return an instance of the clazz type
     * @throws JsonException
     */
    @SuppressWarnings("unchecked")
    public static <T> T asType(String json, final Class<?> clazz)
        throws JsonException {

        try {
            JsonFactory jsonFactory = new JsonFactory();
            com.fasterxml.jackson.core.JsonParser jp = jsonFactory.createParser(json);
            return (T)mapper.readValue(jp, clazz);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }
    }

    /**
     * convert JSON string to assigned type
     * @param json JSON string
     * @return an instance of the assigned type
     */
    @SuppressWarnings("unchecked")
    public static <T> T asType(String json) {
        if (json == null) {
            return null;
        }

        try {
            // Untyped List/Map
            return (T)mapper.readValue(json, Object.class);
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
    public static String asString(Object obj) throws JsonException {
        try {
            return mapper.writeValueAsString(obj);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }
    }

    /**
     * convert Java type to JSON string
     * @param obj Java type to convert
     * @return a JSON pretty string that represents the obj parameter
     * @throws JsonException
     */
    public static String asPrettyString(Object obj) throws JsonException {
        try {
            return prettyMapper.writeValueAsString(obj);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }
    }

    /**
     * convert Java type to Minified JSON string
     * @param obj Java type to convert
     * @return a JSON string that has been minified
     * @throws JsonException
     */
    public static String asMinifiedString(Object obj) throws JsonException {
        String json = asString(obj);
        return JsonMinify.minify(json);
    }

//    @SuppressWarnings("unchecked")
//    public static <T> T as(String str, Class<?> clazz) throws JsonDecodeException {
//        try {
//            return (T)mapper.readValue(str, clazz);
//        } catch (Exception e) {
//            throw new JsonDecodeException("Failed to decode:" + e.getMessage());
//        }
//    }
}
