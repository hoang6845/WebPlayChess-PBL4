package com.pbl4.controller.web;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.pbl4.model.bean.Message;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/PvP")
public class ChatWebsocket {
	 private static Set<Session> clients = 
	            Collections.synchronizedSet(new HashSet<Session>());

	    @OnOpen
	    public void onOpen(Session session) {
	        clients.add(session);
	        System.out.println("New connection: " + session.getId());
	    }

	    @OnMessage
	    public void onMessage(String message, Session session) throws Exception {
	        System.out.println("Received: " + message + " from " + session.getId());
	        Gson gson = new Gson();
	        Message mess = gson.fromJson(message, Message.class);
	        Message responseMessage = new Message(mess.getSender(), mess.getContent());
	        // Chuyển đổi đối tượng thành chuỗi JSON
	        String jsonResponse = gson.toJson(responseMessage);
	        System.out.println(mess.getContent());
	        // Gửi tin nhắn đến tất cả các client khác
	        synchronized(clients) {
	            for (Session client : clients) {
	                    client.getBasicRemote().sendText(jsonResponse);
	            }
	 
	        }
	    }

	    @OnClose
	    public void onClose(Session session) {
	        clients.remove(session);
	        System.out.println("Connection closed: " + session.getId());
	    }
}
