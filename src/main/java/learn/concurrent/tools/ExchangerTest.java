package learn.concurrent.tools;

import java.util.concurrent.Exchanger;

/**
 * Exchanger两个线程之间交换数据，只有一个重载方法:exchange(V x) 和 exchange(V x, long timeout, TimeUnit unit)
 * 等待另一个线程到达此交换点，两一个线程未到时就阻塞；
 * @author chaowang
 * @date 2018年4月7日
 * @param <E>
 */
public class ExchangerTest<E> {
    static Exchanger<Integer> exchanger = new Exchanger<Integer>();
    
    public static void main(String[] args) {
        new Thread(new Runnable() {
            public void run() {
                try {
                    Integer data = exchanger.exchange(1);
                    System.out.println(Thread.currentThread().getName()+"交换到数据："+data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                
            }
        },"线程1").start();
        
        new Thread(new Runnable() {
            public void run() {
                try {
                    Integer data = exchanger.exchange(2);
                    System.out.println(Thread.currentThread().getName()+"交换到数据："+data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"线程2").start();
   }
}
