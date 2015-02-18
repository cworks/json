package cworks.json.streaming;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import cworks.json.Json;
import cworks.json.JsonObject;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JsonStreamingTest {
    
    private static final String JSON_DATA = "[\n" +
        "  {\"id\":1,\"first_name\":\"Roy\",\"last_name\":\"Watkins\",\"email\":\"rwatkins0@studiopress.com\",\"country\":\"China\",\"ip_address\":\"10.242.205.116\"},\n" +
        "  {\"id\":2,\"first_name\":\"Terry\",\"last_name\":\"Kim\",\"email\":\"tkim1@nydailynews.com\",\"country\":\"China\",\"ip_address\":\"59.208.196.189\"},\n" +
        "  {\"id\":3,\"first_name\":\"Justin\",\"last_name\":\"Ward\",\"email\":\"jward2@phpbb.com\",\"country\":\"Indonesia\",\"ip_address\":\"185.116.168.195\"}]";
    
    static class Message {
        public String message;
        public String toString() {
            return "message: " + message;
        }
    }
    
    @Test
    public void testMappingIterator() throws IOException {
        
        ObjectMapper mapper = new ObjectMapper();
        
        MappingIterator<Map> it = mapper.reader(HashMap.class)
            .readValues(JSON_DATA);
        
        while(it.hasNextValue()) {

            System.out.println(it.nextValue());
        }
        
    }
}
