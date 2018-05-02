package learn.concurrent.tools;

import java.util.concurrent.Semaphore;

public class SemaphoreTest<E> {
    private Semaphore availableItems = null;
    private Semaphore availableSpace = null;
    private E[] items = null;
    private int putPosition=0,takePosition=0;
    public SemaphoreTest(int size){
        availableItems = new Semaphore(0);//初始信号量0，默认是nonFair
        availableSpace = new Semaphore(size);//初始信号量size，默认是nonFair
        items = (E[])new Object[size];
    }
    
    public void put(E x) throws InterruptedException{
        availableSpace.acquire();
        doInsert(x);
        availableItems.release();
    }

    public E take() throws InterruptedException{
        availableItems.acquire();
        E item = doExtract();
        availableSpace.release();
        return item;
    }
    
    private synchronized void doInsert(E x) {
        int i = putPosition;
        items[i] = x;
        System.out.println(Thread.currentThread().getName()+"在位置："+i+" 生产："+x);
        putPosition = (++i == items.length)?0:i;
    }
    
    private synchronized E doExtract() {
        int i = takePosition;
        E item = items[i];
        System.out.println("==========="+Thread.currentThread().getName()+"在位置："+i+" 消费："+item);
        items[i] = null;//删除元素
        takePosition = (++i==items.length)?0:i;
        return item;
    }

    public static void main(String[] args) throws InterruptedException {
        final SemaphoreTest<Integer> semaphoreTest = new SemaphoreTest<Integer>(10);
        Runnable r1 = new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                        try {
                            semaphoreTest.put(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            }
        };
        Runnable r2 = new Runnable() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                        try {
                            semaphoreTest.take();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                }
            }
        };
        new Thread(r1,"生产者线程1").start();
        new Thread(r1,"生产者线程2").start();
        new Thread(r2,"消费者线程1").start();
    }
}
