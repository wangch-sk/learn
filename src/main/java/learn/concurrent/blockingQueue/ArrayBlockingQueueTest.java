package learn.concurrent.blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class ArrayBlockingQueueTest {
    static ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(10);
    static AtomicInteger data = new AtomicInteger(0);
    //producer
    static class Producer implements Runnable{
        public void run() {
            try {
                while(true){
                    int i = data.incrementAndGet();
                    System.out.println(Thread.currentThread().getName()+"生产者生产数据 "+i);
                    queue.put(i);
                    Thread.sleep(2000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
  //consumer
    static class Consumer implements Runnable{
        public void run() {
            try {
                while(true){
                    int i = queue.take();
                    System.out.println("消费者消费数据 "+i);
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        
        Producer p = new Producer();
        new Thread(p).start();
        new Thread(p).start();
        
        Consumer c = new Consumer();
        new Thread(c).start();
    }
}
