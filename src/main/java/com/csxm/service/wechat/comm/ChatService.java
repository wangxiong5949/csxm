package com.csxm.service.wechat.comm;

import com.csxm.entity.wechat.WechatUserData;
import com.csxm.util.entity.Result;

import javax.servlet.http.HttpServletRequest;

public interface ChatService {


    /**
     * 聊天的操作
     */
    Result voidChat(HttpServletRequest request, WechatUserData wud);
}
