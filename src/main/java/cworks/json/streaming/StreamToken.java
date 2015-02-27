package cworks.json.streaming;

class StreamToken implements Token {
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
        
        if(this.id.startsWith("root.")) {
            return this.id.substring(4);
        }
        // TODO here
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
        
        return this.id() + "," + valueToString();
    }
}
