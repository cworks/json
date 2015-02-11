/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * Baked with love by comartin
 * Package: net.cworks.json.parser
 * User: comartin
 * Created: 8/13/2014 10:26 AM
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package cworks.json.parser;

import cworks.json.JsonElement;
import cworks.json.JsonException;

public abstract class JsonParser {

    protected Boolean pretty;

    protected String dateFormat;

    protected Boolean allowComments;

    protected JsonParser() {
        pretty(false);
        dateFormat("yyyy-MM-dd'T'HH:mm:ssz");
        allowComments(true);
    }

    public abstract JsonElement toObject(String json)
        throws JsonException;

    public abstract <T> T toObject(String json, final Class<T> clazz)
        throws JsonException;

    public abstract String toJson(JsonElement element)
        throws JsonException;

    public abstract String toJson(Object obj)
        throws JsonException;

    protected void pretty(boolean val) {
        this.pretty = val;
    }

    protected void dateFormat(String val) {
        this.dateFormat = val;
    }

    protected void allowComments(boolean val) {
        this.allowComments = val;
    }




}
