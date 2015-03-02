package cworks.json;

import cworks.json.spi.JsonReader;
import cworks.json.spi.JsonWriter;
import org.junit.Test;

public class JsonIOTest {
    
    @Test
    public void jacksonIO() {
        
        JsonIO io = new JsonIO(JsonLib.JACKSON);
        JsonReader reader = io.getReader();
        JsonWriter writer = io.getWriter();

    }
    
    @Test
    public void gsonIO() {
        
        JsonIO io = new JsonIO(JsonLib.GSON);
        JsonReader reader = io.getReader();
        JsonWriter writer = io.getWriter();
    }
}
