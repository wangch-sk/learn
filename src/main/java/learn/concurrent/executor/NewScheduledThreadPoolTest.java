package learn.concurrent.executor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


public class NewScheduledThreadPoolTest {
    static Set<String> threadNameSet = Collections.synchronizedSet(new HashSet<String>());
    
    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
//        ExecutorService service = Executors.newFixedThreadPool(2);
        Runnable r = new Runnable() {
            public void run() {
                threadNameSet.add(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName());
            }
        };
        
        List<Future<?>> list = new ArrayList<Future<?>>();
        for (int i = 0; i < 5; i++) {
//            list.add(scheduledThreadPool.schedule(r, 3, TimeUnit.SECONDS));
            list.add(scheduledThreadPool.scheduleAtFixedRate(r, 3,1, TimeUnit.SECONDS));
//          list.add(scheduledThreadPool.scheduleWithFixedDelay(r, 3,1, TimeUnit.SECONDS));
        }
        
        for (Future<?> future : list) {
            try {
                future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
        System.out.println("执行完成："+threadNameSet.size());
        System.out.println("执行完成："+threadNameSet);
        scheduledThreadPool.shutdown();
    }
}
