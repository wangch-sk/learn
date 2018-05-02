package learn.algorithm;

/**
 * 排序算法
 * @author chaowang
 * @date 2018年4月12日
 */
public class SortTest{
    public static void main(String[] args){
    }
    
    /**
     * 冒泡排序；时间复杂度：平均O(n2)，最好O(n2),最坏O(n2)，空间复杂度O(1) 稳定
     * 思想：两层循环，每一轮将剩下的最大值依次往后交换到最后的位置；
     * 改进1：增加一个标志（flag）记录最后一次交换的位置，因为flag之后的都已排序好，下一趟只需要比较到flag位置即可，不需要到len-1-i
     * 改进2：每一趟除了找到最大值，还可以反向找到最小值，将时间减少一般
     * @author chaowang
     * @date 2018年4月12日 下午3:18:21
     * @param array
     * @return
     */
    public  static int[] maopaoSort(int[] array){
        int len = array.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len-1-i; j++) {
                if(array[j]>array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        return array;
    }
    /**
     * 选择排序；时间复杂度：平均O(n2)，最好O(n2),最坏O(n2)，空间复杂度O(1) 不稳定
     * 思想：两层循环，每一轮找到剩余的最小值，和外层循环的i位置交换；
     * @author chaowang
     * @date 2018年4月12日 下午3:23:38
     * @param array
     * @return
     */
    public static int[] xuanzheSort(int[] array){
        int minIndex;
        for (int i = 0; i < array.length; i++) {
            minIndex = i;
            for (int j = i; j < array.length; j++) {
                if(array[j]<array[minIndex]){
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }
}