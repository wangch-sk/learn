package learn.runtime;

/**
 * 理论上，以下几种情况addShutdownHook里面的线程都会执行
 * 1、执行程序执行完正常退出
 * 2、按contrl+c
 * 3、kill -15 pid
 * 但是，目前在mac和linux上实验，只有第一种情况执行了，第二中和第三种都没有执行（A_TODO:带进一步测试）
 * @author chaowang
 * @date 2018年3月1日
 */
public class TestShutDownHook {
    public static void main(String[] args) {
        System.out.println("程序启动...");
        for (int i = 0; i < 10000; i++) {
            try {
                System.out.println(i);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        Runtime.getRuntime().addShutdownHook(new Thread(){
            public void run(){
                TestShutDownHook.destory();
            }
        });
//        try {
//            Thread.sleep(1000*60);
//        } catch (InterruptedException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
        System.out.println("程序正常运行结束!");
    }
    
    public static void destory(){
        System.out.println("执行清理工作...");
    }
}
