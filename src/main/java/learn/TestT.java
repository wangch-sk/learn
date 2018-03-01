package learn;


public class TestT {
    
    public static void main(String[] args) {
        String s1 = "1100000";
        String s2 = "0100000";
        long i1 = Long.parseLong(s1,2);
        long i2 = Long.parseLong(s2, 2);
        System.out.println((i1 & i2)==i2);
        
    }
}
