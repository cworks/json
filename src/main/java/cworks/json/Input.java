package cworks.json;

import cworks.json.streaming.StreamHandler;
import cworks.json.streaming.Token;

import java.io.InputStream;
import java.util.List;
import java.util.stream.Stream;

public interface Input {

    /**
     * Read the input stream and stream tokens out
     * @param inputStream
     * @return
     */
    public Stream<Token> asStream(final InputStream inputStream);

    /**
     * Read the input stream and call handler for each token read 
     * @param in
     * @param handler
     * @throws JsonException
     */
    public void asStream(InputStream in, StreamHandler<Token> handler) throws JsonException;

    /**
     * Read the input stream and convert each token to instance of clazz and call handler
     * @param in
     * @param clazz
     * @param handler
     * @param <T>
     * @throws JsonException
     */
    public <T> void asStream(InputStream in, Class<T> clazz, StreamHandler<T> handler) throws JsonException;

    /**
     * Read json element and convert into a JsonElement
     * @param json
     * @return
     * @throws JsonException
     */
    public JsonElement asElement(String json) throws JsonException;
    
    /**
     * Read json object and convert into a JsonObject instance
     * @param json
     * @return
     * @throws JsonException
     */
    public JsonObject asObject(String json) throws JsonException;

    /**
     * Read json object and convert into an instance of clazz
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws JsonException
     */
    public <T> T asObject(String json, final Class<T> clazz) throws JsonException;

    /**
     * Read json array and convert into a JsonArray
     * @param json
     * @return
     * @throws JsonException
     */
    public JsonArray asArray(String json) throws JsonException;
    
    /**
     * Read json array and convert into an array of clazz instances
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws JsonException
     */
    public <T> T[] asArray(String json, final Class<T> clazz) throws JsonException;

    /**
     * Read json array and convert to a list of JsonObjects
     * @param json
     * @return
     * @throws JsonException
     */
    public List<JsonObject> asList(String json) throws JsonException;

    /**
     * Read json array and convert to a list of clazz instances
     * @param json
     * @param clazz
     * @param <T>
     * @return
     * @throws JsonException
     */
    public <T> List<T> asList(String json, Class<T> clazz) throws JsonException;
    
}
