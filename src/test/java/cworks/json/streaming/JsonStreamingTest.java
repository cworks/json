package cworks.json.streaming;

import cworks.json.Json;
import cworks.json.JsonObject;
import cworks.json.TestUser;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class JsonStreamingTest {

    
    @Test
    public void testLargeObject() throws IOException {
        
        InputStream in = new FileInputStream("src/test/resources/large_object.json");
        Json.asStream(in, new StreamHandler<JsonObject>() {
            @Override
            public void handle(JsonObject item) {
                System.out.println(item.asString());
            }
        });
    }
    
    @Test
    public void testSmallGenericObject() throws IOException {

        InputStream in = new FileInputStream("src/test/resources/user.json");
        Json.asStream(in, new StreamHandler<JsonObject>() {
            @Override
            public void handle(JsonObject item) {
                System.out.println(item.toString());
            }
        });
    }
    
    @Test
    public void testSmallObject() throws IOException {

        InputStream in = new FileInputStream("src/test/resources/small_users.json");
        Json.asStream(in, new StreamHandler<JsonObject>() {
            @Override
            public void handle(JsonObject item) {
                System.out.println(item.asString());
            }
        });
    }
        
    @Test
    public void testSmallTypedObject() throws IOException {
        
        InputStream in = new FileInputStream("src/test/resources/small_users.json");
        Json.asStream(in, new StreamHandler<TestUser>() {
            @Override
            public void handle(TestUser item) {
                System.out.println(item.toString());
            }
        });
    }
    
    @Test
    public void testSimpleNumberStreaming() throws IOException {
        
        InputStream in = new FileInputStream("src/test/resources/numbers.json");
        Json.asStream(in, new StreamHandler<Integer>() {
            @Override
            public void handle(Integer number) {
                System.out.println(number.toString() + " ");
            }
        });
    }
    
    @Test
    public void testBasicObject() throws IOException {

        InputStream in = new FileInputStream("src/test/resources/user.json");
        Json.asStream(in, new StreamHandler<Object>() {
            @Override
            public void handle(Object value) {
                System.out.println(value.toString() + " ");
            }
        });
    }
    
    @Test
    public void testBasicMixedArray() throws IOException {
        
        InputStream in = new FileInputStream("src/test/resources/basic_mixed_array.json");
        Json.asStream(in, new StreamHandler<Object>() {
            @Override
            public void handle(Object value) {
                System.out.println(value.toString() + " ");
            }
        });
    }

}
