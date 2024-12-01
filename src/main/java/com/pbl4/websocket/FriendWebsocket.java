package com.pbl4.websocket;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import com.google.gson.Gson;
import com.pbl4.model.bean.Message;
import com.pbl4.model.bean.SessionPlayer;
import com.pbl4.serviceImpl.FriendService;
import com.pbl4.serviceImpl.UserService;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value="/friend")
public class FriendWebsocket {
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
	@OnOpen
	public void onOpen(Session session) {
		clients.add(session);
	}
	
	@OnMessage
	public void onMessage(String message, Session session) throws IOException {
		System.out.println("Received: " + message + " from "+ session.getId());
		Gson gson = new Gson();
		Message mess = gson.fromJson(message, Message.class);
		Message responseMessage = new Message();
		if (mess.getContent().equals("accept")) {
			responseMessage = new Message(mess.getContent(), mess.getUserId(), mess.getFriendId(), mess.getImg());
		}else {
			responseMessage = new Message(mess.getContent(), mess.getUserId(), mess.getFriendId());	
		}
		synchronized (clients) {
			if (responseMessage.getContent().equals("add")) {
				FriendService.getInstance().addnewFriend(responseMessage.getUserId(), responseMessage.getFriendId());
			}else if (responseMessage.getContent().equals("accept")) {
				FriendService.getInstance().acceptFriend(responseMessage.getUserId(), responseMessage.getFriendId());
				System.out.println(responseMessage.getImg());
				Message acceptSuccess = new Message("accept", responseMessage.getUserId(), responseMessage.getFriendId(),UserService.getInstance().findFullnameById(responseMessage.getUserId()),UserService.getInstance().findFullnameById(responseMessage.getFriendId()),responseMessage.getImg());
				String jsonAccept = gson.toJson(acceptSuccess);
				for (Session client : clients) {
							client.getBasicRemote().sendText(jsonAccept);
				}
			}else if (responseMessage.getContent().equals("reject")) {
				FriendService.getInstance().deleteFriend(responseMessage.getUserId(), responseMessage.getFriendId());
				Message rejectSuccess = new Message("reject", responseMessage.getUserId(), responseMessage.getFriendId());
				String jsonReject = gson.toJson(rejectSuccess);
				for (Session client : clients) {
							client.getBasicRemote().sendText(jsonReject);
				}
			}else if(responseMessage.getContent().equals("unFriend")) {
				FriendService.getInstance().deleteFriend(responseMessage.getUserId(), responseMessage.getFriendId());
				Message rejectSuccess = new Message("unFriend", responseMessage.getUserId(), responseMessage.getFriendId());
				String jsonReject = gson.toJson(rejectSuccess);
				for (Session client : clients) {
							client.getBasicRemote().sendText(jsonReject);
				}
			}
		}
	}
	
	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
		System.out.println("Connection closed: " + session.getId());
	}
}
