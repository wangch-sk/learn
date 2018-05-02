package learn.design.singleton;

public class SingletonTest {
    
    /**
     * 饿汉模式
     * @author chaowang
     * @date 2018年4月4日
     */
    static class EhanSingleton{
        private static EhanSingleton instance = new EhanSingleton();
        private EhanSingleton(){
            System.out.println("EhanSingleton构造函数");
        }
        public static EhanSingleton getInstance(){
            return instance;
        }
    }
    
    /**
     * 懒汉-双重校验锁
     * 1、第2个check是防止多个线程同时走进第一个为空判断里面，导致创建多个对象
     * 2、那第1个check是否能去掉呢，可以去掉，但去掉了每次调用都要走到synchronized块，影响性能；加第1个check是为了instance创建后，以后每次都直接返回，不用走到synchronized块
     * 3、instance前加volatile是为了解决编译器指令重排序问题
     * @author chaowang
     * @date 2018年4月4日 下午2:55:21
     * @param args
     */
    static class DoubleCheckSingleton{
        private static volatile DoubleCheckSingleton instance = null;
        private DoubleCheckSingleton(){}
        public static DoubleCheckSingleton getInstance(){
            if(instance==null){ //1
                synchronized(DoubleCheckSingleton.class){
                    if(instance==null){ //2
                        instance = new DoubleCheckSingleton();
                    }
                }
            }
            return instance;
        }
    }
    
    /**
     * 今天内部类
     * @author chaowang
     * @date 2018年4月4日
     */
    static class InnerClassSingleton{
        private static class InstanceHolder{
            private static InnerClassSingleton instance = new InnerClassSingleton();
        } 
        private InnerClassSingleton(){}
        
        public static InnerClassSingleton getInstance(){
            return InstanceHolder.instance;
        }
    }
    
    /**
     * 枚举方式
     * 上面提到的四种实现单例的方式都有共同的缺点：
     * 1）需要额外的工作来实现序列化，否则每次反序列化一个序列化的对象时都会创建一个新的实例。
     * 2）可以使用反射强行调用私有构造器（如果要避免这种情况，可以修改构造器，让它在创建第二个实例的时候抛异常）。
     * 而枚举类很好的解决了这两个问题，使用枚举除了线程安全和防止反射调用构造器之外，还提供了自动序列化机制，防止反序列化的时候创建新的对象。因此，《Effective Java》作者推荐使用的方法。不过，在实际工作中，很少看见有人这么写。
     * 枚举方式虽然很完美的解决了各种问题，但是这种写法多少让人感觉有些生疏。个人的建议是，在没有特殊需求的情况下，使用【双重校验锁】和【静态内部内】方式实现单例模式
     * @author chaowang
     * @date 2018年4月4日 下午3:05:26
     * @param args
     */
    enum EnumSingleton{
        instance;
        public void m(){
            System.out.println("m方法!");
        }
    }
    
    public static void main(String[] args) {
        EnumSingleton.instance.m();
    }
}
