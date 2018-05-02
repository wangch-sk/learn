package learn.comm;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test{
    public static void main(String[] args){
        //处理请求
        String request = "DN(CN=TestRSA1024.p.0200.0102,O=rsaperca139.com.cn)HASH(SHA1)KEYTYPE(RSA1024)CERTTYPE(01)||"
                + "DN(CN=TestRSA2048.p.0200.0102,O=rsaperca139.com.cn)HASH(SHA1)KEYTYPE(RSA2048)CERTTYPE(01)"
                + "DN(CN=TestSM2.p.0200.0101,O=rsaperca139.com.cn)HASH(SM3)KEYTYPE(SM2256)CERTTYPE(01)"
                + "DN(CN=TestRSA1024.c.0200.0102,O=rsaperca139.com.cn)HASH(SHA1)KEYTYPE(RSA1024)CERTTYPE(01)"
                + "DN(CN=TestRSA2048.c.0200.0102,O=rsaperca139.com.cn)HASH(SHA1)KEYTYPE(RSA2048)CERTTYPE(01)"
                + "DN(CN=TestSM2.c.0200.0101,O=rsaperca139.com.cn)HASH(SM3)KEYTYPE(SM2256)CERTTYPE(01)";
//        Pattern requestP = Pattern.compile("(CN=[^,]*),");
//        Map<String,Integer> requestMap = getStringCnt(request,requestP);
//        System.out.println(requestMap);
//        
//        //处理响应
//        String response = "CN=TestRSA1024.p.0200.0102,OU=MiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOuMiniCaOu,O=rsaperca139.com.cn||CN=TestRSA1024.p.0200.0102,OU=xxx";
//        Pattern responseP = requestP;
//        Map<String,Integer> responseMap = getStringCnt(response,responseP);
//        System.out.println(responseMap);
        
        boolean yes = multiExsit(request,"CN=TestRSA1024.p.0200.0102","CN=TestRSA2048.p.0200.0102");
        System.out.println(yes);
    }
    
    
    /**
     * 获取子串个数
     * @author chaowang
     * @date 2018年4月7日 上午11:04:52
     * @param str
     * @param p
     * @return
     */
    public static Map<String,Integer> getStringCnt(String str,Pattern p){
        Map<String,Integer> requestMap = new HashMap<String,Integer>();
        Matcher m = p.matcher(str);
        while (m.find()) { 
            String key = m.group(1);
            if(requestMap.containsKey(key)){
                requestMap.put(key, requestMap.get(key)+1);
            }else{
                requestMap.put(key, 1);
            }
        }
        return requestMap;
    }
    
    /**
     * 查找字符串中是否同时存在多个子串
     * @author chaowang
     * @date 2018年4月7日 下午8:40:08
     * @param originStr 原始字符串
     * @param str1 子串1
     * @param str2 子串2
     * @return
     */
    public static boolean multiExsit(String originStr,String str1,String str2){
        Pattern p = Pattern.compile("("+str1+").*("+str2+")" +"|"+ "("+str2+").*("+str1+")");
        Matcher m = p.matcher(originStr);
        if (m.find()) {
            return true;
        }
        return false;
    }
}