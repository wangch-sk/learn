package learn.algorithm;

/**
 * 查找算法
 * @author chaowang
 * @date 2018年4月12日
 */
public class FindTest{
    public static void main(String[] args){
        
    }
    
    //查找第一个非重复字符
    public static Character get(String s){
        for(int i=0;i<s.length();i++){
            char temp = s.charAt(i);
            boolean hasFind = true;
            for(int j=0;j<s.length();j++){
                if(i!=j && temp==s.charAt(j)){
                    hasFind = false;
                    break;
                }
            }
            if(hasFind){
                return temp;
            }
        }
        return null;
    }
    
    //二分查找（非递归方式）
    public  static int binarySearch(int[] array,int key){
        int low,high,mid;
        low=0;
        high = array.length-1;
        while(low<=high){
            mid = (low+high)/2;
            if(array[mid] == key) return mid;
            if(array[mid]<key) low = mid+1;
            if(array[mid]>key) high = mid-1;
        }
        return -1;
    }
}