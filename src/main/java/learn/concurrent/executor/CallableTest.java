package learn.concurrent.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 测试Callable接口；
 * 用Callable接口代替Runnable接口，传入线程池，获取执行结果；
 * @author chaowang
 * @date 2018年4月7日
 */
public class CallableTest {
    
    static class MyTask implements Callable<Integer>{
        private int value;
        public MyTask(int value){
            this.value = value;
        }
        public Integer call() throws Exception {
            int sum = 0;
            for (int i = 0; i < value; i++) {
                sum+=i;
            }
            return sum;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException{
        ExecutorService es = Executors.newFixedThreadPool(2);
        Future<Integer> result1 = es.submit(new MyTask(10));
        Future<Integer> result2 = es.submit(new MyTask(10));
        
        Thread.sleep(1000);
        int result = result1.get()+result2.get();
        System.out.println("最终结果："+result);
        es.shutdown();
    }
}
