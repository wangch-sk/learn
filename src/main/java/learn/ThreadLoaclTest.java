package learn;

public class ThreadLoaclTest {
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
        ThreadLoaclTest sn = new ThreadLoaclTest();
        new TestThread(sn).start();
        Thread.sleep(2000);
        new TestThread(sn).start();
    }
    
    private static class TestThread extends Thread{
        private ThreadLoaclTest sn;
        public TestThread(ThreadLoaclTest sn){
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
