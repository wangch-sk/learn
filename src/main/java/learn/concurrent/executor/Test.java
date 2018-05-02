package learn.concurrent.executor;

import java.util.concurrent.Executor;

public class Test {
    
    static class TestExecutor implements Executor{
        public void execute(Runnable r) {
            new Thread(r).start();
        }
    }

    public static void main(String[] args) {
        new TestExecutor().execute(new Runnable() {
            
            public void run() {
                System.out.println("11111");
            }
        });
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("2222");
    }
}
