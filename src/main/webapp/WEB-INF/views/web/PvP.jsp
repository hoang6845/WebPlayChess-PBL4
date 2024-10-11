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
                <div class="bg-gray-800 rounded-lg shadow-lg p-6 flex flex-col items-center">
                    <div class="flex justify-between items-center w-full mb-4">
                        <div class="flex items-center hover-effect">
                            <img src="https://images.unsplash.com/photo-1508214751196-bcfd4ca60f91?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80"
                                alt="White Player" class="w-10 h-10 rounded-full mr-2">
                            <div>
                                <p class="font-bold cursor-pointer hover:underline" onclick="showPlayerInfo('WhitePlayer123', 'https://images.unsplash.com/photo-1508214751196-bcfd4ca60f91?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1470&q=80', 500, 250, 75)">WhitePlayer123</p>
                                <div class="flex items-center">
                                    <p class="text-sm text-gray-400">Rank: 500</p>
                                    <span class="ml-2 text-yellow-500">🏆</span>
                                </div>
                            </div>
                        </div>
                        <div class="text-3xl font-bold bg-gray-700 px-4 py-2 rounded hover-effect">
                            10:00
                        </div>
                    </div>

                    <div class="w-full max-w-[500px] aspect-square relative mb-4 mx-auto">
                        <iframe src="./banco/banco.html" scrolling="no" class="absolute inset-0 w-full h-full"></iframe>
                    </div>

                    <div class="flex justify-between items-center w-full">
                        <div class="flex items-center hover-effect">
                            <img src="https://images.unsplash.com/photo-1519085360753-af0119f7cbe7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1374&q=80"
                                alt="Black Player" class="w-10 h-10 rounded-full mr-2">
                            <div>
                                <p class="font-bold cursor-pointer hover:underline" onclick="showPlayerInfo('BlackPlayer456', 'https://images.unsplash.com/photo-1519085360753-af0119f7cbe7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1374&q=80', 6000, 500, 120)">BlackPlayer456</p>
                                <div class="flex items-center">
                                    <p class="text-sm text-gray-400">Rank: 6000</p>
                                    <span class="ml-2 text-yellow-500">🏆</span>
                                </div>
                            </div>
                        </div>
                        <div class="text-3xl font-bold bg-gray-700 px-4 py-2 rounded hover-effect">
                            10:00
                        </div>
                    </div>
                </div>
            </div>

            <div class="w-full lg:w-1/3 mt-8 lg:mt-0">
                <div class="bg-gray-800 rounded-lg shadow-lg p-6">
                    <h2 class="text-2xl font-bold mb-4">Move History</h2>
                    <div class="h-48 overflow-y-auto mb-4 bg-gray-700 p-2 rounded hover-effect">
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
                        <button class="bg-yellow-600 hover:bg-yellow-700 text-white font-bold py-2 px-4 rounded transition duration-300 ease-in-out hover-effect"
                            aria-label="Draw">
                            Draw
                        </button>
                        <button class="bg-red-600 hover:bg-red-700 text-white font-bold py-2 px-4 rounded transition duration-300 ease-in-out hover-effect"
                            aria-label="Accept Loss">
                            Accept Loss
                        </button>
                    </div>

                    <h2 class="text-2xl font-bold mb-4">Chat</h2>
                    <div class="h-48 overflow-y-auto mb-4 bg-gray-700 p-2 rounded hover-effect"  id="chatBox">
                    </div>

                    <div class="flex">
                        <input type="text" id="messageInput" placeholder="Type your message..." class="flex-grow border rounded-l px-4 py-2 bg-gray-700 text-gray-200 focus:outline-none focus:ring-2 focus:ring-gray-500 hover-effect">
                        <button onclick="sendMessage()" class="bg-gray-600 hover:bg-gray-500 text-white font-bold py-2 px-4 rounded-r transition duration-300 ease-in-out hover-effect">Send</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Player Info Modal -->
    <div id="playerInfoModal" class="fixed inset-0 bg-black bg-opacity-50 hidden items-center justify-center">
        <div class="bg-gray-800 p-8 rounded-lg shadow-xl max-w-md w-full relative hover-effect">
            <div class="flex justify-between items-center mb-4">
                <h2 class="text-2xl font-bold" id="playerName"></h2>
                <button onclick="closePlayerInfo()" class="absolute top-2 right-2 text-gray-500 hover:text-gray-300 text-2xl hover-effect">&times;</button>
            </div>
            <div class="flex items-center mb-4">
                <img id="playerAvatar" src="" alt="Player Avatar" class="w-20 h-20 rounded-full mr-4 hover-effect">
                <div>
                    <p>Rank: <span id="playerRank"></span></p>
                    <p>Matches: <span id="playerMatches"></span></p>
                    <p>Friends: <span id="playerFriends"></span></p>
                </div>
            </div>
            <button class="bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded transition duration-300 ease-in-out w-full hover-effect">Add Friend</button>
        </div>
    </div>

    <script>
    	let ws = new WebSocket('ws://localhost:8080/chess-game/PvP');
    	let chatbox = document.getElementById('chatBox');
    	console.log(chatbox);
        ws.onmessage = function(event) {
        	console.log(event.data)
        	const receivedMessage = JSON.parse(event.data);
    
    		// Xử lý thông tin nhận được
    		console.log('Received message:', receivedMessage);
            let newMessage = document.createElement('div');
            newMessage.className = "mb-2";
            newMessage.innerHTML = `<span class="font-bold"> :</span> <p></p>`
            const span = newMessage.querySelector('span');
            span.textContent = receivedMessage.sender+':';
            const p = newMessage.querySelector('p') ;
            p.textContent=receivedMessage.content;
            chatbox.appendChild(newMessage);
            
        };
        class Message {
            constructor(sender, content) {
                this.sender = sender;
                this.content = content;
            }
        }
        
        function sendMessage() {
            let input = document.getElementById('messageInput');
            const username = "${USERMODEL.fullname}"; // Lấy tên người dùng từ JSP
            const message = new Message(username, input.value);
            ws.send(JSON.stringify(message));
            input.value = '';
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

        // Add click event listener to opponent's name
        document.querySelector('.flex.justify-between.items-center:last-child .font-bold.cursor-pointer').addEventListener('click', function() {
            showPlayerInfo('BlackPlayer456', 'https://images.unsplash.com/photo-1519085360753-af0119f7cbe7?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=1374&q=80', 6000, 500, 120);
        });
    </script>

</body>
</html>