/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * Baked with love by corbett
 * Package: net.cworks.json
 * User: corbett
 * Created: 09/10/2013 13:30
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package net.cworks.json;

import net.cworks.json.builder.JsonArrayBuilder;
import net.cworks.json.builder.JsonObjectBuilder;
import net.cworks.json.parser.JsonParser;
import net.cworks.json.parser.JsonParserBuilder;
import net.cworks.json.parser.ParserType;

import java.util.HashMap;
import java.util.Map;

public final class Json {

    private static final String DEFAULT_JSON_PARSER = "jackson";

    private JsonParser parser = null;

    private JsonObject config = null;

    /**
     * Hidden, use factory methods Json() and Json(:parser)
     */
    Json(JsonParser parser) {
        this.parser = parser;
    }

    /**
     * Hidden, use factory methods Json() and Json(:parser)
     *
     * @param parser
     * @param config
     */
    Json(JsonParser parser, JsonObject config) {
        this.parser = parser;
        this.config = config;
    }

    /**
     * Creates Json with default parser and settings
     *
     * @return
     */
    public static Json Json() {
        return Json("{\"pretty\":false}");
    }

    /**
     * Creates Json with customer options
     *
     * @param options Json string containing options
     * @return
     */
    public static Json Json(String options) {

        JsonElement element = defaultParser().toObject(options);
        JsonObject config = validateConfig(element);
        if (config != null) {
            return new Json(defaultParser(config), config);
        }

        return new Json(defaultParser());
    }

    private static JsonObject validateConfig(JsonElement element) {
        if (element == null) {
            return null;
        }
        if (!element.isObject()) {
            return null;
        }

        JsonObject config = (JsonObject) element;
        return config;
    }

    /**
     * Creates Json with a super duper parser
     *
     * @param parser
     * @return
     */
    public static Json Json(JsonParser parser) {

        return new Json(parser);
    }

    /**
     * Create the default JsonParser
     *
     * @return
     */
    static JsonParser defaultParser() {

        Map data = new HashMap<String, Object>();
        data.put("dateFormat", "yyyy-MM-dd'T'HH:mm:ssz");
        data.put("pretty", false);
        data.put("allowComments", true);
        JsonObject config = new JsonObject(data);

        return defaultParser(config);
    }

    /**
     * Create a default JsonParser but with some config options
     *
     * @param config
     * @return
     */
    static JsonParser defaultParser(JsonObject config) {

        String name = System.getProperty("json.defaultParser", "jackson");

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
     * @return Json Object builder
     */
    public JsonObjectBuilder object() {
        return new JsonObjectBuilder();
    }

    /**
     * Builder method for quickly creating a JsonArray
     *
     * @return
     */
    public JsonArrayBuilder array() {
        return new JsonArrayBuilder();
    }

    public JsonArray toArray(String json) {
        JsonElement element = parser.toObject(json);
        if (element.isArray()) {
            return (JsonArray) element;
        }

        return null;
    }

    public JsonObject toObject(String json) {

        JsonElement element = parser.toObject(json);
        if (element.isObject()) {
            return (JsonObject) element;
        }

        return null;
    }

    public <T> T toObject(String json, final Class<T> clazz) throws JsonException {
        return parser.toObject(json, clazz);
    }

    public String toJson(JsonElement element) {

        return parser.toJson(element);
    }

    public String toJson(Object obj) throws JsonException {
        return parser.toJson(obj);
    }


}
