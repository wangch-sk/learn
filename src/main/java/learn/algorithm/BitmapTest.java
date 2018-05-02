package learn.algorithm;

import java.util.BitSet;

/**
 * Bit-map的基本思想：为了减少数据的存储空间而采用的一种位示图存储方案(用位下标表示数字，如果位对应1，则存储了该下标对应的数字)；
 * 比如：32位机器上，对于一个整型数，比如int a=1 在内存中占32bit（4个字节），这是为了方便计算机的运算。但是对于某些应用场景而言，这属于一种巨大的浪费，因为我们可以用对应的32bit位对应存储十进制的0-31个数，而这就是Bit-map的基本思想。
 * Bit-map算法利用这种思想处理大量数据的排序、查询以及去重。在用户群做交集和并集运算的时候也有极大的便利。
 * 
 * 示例中列举了几种应用场景：
 * 1、排序
 * 2、去重
 * 3、Bit-map扩展——Bloom Filter(布隆过滤器)
 * @author chaowang
 * @date 2018年4月12日
 */
public class BitmapTest{
    public static void main(String[] args){
//        sort();
//        removeRepeat();
//        removeRepeatRetain();
        System.out.println(Integer.MAX_VALUE);
        
        bloomFilter("hello");
    }
    
    /**
     * Bit-map应用之快速排序
     * 假设我们要对0-7内的5个元素(4,7,2,5,3)排序（这里假设这些元素没有重复），我们就可以采用Bit-map的方法来达到排序的目的。要表示8个数，我们就只需要8个bit
     * 1、首先开启8bit，位置都设置0
     * 2、遍历一遍数组，将bitmap对应下标的位置设置成1
     * 3、输出值为1的下标即时排好序的结果
     * 
     * @author chaowang
     * @date 2018年4月12日 上午11:27:23
     */
    public static void sort(){
        int[] a = new int[]{4,7,2,5,3};
        int max = 7;//注：其实如果一堆未知数据，首先得找到最大值，才能知道设置多少位的bitmap
        BitSet bs = new BitSet(max+1);//为什么要+1？因为还要表示0
        for (int i : a) {
            bs.set(i, true);
        }
        System.out.println(bs);
    }
    
    /**
     * Bit-map应用之快速去重(不包含重复数据，如果有两个3，输出结果不包含3)
     * 2.5亿个整数中找出不重复的整数的个数，内存空间不足以容纳这2.5亿个整数。 
     * 首先，根据“内存空间不足以容纳这2.5亿个整数”我们可以快速的联想到Bit-map。下边关键的问题就是怎么设计我们的Bit-map来表示这2.5亿个数字的状态了。
     * 其实这个问题很简单，一个数字的状态只有三种，分别为不存在，只有一个，有重复。因此，我们只需要2bits（用两个bitmap表示就行啦）就可以对一个数字的状态进行存储了，假设我们设定一个数字不存在为00，存在一次01，存在两次及其以上为11。那我们大概需要存储空间几十兆左右。
     * 接下来的任务就是遍历一次这2.5亿个数字，如果对应的状态位为00，则将其变为10；如果对应的状态位为10，则将其变为11；如果为11，,对应的转态位保持不变。
     * 最后，我们将状态位为01的进行统计，就得到了不重复的数字个数，时间复杂度为O(n)。
     * 
     * @author chaowang
     * @date 2018年4月12日 上午11:31:33
     */
    public static void removeRepeat(){
        int[] a = new int[]{4,2,3,7,2,5,3,1,1,7,6,1};
        int max = 7;//注：其实如果一堆未知数据，首先得找到最大值，才能知道设置多少位的bitmap
        BitSet bs1 = new BitSet(max+1);//表示2位bit的前一位
        BitSet bs2 = new BitSet(max+1);//表示2位bit的后一位
        for (int i : a) {
            if(!(bs1.get(i))){//如果第一位为0，直接设置成1
                bs1.set(i, true);
            }else if(!(bs2.get(i))){//如果第一位为1，且第二位为0，将第二位设置成1
                bs2.set(i, true);
            }
        }
        System.out.println("第一位中为1的位置："+bs1);
        System.out.println("第二位中为1的位置："+bs2);
        
        //打印出组合为10的位置，即是去重排序的结果
//        for (int i : a) {
//            if(bs1.get(i) && !(bs2.get(i))){
//                System.out.println(i);
//            }
//        }
        
        bs1.andNot(bs2);//上面的for循环可以用这条语句得到同样的效果，取亦或操作
        System.out.println("表示10的位置："+bs1);
    }
    
    /**
     * Bit-map应用之快速去重(包含重复数据，如果有两个3，输出结果包含一个3)
     * 有了上面的removeRepeat思想，这个就更简单了
     * @author chaowang
     * @date 2018年4月12日 上午11:31:33
     */
    public static void removeRepeatRetain(){
        int[] a = new int[]{4,2,3,7,2,5,3,1,1,7,6,1};
        int max = 7;//注：其实如果一堆未知数据，首先得找到最大值，才能知道设置多少位的bitmap
        BitSet bs1 = new BitSet(max+1);//表示2位bit的前一位
        for (int i : a) {
            bs1.set(i, true);
        }
        System.out.println(bs1);
    }
    
    /**
     * Bit-map扩展——Bloom Filter(布隆过滤器)
     * 当一个元素被加入集合中时,通过k个散列函数将这个元素映射成一个位数组中的k个点,并将这k个点全部置为1.
     * 在判断y是否属于这个集合时，对y应用k次哈希函数，若所有hi(y)的位置都是1（1≤i≤k），就认为y是集合中的元素，否则就认为y不是集合中的元素。
     * 有一定的误判率--在判断一个元素是否属于某个集合时,有可能会把不属于这个集合的元素误判为属于这个集合.因此,它不适合那些"零误判"的应用场合.
     * 在能容忍低误判的应用场景下,布隆过滤器通过极少的误判换区了存储空间的极大节省.
     * 
     * 布隆过滤：一个一定不存在的数据会被这个bitmap拦截掉，从而避免了对底层存储系统的查询压力。可以解决【缓存穿透】问题
     * @author chaowang
     * @date 2018年4月12日 下午12:20:02
     */
    public static void bloomFilter(String data){
        //初始化bloomFilter数据
        String[] existData = new String[]{"aa","你好","测试","祖国","good"};
        BitSet bs1 = new BitSet(Integer.MAX_VALUE);
        for (String i : existData) {
            //这里用了3个hash函数
            bs1.set(Math.abs(HashAlgorithms.FNVHash1(i)), true);//取绝对值保证下标是正值
            bs1.set(Math.abs(HashAlgorithms.APHash(i)), true);
            bs1.set(Math.abs(HashAlgorithms.java(i)), true);
        }
        
        if(bs1.get(Math.abs(HashAlgorithms.FNVHash1(data))) 
                && bs1.get(Math.abs(HashAlgorithms.APHash(data))) 
                && bs1.get(Math.abs(HashAlgorithms.java(data)))){
            System.out.println("哈哈、数据存在，继续后续业务...");
        }else{
            System.out.println("数据不存在，不用去存储系统查啦！");
        }
    }
}