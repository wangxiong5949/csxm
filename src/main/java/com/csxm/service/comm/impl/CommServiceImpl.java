package com.csxm.service.comm.impl;

import com.csxm.dao.comm.CommDao;
import com.csxm.entity.UserData;
import com.csxm.enums.ReturnCode;
import com.csxm.service.comm.CommService;
import com.csxm.util.MD5Util;
import com.csxm.util.ResultUtil;
import com.csxm.util.WebUtil;
import com.csxm.util.entity.Result;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CommServiceImpl implements CommService {

    @Resource
    private CommDao commDao;


    /**
     * 登入的操作
     */
    public Map<String, Object> voidLogin(HttpServletRequest request, String userAccount,
                                         String userPwd) {
        Map<String, Object> mData = new HashMap<>();
        mData.put("userAccount", userAccount.trim());
        mData.put("userPwd", MD5Util.string2MD5(userPwd.trim()));
        UserData ud = commDao.findUaData(mData);
        if(ud != null){
            HttpSession session = request.getSession();
            session.setAttribute("userData", ud);
            //登入成功
            mData.put("sType", "1");
        } else {
            //账号或密码错误
            mData.put("sType", "2");
        }
        mData.put("code",0);
        return mData;
    }

    /**
     * 首页菜单显示（查询角色对应菜单信息）
     */
    public Result getEamMenuRoleList(HttpServletRequest request) {
        UserData ud = WebUtil.getUserData(request);
        String roleId = "";
        if (ud != null) {
            roleId = ud.getRoleId();
        }
        Map<String, Object> one = new HashMap<>();
        one.put("roleId", roleId);
        //一级菜单
        List<Map<String, Object>> oneList = commDao.queryEamMenuRoleByRoleId(one);
        //遍历一级菜单得到二级菜单
        for (Map<String, Object> oMap : oneList) {
            Map<String, Object> two = new HashMap<>();
            two.put("roleId", roleId);
            two.put("menuId", oMap.get("menuId"));
            List<Map<String, Object>> twoList = commDao.queryEamMenuRoleByRoleId(two);
            //把二级菜单装进一级菜单里面
            oMap.put("twoList", twoList);
            //遍历二级菜单得到三级菜单
            for (Map<String, Object> tMap : twoList) {
                Map<String, Object> three = new HashMap<>();
                three.put("roleId", roleId);
                three.put("menuId", tMap.get("menuId"));
                List<Map<String, Object>> threeList = commDao.queryEamMenuRoleByRoleId(three);
                //把三级菜单得到二级菜单里面
                tMap.put("threeList", threeList);
            }
        }
        if(oneList.size() > 0){
            return ResultUtil.success(oneList);
        }
        return ResultUtil.error(ReturnCode.ILTWO.getValStr(), "获取菜单失败");
    }

}
