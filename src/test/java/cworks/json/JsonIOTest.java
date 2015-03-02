package cworks.json;

import cworks.json.impl.gson.GsonIO;
import cworks.json.impl.jackson.JacksonIO;
import cworks.json.spi.JsonReader;
import cworks.json.spi.JsonWriter;
import org.junit.Test;

public class JsonIOTest {
    
    @Test
    public void jacksonIO() {
        
        JsonIO io = new JacksonIO();
        JsonReader reader = io.getReader();
        JsonWriter writer = io.getWriter();


        
    }
    
    @Test
    public void gsonIO() {
        
        JsonIO io = new GsonIO();
        JsonReader reader = io.getReader();
        JsonWriter writer = io.getWriter();
    }
}
