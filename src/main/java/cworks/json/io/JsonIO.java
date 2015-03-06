package cworks.json.io;

import cworks.json.spi.JsonReader;
import cworks.json.spi.JsonWriter;

public abstract class JsonIO implements JsonWriter, JsonReader {

    /**
     * to build json pretty or not
     */
    protected Boolean pretty;

    /**
     * Date format to use by default 
     */
    protected String dateFormat;

    /**
     * to allow comments or not 
     */
    protected Boolean allowComments;

    /**
     * Create a JsonIO instance with default settings 
     */
    protected JsonIO() {
        pretty(false);
        dateFormat("yyyy-MM-dd'T'HH:mm:ssz");
        allowComments(true);
    }

    /**
     * Abstraction that must be implemented by concrete classes.  Implementations
     * should return concrete JsonReader that can be used to translate json input
     * into Java types. 
     * @return
     */
    public abstract JsonReader getReader();

    /**
     * Abstraction that must be implemented by concrete classes.  Implementations
     * should return concrete JsonWriter that can be used to translate Java types
     * into json output.
     * @return
     */
    public abstract JsonWriter getWriter();

    /**
     * Should this JsonIO deal in pretty json or not? 
     * @param val
     */
    protected void pretty(boolean val) {
        this.pretty = val;
    }

    /**
     * Customize the default date format 
     * @param val
     */
    protected void dateFormat(String val) {
        this.dateFormat = val;
    }

    /**
     * Should this JsonIO support comments in json?
     * @param val
     */
    protected void allowComments(boolean val) {
        this.allowComments = val;
    }
}
