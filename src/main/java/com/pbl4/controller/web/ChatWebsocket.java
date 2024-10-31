package com.pbl4.controller.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

import com.google.gson.Gson;
import com.pbl4.GamePlay.GameModePvP;
import com.pbl4.filter.AuthorizationFilter;
import com.pbl4.model.bean.Message;
import com.pbl4.model.bean.SessionPlayer;
import com.pbl4.model.bean.Undo;
import com.pbl4.model.bean.UserModel;
import com.pbl4.serviceImpl.RankService;
import com.pbl4.serviceImpl.UserService;
import com.pbl4.utils.SessionUtil;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.EndpointConfig;
import jakarta.websocket.HandshakeResponse;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnMessage;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.server.ServerEndpointConfig;
import jakarta.websocket.server.ServerEndpointConfig.Configurator;

@ServerEndpoint(value = "/PvP", configurator = ChatWebsocket.SessionConfigurator.class)
public class ChatWebsocket {
	private static Set<Session> clients = Collections.synchronizedSet(new HashSet<Session>());

	private static Map<String, Set<SessionPlayer>> rooms = new HashMap<String, Set<SessionPlayer>>();

	private static Map<String, GameModePvP> roomsGame = new HashMap<String, GameModePvP>();

	private HttpSession httpSession;

