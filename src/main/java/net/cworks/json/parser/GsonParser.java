/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Baked with love by corbett
 * Project: json
 * Package: net.cworks.json.parser
 * Class: GsonParser
 * Created: 8/1/14 3:40 PM
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package net.cworks.json.parser;

import net.cworks.json.JsonElement;
import net.cworks.json.JsonException;

public class GsonParser extends JsonParser {

    @Override
    public JsonElement toObject(String json) throws JsonException {
        return null;
    }

    @Override
    public <T> T toObject(String json, Class<T> clazz) throws JsonException {
        return null;
    }

    @Override
    public String toJson(JsonElement element) throws JsonException {
        return null;
    }

    @Override
    public String toJson(Object obj) throws JsonException {
        return null;
    }
}
