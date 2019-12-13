package com.csxm.entity.wechat;

import lombok.Data;

/**
 * 微信用户数据
 */
@Data
public class WechatUserData {

    /** 用户openId */
    private String openId;
    /** 用户userId */
    private String userId;
    /** 用户名称 */
    private String userName;
    /** 用户聊天内容 */
    private String msgChat;


    /** 聊天功能发送消息用户fsUserId */
    private String fsUserId;
    /** 聊天功能接收消息用户jsUserId */
    private String jsUserId;
    /** 聊天功能判断是否是接收消息的用户 true：是、false：不是  */
    private boolean msgType;

}
