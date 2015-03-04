package cworks.json;

import java.io.*;

public class IO {
    
    private static final int MIN_BUFFER_SIZE     = 1;
    private static final int MAX_BUFFER_SIZE     = 1024 * 128000; // 128 MB
    private static final int DEFAULT_BUFFER_SIZE = 1024 * 64;     //  64 MB

    private static final int EOF = -1;

    public static String asString(final File file) throws IOException {
        Reader reader = new FileReader(file);
        return asString(reader);
    }
    
    public static StringBuffer asStringBuffer(final File file) throws IOException {
        Reader reader = new FileReader(file);
        return asStringBuffer(reader);
    }
    
    public static StringBuilder asStringBuilder(final File file) throws IOException {
        Reader reader = new FileReader(file);
        return asStringBuilder(reader);
    }
    
    public static String asString(final Reader reader) throws IOException {
        final StringWriter writer = new StringWriter();
        cp(reader, writer);
        return writer.toString();
    }
    
    public static StringBuffer asStringBuffer(final Reader reader) throws IOException {
        final StringWriter writer = new StringWriter();
        cp(reader, writer);
        return writer.getBuffer();
    }
    
    public static StringBuilder asStringBuilder(final Reader reader) throws IOException {
        final StringWriter writer = new StringWriter();
        cp(reader, writer);
        return new StringBuilder(writer.toString());
    }
    
    public static String asString(final Reader reader, int bufferSize) throws IOException {
        final StringWriter writer = new StringWriter();
        if(!checkInclusive(bufferSize, MIN_BUFFER_SIZE, MAX_BUFFER_SIZE)) {
            bufferSize = DEFAULT_BUFFER_SIZE;
        }
        char[] buffer = new char[bufferSize];
        cp(reader, writer, buffer);
        return writer.toString();
    }
    
    public static long cp(final Reader reader, final Writer writer) throws IOException {
        checkArg(reader);
        checkArg(reader);
        
        char[] buffer = new char[DEFAULT_BUFFER_SIZE];
        return cp(reader, writer, buffer);
    }
    
    public static long cp(final Reader reader, final Writer writer, int bufferSize) throws IOException {
        checkArg(reader);
        checkArg(reader);
        if(!checkInclusive(bufferSize, MIN_BUFFER_SIZE, MAX_BUFFER_SIZE)) {
            bufferSize = DEFAULT_BUFFER_SIZE;
        }
        char[] buffer = new char[bufferSize];
        return cp(reader, writer, buffer);
    }
    
    public static long cp(final Reader reader, final Writer writer, final char[] buffer) throws IOException {
        checkArg(reader);
        checkArg(reader);
        
        long checkSum = 0;
        int n = 0;
        while(EOF != (n = reader.read(buffer))) {
            writer.write(buffer, 0, n);
            checkSum += n;
        }
        return checkSum;
    }

    
    public static void checkArg(Object object) {
        if(object == null) {
            throw new IllegalArgumentException();
        }
    }

    public static boolean checkInclusive(int actual, int min, int max) {
        return actual >= min && actual <= max;
    }

    public static void closeQuietly(Closeable closeable) {
        try {
            if(closeable != null) { closeable.close(); }
        } catch (final IOException ignore) { }
    }


}
