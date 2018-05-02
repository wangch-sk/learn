package learn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class CollectionTest {
    
    public static void main(String[] args) {
        
//        chongfuHashCode();
        test2();
//        System.out.println(randomLevel());
//        String a = "Programming";  
//        String b = new String("Programming");  
//        String c = "Program" + "ming";  
          
//        System.out.println(a == b);  //false
//        System.out.println(a == c);  //true
//        System.out.println(a.equals(b));  //true
//        System.out.println(a.equals(c));  //true
//        System.out.println(a.intern() == b.intern());//true  
        
    }
    
    public static void chongfuHashCode(){
        Object a="aA";
        Object b="aB";
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }
    
    public static void test2(){
        List<String> list = new ArrayList<String>();
        list.add(null);
        list.add("list");
        System.out.println("list="+list);
                
        Set<String> s = new HashSet<String>();
        s.add(null);
        s.add("11");
        System.out.println("set="+s);
        
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("key", "map");
        map.put(null, null);
        System.out.println("hashmap="+map);
        System.out.println("hashmap keys="+map.keySet());
        
        Map<String,Object> tm = new TreeMap<String,Object>(new Comparator<String>() {
            public int compare(String o1, String o2) {
                if(o1==null && o2==null){
                    return 0;
                }
                if(o1==null){
                    return -1;
                }
                if(o2==null){
                    return 1;
                }
                return o1.compareTo(o2);
            }
            
        });
        tm.put("nullvalue", null);
        tm.put("key", "TreeMap");
        tm.put(null, "TreeMap");
        tm.put("key2", "TreeMap");
        tm.put("key3", "TreeMap");
        System.out.println("treemap="+tm);
        
        Map<String,Object> hashTable = new Hashtable<String,Object>();
        hashTable.put("key", "hashTable");
        hashTable.put(null, "hashTable2");
//        hashTable.put("key2", null);
        System.out.println("hashtable="+hashTable);
        System.out.println("hashtable keys="+hashTable.keySet());
        
    }
    
    private static  int randomLevel() {
        int randomSeed = new Random().nextInt() | 0x0100; 
        int x = randomSeed;
        x ^= x << 13;
        x ^= x >>> 17;
        randomSeed = x ^= x << 5;
        if ((x & 0x8001) != 0) // test highest and lowest bits
            return 0;
        int level = 1;
        while (((x >>>= 1) & 1) != 0) ++level;
        
        return level;
        
    }
}
