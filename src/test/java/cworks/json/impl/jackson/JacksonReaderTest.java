package cworks.json.impl.jackson;

import cworks.json.*;
import cworks.json.spi.JsonReader;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JacksonReaderTest extends JsonTestCore {
    
    @Test
    public void testAsElement_String() throws IOException {
        String json = IO.asString(new File("src/test/resources/large_object.json"));
        JsonReader jackson = new JacksonIO().getReader();
        JsonElement element = jackson.asElement(json);
        Assert.assertTrue(element.isObject());
        assertGoodLargeObject(element.asObject());
    }

    @Test
    public void testAsObject_String() throws IOException {
        String json = IO.asString(new File("src/test/resources/large_object.json"));
        JsonReader jackson = new JacksonIO().getReader();
        JsonObject object = jackson.asObject(json);
        Assert.assertNotNull(object);
        assertGoodLargeObject(object);
    }

    @Test
    public void testAsArray_String() throws IOException {
        String json = IO.asString(new File("src/test/resources/small_object_array.json"));
        JsonReader jackson = new JacksonIO().getReader();
        JsonArray array = jackson.asArray(json);
        Assert.assertNotNull(array);
        assertGoodSmallObjectArray(array);
    }
    
    @Test
    public void testAsObject_String_Class() throws IOException {
        
        String json = IO.asString(new File("src/test/resources/small_object.json"));
        JsonReader jackson = new JacksonIO().getReader();
        
        TestUser user = jackson.asObject(json, TestUser.class);
        Assert.assertEquals("Roy", user.getFirstName());
        Assert.assertEquals("Watkins", user.getLastName());
        Assert.assertEquals("rwatkins0@studiopress.com", user.getEmail());
        Assert.assertEquals("China", user.getCountry());
        Assert.assertEquals("10.242.205.116", user.getIpAddress());
    }
    
    @Test
    public void testAsArray_String_Class() throws IOException {

        String json = IO.asString(new File("src/test/resources/small_object_array.json"));
        JsonReader jackson = new JacksonIO().getReader();
        
        TestUser[] users = jackson.asArray(json, TestUser.class);
        Assert.assertEquals(1000, users.length);
        Assert.assertEquals(195, users[194].getId().intValue());
        Assert.assertEquals("Stephen", users[194].getFirstName());
        Assert.assertEquals("Mason", users[194].getLastName());
        Assert.assertEquals("smason5e@barnesandnoble.com", users[194].getEmail());
        Assert.assertEquals("Ukraine", users[194].getCountry());
        Assert.assertEquals("54.49.166.253", users[194].getIpAddress());
    }
    
    @Test
    public void testAsElement_StringBuffer() throws IOException {
        StringBuffer json = IO.asStringBuffer(new File("src/test/resources/large_object.json"));
        JsonReader jackson = new JacksonIO().getReader();
        JsonElement element = jackson.asElement(json);
        Assert.assertTrue(element.isObject());
        assertGoodLargeObject(element.asObject());
    }
    
    @Test
    public void testAsObject_StringBuffer() throws IOException {
        StringBuffer json = IO.asStringBuffer(new File("src/test/resources/large_object.json"));
        JsonReader jackson = new JacksonIO().getReader();
        JsonObject object = jackson.asObject(json);
        Assert.assertNotNull(object);
        assertGoodLargeObject(object);
    }
    
    @Test
    public void testAsArray_StringBuffer() throws IOException {
        StringBuffer json = IO.asStringBuffer(new File("src/test/resources/small_object_array.json"));
        JsonReader jackson = new JacksonIO().getReader();
        JsonArray array = jackson.asArray(json);
        Assert.assertNotNull(array);
        assertGoodSmallObjectArray(array);
    }
    
    @Test
    public void testAsObject_StringBuffer_Class() throws IOException {
        StringBuffer json = IO.asStringBuffer(new File("src/test/resources/small_object.json"));
        JsonReader jackson = new JacksonIO().getReader();

        TestUser user = jackson.asObject(json, TestUser.class);
        Assert.assertEquals("Roy", user.getFirstName());
        Assert.assertEquals("Watkins", user.getLastName());
        Assert.assertEquals("rwatkins0@studiopress.com", user.getEmail());
        Assert.assertEquals("China", user.getCountry());
        Assert.assertEquals("10.242.205.116", user.getIpAddress());
    }
    
    @Test
    public void testAsArray_StringBuffer_Class() throws IOException {
        StringBuffer json = IO.asStringBuffer(new File("src/test/resources/small_object_array.json"));
        JsonReader jackson = new JacksonIO().getReader();

        TestUser[] users = jackson.asArray(json, TestUser.class);
        Assert.assertEquals(1000, users.length);
        Assert.assertEquals(195, users[194].getId().intValue());
        Assert.assertEquals("Stephen", users[194].getFirstName());
        Assert.assertEquals("Mason", users[194].getLastName());
        Assert.assertEquals("smason5e@barnesandnoble.com", users[194].getEmail());
        Assert.assertEquals("Ukraine", users[194].getCountry());
        Assert.assertEquals("54.49.166.253", users[194].getIpAddress());
    }
    
    @Test
    public void testAsElement_StringBuilder() throws IOException {
        StringBuilder json = IO.asStringBuilder(new File("src/test/resources/large_object.json"));
        JsonReader jackson = new JacksonIO().getReader();
        JsonElement element = jackson.asElement(json);
        Assert.assertTrue(element.isObject());
        assertGoodLargeObject(element.asObject());
    }
    
    @Test
    public void testAsElement_Reader() throws IOException {
        Reader reader = new FileReader("src/test/resources/large_object.json");
        JsonReader jackson = new JacksonIO().getReader();
        JsonElement element = jackson.asElement(reader);
        Assert.assertTrue(element.isObject());
        assertGoodLargeObject(element.asObject());
    }
    
    @Test
    public void testAsObject_Reader() throws IOException {
        Reader reader = new FileReader("src/test/resources/large_object.json");
        JsonReader jackson = new JacksonIO().getReader();
        JsonObject object = jackson.asObject(reader);
        Assert.assertTrue(object.isObject());
        assertGoodLargeObject(object.asObject());
    }
    
    @Test
    public void testAsArray_Reader() throws IOException {
        Reader reader = new FileReader("src/test/resources/small_object_array.json");
        JsonReader jackson = new JacksonIO().getReader();
        JsonArray array = jackson.asArray(reader);
        Assert.assertNotNull(array);
        assertGoodSmallObjectArray(array);
    }
}
