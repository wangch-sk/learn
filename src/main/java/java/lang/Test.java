package java.lang;

import java.lang.ThreadLocal.ThreadLocalMap;

public class Test {
    private ThreadLocal<Integer> n = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    public int get(){
        return n.get();
    }
    public void set(Integer v){
        n.set(v);
    }
    
    public static void main(String[] args) throws InterruptedException {
        Test sn = new Test();
        ThreadLocalMap t = new TestThread(sn).threadLocals;
        Thread.sleep(2000);
        new TestThread(sn).start();
    }
    
    private static class TestThread extends Thread{
        private Test sn;
        public TestThread(Test sn){
            this.sn = sn;
        }
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+"设置前:"+this.sn.get());
            this.sn.set(10);
            System.out.println(Thread.currentThread().getName()+"设置后:"+this.sn.get());
        }
    }
}
