package cworks.json.io;

import cworks.json.JsonLib;
import cworks.json.impl.gson.GsonIO;
import cworks.json.impl.jackson.JacksonIO;

public class JsonIOBuilder {

    private JsonIO io = null;

    private JsonIOBuilder(JsonIO io) {
        this.io = io;
    }

    /**
     * Make a default JsonIO instance
     * @return
     */
    public static JsonIOBuilder io() {

        return io(JsonLib.JACKSON);
    }

    /**
     * Make a supported JsonIO instance
     * @param jsonLib
     * @return
     */
    public static JsonIOBuilder io(final JsonLib jsonLib) {

        JsonIO jsonIO;
        switch(jsonLib) {
            case JACKSON:
                jsonIO = new JacksonIO();
                break;
            case GSON:
                jsonIO = new GsonIO();
                break;
            default:
                jsonIO = new JacksonIO();
        }

        return new JsonIOBuilder(jsonIO);
    }

    public JsonIOBuilder pretty() {
        io.pretty(true);
        return this;
    }
    
    public JsonIOBuilder pretty(boolean val) {
        io.pretty(val);
        return this;
    }

    public JsonIOBuilder dateFormat(String format) {
        io.dateFormat(format);
        return this;
    }

    public JsonIOBuilder allowComments() {
        io.allowComments(true);
        return this;
    }
    
    public JsonIOBuilder allowComments(boolean val) {
        io.allowComments(val);
        return this;
    }

    public JsonIO build() {

        if(io == null) {
            throw new NullPointerException(
                "JsonIOBuilder.io is null, cannot build a JsonIO instance."
            );
        }

        // TODO consider returning an internal wrapper instead of actual concrete implementations
        return io;
    }

}


