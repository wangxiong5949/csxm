package com.csxm.controller.wechat.pay;

import com.csxm.entity.wechat.WechatUserData;
import com.csxm.wxutil.WechatUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;


/**
 * 微信支付类
 */
@RequestMapping("/toWxPay")
@Controller
public class PayOneController {


    /**
     * 支付页面的显示
     */
    @RequestMapping("/toPayOneIdex")
    public String toPayOneIdex(HttpServletRequest request){
        WechatUserData wud = WechatUtil.getWechatUserData(request);
        System.out.println(wud.toString());
        return "page/wechat/pay/payOneIdex";
    }

}
