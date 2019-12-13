package com.hybr.service.comm;

import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/websocket")
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<>();


    @OnOpen
    public void onOpen(Session session) {
        this.session = session;
        webSocketSet.add(this);
        System.out.println("webSocketSet有新的连接总数：" + webSocketSet.size());
    }

    @OnClose
    public void onClose() {
        webSocketSet.remove(this);
        System.out.println("webSocketSet连接断开总数：" + webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("webSocketSet收到客户端发来的消息：" + message);
    }

    public void sendMessage(String message) {
        for (WebSocket webSocket : webSocketSet) {
            System.out.println("webSocketSet广播消息：" + message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
