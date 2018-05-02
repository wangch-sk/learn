package learn.comm;

import java.util.Objects;

public class Test2{
    public static void main(String[] args){
        Long o1 = new Long(10);
        Long o2 = 10L;
        System.out.println(Objects.equals(o1, o2));
        
        
        System.out.println(generateLikeStrForTimesAccuracy(2,false));
    }
    
    private static String generateLikeStrForTimesAccuracy(int times,boolean right) {
        int flag = right?1:0;
        StringBuffer sb = new StringBuffer();
        for (int i = 1; i < times; i++) {
            sb.append("_,");
        }
        sb.append(flag+",");
        sb.append("%");
        return sb.toString();
    }
}