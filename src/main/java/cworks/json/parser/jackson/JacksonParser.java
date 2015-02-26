/**
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 * Baked with love by corbett
 * Project: json
 * Package: cworks.json.parser
 * Class: JacksonParser
 * Created: 8/1/14 3:40 PM
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 */
package cworks.json.parser.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.deser.std.StdDelegatingDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.util.StdConverter;
import cworks.json.JsonArray;
import cworks.json.JsonContext;
import cworks.json.JsonElement;
import cworks.json.JsonException;
import cworks.json.JsonHandler;
import cworks.json.JsonNull;
import cworks.json.JsonObject;
import cworks.json.parser.JsonParser;
import cworks.json.streaming.StreamHandler;
import cworks.json.streaming.Token;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class JacksonParser extends JsonParser {

    private final ObjectMapper mapper;

    public JacksonParser() {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(com.fasterxml.jackson.core.JsonParser.Feature.ALLOW_COMMENTS, true);

        
        SimpleModule module = new SimpleModule("cworksJacksonModule");
        module.addDeserializer(JsonObject.class,
            new StdDelegatingDeserializer<>(
                new StdConverter<Map, JsonObject>() {
                    @Override
                    public JsonObject convert(Map map) {
                        return new JsonObject(map);
                    }
                }
            ));

        mapper.registerModule(module);
    }

    @Override
    public JsonElement toObject(String json) throws JsonException {

        JsonElement element = null;

        try {
            if (json.startsWith("[")) {
                List list = toObject(json, List.class);
                element = new JsonArray(list);
            } else if (json.startsWith("{")) {
                Map map = toObject(json, Map.class);
                element = new JsonObject(map);
            }
        } catch(Exception ex) {
            throw new JsonException(ex);
        }

        if(element == null) {
            throw new JsonException("json argument isn't valid Json");
        }

        return element;
    }

    @Override
    public <T> T toObject(String json, Class<T> clazz) throws JsonException {

        T object;
        try {
            object = mapper.readValue(wrapper(json), clazz);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return object;
    }

    @Override
    public <T> T[] toArray(String json, Class<T> clazz) throws JsonException {
        
        T[] array;
        
        try {
            ArrayType arrayType = mapper.getTypeFactory().constructArrayType(clazz);
            array = mapper.readValue(wrapper(json), arrayType);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }
        
        return array;
    }
    
    @Override
    public List<JsonObject> toList(String json) throws JsonException {
        
        List<JsonObject> list;
        
        try {
            CollectionType collectionType = mapper.getTypeFactory()
                .constructCollectionType(List.class, JsonObject.class);
            list = mapper.readValue(wrapper(json), collectionType);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }
        
        return list;
    }

    public void read(final String json, final JsonHandler handler) throws JsonException {

        try {
            com.fasterxml.jackson.core.JsonParser jp = wrapper(json);

            jp.nextToken();

            while(jp.nextToken() == JsonToken.START_OBJECT) {
                final JsonObject thing = mapper.readValue(jp, JsonObject.class);
                if(thing == null) {
                    handler.complete(new JsonContext() { });
                    break;
                }
                handler.handle(thing, new JsonContext() {});
            }
        } catch (IOException ex) {
            throw new JsonException(ex);
        }
    }

    public <T> void read(InputStream in, Class<T> clazz, StreamHandler<T> handler) throws JsonException {
        
        try {
            com.fasterxml.jackson.core.JsonParser jp = wrapper(in);
            //simulate(jp);
            //emit(jp, handler);
            
//            JsonToken firstToken = jp.nextToken();
//            if(firstToken == JsonToken.START_ARRAY) {
//                readArray(jp, clazz, handler);
//            } else if(firstToken == JsonToken.START_OBJECT) {
//                readObject(jp, clazz, handler);
//            }
        } catch(IOException ex) {
            throw new JsonException(ex);
        }
    }


    public void read(InputStream in, StreamHandler<Token> handler) throws JsonException {

        try {
            com.fasterxml.jackson.core.JsonParser jp = wrapper(in);
            //simulate(jp);
            emit(jp, handler);
            
            
            
//            JsonToken firstToken = jp.getCurrentToken();
//            // Doing this because something is wacky with jackson.  In some cases the
//            // parser's pointer is set to the first token and sometimes its set before.
//            if(firstToken == null) {
//                firstToken = jp.nextToken();
//            }
//            if(firstToken == JsonToken.START_ARRAY) {
//                readArray(jp, handler);
//            } else if(firstToken == JsonToken.START_OBJECT) {
//                readObject(jp, handler);
//            }
        } catch(IOException ex) {
            throw new JsonException(ex);
        }
    }


    @Override
    public <T> List<T> toList(String json, Class<T> clazz) throws JsonException {

        List<T> list;

        try {
            CollectionType collectionType = mapper.getTypeFactory()
                .constructCollectionType(List.class, clazz);
            list = mapper.readValue(wrapper(json), collectionType);
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return list;
    }

    @Override
    public String toJson(JsonElement element) throws JsonException {
        if(element.isObject()) {
            Map data = ((JsonObject)element).toMap();
            return toJson(data);
        } else if(element.isArray()) {
            Object[] data = ((JsonArray)element).toArray();
            return toJson(data);
        }

        return "";
    }

    @Override
    public String toJson(Object obj) throws JsonException {

        String json;
        try {
            if(pretty) {
                mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
                json = mapper.writeValueAsString(obj);
            } else {
                mapper.configure(SerializationFeature.INDENT_OUTPUT, false);
                json = mapper.writeValueAsString(obj);
            }
        } catch (Exception ex) {
            throw new JsonException(ex);
        }

        return json;
    }

    private void readArray(com.fasterxml.jackson.core.JsonParser jp, StreamHandler<Object> handler)
        throws IOException {
        
        JsonToken token = jp.nextToken();
        while(token != null) {

            if(token == JsonToken.VALUE_FALSE || token == JsonToken.VALUE_TRUE) {
                Boolean value = mapper.readValue(jp, Boolean.class);
                handler.handle(value);
                //continue;
            } else if(token == JsonToken.VALUE_NUMBER_INT) {
                Integer value = mapper.readValue(jp, Integer.class);
                handler.handle(value);
                //continue;
            } else if(token == JsonToken.VALUE_NUMBER_FLOAT) {
                Double value = mapper.readValue(jp, Double.class);
                handler.handle(value);
                //continue;
            } else if(token == JsonToken.VALUE_STRING) {
                String value = mapper.readValue(jp, String.class);
                handler.handle(value);
                //continue;
            } else if(token == JsonToken.VALUE_NULL) {
                jp.nextToken(); // skip over null value
                handler.handle(new JsonNull());
                //continue;
            } else if(token == JsonToken.START_OBJECT) {
                JsonObject object = mapper.readValue(jp, JsonObject.class);
                handler.handle(object);
                //continue;
            }
            
            // move to next token
            token = jp.nextToken();
        }
    }
    
    private <T> void readArray(com.fasterxml.jackson.core.JsonParser jp, Class<T> clazz, StreamHandler<T> handler) throws IOException {
        
        JsonToken token = jp.nextToken();
        while(token != null) {

            if (isValueToken(token)) {
                final T thing = mapper.readValue(jp, clazz);
                if(thing == null) {
                    //handler.handle(null);
                    break;
                }
                handler.handle(thing);
            } else {
                token = jp.nextToken();
            }
        }

    }
    
    private <T> void readObject(com.fasterxml.jackson.core.JsonParser jp, Class<T> clazz, StreamHandler<T> handler) throws IOException {
        
        if(jp.getCurrentToken() == JsonToken.START_OBJECT) {
            final T thing = mapper.readValue(jp, clazz);
            handler.handle(thing);
        }
    }
    
    private void readObject(com.fasterxml.jackson.core.JsonParser jp, StreamHandler<Object> handler) throws IOException {
        //if(jp.getCurrentToken() == JsonToken.START_OBJECT) {
            final JsonObject thing = mapper.readValue(jp, JsonObject.class);
            handler.handle(thing);
        //}
    }

    /**
     * Create a wrapper around the Jackson parser so that we can tweak some features
     * to our liking
     * *
     * @param json json text
     * @return jackson parser
     * @throws IOException
     */
    private com.fasterxml.jackson.core.JsonParser wrapper(String json) throws IOException {
        JsonFactory factory = mapper.getFactory();
        com.fasterxml.jackson.core.JsonParser parser = factory.createParser(json);

        return new ParserDelegate(parser);
    }

    private com.fasterxml.jackson.core.JsonParser wrapper(InputStream json) throws IOException {
        JsonFactory factory = mapper.getFactory();
        com.fasterxml.jackson.core.JsonParser parser = factory.createParser(json);

        return new ParserDelegate(parser);
    }
    
    private void simulate(com.fasterxml.jackson.core.JsonParser parser) throws IOException {
        
        
        while(parser.nextToken() != null) {
            
            StringBuilder sb = new StringBuilder();
            sb.append(parser.getCurrentName()).append(",").append(parser.getCurrentToken().toString());
            System.out.println(sb.toString());
        }
        
    }
    
    private <T> void emit(com.fasterxml.jackson.core.JsonParser parser, StreamHandler<Token> handler) throws IOException {
        
        Queue<Node> stack = Collections.asLifoQueue(new ArrayDeque<>());
        while(parser.nextToken() != null) {
            JsonToken currentToken = parser.getCurrentToken();
            String currentName = parser.getCurrentName();
            if (currentToken == JsonToken.START_OBJECT) {
                // top-level object (the root)
                if (stack.isEmpty()) {
                    stack.add(new ObjectNode("root"));
                } else {
                    // anonymous object
                    if (isNullOrEmpty(currentName)) {
                        stack.add(new ObjectNode(stack.peek()));
                    } else {
                        // named object
                        stack.add(new ObjectNode(currentName, stack.peek()));
                    }
                }
            } else if (currentToken == JsonToken.END_OBJECT) {
                stack.remove();
            } else if (currentToken == JsonToken.START_ARRAY) {
                // top-level array (the root)
                if (stack.isEmpty()) {
                    stack.add(new ArrayNode("root"));
                } else {
                    // anonymous array
                    if (isNullOrEmpty(currentName)) {
                        stack.add(new ArrayNode(stack.peek()));
                    } else {
                        // named object
                        stack.add(new ArrayNode(currentName, stack.peek()));
                    }
                }
            } else if (currentToken == JsonToken.END_ARRAY) {
                stack.remove();
            } else if (currentToken == JsonToken.VALUE_STRING) {
                Node parent = stack.peek();
                handler.handle(createToken(parent, parser.getValueAsString()));
            } else if (currentToken == JsonToken.VALUE_NUMBER_INT) {
                Node parent = stack.peek();
                handler.handle(createToken(parent, parser.getIntValue()));
            } else if (currentToken == JsonToken.VALUE_NUMBER_FLOAT) {
                Node parent = stack.peek();
                handler.handle(createToken(parent, parser.getDoubleValue()));
            } else if (currentToken == JsonToken.VALUE_FALSE || currentToken == JsonToken.VALUE_TRUE) {
                Node parent = stack.peek();
                handler.handle(createToken(parent, parser.getBooleanValue()));
            } else if(currentToken == JsonToken.VALUE_NULL) {
                Node parent = stack.peek();
                handler.handle(createToken(parent));
            } else if(currentToken == JsonToken.FIELD_NAME) {
                // push on to the value token
                JsonToken valueToken = parser.nextToken();
                if(valueToken == JsonToken.START_OBJECT) {
                    stack.add(new ObjectNode(parser.getCurrentName(), stack.peek()));
                } else if(valueToken == JsonToken.START_ARRAY) {
                    stack.add(new ArrayNode(parser.getCurrentName(), stack.peek()));
                } else if(valueToken == JsonToken.VALUE_FALSE || valueToken == JsonToken.VALUE_TRUE) {
                    Node parent = stack.peek();
                    handler.handle(createToken(parent, parser.getCurrentName(), parser.getBooleanValue()));
                } else if(valueToken == JsonToken.VALUE_STRING) {
                    Node parent = stack.peek();
                    handler.handle(createToken(parent, parser.getCurrentName(), parser.getValueAsString()));
                } else if(valueToken == JsonToken.VALUE_NUMBER_INT) {
                    Node parent = stack.peek();
                    handler.handle(createToken(parent, parser.getCurrentName(), parser.getIntValue()));
                } else if(valueToken == JsonToken.VALUE_NUMBER_FLOAT) {
                    Node parent = stack.peek();
                    handler.handle(createToken(parent, parser.getCurrentName(), parser.getIntValue()));
                } else if(valueToken == JsonToken.VALUE_NULL) {
                    Node parent = stack.peek();
                    handler.handle(createToken(parent, parser.getCurrentName(), null));
                }
            }
        }
        
        assert stack.isEmpty();
        
    }

    
    private Token createToken(Node node) {
        Token token = null;
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode)node;
            token = createToken(arrayNode);
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode)node;
            token = createToken(objectNode);
        }
        return token;
    }

    private Token createToken(Node node, String value) {
        Token token = null;
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode)node;
            token = createToken(arrayNode, value);
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode)node;
            token = createToken(objectNode, value);
        }
        return token;
    }

    private Token createToken(Node node, String name, String value) {
        Token token = null;
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode)node;
            token = createToken(arrayNode, name, value);
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode)node;
            token = createToken(objectNode, name, value);
        }
        return token;    }
    
    private Token createToken(Node node, int value) {
        Token token = null;
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode)node;
            token = createToken(arrayNode, value);
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode)node;
            token = createToken(objectNode, value);
        }
        return token;
    }

    private Token createToken(Node node, String name, int value) {
        Token token = null;
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode)node;
            token = createToken(arrayNode, name, value);
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode)node;
            token = createToken(objectNode, name, value);
        }
        return token;
    }

    private Token createToken(Node node, double value) {
        Token token = null;
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode)node;
            token = createToken(arrayNode, value);
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode)node;
            token = createToken(objectNode, value);
        }
        return token;
    }

    private Token createToken(Node node, String name, double value) {
        Token token = null;
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode)node;
            token = createToken(arrayNode, name, value);
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode)node;
            token = createToken(objectNode, name, value);
        }
        return token;
    }

    private Token createToken(Node node, boolean value) {
        Token token = null;
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode)node;
            token = createToken(arrayNode, value);
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode)node;
            token = createToken(objectNode, value);
        }
        return token;
    }

    private Token createToken(Node node, String name, boolean value) {
        Token token = null;
        if (node.isArray()) {
            ArrayNode arrayNode = (ArrayNode)node;
            token = createToken(arrayNode, name, value);
        } else if (node.isObject()) {
            ObjectNode objectNode = (ObjectNode)node;
            token = createToken(objectNode, name, value);
        }
        return token;
    }    
    
    private Token createToken(ArrayNode node, String value) {

        String id = String.format("%s[%d]", node.id, node.nextIndex());
        return new StreamToken(id, value);
    }

    private Token createToken(ArrayNode node, String name, String value) {

        String id = String.format("%s[%d].%s", node.id, node.nextIndex(), name);
        return new StreamToken(id, value);
    }    

    private Token createToken(ArrayNode node, int value) {

        String id = String.format("%s[%d]", node.id, node.nextIndex());
        return new StreamToken(id, value);
    }

    private Token createToken(ArrayNode node, String name, int value) {

        String id = String.format("%s[%d].%s", node.id, node.nextIndex(), name);
        return new StreamToken(id, value);
    }

    private Token createToken(ArrayNode node, double value) {

        String id = String.format("%s[%d]", node.id, node.nextIndex());
        return new StreamToken(id, value);
    }

    private Token createToken(ArrayNode node, String name, double value) {

        String id = String.format("%s[%d].%s", node.id, node.nextIndex(), name);
        return new StreamToken(id, value);
    }
    
    private Token createToken(ArrayNode node, boolean value) {

        String id = String.format("%s[%d]", node.id, node.nextIndex());
        return new StreamToken(id, value);
    }

    private Token createToken(ArrayNode node, String name, boolean value) {
        
        String id = String.format("%s[%d].%s", node.id, node.nextIndex(), name);
        return new StreamToken(id, value);
    }
    
    private Token createToken(ArrayNode node) {

        String id = String.format("%s[%d]", node.id, node.nextIndex());
        return new StreamToken(id);
    }
    
    private Token createToken(ObjectNode node) {
        
        return new StreamToken(node.id);
    }

    private Token createToken(ObjectNode node, String value) {

        return new StreamToken(node.id, value);
    }

    private Token createToken(ObjectNode node, String name, String value) {
        String id = String.format("%s.%s", node.id, name);
        return new StreamToken(id, value);
    }    
    
    private Token createToken(ObjectNode node, int value) {
        
        return new StreamToken(node.id, value);
    }

    private Token createToken(ObjectNode node, String name, int value) {
        String id = String.format("%s.%s", node.id, name);
        return new StreamToken(id, value);
    }
    
    private Token createToken(ObjectNode node, double value) {
        
        return new StreamToken(node.id, value);
    }

    private Token createToken(ObjectNode node, String name, double value) {
        String id = String.format("%s.%s", node.id, name);
        return new StreamToken(id, value);
    }
    
    private Token createToken(ObjectNode node, boolean value) {
        
        return new StreamToken(node.id, value);
    }

    private Token createToken(ObjectNode node, String name, boolean value) {
        String id = String.format("%s.%s", node.id, name);
        return new StreamToken(id, value);
    }

    private static boolean isValueToken(final JsonToken token) {
        
        return (token == JsonToken.VALUE_FALSE        ||
                token == JsonToken.VALUE_TRUE         ||
                token == JsonToken.VALUE_STRING       ||
                token == JsonToken.VALUE_NULL         ||
                token == JsonToken.VALUE_NUMBER_FLOAT ||
                token == JsonToken.VALUE_NUMBER_INT   ||
                token == JsonToken.VALUE_EMBEDDED_OBJECT);
        
    }

    private boolean isNullOrEmpty(String text) {
        return text == null || text.trim().length() == 0;
    }
    
    private static class Node {
        protected Node parent = null;
        protected String id;
        protected Object value = new JsonNull();
        protected boolean isArray = false;
        protected boolean isObject = false;
        protected boolean isRoot = false;
        
        public Node(String id) {
            this.id = id;
            
        }
        public Node(String id, Object value) {
            this.id = id;
            this.value = value;
        }
        public Node(Node parent) {
            this.parent = parent;
        }
        
        public String toString() {
            return "(" + id + ", " + value.toString() + ")";
        }
        
        public boolean isArray() {
            return this.isArray;
        }
        
        public boolean isObject() {
            return this.isObject;
        }
        
        public boolean isRoot() {
            return this.isRoot;
        }
    }
    
    private static class ObjectNode extends Node {
        
        public ObjectNode(String id) {
            super(id);
            isArray = false;
            isObject = true;
            isRoot = true;
        }

        /**
         * Anonymous object (i.e. null field name)
         * @param parent
         */
        public ObjectNode(Node parent) {
            super(parent);
            if(parent.isArray()) {
                ArrayNode parentArray = (ArrayNode)parent;
                this.id = parent.id + "[" + parentArray.nextIndex() + "]";
            } else if(parent.isObject()) {
                ObjectNode parentObject = (ObjectNode)parent;
                this.id = parentObject.id;
            }
            isArray = false;
            isObject = true;
        }

        /**
         * Named object (i.e. has a real field name) 
         * @param id
         * @param parent
         */
        public ObjectNode(String id, Node parent) {
            super(parent);
            this.id = parent.id + "." + id;
            this.isArray = false;
            this.isObject = true;
        }
    }
    
    private static class ArrayNode extends Node {

        private int i = 0;
        public ArrayNode(String id) {
            super(id);
            isArray = true;
            isObject = false;
            isRoot = true;
        }
        
        public ArrayNode(Node parent) {
            super(parent);
            this.id = parent.id + "." + id;
            isArray = true;
            isObject = false;
        }

        public ArrayNode(String id, Node parent) {
            super(parent);
            this.id = parent.id + "." + id;
            this.isArray = true;
            this.isObject = false;
        }

        public int nextIndex() {
            return i++;
        }
    }
    
    private static class StreamToken implements Token {
        private String id;
        private String stringValue;
        private Integer intValue;
        private Double doubleValue;
        private Boolean booleanValue;
        
        public StreamToken(String id) {
            this.id = id;
        }
        
        public StreamToken(String id, String value) {
            this.id = id;
            this.stringValue = value;
        }
        
        public StreamToken(String id, int value) {
            this.id = id;
            this.intValue = value;
        }
        
        public StreamToken(String id, double value) {
            this.id = id;
            this.doubleValue = value;
        }
        
        public StreamToken(String id, boolean value) {
            this.id = id;
            this.booleanValue = value;
        }
        
        @Override
        public String asString() {
            return this.stringValue;
        }
        
        public int asInteger() {
            return this.intValue;
        }
        
        public double asDouble() {
            return this.doubleValue;
        }
        
        public boolean asBoolean() {
            return this.booleanValue;
        }

        @Override
        public String id() {
            return this.id;
        }
        
        private String valueToString() {
            if(this.stringValue != null) {
                return this.stringValue;
            } else if(this.intValue != null) {
                return String.valueOf(this.intValue);
            } else if(this.doubleValue != null) {
                return String.valueOf(this.doubleValue);
            } else if(this.booleanValue != null) {
                return String.valueOf(this.booleanValue);
            } else {
                return "(null)";
            }
        }
        
        @Override
        public String toString() {
            
            return this.id + "," + valueToString();
        }
    }

}
