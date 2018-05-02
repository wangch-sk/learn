package learn.concurrent.executor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


public class NewCachedThreadPoolTest {
    static Set<String> threadNameSet = Collections.synchronizedSet(new HashSet<String>());
    
    public static void main(String[] args) {
        int n = 10000;
//        ExecutorService service = Executors.newSingleThreadExecutor();//单个线程
//        ExecutorService service = Executors.newCachedThreadPool();//可缓存的线程池，如果池中没有空闲线程就新建线程,线程数没有上限
        ExecutorService service = Executors.newFixedThreadPool(2);//固定线程数的线程池，如果没有空闲线程就等待；
        Runnable r = new Runnable() {
            public void run() {
                //如果增加每个任务的执行时间，就会报错java.lang.OutOfMemoryError: unable to create new native thread
//                try {
//                    Thread.sleep(1000*10);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                threadNameSet.add(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName());
            }
        };
        
        List<Future<?>> list = new ArrayList<Future<?>>();
        for (int i = 0; i < n; i++) {
            list.add(service.submit(r));
        }
        
        for (Future<?> future : list) {
            try {
                future.get();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (ExecutionException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        System.out.println("执行完成："+threadNameSet.size());
        System.out.println("执行完成："+threadNameSet);
//        service.shutdown();
    }
}
