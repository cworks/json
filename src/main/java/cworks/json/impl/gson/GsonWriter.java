package cworks.json.impl.gson;

import cworks.json.JsonArray;
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

public class GsonWriter implements JsonWriter {
    @Override
    public String asJson(Object object) throws JsonException {
        return null;
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
        return null;
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
