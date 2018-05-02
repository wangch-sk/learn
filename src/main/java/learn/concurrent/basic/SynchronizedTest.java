package learn.concurrent.basic;

public class SynchronizedTest{
   public static void main(String[] args) {
       new LogWidget().dosome();
   }
}

class Widget{
    public synchronized void dosome(){
        System.out.println("widget do some...");
    }
}
class LogWidget extends Widget {
    public synchronized void dosome(){
        super.dosome();
        System.out.println("LogWidget do some...");
    }
}