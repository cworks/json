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
    public void testAsElement_Reader() throws IOException {
        Reader reader = new FileReader("src/test/resources/large_object.json");
        JsonReader jackson = new JacksonIO().getReader();
        JsonElement element = jackson.asElement(reader);
        Assert.assertTrue(element.isObject());
        assertGoodLargeObject(element.asObject());
    }
}
