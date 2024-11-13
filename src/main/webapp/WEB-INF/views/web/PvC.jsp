<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="PvCUrl" value="/PvC"/>
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

@keyframes winEffect {
  0% {
    text-shadow: 0 0 5px green, 0 0 10px green, 0 0 15px green, 0 0 20px green;
  }
  
  50% {
    text-shadow: 0 0 10px green, 0 0 20px green, 0 0 30px green, 0 0 40px green;
  }
  
  100% {
    text-shadow: 0 0 15px green, 0 0 30px green, 0 0 40px green, 0 0 50px green;
  }
}

@keyframes loseEffect {
  0% {
    text-shadow: 0 0 5px red, 0 0 10px red, 0 0 15px red, 0 0 20px red;
  }
  
  50% {
    text-shadow: 0 0 10px red, 0 0 20px red, 0 0 30px red, 0 0 40px red;
  }
  
  100% {
    text-shadow: 0 0 15px red, 0 0 30px red, 0 0 40px red, 0 0 50px red;
  }
}

@keyframes drawsEffect {
  0% {
    text-shadow: 0 0 5px white, 0 0 10px white, 0 0 15px white, 0 0 20px white;
  }
  
  50% {
    text-shadow: 0 0 10px white, 0 0 20px white, 0 0 30px white, 0 0 40px white;
  }
  
  100% {
    text-shadow: 0 0 15px white, 0 0 30px white, 0 0 40px white, 0 0 50px white;
  }
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
							class="text-3xl font-bold bg-gray-700 px-4 py-2 rounded hover-effect text-transparent"
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
							<div id="whitePlayerInfo">
								<p class="font-bold cursor-pointer hover:underline" id="whitePlayerName"
									onclick="showPlayerInfo()">${USERMODEL.fullname }</p>
								<div class="flex items-center">
									<p class="text-sm text-gray-400" id="whitePlayerRank">Rank: ${USERMODEL.elo }</p>
									<span class="ml-2 text-yellow-500">🏆</span>
								</div>
							</div>
						</div>
						<div
							class="text-3xl font-bold bg-gray-700 px-4 py-2 rounded hover-effect text-transparent"
							id="whitePlayerClock">10:00</div>
					</div>
				</div>
			</div>

			<div class="w-full lg:w-1/3 mt-8 lg:mt-0">
				<div class="bg-gray-800 rounded-lg shadow-lg p-6 h-full">
					<h2 class="text-2xl font-bold mb-4 block inline-block">Move
						History</h2>
					<button
						class="bg-red-600 hover:bg-red-700 text-white font-bold py-2 px-4 rounded transition duration-300 ease-in-out hover-effect float-right"
						aria-label="Exit" id="btn_leaveRoom">Exit</button>
					<div
						class="h-48 overflow-y-auto mb-4 bg-gray-700 p-2 rounded hover-effect">
						<ul class="list-decimal pl-5" id="MoveHistoryBoard">
							<!--'♜', '♞', '♝', '♛', '♚', '♝', '♞', '♜', '♟', ''  -->
			
						</ul>
					</div>

					<div class="flex justify-center gap-4 mb-4">
						<div
							class="bg-yellow-600 hover:bg-yellow-700 text-white font-bold py-2 px-4 rounded transition duration-300 ease-in-out relative cursor-not-allowed"
							id="showDrawChooseButton"
							aria-label="Draw">Draw
								<div class="hidden" id="drawModel">
									<div class="bg-gray-500 p-4 rounded-lg shadow-xl max-w-md w-48 absolute flex flex-col left-0 -translate-x-1/2 top-0 -translate-y-full cursor-default">									
										<p class="inline">You want to draw?</p>
										<div class="flex justify-center">
											<button class="bg-green-600 hover:bg-green-700 text-white font-bold py-1 px-3 rounded transition duration-300 ease-in-out hover-effect mr-4" onclick="drawToServer(event)">Yes</button>
											<button class="bg-gray-600  text-white font-bold py-1 px-3 rounded transition duration-300 ease-in-out hover-effect" onclick="closeDrawChoose(event)">No</button>								
										</div>
									</div>
								</div>
								
								<div class="hidden" id="acceptDrawModel">
									<div class="bg-gray-500 p-4 rounded-lg shadow-xl max-w-md w-48 absolute flex flex-col left-0 -translate-x-1/2  top-full  cursor-default z-10">									
										<p class="inline">Your Opponent want to draw</p>
										<div class="flex justify-center">
											<button class="bg-green-600 hover:bg-green-700 text-white font-bold py-1 px-3 rounded transition duration-300 ease-in-out hover-effect mr-4" onclick="acceptDrawToServer(event)">Accept</button>
											<button class="bg-gray-600  text-white font-bold py-1 px-3 rounded transition duration-300 ease-in-out hover-effect" onclick="closeAcceptDrawChoose(event)">No</button>								
										</div>
									</div>
								</div>
							</div>
							
						<div
							class="bg-red-600 hover:bg-red-700 text-white font-bold py-2 px-4 rounded transition duration-300 ease-in-out relative cursor-not-allowed"
							id="showAcceptLossChooseButton"
							aria-label="Accept Loss">Accept Loss
								<div class="hidden" id="acceptLossModel">
									<div class="bg-gray-500 p-4 rounded-lg shadow-xl max-w-md w-48 absolute flex flex-col top-0 -translate-y-full cursor-default">									
										<p class="inline">You want to give up?</p>
										<div class="flex justify-center">
											<button class="bg-green-600 hover:bg-green-700 text-white font-bold py-1 px-3 rounded transition duration-300 ease-in-out hover-effect mr-4 " onclick="acceptLossToServer(event)">Yes</button>
											<button class="bg-gray-600  text-white font-bold py-1 px-3 rounded transition duration-300 ease-in-out hover-effect" onclick="closeAcceptLossChoose(event)">No</button>								
										</div>
									</div>
								</div>
							</div>
						
						
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
				<img id="playerAvatar"
					src="https://png.pngtree.com/png-clipart/20190920/original/pngtree-user-flat-character-avatar-png-png-image_4643588.jpg"
					alt="Player Avatar"
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

	<!-- EndGame Modal -->
	<div id="EndGameModal"
		class="fixed flex inset-0 bg-black bg-opacity-50 items-center justify-center z-10 hidden">
		<div id="EndGameModal_container"
			class="bg-gray-500 p-8 rounded-lg shadow-xl max-w-md w-full relative hover-effect">
			<div class="flex justify-center items-center mb-4">
				<h2 class="text-2xl font-bold text-center text-white"
					id="playerName">Trận đấu đã kết thúc</h2>
			</div>
			<div class="flex items-center mb-4 justify-around">
				<i class="fa-solid fa-trophy text-5xl text-yellow-400 "></i> <img
					id="playerAvatar"
					src="https://png.pngtree.com/png-clipart/20190920/original/pngtree-user-flat-character-avatar-png-png-image_4643588.jpg"
					alt="Player Avatar"
					class="w-20 h-20 rounded-full mr-4 hover-effect" > 
				<i class="fa-solid fa-trophy text-5xl text-yellow-400 "></i>
			</div>
			<div class="flex justify-center items-center">
				<p class="font-bold text-l text-yellow-400" id="eloChange"></p>
			</div>
			<div class="flex justify-center items-center mb-4">
				<p class="font-bold text-3xl text-white" id="eloReal"></p>
			</div>
			<button
				class="bg-red-600 hover:bg-red-700 text-white font-bold py-2 px-4 rounded transition duration-300 ease-in-out w-full hover-effect"
				onclick="goHome()">
				Home</button>
			<button
				class="bg-green-600 hover:bg-green-700 text-white font-bold py-2 px-4 rounded transition duration-300 ease-in-out w-full hover-effect"
				onclick="goHistoryMove()">
				Xem lại ván đấu</button>
			<button
				class="bg-blue-600 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded transition duration-300 ease-in-out w-full hover-effect" id="btn_taiDau">
				Chơi lại</button>

		</div>

	</div>
	<script>
		var checkTypePlayer="white";
	
    	$(document).ready(function(){
    		let showAcceptLossChooseButton= document.getElementById('showAcceptLossChooseButton');
			showAcceptLossChooseButton.classList.add('cursor-pointer');
			showAcceptLossChooseButton.classList.remove('cursor-not-allowed');
			showAcceptLossChooseButton.addEventListener('click',showAcceptLossChoose);
			
			let modal = document.getElementById('modal');
			modal.classList.add('hidden');
    	})

        function showAcceptLossChoose(){
        	document.getElementById('acceptLossModel').classList.remove("hidden");
        }
        
        function closeAcceptLossChoose(event) {
            event.stopPropagation();
        	document.getElementById('acceptLossModel').classList.add("hidden");
        	console.log("da chay");
		}
        
        function startBlackPlayerClock(){
        	document.getElementById('blackPlayerClock').classList.add("bg-white");
        	document.getElementById('whitePlayerClock').classList.remove("bg-white");
        }
        function startWhitePlayerClock(){
        	document.getElementById('blackPlayerClock').classList.remove("bg-white");
        	document.getElementById('whitePlayerClock').classList.add("bg-white");
        }
        
        function dropSendToServer(pieceId,destinationSquareId){
			$.ajax({
				url: '${PvCUrl}',
				type: 'POST',
	            contentType: 'application/json',
	            data: JSON.stringify({
	            	type: "move",
	            	level: "${level}",
	                pieceId: pieceId,
	                destinationSquareId: destinationSquareId
	            }),
	            dataType: 'json',
	            success: function (result) {
	            	console.log(result);
	            	if (result.type ==100){
	            		console.log("vao day");
	            		checkTypePlayer="black";
	            		let pieceId = findPieceId(result.Chess);
	            		const piece = document.getElementById(pieceId);
	            		destinationSquareId = (String.fromCharCode(97 + result.toX)+""+(8-result.toY));
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
	    				drag({target: piece, dataTransfer:dataTransfer},1);
	    				console.log(dataTransfer);
	    			    drop({currentTarget:destinationSquare, dataTransfer: dataTransfer},1);
	    			    checkTypePlayer="white";
	            	}
	            	
	            },
	            error: function (error) {
	            	 console.log(error);
	            }
			});  
        	
        }
        
        function findPieceId(i){
        	let pieceId;
        		if (i<=7&&i>=0){
        			pieceId="pawn"+ (String.fromCharCode(97 + i)+"7");
        		}else if (i==8||i==9){
        			if (i==8){
        				pieceId="rooka8";
        			}else {
        				pieceId="rookh8";
        			}
        		}else if (i==10||i==11){
        			if (i==10){
        				pieceId="knightb8";
        			}else{
        				pieceId="knightg8";
        			}
        		}else if (i==12||i==13){
        			if (i==12){
        				pieceId="bishopc8";
        			}else{
        				pieceId="bishopf8";
        			}
        		}else if(i==14){
        			pieceId="queend8";
        		}else if (i==15){
        			pieceId="kinge8"
        		}
        	return pieceId;
        }

    </script>

	<script  src="<c:url value='/template/web/ChessBoard/mainPvC.js' />"></script>
</body>
</html>