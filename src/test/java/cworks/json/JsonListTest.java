package cworks.json;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.List;

public class JsonListTest {
    
    //@Test
    public void testToListBasic() {
        List list = Json.asObject(JsonTestData.USERS_ARRAY, List.class);
        Assert.assertEquals(10, list.size());
        
        List<JsonObject> users = Json.asList(new File("src/test/resources/small_users.json"));
        Assert.assertEquals(1000, users.size());
    }
}
