package learn.serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Date;

import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

public class HessianSerialize {
    public static byte[] serialize(Object obj) throws IOException{  
        if(obj==null) throw new NullPointerException();  
          
        ByteArrayOutputStream os = new ByteArrayOutputStream();  
        HessianOutput ho = new HessianOutput(os);  
        ho.writeObject(obj);  
        return os.toByteArray();  
    }  
      
    public static Object deserialize(byte[] by) throws IOException{  
        if(by==null) throw new NullPointerException();  
          
        ByteArrayInputStream is = new ByteArrayInputStream(by);  
        HessianInput hi = new HessianInput(is);
        return hi.readObject();  
    }  
    
    public static void main(String[] args) throws Exception {
        Order order = new Order(1L,100L,"29码红色上衣",new Date(),new User(9L,"张三"),StatusEnum._异常);
        
        byte[] b = serialize(order);
        System.out.println("长度："+b.length);
        
        Order order2 = (Order)deserialize(b);
        System.out.println(order2);
        System.out.println(order2.AA);
    }
}

