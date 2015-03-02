package cworks.json;

import cworks.json.impl.gson.GsonIO;
import cworks.json.impl.jackson.JacksonIO;
import cworks.json.spi.JsonReader;
import cworks.json.spi.JsonWriter;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class JsonIOTest {
    
    @Test
    public void jacksonIO() {
        
        JsonIO io = new JacksonIO();
        JsonReader reader = io.getReader();
        JsonWriter writer = io.getWriter();
        
        Map user = reader.asObject(JsonTestData.USER, HashMap.class);
        System.out.println(user.toString());

    }
    
    @Test
    public void gsonIO() {
        
        JsonIO io = new GsonIO();
        JsonReader reader = io.getReader();
        JsonWriter writer = io.getWriter();
    }
}
