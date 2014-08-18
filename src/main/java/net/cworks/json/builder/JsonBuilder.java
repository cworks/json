/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * Baked with love by comartin
 * Package: net.cworks.json.builder
 * User: comartin
 * Created: 8/11/2014 11:20 AM
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package net.cworks.json.builder;

import net.cworks.json.JsonDeserializer;
import net.cworks.json.JsonErrorHandler;
import net.cworks.json.JsonObject;
import net.cworks.json.JsonSerializer;
import net.cworks.json.parser.JsonParser;

public abstract class JsonBuilder {

    private JsonConfigurationStep configurationBuilder;
    private JsonStep jsonStep;

    public JsonBuilder parser(JsonParser parser) {
        return this;
    }

    public JsonBuilder serializer(JsonSerializer serializer) {
        return this;
    }

    public JsonBuilder deserializer(JsonDeserializer deserializer) {
        return this;
    }

    public JsonBuilder errorHandler(JsonErrorHandler handler) {

        return this;
    }


    public JsonBuilder pretty() {
        return this;
    }

    // Terminal
    public JsonObject toObject(String json) {
        return null;
    }

    // Terminal
    public String toString(JsonObject object) {
        return null;
    }
}
