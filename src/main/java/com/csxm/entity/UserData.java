package com.csxm.entity;

import lombok.Data;

/**
 * 用户数据
 */
@Data
public class UserData {

    /** 用户id */
    private String userId;
    /** 用户名称 */
    private String userName;
    /** 用户角色id */
    private String roleId;

}
