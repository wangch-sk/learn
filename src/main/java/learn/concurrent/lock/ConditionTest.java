package learn.concurrent.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {
    static final ReentrantLock lock = new ReentrantLock();
    static final Condition condition = lock.newCondition();
    
    public static void main(String[] args) throws InterruptedException {
//        lock.lock();
//        try {
//            
//        } finally {
//            lock.unlock();
//        }
        
        new Thread(new Runnable() {
            public void run() {
                lock.lock();
                try {
                    System.out.println("进入线程1");
                    condition.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
                System.out.println("等待结束");
            }
        }).start();
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                try {
                    System.out.println("发送通知");
                    condition.signal();
                } finally {
                    lock.unlock();
                }
            }
        }).start();
    }
}
