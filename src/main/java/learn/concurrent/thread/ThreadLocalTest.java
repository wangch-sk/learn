package learn.concurrent.thread;
public class ThreadLocalTest {
    public static ThreadLocal<Integer> num = new ThreadLocal<Integer>(){
        protected Integer initialValue() {
            System.out.println(Thread.currentThread().getName()+"初始化！");
            return 100;
        };
    };
    
    
    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable(){
            public void run() {
                num.set(num.get()+1);
                System.out.println(Thread.currentThread().getName()+"获取值："+num.get());
            }
        },"线程1");
        
        t1.start();
        t1.join();
        
        Thread t2 = new Thread(new Runnable(){
            public void run() {
                System.out.println(Thread.currentThread().getName()+"获取值："+num.get());
            }
        },"线程2");
        t2.start();
    }
}
