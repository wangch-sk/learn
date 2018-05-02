package learn.io;

import java.io.File;
import java.io.FilenameFilter;

import learn.testUtils.PrintUtil;

public class FileTest {
    
    public FileTest(){
        String s;
    }
    public static void main(String[] args) {
        File file = new File("/Users/chaowang/Desktop/books");
        String[] allFile = file.list();
        String[] filterFile = file.list(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".pdf");
            }
        });
        PrintUtil.print(allFile, false, ",");
        System.out.println("=============");
        PrintUtil.print(filterFile, false, "");
        
    }
}
