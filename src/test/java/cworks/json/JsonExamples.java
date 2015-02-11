/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Baked with love by corbett
 * Package: net.cworks.json
 * User: corbett
 * Created: 09/10/2013 13:30
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package cworks.json;

import cworks.json.builder.JsonArrayBuilder;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class JsonExamples {

    private static final Random rand = new Random(System.currentTimeMillis());

    @Test
    public void testAsArray() {

        JsonArray users = Json.asArray(JsonTestData.USERS_ARRAY);

        JsonObject user1  = users.get(0);
        JsonObject user2  = users.get(1);
        JsonObject user10 = users.get(9);

        Assert.assertEquals("Alice",   user1.getString("first_name"));
        Assert.assertEquals("Timothy", user2.getString("first_name"));
        Assert.assertEquals("Tina",    user10.getString("first_name"));

        Assert.assertEquals(10, users.size());
    }

    @Test
    public void testAsObject() {

        JsonObject user = Json.asObject(JsonTestData.USER);

        Assert.assertEquals(Integer.valueOf(1), user.getInteger("id"));
        Assert.assertEquals("Justin", user.getString("firstName"));
        Assert.assertEquals("Harrison", user.getString("lastName"));
        Assert.assertEquals("jharrison0@diigo.com", user.getString("email"));
        Assert.assertEquals("China", user.getString("country"));
        Assert.assertEquals("205.174.198.42", user.getString("ipAddress"));

    }

    /**
     * This tests the ability of Json to deal with inconsistent field names
     * @see JsonTestData
     */
    @Test
    public void testJackedUserAsObject() {

        JsonObject user = Json.asObject(JsonTestData.JACKED_USER);

        Assert.assertEquals(Integer.valueOf(1), user.getInteger("id"));
        Assert.assertEquals("Justin", user.getString("firstName"));
        Assert.assertEquals("Harrison", user.getString("lastName"));
        Assert.assertEquals("jharrison0@diigo.com", user.getString("email"));
        Assert.assertEquals("China", user.getString("country"));
        Assert.assertEquals("205.174.198.42", user.getString("ipAddress"));
    }

    @Test
    public void testAsObjectToJavaInstance() {

        TestUser user = Json.asObject(JsonTestData.USER, TestUser.class);

        Assert.assertEquals("Justin", user.getFirstName());
        Assert.assertEquals("Harrison", user.getLastName());
        Assert.assertEquals("jharrison0@diigo.com", user.getEmail());
        Assert.assertEquals("China", user.getCountry());
        Assert.assertEquals("205.174.198.42", user.getIpAddress());

    }

    @Test
    public void testJackedAsObjectToJavaInstance() {

        TestUser user = Json.asObject(JsonTestData.JACKED_USER, TestUser.class);

        Assert.assertEquals("Justin", user.getFirstName());
        Assert.assertEquals("Harrison", user.getLastName());
        Assert.assertEquals("jharrison0@diigo.com", user.getEmail());
        Assert.assertEquals("China", user.getCountry());
        Assert.assertEquals("205.174.198.42", user.getIpAddress());
    }

    @Test
    public void randomExamples() {

        JsonObject jo = Json.object()
            .array("puppies", Json.array().add("Bucky").add("Nacho").build())
            .build();

        System.out.println(Json.asString(jo));

        JsonArray ja = Json.array().add("Red", "Green", "Blue").build();
        System.out.println(Json.asString(ja));

        ja = Json.array().add(new String[]{ "One", "Two", "Three" }).build();
        System.out.println(Json.asString(ja));

        jo = Json.object().build();
        System.out.println(Json.asString(jo));

        ja = Json.array().build();
        System.out.println(Json.asString(ja));

        jo = Json.object()
            .string("name", "Chuck Norris")
            .binary("picture", JsonTestData.CHUCK_NORRIS_IMAGE.getBytes())
            .build();
        System.out.println(Json.asString(jo));

        ja = Json.array().add(true)
            .add("Nacho")
            .add(100)
            .add(Json.object().string("address", "1 easy street").build())
            .build();
        System.out.println(Json.asString(ja));

        JsonElement je = Json.object().string("name", "Bucky")
            .array("eyes", Json.array()
                .add(Json.object().string("position", "left" ).string("color", "blue" ).build())
                .add(Json.object().string("position", "right").string("color", "brown").build())
                .build())
            .build();

        System.out.println(Json.asString(je));

        JsonArrayBuilder builder = Json.array();
        for(int i = 0; i < 100; i++) {
            builder.add(rand.nextInt());
        }
        ja = builder.build();
        System.out.println(Json.asString(ja));

        JsonArray jsonArray = Json.asArray("[true,\"Nacho\",100,{\"address\":\"1 easy street\"}]");
        Boolean bool = jsonArray.get(0);
        String str   = jsonArray.get(1);
        Integer num  = jsonArray.get(2);
        JsonObject object = jsonArray.get(3);

        Assert.assertTrue(bool);
        Assert.assertEquals("Nacho", str);
        Assert.assertEquals(100L, num.longValue());
        Assert.assertEquals("1 easy street", object.getString("address"));

    }

    @Test
    public void simpleUse() {

        JsonObject bucky = Json.object()
            .string("name", "Bucky Martin")
            .number("age", 4)
            .build();
        System.out.println(Json.asString(bucky));

        JsonObject lola = Json.object()
            .string("name", "Lola")
            .number("age", 6)
            .build();
        System.out.println(lola.asString());

    }

    @Test
    public void buildParser() {

        Map data = new HashMap<String, Object>();
        data.put("name", "Nacho Libre");
        data.put("year", 2008);
        data.put("boxOffice", "10 million");

        System.out.println(Json.asString(data));
        System.out.println(Json.asPrettyString(data));

    }

}
