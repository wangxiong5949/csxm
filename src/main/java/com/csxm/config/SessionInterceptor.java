package com.csxm.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class SessionInterceptor extends WebMvcConfigurerAdapter {


    /**
     * 注册 拦截器
     * 配置拦截器需要拦截的地址
     */
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new InterceptToWebConfig()).addPathPatterns("/toWeb/**");
        registry.addInterceptor(new InterceptCommConfig()).addPathPatterns("/comm/**");
        registry.addInterceptor(new InterceptToWechatConfig()).addPathPatterns("/toWechat/**");
        registry.addInterceptor(new InterceptToWxPayConfig()).addPathPatterns("/toWxPay/**");
    }
}
