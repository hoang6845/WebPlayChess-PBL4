package com.pbl4.controller.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gson.Gson;
import com.pbl4.model.bean.Message;

import jakarta.websocket.OnClose;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;

@ServerEndpoint(value = "/PvP")
public class ChatWebsocket {
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());
	
	private static Map<String, Set<Session>> rooms = new HashMap<String, Set<Session>>();

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
		Message responseMessage = new Message(mess.getRoom(), mess.getType(), mess.getSender(), mess.getContent());
		// Chuyển đổi đối tượng thành chuỗi JSON
		String jsonResponse = gson.toJson(responseMessage);
		
		System.out.println(mess.getContent());
		// Gửi tin nhắn đến tất cả các client khác
		synchronized (clients) {
			if (responseMessage.getType().equals("join")){
				String roomId = responseMessage.getRoom();
				rooms.putIfAbsent(roomId, new HashSet<Session>());
				Set<Session> players = rooms.get(roomId);
				players.add(session);
				System.out.println("Session " + session.getId()+" Player "+ responseMessage.getSender() + " joined room: " + roomId);
				System.out.println("Room "+ roomId + " có "+ players.size()+" Player");
				Message joinMessage = new Message(roomId,"join",responseMessage.getSender(),String.valueOf(players.size()));
				String jsonJoinMessage =gson.toJson(joinMessage);
				for (Session client : clients) {
					client.getBasicRemote().sendText(jsonJoinMessage);
				}
			}
			else if (responseMessage.getType().equals("chat")) {
				for (Session client : clients) {
					client.getBasicRemote().sendText(jsonResponse);
				}
				
			}
			else if (responseMessage.getType().equals("out")) {
					String roomId = responseMessage.getRoom();
					Set<Session> players = rooms.get(roomId);
					for (Session player : players) {
						if (player.getId().equals(session.getId())) {
							players.remove(player);
							break;
						}
					}
					System.out.println("Session " + session.getId()+" Player "+ responseMessage.getSender() + " out room: " + roomId);
					System.out.println("Room "+ roomId + " có "+ players.size()+" Player");
					Message outMessage = new Message(roomId,"out",responseMessage.getSender(),String.valueOf(players.size()));
					String jsonoutMessage =gson.toJson(outMessage);
					for (Session client : clients) {
						client.getBasicRemote().sendText(jsonoutMessage);
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
