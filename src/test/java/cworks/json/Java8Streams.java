package cworks.json;

import cworks.json.streaming.Token;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Spliterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Java8Streams {
    
    @Test
    public void basicExample() {
        
        List<String> names = Arrays.asList("Bucky", "Nacho", "Gracie", "Lola", "Charlie");
        names.stream()
            .filter(s -> s.contains("ie"))
            .map(String::toLowerCase)
            .sorted()
            .forEach(System.out::println);
                
    }
    
    @Test
    public void streamOfExample() {
        
        Stream.of("Bucky", "Nacho", "Gracie", "Lola", "Charlie")
            .filter(s -> s.contains("ie"))
            .map(String::toLowerCase)
            .sorted()
            .forEach(System.out::println);
        
    }
    
    @Test
    public void processingOrder() {

        // filter will not execute because terminal method is absent
        Stream.of("Bucky", "Nacho", "Gracie", "Lola", "Charlie")
            .filter(s -> {
                System.out.println("filter: " + s);
                return true;
            });

        // the filter code runs when the forEach terminal method is present
        Stream.of("Bucky", "Nacho", "Gracie", "Lola", "Charlie")
            .filter(s -> {
                System.out.println("filter: " + s);
                return true;
            })
            .forEach(s -> {
                System.out.println("forEach: " + s);
            });
    }
    
    @Test
    public void testSupplier() {


        StreamSupport.stream(new Spliterator<Token>() {
            @Override
            public boolean tryAdvance(Consumer<? super Token> action) {
                return false;
            }

            @Override
            public Spliterator<Token> trySplit() {
                return null;
            }

            @Override
            public long estimateSize() {
                return 0;
            }

            @Override
            public int characteristics() {
                return 0;
            }
        }, false);
        
        
        final BlockingQueue<Token> tokenQueue = new LinkedBlockingQueue<Token>();
        

        
        Supplier<Token> tokenSupplier = new Supplier<Token>() {

            @Override
            public Token get() {
                try {
                    return tokenQueue.take();
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };

        // generate data
        for(int i = 0; i < 10; i++) {
            int n = i;
            tokenQueue.add(new Token() {

                @Override
                public String asString() {
                    return String.valueOf(n);
                }

                @Override
                public int asInteger() {
                    return n;
                }

                @Override
                public double asDouble() {
                    return (double)n;
                }

                @Override
                public boolean asBoolean() {
                    return n % 2 == 0;
                }

                @Override
                public String id() {
                    return String.valueOf(n);
                }
            });
        }

        Stream.generate(tokenSupplier)
                .forEach(token -> {
                    System.out.println(token.toString());
                });
        
    }
    

}
