package com.csxm.util.entity;

import lombok.Data;

/**
 * 返回结果工具实体类
 */
@Data
public class Result<T> {

    /** 返回码 */
    private Integer code;

    /** 提示信息 */
    private String msg;

    /** 返回的具体内容 */
    private T data;

}
