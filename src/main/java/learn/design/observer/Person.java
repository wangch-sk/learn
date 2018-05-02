package learn.design.observer;

import java.util.Observable;

public class Person extends Observable{
    private int age;
    public int getAge() {
        return age;
    }

    public void setAge(int age){
        if(this.age!=age){
            this.age = age;
            this.setChanged();
        }
        System.out.println("我的年龄变了！");
        this.notifyObservers();
    }
}
