import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.apache.log4j.Logger;

public class Launcher {
	final static Logger logger = Logger.getLogger(Launcher.class);
	public static void main(String[] args) throws UnsupportedEncodingException {
//		String sb = "A9DD3019\u002DC150\u002D4FD5\u002DB60E\u002DB06CC73F3A73";
		String sb = "A9DD3019-C150-4FD5-B60E-B06CC73F3A73";
		String xmString = new String(sb.getBytes("Unicode"));  
		System.out.println(xmString);
	    String xmlUTF8 = URLEncoder.encode(xmString, "UTF-8");  
	    System.out.println("utf-8 编码：" + xmlUTF8) ; 
	    
//	    System.out.println(URLEncoder.encode(sb, "Unicode"));
//	    System.out.println(URLDecoder.decode(sb, "utf-8"));
	    System.out.println(new String(sb.getBytes("utf-8"),"Unicode"));
	}

	
	public static void cal2(){

	}
}
