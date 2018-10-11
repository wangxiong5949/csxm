package com.csxm.dao.comm;

import com.csxm.entity.UserData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface CommDao {


    /**
     * 根据帐号密码查询用户表
     */
    UserData findUaData(Map<String, Object> mCs);

    /**
     * 首页菜单显示（查询角色对应菜单信息）
     */
    List<Map<String, Object>> queryEamMenuRoleByRoleId(Map<String, Object> mCs);

}
