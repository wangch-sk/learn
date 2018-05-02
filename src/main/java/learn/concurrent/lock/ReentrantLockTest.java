package learn.concurrent.lock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    static final ReentrantLock lock = new ReentrantLock();
    
    public static void main(String[] args) {
        lock.lock();
        new Thread(new Runnable() {
            public void run() {
                lock.lock();
                System.out.println(Thread.currentThread().getName());
                lock.unlock();
            }
        }).start();
        for (int i = 0; i < 10; i++) {
            System.out.println(i);
        }
        System.out.println("holdcount="+lock.getHoldCount());
        lock.unlock();
    }
}
