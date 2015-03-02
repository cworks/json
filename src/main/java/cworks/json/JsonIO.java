package cworks.json;

import cworks.json.spi.JsonReader;
import cworks.json.spi.JsonWriter;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public abstract class JsonIO implements JsonWriter, JsonReader {
    

    protected JsonIO(JsonLib lib) {

    }
    
    public abstract JsonReader getReader();
    public abstract JsonWriter getWriter();
}
