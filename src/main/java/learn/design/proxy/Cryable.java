package learn.design.proxy;

public interface Cryable {
    public void cry();
}
class Car implements Cryable{

    public void cry() {
        System.out.println("滴滴滴");
    }
}
