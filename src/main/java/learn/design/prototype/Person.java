package learn.design.prototype;

public class Person implements Cloneable{
    private String name;
    private Hand hand;

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        
        this.name = name;
    }
    
    @Override
    protected Object clone(){
        try {
            Person p = (Person)super.clone();
            Hand h = (Hand)p.getHand().clone();
            p.setHand(h);
            return p;
        } catch (CloneNotSupportedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }
    
    @Override
    public String toString() {
        return this.name + this.hand.getFingerNum();
    }
}

class Hand implements Cloneable{
    public Integer fingerNum;

    public Integer getFingerNum() {
        return fingerNum;
    }

    public void setFingerNum(int fingerNum) {
        this.fingerNum = fingerNum;
    }
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
