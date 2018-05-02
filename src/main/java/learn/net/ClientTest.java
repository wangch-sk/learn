package learn.net;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

import learn.serialize.JavaSerialize;
import learn.serialize.Order;
import learn.serialize.StatusEnum;  
  
public class ClientTest extends Thread {  
      
    //定义一个Socket对象  
    Socket socket = null;  
  
    public ClientTest(String host, int port) {  
        try {  
            //需要服务器的IP地址和端口号，才能获得正确的Socket对象  
            socket = new Socket(host, port);  
        } catch (UnknownHostException e) {  
            e.printStackTrace();  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
          
    }  
  
    @Override  
    public void run() {  
        //客户端一连接就可以写数据个服务器了  
        new sendMessThread().start();  
        super.run();  
        try {  
            // 读Sock里面的数据  
            InputStream s = socket.getInputStream();  
            byte[] buf = new byte[1024];  
            int len = 0;  
            while ((len = s.read(buf)) != -1) {  
                System.out.println(new String(buf, 0, len));  
            }  
          
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      
    //往Socket里面写数据，需要新开一个线程  
    class sendMessThread extends Thread{  
        @Override  
        public void run() {  
            super.run();  
            //写操作  
            Scanner scanner=null;  
            OutputStream os= null;  
            try {  
                scanner=new Scanner(System.in);  
                os= socket.getOutputStream();  
                String in="";  
                do {  
                    in=scanner.next();  
                    os.write(("客户端:"+in).getBytes());
                    try {
                        os.write(JavaSerialize.serialize(new Order(1L,100L,"29码红色上衣",new Date(),null,StatusEnum._正常)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    os.flush();  
                } while (!in.equals("bye"));  
            } catch (IOException e) {  
                e.printStackTrace();  
            }   
            scanner.close();  
            try {  
                os.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }
    
    public static void main(String[] args) {
        //需要服务器的正确的IP地址和端口号  
        ClientTest client2=new ClientTest("127.0.0.1", 6768);  
        client2.start();  
    }
} 