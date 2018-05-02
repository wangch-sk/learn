package learn.serialize;

import java.io.Serializable;

public enum StatusEnum implements Serializable{
    _正常(1,"正常"),
    _异常(2,"异常");
    
    private int code;
    private String name;
    
    private StatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

