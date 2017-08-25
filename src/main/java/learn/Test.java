package learn;


public class Test {
    
    static private class Lock { 
        int a=10;
    };
    private static Lock lock = new Lock();
    private static class ObjectA {  
        String str;  // 4  
        int i1; // 4  
        byte b1; // 1  
        byte b2; // 1  
        int i2;  // 4   
        Lock obj; //4  
        byte b3;  // 1 
        int i3;  // 4 
        long l1;
    }
    //8+4+4+4+1+1+4+4+1+4+8
    
    public static void main(String[] args) throws IllegalAccessException {
        byte b = (byte)1;
        System.out.println(SizeOfObject.fullSizeOf(b));//8+4+1,按8的倍数补齐=16
        char c = 'a';
        System.out.println(SizeOfObject.fullSizeOf(c));//8+4+2,按8的倍数补齐=16
        short s = 1;
        System.out.println(SizeOfObject.fullSizeOf(s));//8+4+2,按8的倍数补齐=16
        int i = 1000;
        System.out.println(SizeOfObject.sizeOf(i));//8+4+4,按8的倍数补齐=16
        long l = 10l;
        System.out.println(SizeOfObject.fullSizeOf(l));//8+4+8,按8的倍数补齐=24
        float f = 10f;
        System.out.println(SizeOfObject.fullSizeOf(f));//8+4+4,按8的倍数补齐=16
        double d = 100000000.012321;
        System.out.println(SizeOfObject.fullSizeOf(d));//8+4+8,按8的倍数补齐=24
        
        Integer aa=1;
        String str = "";
        System.out.println(str.length());
        System.out.println(SizeOfObject.fullSizeOf(str));//8+4+8,按8的倍数补齐=24
    }
}
