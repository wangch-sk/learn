package learn;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public class UnsafeTest {
    private volatile long value;
    
    public static void main(String[] args) {
        Field f = null;
        Unsafe unsafe = null;
        try {
            f = Unsafe.class.getDeclaredField("theUnsafe");
            f.setAccessible(true);
            unsafe = (Unsafe) f.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        try {
            long valueOffset = unsafe.objectFieldOffset
                    (UnsafeTest.class.getDeclaredField("value"));
            System.out.println(valueOffset);
        } catch (NoSuchFieldException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SecurityException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
