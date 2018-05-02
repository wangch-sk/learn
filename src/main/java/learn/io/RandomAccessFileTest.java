package learn.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {
    public static void main(String[] args) throws IOException {
        String file = "/Users/chaowang/Desktop/books/1.txt";
        
        RandomAccessFile raf = new RandomAccessFile(file, "r");
        int a = raf.readInt();
        System.out.println(a);
        
        raf.close();
    }
}
