package learn.testUtils;

/**
 * 答应工具类
 * @author chaowang
 * @date 2018年3月31日
 */
public class PrintUtil {
    /**
     * 打印数组
     * @author chaowang
     * @date 2018年3月31日 下午4:19:49
     * @param array 要打印的数组
     * @param isOneLine 是否打印在一行
     * @param divide 如果打印在一行，有什么分隔符；如果isOneLine=false,本参数无效
     */
    public static void print(Object[] array,boolean isOneLine,String divide){
        if(array==null || array.length==0){
            System.out.println("数组为空！");
            return;
        }
        for (int i = 0; i < array.length; i++) {
            if(isOneLine){
                if(i<array.length-1){
                    System.out.print(array[i]+divide);
                }else{
                    System.out.print(array[i]); 
                }
            }else{
                System.out.println(array[i]);
            }
        }
        System.out.println();
    }
    
    public static void print(byte[] array,boolean isOneLine,String divide){
        if(array==null || array.length==0){
            System.out.println("数组为空！");
            return;
        }
        for (int i = 0; i < array.length; i++) {
            if(isOneLine){
                if(i<array.length-1){
                    System.out.print(array[i]+divide);
                }else{
                    System.out.print(array[i]); 
                }
            }else{
                System.out.println(array[i]);
            }
        }
        System.out.println();
    }
}
