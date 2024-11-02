<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
</head>
<body>
	<style>
.square-frame {
	position: relative;
	width: 100%;
	padding-bottom: 100%;
}

.square-frame iframe {
	position: absolute;
	top: 0;
	left: 0;
	width: 100%;
	height: 100%;
	border: none;
}

.hover-effect {
	transition: all 0.3s ease;
}

.hover-effect:hover {
	transform: scale(1.05);
	box-shadow: 0 0 15px rgba(255, 255, 255, 0.2);
}
</style>
	<div class="container mx-auto p-4 text-white">
		<div class="flex flex-col lg:flex-row gap-8">
			<div class="w-full lg:w-2/3">
				<div
					class="bg-gray-800 rounded-lg shadow-lg p-6 flex flex-col items-center">
					<div class="flex justify-between items-center w-full mb-4"
						id="blackPlayer">
						<div class="flex items-center hover-effect cursor-pointer rounded"
							id="blackPlayerContent" style="padding: 2px 8px;">
							<img
								src='<c:url value='template/web/ChessBoard/img/avatarNoPeople.jpg'></c:url>'
								alt="Black Player img" class="w-10 h-10 rounded-full mr-2">
							<div id="blackPlayerInfo"></div>
						</div>
						<div
							class="text-3xl font-bold bg-gray-700 px-4 py-2 rounded hover-effect opacity-50"
							id="blackPlayerClock">10:00</div>
					</div>

					<div
						class="w-full max-w-[500px] aspect-square relative mb-4 mx-auto"
						id="board">
						<%@ include file="/common/chessBoard/chessBoard.jsp"%>
					</div>

					<div class="flex justify-between items-center w-full"
						id="whitePlayer">
						<div class="flex items-center hover-effect cursor-pointer rounded"
							id="whitePlayerContent" style="padding: 2px 8px;">
							<img
								src='<c:url value='template/web/ChessBoard/img/avatarNoPeople.jpg'></c:url>'
								alt="Black Player" class="w-10 h-10 rounded-full mr-2">
							<div id="whitePlayerInfo"></div>
						</div>
						<div
							class="text-3xl font-bold bg-gray-700 px-4 py-2 rounded hover-effect opacity-50"
							id="whitePlayerClock">10:00</div>
					</div>
				</div>
			</div>

			<div class="w-full lg:w-1/3 mt-8 lg:mt-0">
				<div class="bg-gray-800 rounded-lg shadow-lg p-6 h-full">
					<h2 class="text-2xl font-bold mb-4 block inline-block">Move
						History</h2>
					<div
						class="text-white font-bold py-2 px-4 rounded transition duration-300 ease-in-out hover-effect inline-block cursor-pointer"
						id="numberInRoom">
						<i class="fa-solid fa-eye"></i> ${numberInRoom} 5
					</div>
					<button
						class="bg-red-600 hover:bg-red-700 text-white font-bold py-2 px-4 rounded transition duration-300 ease-in-out hover-effect float-right"
						aria-label="Draw" id="btn_leaveRoom">Exit</button>
					<div
						class="h-48 overflow-y-auto mb-4 bg-gray-700 p-2 rounded hover-effect">
						<ul class="list-decimal pl-5">
							<li>e4 e5</li>
							<li>Nf3 Nc6</li>
							<li>Bb5 a6</li>
							<li>Ba4 Nf6</li>
							<li>O-O Be7</li>
							<li>Re1 b5</li>
							<li>Bb3 d6</li>
							<li>c3 O-O</li>
						</ul>
					</div>

					<div class="flex justify-center gap-4 mb-4">
						<button
							class="bg-yellow-600 hover:bg-yellow-700 text-white font-bold py-2 px-4 rounded transition duration-300 ease-in-out hover-effect"
							aria-label="Draw">Draw</button>
						<button
							class="bg-red-600 hover:bg-red-700 text-white font-bold py-2 px-4 rounded transition duration-300 ease-in-out hover-effect"
							aria-label="Accept Loss">Accept Loss</button>
					</div>

					<h2 class="text-2xl font-bold mb-4">Chat</h2>
					<div
						class="h-48 overflow-y-auto mb-4 bg-gray-700 p-2 rounded hover-effect"
						id="chatBox"></div>

					<div class="flex">
						<input type="text" id="messageInput"
							placeholder="Type your message..."
							class="flex-grow border rounded-l px-4 py-2 bg-gray-700 text-gray-200 focus:outline-none focus:ring-2 focus:ring-gray-500 hover-effect">
						<button onclick="sendMessage()"
							class="bg-gray-600 hover:bg-gray-500 text-white font-bold py-2 px-4 rounded-r transition duration-300 ease-in-out hover-effect">Send</button>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Player Info Modal -->
	<div id="playerInfoModal"
		class="fixed inset-0 bg-black bg-opacity-50 hidden items-center justify-center z-10">
		<div id="playerInfoModal_container"
			class="bg-gray-500 p-8 rounded-lg shadow-xl max-w-md w-full relative hover-effect">
			<div class="flex justify-between items-center mb-4">
				<h2 class="text-2xl font-bold" id="playerName"></h2>
			</div>
			<div class="flex items-center mb-4">
				<img id="playerAvatar" src="" alt="Player Avatar"
					class="w-20 h-20 rounded-full mr-4 hover-effect">
				<div>
					<p>
						Rank: <span id="playerRank"></span>
					</p>
					<p>
						Matches: <span id="playerMatches"></span>
					</p>
					<p>
						Friends: <span id="playerFriends"></span>
					</p>
				</div>
			</div>
			<button
				class="bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded transition duration-300 ease-in-out w-full hover-effect">Add
				Friend</button>
		</div>

	</div>


	<script>	
    	var whitePlayerTime = 10 * 60; // 5 phút tính bằng giây
    	var blackPlayerTime = 10 * 60; // 5 phút tính bằng giây
    	var whitePlayerInterval, blackPlayerInterval;

    	function startWhitePlayerClock() {
    		  clearInterval(blackPlayerInterval); // Dừng đồng hồ của Player black
    		  let whiteClock = document.getElementById('whitePlayerClock');
    		  let blackClock = document.getElementById('blackPlayerClock');
    		  whiteClock.classList.add("clockRun");
    		  blackClock.classList.remove("clockRun");
    		  whitePlayerInterval = setInterval(() => {
    		    if (whitePlayerTime > 0) {
    		    	whitePlayerTime--;
    		      updateClock('whitePlayerClock', whitePlayerTime);
    		    } else {
    		      clearInterval(whitePlayerInterval);
    		      console.log('Player white hết giờ!');
    		    }
    		  }, 1000); // Cập nhật mỗi giây
    		}
    	function startBlackPlayerClock() {
  		  clearInterval(whitePlayerInterval); // Dừng đồng hồ của Player white
  		  let whiteClock = document.getElementById('whitePlayerClock');
		  let blackClock = document.getElementById('blackPlayerClock');
		  blackClock.classList.add("clockRun");
		  whiteClock.classList.remove("clockRun");
  		  blackPlayerInterval = setInterval(() => {
  		    if (blackPlayerTime > 0) {
  		    	blackPlayerTime--;
  		      updateClock('blackPlayerClock', blackPlayerTime);
  		    } else {
  		      clearInterval(blackPlayerInterval);
  		      console.log('Player white hết giờ!');
  		    }
  		  }, 1000); // Cập nhật mỗi giây
  		}
    	function updateClock(elementId, time) {
    		  const minutes = Math.floor(time / 60);
    		  const seconds = time % 60;
    		 /*  document.getElementById(elementId).textContent = formattedTime; */
    		  document.getElementById(elementId).textContent = minutes+":"+seconds;
    		}
    	
        function startCountdown(callback) {
            let modal = document.getElementById('modal');
            let countdownValue = 3;

            // Thiết lập để hiển thị chữ "Start"
            modal.style.fontSize = '200px'; // Tăng kích thước chữ
            modal.textContent = "Start"; 

            // Đợi 1 giây trước khi bắt đầu đếm ngược từ 3
            setTimeout(() => {
                const interval = setInterval(() => {
                    modal.textContent = countdownValue; // Cập nhật nội dung

                    if (countdownValue < 1) {
                        clearInterval(interval); // Dừng đếm ngược khi đạt 0
                        modal.textContent = 'Play!';
                        setTimeout(() => {
                            modal.classList.add('modal_hidden'); // Ẩn sau 1 giây
                        }, 1000);
                        callback();
                    }
                    countdownValue--; // Giảm 1
                }, 1000); // Cập nhật mỗi giây
            }, 1000); // Bắt đầu đếm ngược sau khi hiển thị "Start" 1 giây
        }
    	
    	var RoomGame = '${room}';
    	var checkTypePlayer ="";
    	let ws = new WebSocket('ws://192.168.1.8:8080/chess-game/PvP'); 
    /* 	 class Message {
             constructor(room, type, sender, content) {
             	this.room = room;
             	this.type= type;
                 this.sender = sender;
                 this.content = content;
             }
         } */
    	function joinRoom(){
         	const room = "${room}";
         	console.log(room);
         	const userId = "${USERMODEL.id}";
         	const message = new Message(room, "join", userId,"");
         	console.log(message);
         	ws.send(JSON.stringify(message));
         }
    	ws.addEventListener('open',function(ev){
    		joinRoom();
    	});
    	let numberInRoom = 0;
    	window.onbeforeunload = function(){
    		outRoom();
    	};
    	
    	document.getElementById("btn_leaveRoom").addEventListener('click',function(){
    		outRoom();
    		ws.close();
    		setTimeout(() => {
    	        window.location.assign("/chess-game/trang-chu?page=home");
    	    }, 1000); // Chờ 1000ms trước khi chuyển hướng
    	});
    	let chatbox = document.getElementById('chatBox');
    	console.log(chatbox);
    	
    	function endGameToServer(){
    		
    	}
    	
        ws.onmessage = function(event) {
        	console.log(event.data)
        	const receivedMessage = JSON.parse(event.data);
    
    		// Xử lý thông tin nhận được
    		console.log('Received message:', receivedMessage);
    		if (receivedMessage.type == "chat"){
    			if (receivedMessage.room == RoomGame){
	    			let newMessage = document.createElement('div');
		            newMessage.className = "mb-2";
		            newMessage.innerHTML = `<span class="font-bold"> :</span> <p></p>`
		            const span = newMessage.querySelector('span');
		            span.textContent = receivedMessage.sender+':';
		            const p = newMessage.querySelector('p') ;
		            p.classList.add('inline-block');
		            p.textContent=receivedMessage.content;
		            chatbox.appendChild(newMessage);
    			}
    		}
    		else if(receivedMessage.type == "join"){
    			if (receivedMessage.room == RoomGame){
    				let numberInRoom = document.getElementById('numberInRoom');
    				console.log(numberInRoom);
    				numberInRoom.innerHTML=`<i class="fa-solid fa-eye"></i>
    										<p></p>`
    				const p=numberInRoom.querySelector('p');
    				p.classList.add('inline-block');
    				p.textContent = receivedMessage.content;
    				let IntNumberInRoom =receivedMessage.content;
    				console.log(IntNumberInRoom);
    				if(IntNumberInRoom>=3){
    					let modal = document.getElementById('modal');
        				modal.classList.add('modal_hidden');
    				}
    				if(IntNumberInRoom==1){
    					
    					let whitePlayerInfo = document.getElementById('whitePlayerInfo');
    					whitePlayerInfo.innerHTML = `<p class="font-bold cursor-pointer hover:underline" id="whitePlayerName"
							onclick="showPlayerInfo()">WhitePlayer</p>
							<div class="flex items-center">
								<p class="text-sm text-gray-400" id="whitePlayerRank">Rank: ?</p>
								<span class="ml-2 text-yellow-500">🏆</span>
							</div>`
    					let whitePlayerName = whitePlayerInfo.querySelector('#whitePlayerName');
    					let whitePlayerRank = whitePlayerInfo.querySelector('#whitePlayerRank');
    					console.log(whitePlayerName);
    					whitePlayerName.textContent = "${USERMODEL.fullname}" ;
    					whitePlayerRank.textContent = "${USERMODEL.getElo()}" ;
    					let whitePlayerContent = document.getElementById('whitePlayerContent');
    					console.log(whitePlayerContent);
    					whitePlayerContent.appendChild(whitePlayerInfo)
							
    				}
    			}
    		}
    		else if(receivedMessage.type == "out"){
    			if (receivedMessage.room == RoomGame){
    				let numberInRoom = document.getElementById('numberInRoom');
    				console.log(numberInRoom);
    				numberInRoom.innerHTML=`<i class="fa-solid fa-eye"></i>
    										<p></p>`
    				const p=numberInRoom.querySelector('p');
    				p.classList.add('inline-block');
    				p.textContent = receivedMessage.content;
    			}
    		}
    		else if(receivedMessage.type == "start"){
    			if (receivedMessage.room == RoomGame){
    				
    				//set info whitePlayer
    				let whitePlayerInfo = document.getElementById('whitePlayerInfo');
					whitePlayerInfo.innerHTML = `<p class="font-bold cursor-pointer hover:underline" id="whitePlayerName"
						onclick="showPlayerInfo()">WhitePlayer</p>
						<div class="flex items-center">
							<p class="text-sm text-gray-400" id="whitePlayerRank">Rank: ?</p>
							<span class="ml-2 text-yellow-500">🏆</span>
						</div>`
					let whitePlayerName = whitePlayerInfo.querySelector('#whitePlayerName');
					let whitePlayerRank = whitePlayerInfo.querySelector('#whitePlayerRank');
					console.log(whitePlayerName);
					whitePlayerName.textContent = receivedMessage.whiteModel.fullname ;
					whitePlayerRank.textContent = receivedMessage.whiteModel.elo ;
					let whitePlayerContent = document.getElementById('whitePlayerContent');
					console.log(whitePlayerContent);
					whitePlayerContent.appendChild(whitePlayerInfo)
    				
					//set info backPlayer
					let blackPlayerInfo = document.getElementById('blackPlayerInfo');
					blackPlayerInfo.innerHTML = `<p class="font-bold cursor-pointer hover:underline" id="blackPlayerName"
						onclick="showPlayerInfo()"></p>
						<div class="flex items-center">
							<p class="text-sm text-gray-400" id="blackPlayerRank">Rank: ?</p>
							<span class="ml-2 text-yellow-500">🏆</span>
						</div>`
					let blackPlayerName = blackPlayerInfo.querySelector('#blackPlayerName');
					let blackPlayerRank = blackPlayerInfo.querySelector('#blackPlayerRank');
					console.log(blackPlayerName);
					blackPlayerName.textContent = receivedMessage.blackModel.fullname ;
					blackPlayerRank.textContent = receivedMessage.blackModel.elo ;
					let blackPlayerContent = document.getElementById('blackPlayerContent');
					console.log(blackPlayerContent);
					blackPlayerContent.appendChild(blackPlayerInfo)
    				
    				let [whitePlayer, blackPlayer]=receivedMessage.sender.split("|");
    				console.log(whitePlayer);
    				console.log(blackPlayer);
    				const username = "${USERMODEL.id}";
    				if (username == whitePlayer){
    					checkTypePlayer = "white";
    				}else if(username == blackPlayer){
    					checkTypePlayer = "black";
    					let chessBoard = document.querySelector('.chessBoard');
    				    chessBoard.classList.add('reverse');
    				    let piece= document.querySelectorAll('.piece');
    				    piece.forEach(function(element){
    				    	console.log(piece);
    				        element.classList.add('reverse');
    				    });
    				    let coordinate= document.querySelectorAll('.coordinate');
    				    coordinate.forEach(function(element){
    				        console.log(coordinate);
    				        element.classList.add('reverse');
    				    });
    				    let whitePlayer = document.getElementById('whitePlayer');
    				    let blackPlayer = document.getElementById('blackPlayer');
    				    let board = document.getElementById('board');
    				    whitePlayer.classList.add('order-1');
    				    blackPlayer.classList.add('order-3');
    				    board.classList.add('order-2');
    				    
    				}
    				let modal = document.getElementById('modal');

    		//		modal.classList.add('modal_hidden');
    				startCountdown(startWhitePlayerClock);
    		//		startWhitePlayerClock();

    				console.log("da chay dong ho white")
    				
    				
    			}
    		}
    		else if (receivedMessage.type == "move"){
    			if (receivedMessage.room == RoomGame&&receivedMessage.sender != "${USERMODEL.id}"){
    				let [pieceId, destinationSquareId] = receivedMessage.content.split("|");
    				console.log(pieceId);
    				 const piece = document.getElementById(pieceId);
    				 const destinationSquare = document.getElementById(destinationSquareId);
    				 const dataTransfer = {
    				          data: {},
    				          setData: function(type,val) {
    				            this.data[type] = val;
    				          },
    				          getData: function(type){
    				            return this.data[type];
    				          }
    				 }
    				 console.log(piece);
    				 drag({target: piece, dataTransfer:dataTransfer},1);
    				 console.log(dataTransfer);
    			     drop({currentTarget:destinationSquare, dataTransfer: dataTransfer},1);
    				 
    			}
    		}else if (receivedMessage.type == "updateMove"){
    			
    		}else if(receivedMessage.type == "restart"){
    			if (receivedMessage.room == RoomGame){
    				whitePlayerTime = 10 * 60; 
    		    	blackPlayerTime = 10 * 60; 
    		    	clearInterval(whitePlayerInterval);
    		    	clearInterval(blackPlayerInterval);
    		    	document.getElementById('whitePlayerClock').textContent = "10:00";
    		    	document.getElementById('blackPlayerClock').textContent = "10:00";
    				checkTypePlayer = "";
    				let modal = document.getElementById('modal');
    				modal.classList.remove('modal_hidden');
    				modal.innerHTML=`<div class="modal__message">Awaiting opponent...</div>`;
    				let chessBoard = document.querySelector('.chessBoard');
				    chessBoard.classList.remove('reverse');
				    let piece= document.querySelectorAll('.piece');
				    piece.forEach(function(element){
				    	console.log(piece);
				        element.classList.remove('reverse');
				    });
				    let coordinate= document.querySelectorAll('.coordinate');
				    coordinate.forEach(function(element){
				        console.log(coordinate);
				        element.classList.remove('reverse');
				    });
    			}
    		}
    		
    		
          
            
        };
        
        class Room {
        	constructor(room, whitePlayer, blackPlayer){
        		this.room = room;
        		this.whitePlayer = whitePlayer;
        		this.blackPlayer = blackPlayer;
        	}
        }
       
       
        
        function outRoom(){
        	const room = "${room}";
        	console.log(room);
        	const userId = "${USERMODEL.id}";
        	const message = new Message(room, "out", userId,"");
        	console.log(message);
        	ws.send(JSON.stringify(message));
        }
        
        function sendMessage() {
            let input = document.getElementById('messageInput');
            const room = "${room}";
            console.log(room);
            const userId = "${USERMODEL.id}"; // Lấy tên người dùng từ JSP
            const message = new Message(room, "chat", userId, input.value);
            console.log(message);
            ws.send(JSON.stringify(message));
            input.value = '';
        }
        
        function dropSendToServer(pieceId,destinationSquareId){
        	const room = "${room}";
            console.log(room);
            const username = "${USERMODEL.id}";
        	const message = new Message(room,"move",username,pieceId+"|"+destinationSquareId)
        	 ws.send(JSON.stringify(message));
        	console.log(message);
        }
        
        function endGameToServer(){
		  	const room = "${room}";
            console.log(room);
            const userId = "${USERMODEL.id}";
            const message = new Message(room,"win",userId,"");
            console.log(message);
        	ws.send(JSON.stringify(message));
	  }
    
        function showPlayerInfo(name, avatar, rank, matches, friends) {
            document.getElementById('playerName').textContent = name;
            document.getElementById('playerAvatar').src = avatar;
            document.getElementById('playerRank').textContent = rank;
            document.getElementById('playerMatches').textContent = matches;
            document.getElementById('playerFriends').textContent = friends;
            document.getElementById('playerInfoModal').classList.remove('hidden');
            document.getElementById('playerInfoModal').classList.add('flex');
        }

        function closePlayerInfo() {
            document.getElementById('playerInfoModal').classList.add('hidden');
            document.getElementById('playerInfoModal').classList.remove('flex');
        }
        
        const modal = document.getElementById("playerInfoModal");
        const modalContainer = document.getElementById("playerInfoModal_container");
     // click vào ngoài container sẽ tắt
     	console.log(modal);
        modal.addEventListener('click', closePlayerInfo);
        modalContainer.addEventListener('click', function (event) {
            event.stopPropagation();
            // ngăn việc nổi bọt(sự kiện nổi bọt)
        });

        // Add click event listener to opponent's name
        document.querySelector('.flex.justify-between.items-center:last-child .font-bold.cursor-pointer').addEventListener('click', function() {
            showPlayerInfo('BlackPlayer456', 'https://images.unsplash.com/photo-1519085360753-af0119f7cbe7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1374&q=80', 6000, 500, 120);
        });

    </script>

	<script src="<c:url value='template/web/ChessBoard/main.js' />"></script>
</body>
</html>