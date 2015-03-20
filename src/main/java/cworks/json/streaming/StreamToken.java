package cworks.json.streaming;

import cworks.json.JsonObject;

class StreamToken implements Token {
    private String name;
    private String stringValue;
    private Integer intValue;
    private Double doubleValue;
    private Boolean booleanValue;

    public StreamToken(String name) {
        this.name = name;
    }

    public StreamToken(String name, String value) {
        this.name = name;
        this.stringValue = value;
    }

    public StreamToken(String name, int value) {
        this.name = name;
        this.intValue = value;
    }

    public StreamToken(String name, double value) {
        this.name = name;
        this.doubleValue = value;
    }

    public StreamToken(String name, boolean value) {
        this.name = name;
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
    
    public String asJson() {
        
        JsonObject object = new JsonObject();
        if(this.stringValue != null) {
            return object.setString(name(), this.stringValue).asString();
        } else if(this.intValue != null) {
            return object.setNumber(name(), this.intValue).asString();
        } else if(this.doubleValue != null) {
            return object.setNumber(name(), this.doubleValue).asString();
        } else if(this.booleanValue != null) {
            return object.setBoolean(name(), this.booleanValue).asString();
        } else {
            return object.setString(name(), null).asString();
        }
    }

    @Override
    public String name() {
        if(this.name.startsWith("root.")) {
            return this.name.substring(5);
        } else if(this.name.startsWith("root")) {
            return this.name.substring(4);
        }

        return this.name;
    }

    @Override
    public String type() {
        return null;
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
        
        return this.name() + "," + valueToString();
    }
}
