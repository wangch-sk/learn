package learn.design.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {
    public static void main(String[] args) {
        Cryable c = getProxy(new Car());
        c.cry();
    }
    
    public static Cryable getProxy(final Cryable target){
        return (Cryable)Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), new InvocationHandler(){

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // TODO Auto-generated method stub
                System.out.println("代理之前");
                Object result = method.invoke(target, args);
                System.out.println("代理之后");
                return result;
            }
               
           });
    }
    
}
