package learn.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 测试lock和lockInterruptibly的区别
 * lock 与 lockInterruptibly比较区别在于：
 * lock 优先考虑获取锁，待获取锁成功后，才响应中断。
 * lockInterruptibly 优先考虑响应中断，而不是响应锁的普通获取或重入获取。
 * 
 * ReentrantLock.lockInterruptibly允许在等待时由其它线程调用等待线程的Thread.interrupt方法来中断等待线程的等待而直接返回，这时不用获取锁，而会抛出一个InterruptedException。 
 * ReentrantLock.lock方法不允许Thread.interrupt中断,即使检测到Thread.isInterrupted,一样会继续尝试获取锁，失败则继续休眠
 * @author chaowang
 * @date 2018年4月6日
 */
public class ReentrantLockTest2 {
    
    public static void main(String[] args) throws InterruptedException {
        final Lock lock=new ReentrantLock();  
        lock.lock();  
        Thread.sleep(1000);
        Thread t1=new Thread(new Runnable(){  
            public void run() { 
                lock.lock();
//              try {  
//                  lock.lockInterruptibly();  
//              } catch (InterruptedException e) {  
//                  e.printStackTrace();  
//              }
                System.out.println(Thread.currentThread().getName()+" interrupted.");  
            }  
        });  
        t1.start();  
        Thread.sleep(1000);  
        t1.interrupt();  
        Thread.sleep(1000);
    }
}
