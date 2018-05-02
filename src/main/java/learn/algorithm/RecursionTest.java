package learn.algorithm;

/**
 * 题目：编写一个函数，实现如下功能
 * 输入1，打印：
 * X
 * 输入2，打印：
 * X X
 *  X 
 * X X
 * 输入3，打印：
 * X X   X X
 *  X     X 
 * X X   X X
 *    X X   
 *     X    
 *    X X   
 * X X   X X
 *  X     X 
 * X X   X X
 * 依次类推...
 * @author chaowang
 * @date 2018年3月7日
 */
public class RecursionTest {
    
    /**
     * 计算数组
     * @author chaowang
     * @date 2018年3月7日 下午4:44:11
     * @param n
     * @return
     */
    private static int[][] calculate(int n){
        if(n<1){
            return new int[][]{};
        }
        if(n==1){
            return new int[][]{{1}};
        }
        int[][] shape = calculate(n-1);
        int[][] resultArray = new int[shape.length*3][shape[0].length*3];//结果数组
        setAtPosition(resultArray,shape,0,0);//左上角
        setAtPosition(resultArray,shape,0,shape[0].length*2);//右上角
        setAtPosition(resultArray,shape,shape.length,shape[0].length);//中间
        setAtPosition(resultArray,shape,shape.length*2,0);//左下角
        setAtPosition(resultArray,shape,shape.length*2,shape[0].length*2);//右下角
        return resultArray;
    }
    /**
     * 设置数组特定位置的值
     * @author chaowang
     * @date 2018年3月7日 下午4:28:17
     * @param resultArray 结果数组
     * @param orginArray 源数组
     * @param startRow 开始行
     * @param startColumn 开始列
     */
    private static void setAtPosition(int[][] resultArray,int[][] orginArray,int startRow,int startColumn){
        for (int i = 0; i < orginArray.length; i++) {
            for (int j = 0; j < orginArray[0].length; j++) {
                resultArray[i+startRow][j+startColumn] = orginArray[i][j];
            }
        }
    }
    /**
     * 打印
     * @author chaowang
     * @date 2018年3月7日 下午4:44:34
     * @param n
     */
    public static void draw(int n){
        int[][] array = calculate(n);
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if(array[i][j]==1){
                    System.out.print("X");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println();//换行
        }
    }
    
    public static void main(String[] args) {
        draw(1);
    }
}
