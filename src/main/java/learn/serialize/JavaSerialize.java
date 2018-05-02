package learn.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

import learn.testUtils.PrintUtil;

public class JavaSerialize {
    public static byte[] serialize(Object obj) throws Exception {  
        if(obj==null) throw new NullPointerException();
        
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        ObjectOutputStream out = new ObjectOutputStream(os);
        out.writeObject(obj);  
        return os.toByteArray();
    }
    
    public static Object deserialize(byte[] by) throws Exception {  
        if(by==null) throw new NullPointerException();  
          
        ByteArrayInputStream is = new ByteArrayInputStream(by);  
        ObjectInputStream in = new ObjectInputStream(is);  
        return in.readObject();  
    } 
    
    public static void main(String[] args) throws Exception {
        Order order = new Order(1L,100L,"29码红色上衣",new Date(),new User(9L,"张三"),StatusEnum._正常);
        
        byte[] b = serialize(order);
        PrintUtil.print(b, true, ",");
        System.out.println("长度："+b.length);
        
        Order order2 = (Order)deserialize(b);
        System.out.println(order2);
        
    }
}

