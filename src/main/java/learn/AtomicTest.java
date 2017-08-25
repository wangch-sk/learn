package learn;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
    
    public static void main(String[] args) {
        
        AtomicInteger i = new AtomicInteger();
        
        System.out.println(i.incrementAndGet());
        
        System.out.println(i.get());
    }
}
