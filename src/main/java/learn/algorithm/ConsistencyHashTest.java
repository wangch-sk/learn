package learn.algorithm;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
/**
 * 一致性Hash算法
 * 如果服务节点比较少时，容易造成数据分布不均匀；为了解决数据倾斜的问题，一致性hash算法引入虚拟节点；
 * 虚拟节点其实很简单：即对每一个服务节点计算多个哈希，每个计算结果位置都放置一个此服务节点，称为虚拟节点；具体做法可以在服务器ip或主机名的后面增加编号来实现
 * 比如：分别计算 “Node A#1”、“Node A#2”、“Node A#3”、“Node B#1”、“Node B#2”、“Node B#3”的哈希值，于是形成六个虚拟节点；
 * 在实际应用中，通常将虚拟节点数设置为32甚至更大，因此即使很少的服务节点也能做到相对均匀的数据分布。
 * @author chaowang
 * @date 2018年3月28日
 */
public class ConsistencyHashTest{
    //存储节点Hash到节点的映射
    private static final TreeMap<Integer,String> nodeMap = new TreeMap<Integer,String>();
    static{
        initNode(4,32);//实际应用中，通常将虚拟节点数设置为32甚至更大，因此即使很少的服务节点也能做到相对均匀的数据分布。
    }
    
    /**
     * 初始化节点
     * @author chaowang
     * @date 2018年3月28日 下午5:41:10
     * @param n 实际节点数
     * @param m 虚拟节点数
     */
    private static void initNode(int n,int m){
        for (int i = 0; i < n; i++) {//n个节点数
            for (int j = 0; j < m; j++) {//每个节点m个虚拟节点，将m个虚拟节点的hash值都映射到实际节点上（这里就是ip地址）
                String nodeFlag = "192.168.13."+i+"#"+j;//也可以机器名称
                String node = "192.168.13."+i;
                nodeMap.put(hash(nodeFlag),node);
            }
        }
//        nodeMap.remove(1957605583);//模拟最后一台机器宕机
    }
    public static int hash(String k){
        int a = HashAlgorithms.FNVHash1(k);
        return a>=0?a:0-a;
    }
    
    public static int findNodeCode(String key){
        int hashCode = hash(key);
        int firstNodeCode = -1;
        for (Integer nodeHash : nodeMap.keySet()) {
            if(firstNodeCode==-1){
                firstNodeCode = nodeHash;
            }
            if(hashCode<nodeHash){
                return nodeHash;
            }
        }
        //如果大于所有节点，返回第一个节点
        return firstNodeCode;
    }
    
    public static String findNode(String key){
        return nodeMap.get(findNodeCode(key));
    }
    
    public static void main(String[] args){
        Map<String,Integer> countMap = new HashMap<String,Integer>();
        for (int i = 0; i < 100; i++) {
            String k = i+"";
            String node = findNode(k);
//            System.out.println(i+":"+node);
            if(countMap.containsKey(node)){
                countMap.put(node, countMap.get(node)+1);
            }else{
                countMap.put(node, 1);
            }
        }
        
        System.out.println(countMap);
    }
}