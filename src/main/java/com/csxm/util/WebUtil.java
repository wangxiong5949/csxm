package com.csxm.util;


import com.csxm.entity.UserData;

import javax.servlet.http.HttpServletRequest;

public class WebUtil {
	
    /**
     * 从session中获取web端用户信息
     */
    public static UserData getUserData(HttpServletRequest request) {
        return (UserData) request.getSession().getAttribute("userData");
    }

}
