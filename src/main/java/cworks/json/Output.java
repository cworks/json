package cworks.json;

import java.io.File;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface Output {

    public String asJson(Object object) throws JsonException;
    public String asJson(Object object, Type objectType) throws JsonException;
    public String asJson(Object[] objects) throws JsonException;
    public String asJson(Object[] objects, Type arrayType) throws JsonException;
    
    public String asJson(List object) throws JsonException;
    public String asJson(List objects, Type listType) throws JsonException;
    
    public String asJson(Map object) throws JsonException;
    public String asJson(Map object, Type mapType) throws JsonException;
    
    public void asJson(Object object, Writer output) throws JsonException;
    public void asJson(Object object, Type objectType, Writer output) throws JsonException;
    public void asJson(Object[] objects, Writer output) throws JsonException;
    public void asJson(Object[] objects, Type arrayType, Writer output) throws JsonException;
    
    public void asJson(Object object, File output) throws JsonException;
    public void asJson(Object object, Type objectType, File output) throws JsonException;
    public void asJson(Object[] objects, File output) throws JsonException;
    public void asJson(Object[] objects, Type arrayType, File output) throws JsonException;
    
    public void asJson(Object object, OutputStream output) throws JsonException;
    public void asJson(Object object, Type objectType, OutputStream output) throws JsonException;
    public void asJson(Object[] objects, OutputStream output) throws JsonException;
    public void asJson(Object[] objects, Type objectType, OutputStream output) throws JsonException;
    
    public void asJson(Object object, Path output) throws JsonException;
    public void asJson(Object object, Type objectType, Path output) throws JsonException;
    public void asJson(Object[] objects, Path output) throws JsonException;
    public void asJson(Object[] objects, Type objectType, Path output) throws JsonException;
    
    public String asJson(JsonElement element) throws JsonException;
    public String asJson(JsonElement element, Writer output) throws JsonException;
    public String asJson(JsonElement element, File output) throws JsonException;
    public String asJson(JsonElement element, OutputStream output) throws JsonException;
    public String asJson(JsonElement element, Path output) throws JsonException;

    public String asJson(JsonObject object) throws JsonException;
    public String asJson(JsonObject object, Writer output) throws JsonException;
    public String asJson(JsonObject object, File output) throws JsonException;
    public String asJson(JsonObject object, OutputStream output) throws JsonException;
    public String asJson(JsonObject object, Path output) throws JsonException;

    public String asJson(JsonArray array) throws JsonException;
    public String asJson(JsonArray array, Writer output) throws JsonException;
    public String asJson(JsonArray array, File output) throws JsonException;
    public String asJson(JsonArray array, OutputStream output) throws JsonException;
    public String asJson(JsonArray array, Path output) throws JsonException;

}

interface In {
    public JsonElement asElement(String json) throws JsonException;
    public JsonObject  asObject(String json) throws JsonException;
    public JsonArray   asArray(String json) throws JsonException;

    public <T> T asObject(String json, Type objectType) throws JsonException;
    public <T> T asObject(String json, Class<T> objectType) throws JsonException;

    public <T> T[] asArray(String json, Type arrayType) throws JsonException;
    public <T> T[] asArray(String json, Class<T> arrayType) throws JsonException;

    public List<JsonObject> asList(String json) throws JsonException;
    public <T> List<T> asList(String json, Type listType) throws JsonException;
    public <T> List<T> asList(String json, Class<T> listType) throws JsonException;

    public Map<String, ? extends JsonElement> asMap(String json) throws JsonException;
    public <T> Map<String, ? extends T> asMap(String json, Type mapType) throws JsonException;
    public <T> Map<String, ? extends T> asMap(String json, Class<T> mapType) throws JsonException;
    
    public JsonElement asElement(Reader input) throws JsonException;
    public JsonObject asObject(Reader input) throws JsonException;
    public JsonArray asArray(Reader input) throws JsonException;

    public <T> T asObject(Reader input, Type objectType) throws JsonException;
    public <T> T asObject(Reader input, Class<T> objectType) throws JsonException;

    
    public void asJson(Object object, Type objectType, Writer output) throws JsonException;
    public void asJson(Object[] objects, Writer output) throws JsonException;
    public void asJson(Object[] objects, Type arrayType, Writer output) throws JsonException;

    public void asJson(Object object, File output) throws JsonException;
    public void asJson(Object object, Type objectType, File output) throws JsonException;
    public void asJson(Object[] objects, File output) throws JsonException;
    public void asJson(Object[] objects, Type arrayType, File output) throws JsonException;

    public void asJson(Object object, OutputStream output) throws JsonException;
    public void asJson(Object object, Type objectType, OutputStream output) throws JsonException;
    public void asJson(Object[] objects, OutputStream output) throws JsonException;
    public void asJson(Object[] objects, Type objectType, OutputStream output) throws JsonException;

    public void asJson(Object object, Path output) throws JsonException;
    public void asJson(Object object, Type objectType, Path output) throws JsonException;
    public void asJson(Object[] objects, Path output) throws JsonException;
    public void asJson(Object[] objects, Type objectType, Path output) throws JsonException;

    public String asJson(JsonElement element) throws JsonException;
    public String asJson(JsonElement element, Writer output) throws JsonException;
    public String asJson(JsonElement element, File output) throws JsonException;
    public String asJson(JsonElement element, OutputStream output) throws JsonException;
    public String asJson(JsonElement element, Path output) throws JsonException;

    public String asJson(JsonObject object) throws JsonException;
    public String asJson(JsonObject object, Writer output) throws JsonException;
    public String asJson(JsonObject object, File output) throws JsonException;
    public String asJson(JsonObject object, OutputStream output) throws JsonException;
    public String asJson(JsonObject object, Path output) throws JsonException;

    public String asJson(JsonArray array) throws JsonException;
    public String asJson(JsonArray array, Writer output) throws JsonException;
    public String asJson(JsonArray array, File output) throws JsonException;
    public String asJson(JsonArray array, OutputStream output) throws JsonException;
    public String asJson(JsonArray array, Path output) throws JsonException;

}


