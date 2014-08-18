/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
 * Baked with love by comartin
 * Package: net.cworks.json.parser
 * User: comartin
 * Created: 8/13/2014 10:14 AM
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package net.cworks.json.parser;

public class JsonParserBuilder {

    private JsonParser parser = null;

    private JsonParserBuilder(JsonParser parser) {
        this.parser = parser;
    }

    /**
     * Make a default JsonParser instance
     * @return
     */
    public static JsonParserBuilder parser() {

        return parser(ParserType.jackson);
    }

    /**
     * Make a supported JsonParser instance
     * @param parserType
     * @return
     */
    public static JsonParserBuilder parser(final ParserType parserType) {

        JsonParser parser = null;
        switch(parserType) {
            case jackson:
                parser = new JacksonParser();
                break;
            case gson:
            case jsonLib:
            default:
                parser = new JacksonParser();
        }

        return new JsonParserBuilder(parser);
    }

    public JsonParserBuilder pretty(boolean val) {
        parser.pretty(val);
        return this;
    }

    public JsonParserBuilder dateFormat(String format) {
        parser.dateFormat(format);
        return this;
    }

    public JsonParserBuilder allowComments(boolean val) {
        parser.allowComments(val);
        return this;
    }

    public JsonParser make() {

        if(parser == null) {
            throw new NullPointerException(
                "JsonParserBuilder.parser is null, cannot make JsonParser instance."
            );
        }

        return parser;
    }

}


