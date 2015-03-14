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
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static cworks.json.IO.closeQuietly;

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
            } else {
                mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
            }
            json = mapper.writeValueAsString(object);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return json;
    }

    @Override
    public <T> String asJson(Object object, Class<T> objectType) throws JsonException {
        return null;
    }

    @Override
    public String asJson(Object[] objects) throws JsonException {
        return null;
    }

    @Override
    public <T> String asJson(Object[] objects, Class<T> arrayType) throws JsonException {
        return null;
    }

    @Override
    public String asJson(List object) throws JsonException {
        return null;
    }

    @Override
    public <T> String asJson(List objects, Class<T> listType) throws JsonException {
        return null;
    }

    @Override
    public String asJson(Map object) throws JsonException {
        return null;
    }

    @Override
    public <T> String asJson(Map object, Class<T> mapType) throws JsonException {
        return null;
    }

    @Override
    public void asJson(Object object, Writer output) throws JsonException {

    }

    @Override
    public <T> void asJson(Object object, Class<T> objectType, Writer output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, Writer output) throws JsonException {

    }

    @Override
    public <T> void asJson(Object[] objects, Class<T> arrayType, Writer output) throws JsonException {

    }

    @Override
    public void asJson(Object object, File output) throws JsonException {

    }

    @Override
    public <T> void asJson(Object object, Class<T> objectType, File output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, File output) throws JsonException {

    }

    @Override
    public <T> void asJson(Object[] objects, Class<T> arrayType, File output) throws JsonException {

    }

    @Override
    public void asJson(Object object, OutputStream output) throws JsonException {

    }

    @Override
    public <T> void asJson(Object object, Class<T> objectType, OutputStream output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, OutputStream output) throws JsonException {

    }

    @Override
    public <T> void asJson(Object[] objects, Class<T> objectType, OutputStream output) throws JsonException {

    }

    @Override
    public void asJson(Object object, Path output) throws JsonException {

    }

    @Override
    public <T> void asJson(Object object, Class<T> objectType, Path output) throws JsonException {

    }

    @Override
    public void asJson(Object[] objects, Path output) throws JsonException {

    }

    @Override
    public <T> void asJson(Object[] objects, Class<T> objectType, Path output) throws JsonException {

    }

    @Override
    public String asJson(JsonElement element) throws JsonException {

        if(element.isObject()) {
            return asJson(element.asObject());
        } else if(element.isArray()) {
            return asJson(element.asArray());
        }

        return "";
    }

    @Override
    public void asJson(JsonElement element, Writer output) throws JsonException {

        try {
            if(element.isObject()) {
                asJson(element.asObject(), output);
            } else if(element.isArray()) {
                asJson(element.asArray(), output);
            }
        } finally {
            closeQuietly(output);
        }
        
    }

    @Override
    public void asJson(JsonElement element, File output) throws JsonException {
    }

    @Override
    public void asJson(JsonElement element, OutputStream output) throws JsonException {
    }

    @Override
    public void asJson(JsonElement element, Path output) throws JsonException {
    }

    @Override
    public String asJson(JsonObject object) throws JsonException {

        String json;
        try {
            if(context.isPretty()) {
                mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            } else {
                mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
            }
            json = mapper.writeValueAsString(object);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return json;
    }

    @Override
    public void asJson(JsonObject object, Writer output) throws JsonException {

        try {
            if(context.isPretty()) {
                mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                mapper.writeValue(output, object);
            } else {
                mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
                mapper.writeValue(output, object);
            }
        } catch (IOException ex) {
            throw new JsonException(ex);
        } finally {
            closeQuietly(output);
        }
    }

    @Override
    public void asJson(JsonObject object, File output) throws JsonException {
    }

    @Override
    public void asJson(JsonObject object, OutputStream output) throws JsonException {
    }

    @Override
    public void asJson(JsonObject object, Path output) throws JsonException {
    }

    @Override
    public String asJson(JsonArray array) throws JsonException {

        String json;
        try {
            if(context.isPretty()) {
                mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                json = mapper.writeValueAsString(array);
            } else {
                mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
                json = mapper.writeValueAsString(array);
            }
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return json;
    }

    @Override
    public void asJson(JsonArray array, Writer output) throws JsonException {
        
        
    }

    @Override
    public void asJson(JsonArray array, File output) throws JsonException {
    }

    @Override
    public void asJson(JsonArray array, OutputStream output) throws JsonException {
    }

    @Override
    public void asJson(JsonArray array, Path output) throws JsonException {
    }
}
