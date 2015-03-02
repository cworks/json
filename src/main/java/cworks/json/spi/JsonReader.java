package cworks.json.spi;

import cworks.json.JsonArray;
import cworks.json.JsonElement;
import cworks.json.JsonException;
import cworks.json.JsonObject;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface JsonReader {
    public JsonElement asElement(String input) throws JsonException;
    public JsonObject asObject(String input) throws JsonException;
    public JsonArray asArray(String input) throws JsonException;

    public <T> T asObject(String input, Type objectType) throws JsonException;
    public <T> T asObject(String input, Class<T> objectType) throws JsonException;
    public <T> T[] asArray(String input, Type arrayType) throws JsonException;
    public <T> T[] asArray(String input, Class<T> arrayType) throws JsonException;

    public JsonElement asElement(StringBuffer input) throws JsonException;
    public JsonObject  asObject(StringBuffer input) throws JsonException;
    public JsonArray   asArray(StringBuffer input) throws JsonException;

    public <T> T asObject(StringBuffer input, Type objectType) throws JsonException;
    public <T> T asObject(StringBuffer input, Class<T> objectType) throws JsonException;
    public <T> T[] asArray(StringBuffer input, Type arrayType) throws JsonException;
    public <T> T[] asArray(StringBuffer input, Class<T> arrayType) throws JsonException;

    public JsonElement asElement(StringBuilder input) throws JsonException;
    public JsonObject  asObject(StringBuilder input) throws JsonException;
    public JsonArray   asArray(StringBuilder input) throws JsonException;

    public <T> T asObject(StringBuilder input, Type objectType) throws JsonException;
    public <T> T asObject(StringBuilder input, Class<T> objectType) throws JsonException;
    public <T> T[] asArray(StringBuilder input, Type arrayType) throws JsonException;
    public <T> T[] asArray(StringBuilder input, Class<T> arrayType) throws JsonException;

    public JsonElement asElement(Reader input) throws JsonException;
    public JsonObject asObject(Reader input) throws JsonException;
    public JsonArray asArray(Reader input) throws JsonException;

    public <T> T asObject(Reader input, Type objectType) throws JsonException;
    public <T> T asObject(Reader input, Class<T> objectType) throws JsonException;
    public <T> T[] asArray(Reader input, Type arrayType) throws JsonException;
    public <T> T[] asArray(Reader input, Class<T> arrayType) throws JsonException;

    public JsonElement asElement(File input) throws JsonException;
    public JsonObject asObject(File input) throws JsonException;
    public JsonArray asArray(File input) throws JsonException;

    public <T> T asObject(File input, Type objectType) throws JsonException;
    public <T> T asObject(File input, Class<T> objectType) throws JsonException;
    public <T> T[] asArray(File input, Type arrayType) throws JsonException;
    public <T> T[] asArray(File input, Class<T> arrayType) throws JsonException;

    public JsonElement asElement(InputStream input) throws JsonException;
    public JsonObject asObject(InputStream input) throws JsonException;
    public JsonArray asArray(InputStream input) throws JsonException;

    public <T> T asObject(InputStream input, Type objectType) throws JsonException;
    public <T> T asObject(InputStream input, Class<T> objectType) throws JsonException;
    public <T> T[] asArray(InputStream input, Type arrayType) throws JsonException;
    public <T> T[] asArray(InputStream input, Class<T> arrayType) throws JsonException;

    public JsonElement asElement(Path input) throws JsonException;
    public JsonObject asObject(Path input) throws JsonException;
    public JsonArray asArray(Path input) throws JsonException;

    public <T> T asObject(Path input, Type objectType) throws JsonException;
    public <T> T asObject(Path input, Class<T> objectType) throws JsonException;
    public <T> T[] asArray(Path input, Type arrayType) throws JsonException;
    public <T> T[] asArray(Path input, Class<T> arrayType) throws JsonException;

    public List<JsonObject> asList(String input) throws JsonException;
    public List<JsonObject> asList(StringBuffer input) throws JsonException;
    public List<JsonObject> asList(StringBuilder input) throws JsonException;
    public List<JsonObject> asList(File input) throws JsonException;
    public List<JsonObject> asList(Path input) throws JsonException;
    public List<JsonObject> asList(Reader input) throws JsonException;
    public List<JsonObject> asList(InputStream input) throws JsonException;

    public <T> List<T> asList(String input, Type listType) throws JsonException;
    public <T> List<T> asList(StringBuffer input, Type listType) throws JsonException;
    public <T> List<T> asList(StringBuilder input, Type listType) throws JsonException;
    public <T> List<T> asList(File input, Type listType) throws JsonException;
    public <T> List<T> asList(Path input, Type listType) throws JsonException;
    public <T> List<T> asList(Reader input, Type listType) throws JsonException;
    public <T> List<T> asList(InputStream input, Type listType) throws JsonException;

    public <T> List<T> asList(String input, Class<T> listType) throws JsonException;
    public <T> List<T> asList(StringBuffer input, Class<T> listType) throws JsonException;
    public <T> List<T> asList(StringBuilder input, Class<T> listType) throws JsonException;
    public <T> List<T> asList(File input, Class<T> listType) throws JsonException;
    public <T> List<T> asList(Path input, Class<T> listType) throws JsonException;
    public <T> List<T> asList(Reader input, Class<T> listType) throws JsonException;
    public <T> List<T> asList(InputStream input, Class<T> listType) throws JsonException;

    public Map<String, ? extends JsonElement> asMap(String input) throws JsonException;
    public Map<String, ? extends JsonElement> asMap(StringBuffer input) throws JsonException;
    public Map<String, ? extends JsonElement> asMap(StringBuilder input) throws JsonException;
    public Map<String, ? extends JsonElement> asMap(File input) throws JsonException;
    public Map<String, ? extends JsonElement> asMap(Path input) throws JsonException;
    public Map<String, ? extends JsonElement> asMap(Reader input) throws JsonException;
    public Map<String, ? extends JsonElement> asMap(InputStream input) throws JsonException;

    public <T> Map<String, ? extends T> asMap(String input, Type mapType) throws JsonException;
    public <T> Map<String, ? extends T> asMap(StringBuffer input, Type mapType) throws JsonException;
    public <T> Map<String, ? extends T> asMap(StringBuilder input, Type mapType) throws JsonException;
    public <T> Map<String, ? extends T> asMap(File input, Type mapType) throws JsonException;
    public <T> Map<String, ? extends T> asMap(Path input, Type mapType) throws JsonException;
    public <T> Map<String, ? extends T> asMap(Reader input, Type mapType) throws JsonException;
    public <T> Map<String, ? extends T> asMap(InputStream input, Type mapType) throws JsonException;

    public <T> Map<String, ? extends T> asMap(String input, Class<T> mapType) throws JsonException;
    public <T> Map<String, ? extends T> asMap(StringBuffer input, Class<T> mapType) throws JsonException;
    public <T> Map<String, ? extends T> asMap(StringBuilder input, Class<T> mapType) throws JsonException;
    public <T> Map<String, ? extends T> asMap(File input, Class<T> mapType) throws JsonException;
    public <T> Map<String, ? extends T> asMap(Path input, Class<T> mapType) throws JsonException;
    public <T> Map<String, ? extends T> asMap(Reader input, Class<T> mapType) throws JsonException;
    public <T> Map<String, ? extends T> asMap(InputStream input, Class<T> mapType) throws JsonException;
}