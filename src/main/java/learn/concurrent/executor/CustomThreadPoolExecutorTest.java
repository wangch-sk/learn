package learn.concurrent.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 自定义线程池；继承ThreadPoolExecutor，重写beforeExecute，afterExecute等方法
 * 参考Executors的创建方法；
 * @author chaowang
 * @date 2018年4月5日
 */
public class CustomThreadPoolExecutorTest extends ThreadPoolExecutor{

    private CustomThreadPoolExecutorTest(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit,
            BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }
    
    public static ThreadPoolExecutor newFixedThreadPool(int nThreads) {
        return new CustomThreadPoolExecutorTest(nThreads, nThreads,
                                      0L, TimeUnit.MILLISECONDS,
                                      new LinkedBlockingQueue<Runnable>());
    }
    
    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        System.out.println(Thread.currentThread().getName()+"任务开始执行");
    }
    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        super.afterExecute(r, t);
        synchronized (this) {
            System.out.println(Thread.currentThread().getName()+"任务执行完成！当前活动线程数："+this.getActiveCount());
        }
    }
    
    @Override
    protected void terminated() {
        super.terminated();
        System.out.println("线程池关闭！");
    }
    
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor pool = CustomThreadPoolExecutorTest.newFixedThreadPool(2);
        Runnable r = new Runnable() {
            public void run() {
//                try {
//                    Thread.sleep(4);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                System.out.println("ce11");
            }
        };
        pool.submit(r);
        pool.submit(r);
        Thread.sleep(10);
        System.out.println("当前活动线程数："+pool.getActiveCount());
        pool.shutdown();
    }
}
