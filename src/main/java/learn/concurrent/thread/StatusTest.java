package learn.concurrent.thread;

/**
 * 测试线程的状态，测试结果：
 * 
 * start之前：线程1的状态：NEW
 * 在运行中时：线程1的状态：RUNNABLE
 * 在sleep时：线程1的状态：TIMED_WAITING
 * 锁被占用时：线程1的状态：BLOCKED
 * 在wait(time)时：线程1的状态：TIMED_WAITING
 * 在wait()时：线程1的状态：WAITING
 * 执行完成后：TERMINATED
 * 
 * @author chaowang
 * @date 2018年3月25日
 */
public class StatusTest {
    static final Object lock = new Object();
    public static void main(String[] args) throws InterruptedException {
        Runnable r = new Runnable() {
            public void run() {
                System.out.println("在运行中时："+Thread.currentThread().getName() + "的状态：" + Thread.currentThread().getState());
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (lock) {
                    try {
                        lock.wait(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t1 = new Thread(r,"线程1");
        System.out.println("start之前："+t1.getName()+"的状态："+t1.getState());
        t1.start();
        Thread.sleep(10);//让线程t1执行
        System.out.println("在sleep时："+t1.getName()+"的状态："+t1.getState());
        synchronized(lock){
            Thread.sleep(30);
            System.out.println("锁被占用时："+t1.getName()+"的状态："+t1.getState());
        }
        Thread.sleep(40);
        System.out.println("在wait(time)时："+t1.getName()+"的状态："+t1.getState());
        Thread.sleep(120);
        System.out.println("在wait()时："+t1.getName()+"的状态："+t1.getState());
        Thread.sleep(120);
        synchronized(lock){
            lock.notify();
        }
        t1.stop();
        t1.join();//让t1执行完
        System.out.println("执行完成后："+t1.getState());
        
    }
}
