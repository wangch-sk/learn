package learn.serialize;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class Order implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public static String AA = "aa";
    private Long id;
    private Long skuId;
    private String skuName;
    private Date cTime;
    private User buyUser;
    private StatusEnum status;
    
    public static String getAA() {
        return AA;
    }
    public static void setAA(String aA) {
        AA = aA;
    }
    public Order(Long id, Long skuId, String skuName, Date cTime, User buyUser,StatusEnum status) {
        super();
        this.id = id;
        this.skuId = skuId;
        this.skuName = skuName;
        this.cTime = cTime;
        this.buyUser = buyUser;
        this.status = status;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getSkuId() {
        return skuId;
    }
    public void setSkuId(Long skuId) {
        this.skuId = skuId;
    }
    public String getSkuName() {
        return skuName;
    }
    public void setSkuName(String skuName) {
        this.skuName = skuName;
    }
    public Date getcTime() {
        return cTime;
    }
    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }
    public User getBuyUser() {
        return buyUser;
    }
    public void setBuyUser(User buyUser) {
        this.buyUser = buyUser;
    }
    public StatusEnum getStatus() {
        return status;
    }
    public void setStatus(StatusEnum status) {
        this.status = status;
    }
    @Override
    public String toString() {
        return JSON.toJSONString(this,SerializerFeature.WriteMapNullValue);
    }
}
class User extends Person implements Serializable{
    /** */
    private static final long serialVersionUID = 1L;
    private Long id;
    private String Name;
    public User(Long id, String name) {
        super();
        this.id = id;
        Name = name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
}

class Person implements Serializable{
    /** */
    private static final long serialVersionUID = 1L;
    private String Name;
    public Person() {
        super();
    }
    public Person(Long id, String name) {
        super();
        Name = name;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
}

