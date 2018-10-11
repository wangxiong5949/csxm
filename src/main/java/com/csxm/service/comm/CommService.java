package com.csxm.service.comm;

import com.csxm.util.entity.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface CommService {


    /**
     * 登入的操作
     */
    Map<String, Object> voidLogin(HttpServletRequest request, String userAccount, String userPwd);

    /**
     * 首页菜单显示（查询角色对应菜单信息）
     */
    Result getEamMenuRoleList(HttpServletRequest request);
}
