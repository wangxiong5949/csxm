package com.csxm.config;

import com.csxm.entity.wechat.WechatUserData;
import com.csxm.wxutil.WechatUtil;
import net.sf.json.JSONObject;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * toWxPay 开头的地址拦截器操作
 */
public class InterceptToWxPayConfig implements HandlerInterceptor {


    /**
     * controller 执行之前调用
     */
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception{
        HttpSession session = request.getSession();
        WechatUserData wud = (WechatUserData) session.getAttribute("wechatUserData");
        if(wud == null) {
            //判断是否有获取微信openId的code
            String code = request.getParameter("code");
            if(code != null && !"".equals(code)){
                JSONObject jos = WechatUtil.getOpenId(code);
                String openId = jos.getString("openid");
                WechatUserData w = new WechatUserData();
                w.setOpenId(openId);
                session.setAttribute("wechatUserData", w);
                return true;
            } else {
                //获取code
                WechatUtil.getCode(response, request);
                return false;
            }
        }
        return true;
    }

}
