package learn.concurrent.basic;

public class JoinTest implements Runnable{
    public void run() {
        for (int i = 0; i < 100; i++) {
            if("线程1".equals(Thread.currentThread().getName())){
                System.out.println("==========线程1放弃！");
                Thread.yield();
            }
            System.out.println(Thread.currentThread().getName()+"="+i);
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        JoinTest r = new JoinTest();
        Thread t1 = new Thread(r,"线程1");
        Thread t2 = new Thread(r,"线程2");
        t1.start();
        t2.start();
        System.out.println("=======切换主线程=======");
        t1.join();
        System.out.println("=======切换1=======");
        t2.join();
        System.out.println("=======切换2=======");
    }
}
