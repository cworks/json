package cworks.json.impl.jackson;

import cworks.json.IO;
import cworks.json.Json;
import cworks.json.JsonElement;
import cworks.json.JsonObject;
import cworks.json.JsonTestCore;
import cworks.json.JsonTestData;
import cworks.json.spi.JsonReader;
import cworks.json.spi.JsonWriter;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class JacksonWriterTest extends JsonTestCore {
    
    @Test
    public void testAsJson_JsonElement() throws IOException {
        String json1 = IO.asString(new File("src/test/resources/large_object.json"));
        JsonReader reader = new JacksonIO().getReader();
        JsonElement element = reader.asElement(json1);
        Assert.assertTrue(element.isObject());
        assertGoodLargeObject(element.asObject());

        JsonWriter writer = new JacksonIO().getWriter();
        String json2 = writer.asJson(element);
        
        Assert.assertEquals(json1, json2);
    }
    
    @Test
    public void testAsJson_JsonElement_Writer() throws IOException {

        JsonObject user = Json.asObject(JsonTestData.USER);
        JsonWriter writer = new JacksonIO().getWriter();
        Writer fileWriter = new FileWriter(new File("src/test/resources/user.json"));
        // Writer sw = new StringWriter();
        // write JsonElement as json text to the FileWriter
        writer.asJson(user, fileWriter);
    }
}
