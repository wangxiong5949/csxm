package com.csxm.enums;


/**
 * 全局返回码枚举类
 */
public enum ReturnCode {

    ILONE("成功", 0),
    ILTWO("失败", 1),
    ILTHREE("异常捕获", 2),
    ;

    // 成员变量
    private String keyName;//key值名称
    private Integer valStr;//value值

    // 构造方法
    private ReturnCode(String keyName, Integer valStr) {
        this.keyName = keyName;
        this.valStr = valStr;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public Integer getValStr() {
        return valStr;
    }

    public void setValStr(Integer valStr) {
        this.valStr = valStr;
    }
}
