package cworks.json.impl.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import cworks.json.JsonArray;
import cworks.json.JsonContext;
import cworks.json.JsonElement;
import cworks.json.JsonException;
import cworks.json.JsonObject;
import cworks.json.spi.JsonWriter;

import java.io.File;
import java.io.OutputStream;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public class JacksonWriter implements JsonWriter {
    
    private final JsonContext context;
    private final ObjectMapper mapper;
    
    public JacksonWriter(ObjectMapper mapper, JsonContext context) {
        this.mapper = mapper;
        this.context = context;
    }
    
    @Override
    public String asJson(Object object) throws JsonException {
        String json;
        try {
            if(context.isPretty()) {
                mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                json = mapper.writeValueAsString(object);
            } else {
                mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
                json = mapper.writeValueAsString(object);
            }
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return json;
    }

    @Override
    public String asJson(Object object, Type objectType) throws JsonException {
        return null;
    }

    @Override
    public String asJson(Object[] objects) throws JsonException {
        return null;
    }

    @Override
    public String asJson(Object[] objects, Type arrayType) throws JsonException {
        return null;
    }

    @Override
    public String asJson(List object) throws JsonException {
        return null;
    }

    @Override
    public String asJson(List objects, Type listType) throws JsonException {
        return null;
    }

    @Override
    public String asJson(Map object) throws JsonException {
        return null;
    }

    @Override
    public String asJson(Map object, Type mapType) throws JsonException {
        return null;
    }

    @Override
    public void asJson(Object object, Writer output) throws JsonException {

    }

    @Override
    public void asJson(Object object, Type objectType, Writer output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, Writer output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, Type arrayType, Writer output) throws JsonException {

    }

    @Override
    public void asJson(Object object, File output) throws JsonException {

    }

    @Override
    public void asJson(Object object, Type objectType, File output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, File output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, Type arrayType, File output) throws JsonException {

    }

    @Override
    public void asJson(Object object, OutputStream output) throws JsonException {

    }

    @Override
    public void asJson(Object object, Type objectType, OutputStream output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, OutputStream output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, Type objectType, OutputStream output) throws JsonException {

    }

    @Override
    public void asJson(Object object, Path output) throws JsonException {

    }

    @Override
    public void asJson(Object object, Type objectType, Path output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, Path output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, Type objectType, Path output) throws JsonException {

    }

    @Override
    public String asJson(JsonElement element) throws JsonException {

        if(element.isObject()) {
            Map data = ((JsonObject)element).toMap();
            return asJson(data);
        } else if(element.isArray()) {
            Object[] data = ((JsonArray)element).toArray();
            return asJson(data);
        }

        return "";
    }

    @Override
    public String asJson(JsonElement element, Writer output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonElement element, File output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonElement element, OutputStream output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonElement element, Path output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonObject object) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonObject object, Writer output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonObject object, File output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonObject object, OutputStream output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonObject object, Path output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonArray array) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonArray array, Writer output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonArray array, File output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonArray array, OutputStream output) throws JsonException {
        return null;
    }

    @Override
    public String asJson(JsonArray array, Path output) throws JsonException {
        return null;
    }
}
