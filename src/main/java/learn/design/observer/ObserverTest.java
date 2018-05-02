package learn.design.observer;

import java.util.Observable;
import java.util.Observer;

public class ObserverTest {
    public static void main(String[] args) {
        Person t = new Person();
        Observer o1 = new Observer() {
            public void update(Observable o, Object arg) {
                System.out.println("他的年龄变了！");
            }
        };
        t.addObserver(o1);
        t.addObserver(new Observer() {
            
            public void update(Observable o, Object arg) {
                System.out.println("尼玛，他的年龄变了！");
            }
        });
        
        t.setAge(1);
        t.deleteObserver(o1);
        t.setAge(2);
    }
    
}
