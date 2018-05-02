package learn.concurrent.visiable;

/**
 * 测试重排序;
 * x=1,y=0
 * x=0,y=1
 * x=1,y=1
 * @author chaowang
 * @date 2018年4月5日
 */
public class PossibleReorder{
    private static int x=0,y=0;
    private static int a=0,b=0;
    
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 10000; i++) {
            Thread one = new Thread(new Runnable() {
                public void run() {
                    a = 1;
                    x = b;
                }
            });
            Thread two = new Thread(new Runnable() {
                public void run() {
                    b = 1;
                    y = a;
                }
            });
            one.start();
            two.start();
            
            one.join();
            two.join();
            
            if(x==y){
                System.out.println("x="+x+",y="+y);
            }else{
//                System.out.println();
            }
            x=y=a=b=0;
        }
    }
}
