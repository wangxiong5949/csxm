package com.csxm.config;

import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * WebSocket 主要方法
 */
@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        System.out.println("【websocket消息】有新的连接, 总数:{}" + webSocketSet.size());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        System.out.println("【websocket消息】连接断开, 总数:{}" + webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("【websocket消息】收到客户端发来的消息:{}" + message);
    }

    public void sendMessage(Object object) {
        for (WebSocket webSocket: webSocketSet) {
            JSONObject JoBject = JSONObject.fromObject(object);
            System.out.println("【websocket消息】广播消息" + JoBject);
            try {
                webSocket.session.getBasicRemote().sendText(JoBject.toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
