package learn.concurrent.basic;

public class WaitTest implements Runnable{
    public void run() {
        System.out.println(Thread.currentThread().getName()+"执行");
        if("线程2".equals(Thread.currentThread().getName())){
            synchronized (this) {
//                this.notify();
            
                try {
                    this.wait();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                System.out.println(11111);
            }
        }
    }
    
    public synchronized void m(){
        System.out.println(Thread.currentThread().getName()+"执行对象的同步方法开始...");
        try {
            this.wait();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"执行对象的同步方法结束!");
    }
    
    public static void main(String[] args) throws InterruptedException {
        WaitTest r = new WaitTest();
        Thread t1 = new Thread(r,"线程1");
        Thread t2 = new Thread(r,"线程2");
        t1.start();
        t2.start();
        r.m();
    }
}
