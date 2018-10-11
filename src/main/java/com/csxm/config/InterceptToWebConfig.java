package com.csxm.config;

import com.csxm.entity.UserData;
import com.csxm.util.WebUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * toWeb 开头的地址拦截器操作
 */
public class InterceptToWebConfig implements HandlerInterceptor {


    /**
     * controller 执行之前调用
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception{
        UserData ud = WebUtil.getUserData(request);
        if (ud == null){
            //重定向去的地址
            response.sendRedirect("/csxm/comm/toLogin");
            return false;
        }
        return true;
    }

    /**
     * controller 执行之后，且页面渲染之前调用
     */
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) {
    }

    /**
     * 页面渲染之后调用，一般用于资源清理操作
     */
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
                                Object handler, Exception ex) {
    }
}
