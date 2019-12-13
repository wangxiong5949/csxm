package com.csxm.service.wechat.comm.impl;

import com.csxm.config.WebSocket;
import com.csxm.entity.UserData;
import com.csxm.entity.wechat.WechatUserData;
import com.csxm.service.wechat.comm.ChatService;
import com.csxm.util.ResultUtil;
import com.csxm.util.UUIDUtil;
import com.csxm.util.WebUtil;
import com.csxm.util.entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class ChatServiceImpl implements ChatService {

    @Autowired
    private WebSocket webSocket;

    /**
     * 聊天的操作
     */
    public Result voidChat(HttpServletRequest request, WechatUserData wud) {
        webSocket.sendMessage(wud);
        return ResultUtil.success(wud);
    }



}
