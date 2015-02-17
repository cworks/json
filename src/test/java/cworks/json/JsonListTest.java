package cworks.json;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class JsonListTest {
    
    @Test
    public void testToListBasic() {
        List<JsonElement> users = Json.asList(new File("src/test/resources/small_users.json"));
        Assert.assertEquals(1000, users.size());
    }
}
