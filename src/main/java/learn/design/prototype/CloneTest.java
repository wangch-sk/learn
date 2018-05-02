package learn.design.prototype;

public class CloneTest {
    public static void main(String[] args) {
        Hand h = new Hand();
        h.setFingerNum(10);
        Person p = new Person();
        p.setName("zhangsan");
        p.setHand(h);
//        Person p2 = p.
        System.out.println(p);
        
        Person p2 = (Person)p.clone();
        p2.setName("lisi");
        p2.getHand().setFingerNum(5);
        System.out.println(p2);
        System.out.println(p);
    }
    
}