	public static class SessionConfigurator extends Configurator {
		public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
			HttpSession httpSession = (HttpSession) request.getHttpSession();
			config.getUserProperties().put(HttpSession.class.getName(), httpSession);
		}
	}

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {
		clients.add(session);
		System.out.println("New connection: " + session.getId());
		httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
	}

	@OnMessage
	public void onMessage(String message, Session session) throws Exception {
		System.out.println("Received: " + message + " from " + session.getId());
		Gson gson = new Gson();
		Message mess = gson.fromJson(message, Message.class);
		Message responseMessage = new Message(mess.getRoom(), mess.getType(), mess.getSender(), mess.getContent());
		// Chuyển đổi đối tượng thành chuỗi JSON
		String jsonResponse = gson.toJson(responseMessage);
		System.out.println(jsonResponse);
		System.out.println(mess.getContent());
		// Gửi tin nhắn đến tất cả các client khác
		synchronized (clients) {
			if (responseMessage.getType().equals("join")) {
				String roomId = responseMessage.getRoom();
				rooms.putIfAbsent(roomId, new LinkedHashSet<SessionPlayer>());
				Set<SessionPlayer> players = rooms.get(roomId);
				String senderName = UserService.getInstance()
						.findUserNameById(Long.parseLong(responseMessage.getSender()));
				SessionPlayer newPlayer = new SessionPlayer(session, senderName,
						Long.parseLong(responseMessage.getSender()));
				players.add(newPlayer);
				System.out.println("Session " + session.getId() + " Player " + senderName + " joined room: " + roomId);
				System.out.println("Room " + roomId + " có " + players.size() + " Player");

				/*
				 * if (players.size()==2) {
				 * 
				 * Iterator<SessionPlayer> iterator = players.iterator(); SessionPlayer
				 * firstPlayer = iterator.next(); SessionPlayer secondPlayer = iterator.next();
				 * UserModel whiteModel =
				 * UserService.getInstance().FindUserById(firstPlayer.getUserId()); UserModel
				 * blackModel =
				 * UserService.getInstance().FindUserById(secondPlayer.getUserId());
				 * 
				 * //cách 1 SessionUtil.getInstance().putValueBySession("WHITEMODEL",
				 * whiteModel); //cách 2 httpSession.setAttribute("WHITEMODEL", whiteModel); //
				 * Không dùng được vì 2 cách đều cần reload lại trang để lấy dữ liệu trên http
				 * 
				 * }
				 */

				Message joinMessage = new Message(roomId, "join", responseMessage.getSender(),
						String.valueOf(players.size()));
				String jsonJoinMessage = gson.toJson(joinMessage);
				for (Session client : clients) {
					client.getBasicRemote().sendText(jsonJoinMessage);
				}
				if (players.size() == 2) {
					if (roomsGame.putIfAbsent(roomId, new GameModePvP()) == null) {
						GameModePvP G = roomsGame.get(roomId);
						G.begin();
						Iterator<SessionPlayer> iterator = players.iterator();
						SessionPlayer firstPlayer = iterator.next();
						SessionPlayer SecondPlayer = iterator.next();
						UserModel whiteModel = UserService.getInstance().FindUserById(firstPlayer.getUserId());
						UserModel blackModel = UserService.getInstance().FindUserById(SecondPlayer.getUserId());
						Message gameStartMessage = new Message(roomId, "start",
								firstPlayer.getUserId() + "|" + SecondPlayer.getUserId(), "start", whiteModel,
								blackModel);
						String jsonGameStartMessage = gson.toJson(gameStartMessage);
						System.out.println(firstPlayer.getNameSession() + "|" + SecondPlayer.getNameSession());
						for (Session client : clients) {
							for (SessionPlayer player : players) {
								if (client.equals(player.getSession())) {
									client.getBasicRemote().sendText(jsonGameStartMessage);
								}
							}

						}
					}
				} else if (players.size() >= 3) {
					GameModePvP G = roomsGame.get(roomId);
					Stack<Undo> u = G.getU();
					Undo[] historyMove = new Undo[u.size()];
					u.copyInto(historyMove);
					for (Undo undo : historyMove) {
						System.out.println(undo.getI() + " " + undo.getBegin().getX() + "," + undo.getBegin().getY()
								+ " " + undo.getEnd().getX() + "," + undo.getEnd().getY());
						if (!undo.getBegin().equals(G.Pdie)) {
							// nước đi hợp lệ
							for (Session client : clients) {
								for (SessionPlayer player : players) {
									if (client.equals(player.getSession())
											&& player.getUserId() == Long.parseLong(responseMessage.getSender())) {
										// tạo message "updateHistoryMove"
										// client.getBasicRemote().sendText("");
									}
								}
							}
							// test code tương đương vòng for trên
//							for (SessionPlayer player : players) {
//								if (player.getUserId()==Long.parseLong(responseMessage.getSender())){
//									player.getSession().getBasicRemote().sendText("");
//								}
//							}
						}
					}
				}
			} else if (responseMessage.getType().equals("chat")) {
				String senderName = UserService.getInstance()
						.findUserNameById(Long.parseLong(responseMessage.getSender()));
				String roomId = responseMessage.getRoom();
				Set<SessionPlayer> players = rooms.get(roomId);
				Message chatMessage = new Message(roomId, "chat", senderName, responseMessage.getContent());
				String jsonChatMessage = gson.toJson(chatMessage);
				for (Session client : clients) {
					for (SessionPlayer player : players) {
						if (client.equals(player.getSession())) {
							client.getBasicRemote().sendText(jsonChatMessage);
						}
					}

				}

			} else if (responseMessage.getType().equals("out")) {
				String senderName = UserService.getInstance()
						.findUserNameById(Long.parseLong(responseMessage.getSender()));
				String roomId = responseMessage.getRoom();
				Set<SessionPlayer> players = rooms.get(roomId);
				for (SessionPlayer player : players) {
					if (player.getId().equals(session.getId())) {
						players.remove(player);
						break;
					}
				}
				System.out.println("Session " + session.getId() + " Player " + senderName + " out room: " + roomId);
				System.out.println("Room " + roomId + " có " + players.size() + " Player");
				Message outMessage = new Message(roomId, "out", responseMessage.getSender(),
						String.valueOf(players.size()));
				String jsonoutMessage = gson.toJson(outMessage);
				for (Session client : clients) {
					client.getBasicRemote().sendText(jsonoutMessage);
				}

				if (players.size() == 1) {
					roomsGame.remove(roomId);
					Message restartMessage = new Message(roomId, "restart", responseMessage.getSender(), "restart");
					String jsonRestartMessage = gson.toJson(restartMessage);
					for (Session client : clients) {
						client.getBasicRemote().sendText(jsonRestartMessage);
					}
				}

			} else if (responseMessage.getType().equals("move")) {
				String roomId = responseMessage.getRoom();
				String content = responseMessage.getContent();
				int indexOfI = content.indexOf('|');
				String chessMove = content.substring(0, indexOfI);
				String moveTo = content.substring(indexOfI + 1);
				GameModePvP G = roomsGame.get(roomId);
				ArrayList<Integer> data = ConvertChessBoardToBoard(chessMove, moveTo);
				G.Move(data.get(0), data.get(1), data.get(2));
				Message MoveMessage = new Message(roomId, "move", responseMessage.getSender(), content);
				String jsonMoveMessage = gson.toJson(MoveMessage);
				Set<SessionPlayer> players = rooms.get(roomId);
				for (Session client : clients) {
					for (SessionPlayer player : players) {
						if (client.equals(player.getSession())) {
							client.getBasicRemote().sendText(jsonMoveMessage);
						}
					}

				}
			}else if (responseMessage.getType().equals("win")) {
				String roomId = responseMessage.getRoom();
				GameModePvP G = roomsGame.get(roomId);
				Message endGame = new Message(roomId, "win", responseMessage.getSender(), "");
				String jsonEndGame = gson.toJson(endGame);
				Set<SessionPlayer> players = rooms.get(roomId);
				Iterator<SessionPlayer> iterator = players.iterator();
				SessionPlayer firstPlayer = iterator.next();
				SessionPlayer SecondPlayer = iterator.next();
				if (firstPlayer.getUserId()==Long.parseLong(responseMessage.getSender())) {
					if (G.endGameP1()) {
						for (Session client : clients) {
							for (SessionPlayer player : players) {
								if (client.equals(player.getSession())) {
									client.getBasicRemote().sendText(jsonEndGame);
								}
							}
						}
					}
					else {
						System.out.println("!!!!!!!!!!Lỗi chưa tìm được người chiến thắng");
					}
				}else if (SecondPlayer.getUserId()==Long.parseLong(responseMessage.getSender())) {
					if (G.endGameP2()) {
						for (Session client : clients) {
							for (SessionPlayer player : players) {
								if (client.equals(player.getSession())) {
									client.getBasicRemote().sendText(jsonEndGame);
								}
							}
						}
					}
					else {
						System.out.println("!!!!!!!!!!Lỗi chưa tìm được người chiến thắng");
					}
				}
			}
		}
	}

	@OnClose
	public void onClose(Session session) {
		clients.remove(session);
		System.out.println("Connection closed: " + session.getId());

	}

	@OnError
	public void onError(Session session, Throwable throwable) {
		if (throwable instanceof IOException) {
			System.out.println("Client disconnected unexpectedly: " + throwable.getMessage());
		} else { // Ghi lại các lỗi khác
			throwable.printStackTrace();
		}
	}

	public ArrayList<Integer> ConvertChessBoardToBoard(String chessMove, String moveTo) {
		ArrayList<Integer> data = new ArrayList<Integer>();
		int chessMoveX = chessMove.charAt(chessMove.length() - 2) - 'a';
		int chessMoveY = chessMove.charAt(chessMove.length() - 1) - '0';
		chessMoveY = 8 - (chessMoveY);
		if (chessMoveY == 0 || chessMoveY == 1) {
			System.out.println("-----------Quân đen");
		} else if (chessMoveY == 6 || chessMoveY == 7) {
			System.out.println("-----------Quân trắng");
		} else {
			System.out.println("----------------Lỗi");
		}
		int moveToX = moveTo.charAt(moveTo.length() - 2) - 'a';
		int moveToY = moveTo.charAt(moveTo.length() - 1) - '0';
		moveToY = 8 - moveToY;
		switch (chessMoveY) {
		case 1:
		case 6:
			data.add(chessMoveX);
			break;
		case 0:
		case 7:
			switch (chessMoveX) {
			case 0:
				data.add(8);
				break;
			case 7:
				data.add(9);
				break;
			case 1:
				data.add(10);
				break;
			case 6:
				data.add(11);
				break;
			case 2:
				data.add(12);
				break;
			case 5:
				data.add(13);
				break;
			case 3:
				data.add(14);
				break;
			case 4:
				data.add(15);
				break;
			}
		}
		data.add(moveToX);
		data.add(moveToY);
		System.out.println("_______" + data.size() + ": " + "quân thứ " + data.get(0) + " đi tới: " + data.get(1) + " "
				+ data.get(2));
		return data;
	}

}
