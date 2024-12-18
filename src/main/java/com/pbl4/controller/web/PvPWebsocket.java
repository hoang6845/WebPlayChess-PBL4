package com.pbl4.controller.web;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.UUID;

import com.google.gson.Gson;
import com.pbl4.GamePlay.GameModePvP;
import com.pbl4.model.bean.ChessInfo;
import com.pbl4.model.bean.HistoryModel;
import com.pbl4.model.bean.HistoryMoveOfGame;
import com.pbl4.model.bean.Message;
import com.pbl4.model.bean.SessionPlayer;
import com.pbl4.model.bean.Undo;
import com.pbl4.model.bean.UserModel;
import com.pbl4.serviceImpl.HistoryService;
import com.pbl4.serviceImpl.ProfileService;
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

@ServerEndpoint(value = "/PvP", configurator = PvPWebsocket.SessionConfigurator.class)
public class PvPWebsocket {
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
		//lấy httpsession từ websocket và truyền làm thuộc tính của session
		httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		session.getUserProperties().put("httpSession", httpSession);
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
		// Gửi tin nhắn đến tất cả các client khác
		synchronized (clients) {
			if (responseMessage.getType().equals("join")) {
				String roomId = responseMessage.getRoom();
				rooms.putIfAbsent(roomId, new LinkedHashSet<SessionPlayer>());
				Set<SessionPlayer> players = rooms.get(roomId);
				String senderName = UserService.getInstance()
						.findFullnameById(Long.parseLong(responseMessage.getSender()));
				SessionPlayer newPlayer = new SessionPlayer(session, senderName,
						Long.parseLong(responseMessage.getSender()));
				players.add(newPlayer);
				System.out.println("Session " + session.getId() + " Player " + senderName + " joined room: " + roomId);
				System.out.println("Room " + roomId + " có " + players.size() + " Player");

				Message joinMessage = new Message(roomId, "join", responseMessage.getSender(),
						String.valueOf(players.size()));
				String jsonJoinMessage = gson.toJson(joinMessage);
				for (Session client : clients) {
					client.getBasicRemote().sendText(jsonJoinMessage);
				}
				if (players.size() == 2) {
					System.out.println("-------------- START");
					if (roomsGame.putIfAbsent(roomId, new GameModePvP()) == null) {
						System.out.println("setUp trận đấu");
						GameModePvP G = roomsGame.get(roomId);
						G.begin();
						Iterator<SessionPlayer> iterator = players.iterator();
						SessionPlayer firstPlayer = iterator.next();
						SessionPlayer SecondPlayer = iterator.next();
						UserModel whiteModel = UserService.getInstance().FindUserById(firstPlayer.getUserId());
						whiteModel.setAvatar(ProfileService.getInstance().findByUserId(whiteModel.getId()).getImageOfUser());
						UserModel blackModel = UserService.getInstance().FindUserById(SecondPlayer.getUserId());
						blackModel.setAvatar(ProfileService.getInstance().findByUserId(blackModel.getId()).getImageOfUser());
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
					ChessInfo[] arr = new ChessInfo[32];
					Iterator<SessionPlayer> iterator = players.iterator();
					SessionPlayer firstPlayer = iterator.next();
					SessionPlayer SecondPlayer = iterator.next();
					UserModel whiteModel = UserService.getInstance().FindUserById(firstPlayer.getUserId());
					whiteModel.setAvatar(ProfileService.getInstance().findByUserId(whiteModel.getId()).getImageOfUser());
					UserModel blackModel = UserService.getInstance().FindUserById(SecondPlayer.getUserId());
					blackModel.setAvatar(ProfileService.getInstance().findByUserId(blackModel.getId()).getImageOfUser());
					for (int i = 0; i < 16; i++) {
						arr[i] = ChessInfo.getInstance().convertChessToChessInfo(G.mgr.Player[i], i);
						arr[i + 16] = ChessInfo.getInstance().convertChessToChessInfo(G.mgr.Computer[i], i);
					}
					ArrayList<ChessInfo> data = new ArrayList<ChessInfo>(Arrays.asList(arr));
					Message updateBoardForNew = new Message(roomId, "updateMove", responseMessage.getSender(), "",
							whiteModel, blackModel, data);
					String jsonUpdate = gson.toJson(updateBoardForNew);
					for (Session client : clients) {
						for (SessionPlayer player : players) {
							if (client.equals(player.getSession())
									&& player.getUserId() == Long.parseLong(responseMessage.getSender())) {
								client.getBasicRemote().sendText(jsonUpdate);
							}
						}
					}
				}
			} else if (responseMessage.getType().equals("chat")) {
				String senderName = UserService.getInstance()
						.findFullnameById(Long.parseLong(responseMessage.getSender()));
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
				System.out.println("---------- Đã vào out");
				String senderName = UserService.getInstance()
						.findFullnameById(Long.parseLong(responseMessage.getSender()));
				String roomId = responseMessage.getRoom();
				Set<SessionPlayer> players = rooms.get(roomId);
			
				if (players!=null) {				
					Message outMessage = new Message(roomId, "out", responseMessage.getSender(),
							String.valueOf(players.size()-1));
					String jsonoutMessage = gson.toJson(outMessage);
					for (Session client : clients) {
						
						if (client!=session) {
							client.getBasicRemote().sendText(jsonoutMessage);						
						}else {
							System.out.println("Session " + session.getId()+ ": m out r không gửi");
						}
					}
				}
				
				/*
				 * if (players.size() == 1) { roomsGame.remove(roomId); Message restartMessage =
				 * new Message(roomId, "restart", responseMessage.getSender(), "restart");
				 * String jsonRestartMessage = gson.toJson(restartMessage); for (Session client
				 * : clients) { client.getBasicRemote().sendText(jsonRestartMessage); } }
				 */

				if (players!=null&&players.size()>=2) {
					Iterator<SessionPlayer> iterator = players.iterator();
					SessionPlayer whitePlayer = iterator.next();
					SessionPlayer blackPlayer = iterator.next();
					if (Long.parseLong(responseMessage.getSender()) == whitePlayer.getUserId()) {
						HistoryModel h = new HistoryModel();
						h.setWhiteId(whitePlayer.getUserId());
						h.setBlackId(blackPlayer.getUserId());
						h.setResult("lose");
						h.setCreateBy(whitePlayer.getNameSession());
						Message endGame = new Message(roomId, "lose", responseMessage.getSender(), "");
						endGame.setContent(whitePlayer.getNameSession());
						String jsonEndGame = gson.toJson(endGame);
						if (players!=null) {			
							for (SessionPlayer player : players) {
								if (player.getId().equals(session.getId())) {
									players.remove(player);
									break;
								}
							}
							System.out.println("Session " + session.getId() + " Player " + senderName + " out room: " + roomId);
							System.out.println("Room " + roomId + " có " + players.size() + " Player");
							
						}
						for (Session client : clients) {
							for (SessionPlayer player : players) {
								if (client.equals(player.getSession())) {
									client.getBasicRemote().sendText(jsonEndGame);
								}
							}
						}
//						HistoryService.getInstance().insert(h);
//						roomsGame.remove(roomId);
//						rooms.remove(roomId);
						GameEnd(h,roomId);
					} else if (Long.parseLong(responseMessage.getSender()) == blackPlayer.getUserId()) {
						HistoryModel h = new HistoryModel();
						h.setWhiteId(whitePlayer.getUserId());
						h.setBlackId(blackPlayer.getUserId());
						h.setResult("win");
						h.setCreateBy(whitePlayer.getNameSession());
						Message endGame = new Message(roomId, "lose", responseMessage.getSender(), "");
						endGame.setContent(blackPlayer.getNameSession());
						String jsonEndGame = gson.toJson(endGame);
						if (players!=null) {			
							for (SessionPlayer player : players) {
								if (player.getId().equals(session.getId())) {
									players.remove(player);
									break;
								}
							}
							System.out.println("Session " + session.getId() + " Player " + senderName + " out room: " + roomId);
							System.out.println("Room " + roomId + " có " + players.size() + " Player");
							
						}
						for (Session client : clients) {
							for (SessionPlayer player : players) {
								if (client.equals(player.getSession())) {
									client.getBasicRemote().sendText(jsonEndGame);
								}
							}
						}
//						HistoryService.getInstance().insert(h);
//						roomsGame.remove(roomId);
//						rooms.remove(roomId);
						GameEnd(h, roomId);
					}
				}
				else if (players!=null) {			
					for (SessionPlayer player : players) {
						if (player.getId().equals(session.getId())) {
							players.remove(player);
							break;
						}
					}
					System.out.println("Session " + session.getId() + " Player " + senderName + " out room: " + roomId);
					System.out.println("Room " + roomId + " có " + players.size() + " Player");
					
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
			} else if (responseMessage.getType().equals("win")) {
				String roomId = responseMessage.getRoom();
				GameModePvP G = roomsGame.get(roomId);
				Message endGame = new Message(roomId, "win", responseMessage.getSender(), "");

				Set<SessionPlayer> players = rooms.get(roomId);
				Iterator<SessionPlayer> iterator = players.iterator();
				SessionPlayer firstPlayer = iterator.next();
				SessionPlayer SecondPlayer = iterator.next();

				HistoryModel h = new HistoryModel();
				h.setWhiteId(firstPlayer.getUserId());
				h.setBlackId(SecondPlayer.getUserId());
				h.setCreateDate(new Date(System.currentTimeMillis()));
				if (firstPlayer.getUserId() == Long.parseLong(responseMessage.getSender())) {
					if (G.endGameP1()) {
						h.setResult("win");
						h.setCreateBy(firstPlayer.getNameSession());
						endGame.setContent(firstPlayer.getNameSession());
						String jsonEndGame = gson.toJson(endGame);
						for (Session client : clients) {
							for (SessionPlayer player : players) {
								if (client.equals(player.getSession())) {
									client.getBasicRemote().sendText(jsonEndGame);
								}
							}
						}
					} else {
						System.out.println("!!!!!!!!!!Lỗi chưa tìm được người chiến thắng");
					}
				} else if (SecondPlayer.getUserId() == Long.parseLong(responseMessage.getSender())) {
					if (G.endGameP2()) {
						h.setResult("lose");
						h.setCreateBy(SecondPlayer.getNameSession());
						endGame.setContent(SecondPlayer.getNameSession());
						String jsonEndGame = gson.toJson(endGame);
						for (Session client : clients) {
							for (SessionPlayer player : players) {
								if (client.equals(player.getSession())) {
									client.getBasicRemote().sendText(jsonEndGame);
								}
							}
						}
					} else {
						System.out.println("!!!!!!!!!!Lỗi chưa tìm được người chiến thắng");
					}
				}

				Stack<Undo> u = new Stack<>();
				for (Undo undo : G.getU()) {
				    u.push(new Undo(undo));  
				}
				System.out.println("History move:-----------------");
				while (!u.isEmpty()) {
					System.out.println(u.lastElement().getI() + " " + u.getLast().getPorC() + " :" + "begin: "
							+ u.getLast().getBegin().getX() + " " + u.getLast().getBegin().getY() + "to "
							+ u.getLast().getEnd().getX() + " " + u.getLast().getEnd().getY());
					u.pop();
				}
//				HistoryService.getInstance().insert(h);
//				roomsGame.remove(roomId);
//				rooms.remove(roomId);
				GameEnd(h, roomId);
			} else if (responseMessage.getType().equals("lose")) {
				String roomId = responseMessage.getRoom();
				GameModePvP G = roomsGame.get(roomId);
				Message endGame = new Message(roomId, "lose", responseMessage.getSender(), "");

				Set<SessionPlayer> players = rooms.get(roomId);
				Iterator<SessionPlayer> iterator = players.iterator();
				SessionPlayer firstPlayer = iterator.next();
				SessionPlayer SecondPlayer = iterator.next();

				HistoryModel h = new HistoryModel();
				h.setWhiteId(firstPlayer.getUserId());
				h.setBlackId(SecondPlayer.getUserId());
				h.setCreateDate(new Date(System.currentTimeMillis()));
				if (firstPlayer.getUserId() == Long.parseLong(responseMessage.getSender())) {
					h.setResult("lose");
					h.setCreateBy(firstPlayer.getNameSession());
					endGame.setContent(firstPlayer.getNameSession());
					String jsonEndGame = gson.toJson(endGame);
					for (Session client : clients) {
						for (SessionPlayer player : players) {
							if (client.equals(player.getSession())) {
								client.getBasicRemote().sendText(jsonEndGame);
							}
						}
					}
				} else if (SecondPlayer.getUserId() == Long.parseLong(responseMessage.getSender())) {
					h.setResult("win");
					h.setCreateBy(SecondPlayer.getNameSession());
					endGame.setContent(SecondPlayer.getNameSession());
					String jsonEndGame = gson.toJson(endGame);
					for (Session client : clients) {
						for (SessionPlayer player : players) {
							if (client.equals(player.getSession())) {
								client.getBasicRemote().sendText(jsonEndGame);
							}
						}
					}
				}
//				HistoryService.getInstance().insert(h);
//				roomsGame.remove(roomId);
//				rooms.remove(roomId);
				GameEnd(h, roomId);
			} else if (responseMessage.getType().equals("draw")) {
				String roomId = responseMessage.getRoom();
				Message wantDraw = new Message(roomId, "draw", responseMessage.getSender(), "");

				Set<SessionPlayer> players = rooms.get(roomId);
				Iterator<SessionPlayer> iterator = players.iterator();
				SessionPlayer firstPlayer = iterator.next();
				SessionPlayer SecondPlayer = iterator.next();
				if (firstPlayer.getUserId() == Long.parseLong(responseMessage.getSender())) {
					String jsonWantDraw = gson.toJson(wantDraw);
					for (Session client : clients) {
						for (SessionPlayer player : players) {
							if (client.equals(player.getSession())
									&& player.getSession().equals(SecondPlayer.getSession())) {
								client.getBasicRemote().sendText(jsonWantDraw);
							}
						}
					}
				} else if (SecondPlayer.getUserId() == Long.parseLong(responseMessage.getSender())) {
					String jsonWantDraw = gson.toJson(wantDraw);
					for (Session client : clients) {
						for (SessionPlayer player : players) {
							if (client.equals(player.getSession())
									&& player.getSession().equals(firstPlayer.getSession())) {
								client.getBasicRemote().sendText(jsonWantDraw);

							}
						}
					}
				}
			} else if (responseMessage.getType().equals("acceptDraw")) {
				String roomId = responseMessage.getRoom();
				Message acceptDraw = new Message(roomId, "acceptDraw", responseMessage.getSender(), "");

				Set<SessionPlayer> players = rooms.get(roomId);
				Iterator<SessionPlayer> iterator = players.iterator();
				SessionPlayer firstPlayer = iterator.next();
				SessionPlayer SecondPlayer = iterator.next();

				HistoryModel h = new HistoryModel();
				h.setWhiteId(firstPlayer.getUserId());
				h.setBlackId(SecondPlayer.getUserId());
				h.setCreateDate(new Date(System.currentTimeMillis()));
				h.setResult("draw");

				if (firstPlayer.getUserId() == Long.parseLong(responseMessage.getSender())) {
					String jsonAcceptDraw = gson.toJson(acceptDraw);
					h.setCreateBy(SecondPlayer.getNameSession());
					for (Session client : clients) {
						for (SessionPlayer player : players) {
							if (client.equals(player.getSession())) {
								client.getBasicRemote().sendText(jsonAcceptDraw);
							}
						}
					}
				} else if (SecondPlayer.getUserId() == Long.parseLong(responseMessage.getSender())) {
					String jsonAcceptDraw = gson.toJson(acceptDraw);
					h.setCreateBy(SecondPlayer.getNameSession());
					for (Session client : clients) {
						for (SessionPlayer player : players) {
							if (client.equals(player.getSession())) {
								client.getBasicRemote().sendText(jsonAcceptDraw);

							}
						}
					}
				}
//				HistoryService.getInstance().insert(h);
//				roomsGame.remove(roomId);
//				rooms.remove(roomId);
				GameEnd(h, roomId);
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
	
	public void GameEnd(HistoryModel h, String roomId) {
		HistoryService.getInstance().insert(h);
		String whiteName = UserService.getInstance().findFullnameById(h.getWhiteId());
		String blackName = UserService.getInstance().findFullnameById(h.getBlackId());
		Stack<Undo> listMove = roomsGame.get(roomId).getU();
		HistoryMoveOfGame hmg = new HistoryMoveOfGame(UUID.randomUUID().toString(), listMove, whiteName, blackName);
		hmg.setResult(h.getResult());
		Set<SessionPlayer> players = rooms.get(roomId);
		for (SessionPlayer player:players) {
			SessionUtil.getInstance().putValueBySession((HttpSession)player.getSession().getUserProperties().get("httpSession"),"HistoryMoveOfGame", hmg);
		}
		roomsGame.remove(roomId);
		rooms.remove(roomId);
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
