package learn.net;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import learn.serialize.JavaSerialize;
import learn.serialize.Order;

public class ServerTest extends Thread {  
  
    // 定义服务器接口ServerSocket  
    ServerSocket server = null;  
  
    // 定义一个服务器，定义端口  
    public ServerTest(int port) {  
        try {  
            server = new ServerSocket(port);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
  
    // 发送消息的线程  
    @Override  
    public void run() {  
        super.run();  
        try {  
            System.out.println("服务器在启动中...等待用户的连接");  
            //一直接收用户的连接，连接之后发送一条短信给用户  
            while(true){  
                // 建立socket接口，accept方法是一个阻塞进程,等到有用户连接才往下走  
                // 定义Socket类  
                Socket  socket = server.accept();  
                //通过socket对象可以获得输出流，用来写数据  
                OutputStream os = socket.getOutputStream();  
                // 向客户端发送消息  
                os.write("服务器正在向你发送消息！".getBytes());  
                //在服务器上显示连接的上的电脑、  
                System.out.println(socket.getInetAddress().getHostAddress()+"连接上了！");  
                //通过socket对象可以获得输入流，用来读取用户数据  
                InputStream is=socket.getInputStream();  
                //读取数据  
                int len=0;  
                byte[] buf=new byte[1024];  
                while ((len=is.read(buf))!=-1) {  
                    //直接把获得的数据打印出来  
                    System.out.println("服务器接收到客户端的数据："+new String(buf,0,len));
                    try {
                        System.out.println(JavaSerialize.deserialize(buf));
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
    
    public static void main(String[] args) {
      //这里服务器只需要定义一个端口号就可以了，程序会自动获取IP地址  
        //但是客户端需要连接这个服务器时，需要知道它的IP地址还有端口号  
        //ip地址的查看方法：进入cmd窗口，输入ipconfig/all可以看到  
         ServerTest server=new ServerTest(6768);  
         server.start();  
    }
}  
