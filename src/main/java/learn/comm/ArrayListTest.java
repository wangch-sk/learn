package learn.comm;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 多个线程同时对ArrayList写入，会有问题，出现有些位置写入了null值，而且还有可能直接报错“数组越界”
 * @author chaowang
 * @date 2018年3月23日
 */
public class ArrayListTest {
    public static List<Integer> data = new ArrayList<Integer>();//解决方案：用ConcurrentLinkedQueue代替
    public static void main(String[] args) {
        
        final CountDownLatch cdl = new CountDownLatch(2);
        final int n = 100;
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                for (int i = 0; i < n; i++) {
                    data.add(i);
                }
                cdl.countDown();
            }
        });
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                for (int i = n; i < n*2; i++) {
                    data.add(i);
                }
                cdl.countDown();
            }
        });
        
        t1.start();
        t2.start();
        
        try {
            cdl.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        for (Integer i : data) {
            System.out.println(i);
        }
        
        System.out.println("===="+data.size());
    }
}

