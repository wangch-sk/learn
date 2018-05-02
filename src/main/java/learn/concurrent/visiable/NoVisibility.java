package learn.concurrent.visiable;

/**
 * 测试可见性
 * @author chaowang
 * @date 2018年4月5日
 */
public class NoVisibility{
    private static boolean ready;
    private static int num;
    
    public static void main(String[] args) throws InterruptedException {
        
        Runnable r = new Runnable() {
            public void run() {
                while(!ready){
                    System.out.println("没改");
                    Thread.yield();
//                    try {
//                        Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        // TODO Auto-generated catch block
//                        e.printStackTrace();
//                    }
                }
                System.out.println(num);
            }
        };
        for (int i = 0; i < 1; i++) {
            new Thread(r).start();
        }
        Thread.sleep(10);
        num = 42;
        
        ready = true;
        
    }
}
