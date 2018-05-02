package learn.comm.tools;

/**
 * 如下死锁demo代码
 * 测试jstack命令
 * @author chaowang
 * @date 2018年3月25日
 */
public class JStackTest{
    static final Object locka = new Object();
    static final Object lockb = new Object();
    public static void main(String[] args){
        Thread t1 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName()+"开始执行...");
                synchronized (locka) {
                    System.out.println(Thread.currentThread().getName()+"拿到locka");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (lockb) {
                        System.out.println(Thread.currentThread().getName()+"拿到lockb");
                    }
                }
                System.out.println(Thread.currentThread().getName()+"结束执行！");
            }
        },"王超的线程1");
        Thread t2 = new Thread(new Runnable() {
            public void run() {
                System.out.println(Thread.currentThread().getName()+"开始执行...");
                synchronized (lockb) {
                    System.out.println(Thread.currentThread().getName()+"拿到lockb");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (locka) {
                        System.out.println(Thread.currentThread().getName()+"拿到locka");
                    }
                }
                System.out.println(Thread.currentThread().getName()+"结束执行！");
            }
        },"王超的线程2");
        
        t1.start();
        t2.start();
    }
}