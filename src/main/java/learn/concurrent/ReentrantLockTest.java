package learn.concurrent;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    static final ReentrantLock lock = new ReentrantLock();
    
    public static void main(String[] args) {
        lock.lock();
        System.out.println("1");
        lock.unlock();
    }
}
