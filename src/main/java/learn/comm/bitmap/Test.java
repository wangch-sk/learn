package learn.comm.bitmap;

import java.util.BitSet;

public class Test{
    public static void main(String[] args){
        int[] a = new int[]{4,7,2,5,3};
        BitSet bs = new BitSet(8);
        for (int i : a) {
            bs.set(i, true);
        }
        System.out.println(bs);
        
        System.out.println(63 >> 6);
    }
}