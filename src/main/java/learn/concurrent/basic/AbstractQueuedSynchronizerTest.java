package learn.concurrent.basic;

import java.util.concurrent.locks.Lock;

public class AbstractQueuedSynchronizerTest{
    public static void main(String[] args) {
        final Lock lock = new Mutex();
        Runnable r = new Runnable() {
            public void run() {
                lock.lock();
                for (int i = 0; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName());
                }
                lock.unlock();
            }
        };
        Thread t1 = new Thread(r);
        Thread t2 = new Thread(r);
        t1.start();
        t2.start();
        System.out.println("========结束=======");
    }
}
