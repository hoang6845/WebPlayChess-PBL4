<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lịch sử nước đi</title>
</head>
<body>
    <div class="container mx-auto p-4">
        <div class="bg-gray-800 rounded-lg shadow-lg">
            <div class="border-b border-gray-200 p-4">
                <div class="flex justify-center items-center">
                    <div class="text-center">
                        <h2 class="text-2xl font-bold text-white">${HistoryMoveOfGame.whiteName} vs ${HistoryMoveOfGame.blackName}</h2>
                        <p class="text-md text-white mt-2">Classical</p>
                    </div>
                </div>
            </div>
            <div class="flex p-4">
                <div class="w-2/3 aspect-square rounded-lg max-h-[500px] flex items-center justify-center mx-32">
                    <div class="w-full max-w-[500px] aspect-square flex items-center justify-center">
                    	<%@ include file="/common/chessBoard/chessBoard.jsp"%>
                    </div>
                    <div id="deadPlace" class="hidden"></div>
                </div>

                <div class="w-1/3 ml-4">
                    <div class="bg-gray-700 rounded-lg p-4 h-full overflow-y-auto">
                        <ul class="space-y-2 overflow-y-auto max-h-[500px]" id="movesList">
                            
                        </ul>
                    </div>
                </div>
            </div>

            <div class="border-t border-gray-200 p-4">
                <div class="flex justify-center space-x-4">
 <!--                    <button class="p-2 bg-gray-100 rounded-md hover-effect text-gray-700" id="firstMove">
                        <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11 19l-7-7 7-7m8 14l-7-7 7-7" />
                        </svg>
                    </button> -->
                    <button class="p-2 bg-gray-100 rounded-md hover-effect text-gray-700" id="prevMove">
                        <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 19l-7-7 7-7" />
                        </svg>
                    </button>
                    <button class="p-2 bg-gray-100 rounded-md hover-effect text-gray-700" id="nextMove">
                        <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7" />
                        </svg>
                    </button>
 <!--                    <button class="p-2 bg-gray-100 rounded-md hover-effect text-gray-700" id="lastMove">
                        <svg class="w-6 h-6" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 5l7 7-7 7M5 5l7 7-7 7" />
                        </svg>
                    </button> -->
                </div>
            </div>
        </div>
    </div>

	<script src="<c:url value='template/web/history/main.js' />"></script>
    <script>
 //   [{"i":4,"PorC":-1,"begin":{"x":4,"y":6},"end":{"x":4,"y":4},"checkQueen":false},{"i":4,"PorC":1,"begin":{"x":4,"y":1},"end":{"x":4,"y":3},"checkQueen":false},{"i":5,"PorC":-1,"begin":{"x":5,"y":6},"end":{"x":5,"y":4},"checkQueen":false},{"i":5,"PorC":-1,"begin":{"x":5,"y":4},"end":{"x":-100,"y":-100},"checkQueen":false},{"i":4,"PorC":1,"begin":{"x":4,"y":3},"end":{"x":5,"y":4},"checkQueen":false},{"i":11,"PorC":-1,"begin":{"x":6,"y":7},"end":{"x":5,"y":5},"checkQueen":false},{"i":3,"PorC":1,"begin":{"x":3,"y":1},"end":{"x":3,"y":3},"checkQueen":false},{"i":10,"PorC":-1,"begin":{"x":1,"y":7},"end":{"x":2,"y":5},"checkQueen":false},{"i":4,"PorC":-1,"begin":{"x":4,"y":4},"end":{"x":-100,"y":-100},"checkQueen":false},{"i":3,"PorC":1,"begin":{"x":3,"y":3},"end":{"x":4,"y":4},"checkQueen":false},{"i":3,"PorC":1,"begin":{"x":4,"y":4},"end":{"x":-100,"y":-100},"checkQueen":false},{"i":10,"PorC":-1,"begin":{"x":2,"y":5},"end":{"x":4,"y":4},"checkQueen":false},{"i":12,"PorC":1,"begin":{"x":2,"y":0},"end":{"x":6,"y":4},"checkQueen":false},{"i":14,"PorC":-1,"begin":{"x":3,"y":7},"end":{"x":4,"y":6},"checkQueen":false},{"i":11,"PorC":-1,"begin":{"x":5,"y":5},"end":{"x":-100,"y":-100},"checkQueen":false},{"i":12,"PorC":1,"begin":{"x":6,"y":4},"end":{"x":5,"y":5},"checkQueen":false},{"i":10,"PorC":-1,"begin":{"x":4,"y":4},"end":{"x":5,"y":2},"checkQueen":false}]
    	console.log(`${StackMove}`);
    	let StackMoveStringified = JSON.stringify(${StackMove}); // chuyển đối tượng js thành chuỗi json
    	let StackMove = JSON.parse(StackMoveStringified);// chuyển json thành đối tượng js
    	//phải làm vậy vì ${StackMove} là đối tượng js kiểu String, stamove[0] là '[' chứ không phải {"i":4,"PorC":-1,"begin":{"x":4,"y":6},"end":{"x":4,"y":4},"checkQueen":false}
    	console.log(StackMove);
    	let movesList = document.getElementById('movesList');
    	window.onload = function(){
    		let modal = document.getElementById('modal');
    		modal.classList.add('hidden');
    		let currentIndex=0;
    		Array.from(StackMove).forEach(function(move, index){
    			if (move.end.x!=-100){
    				currentIndex++;
	    			let li = document.createElement('li');
	    			li.classList.add("flex", "justify-between", "p-2", "hover:bg-gray-600", "rounded", "move-item");
	    			li.setAttribute('data-move', currentIndex-1);
	    			li.setAttribute('index-stack', index);
	    			//div.innerHTML=``;
	    			let span = document.createElement('span');
	    			span.classList.add("text-white");
	    			if (move.PorC==1){
	    				li.classList.add("border-solid", "border-l-4", "border-black");
	    				if (move.i >= 0 && move.i <= 7) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♟ ' + (String.fromCharCode(97 + move.end.x) + "" + (8-move.end.y)); // pawn
	    				} else if (move.i === 8 || move.i === 9) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♜ ' + (String.fromCharCode(97 + move.end.x) + "" + (8-move.end.y)); // rook
	    				} else if (move.i === 10 || move.i === 11) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♞ ' + (String.fromCharCode(97 + move.end.x) + "" + (8-move.end.y)); // knight
	    				} else if (move.i === 12 || move.i === 13) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♝ ' + (String.fromCharCode(97 + move.end.x) + "" +(8-move.end.y)); // bishop
	    				} else if (move.i === 14) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♛ ' + (String.fromCharCode(97 + move.end.x) + "" + (8-move.end.y)); // queen
	    				} else if (move.i === 15) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♚ ' + (String.fromCharCode(97 + move.end.x) + "" + (8-move.end.y)); // king
	    				}
	    			}else if (move.PorC==-1){
	    				li.classList.add("border-solid", "border-l-4", "border-white");
	    				if (move.i >= 0 && move.i <= 7) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♙ ' + (String.fromCharCode(97 + move.end.x) + "" + (8-move.end.y)); // pawn
	    				} else if (move.i === 8 || move.i === 9) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♖ ' + (String.fromCharCode(97 + move.end.x) + "" + (8-move.end.y)); // rook
	    				} else if (move.i === 10 || move.i === 11) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♘ ' + (String.fromCharCode(97 + move.end.x) + "" + (8-move.end.y)); // knight
	    				} else if (move.i === 12 || move.i === 13) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♗ ' + (String.fromCharCode(97 + move.end.x) + "" + (8-move.end.y)); // bishop
	    				} else if (move.i === 14) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♕ ' + (String.fromCharCode(97 + move.end.x) + "" + (8-move.end.y)); // queen
	    				} else if (move.i === 15) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♔ ' + (String.fromCharCode(97 + move.end.x) + "" + (8-move.end.y)); // king
	    				}

	    			}
	    			li.appendChild(span);
	    			movesList.appendChild(li);
    			}else {
    				let li = document.createElement('li');
	    			li.classList.add("flex", "justify-between", "p-2", "hover:bg-gray-600", "rounded");
	    			li.setAttribute('index-stack', index);
	    			//div.innerHTML=``;
	    			let span = document.createElement('span');
	    			span.classList.add("text-white");
	    			if (move.PorC==1){
	    				li.classList.add("border-solid", "border-l-4", "border-black","hidden");
	    				if (move.i >= 0 && move.i <= 7) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♟ ' + "dead";
	    				} else if (move.i === 8 || move.i === 9) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♜ ' + "dead"; // rook
	    				} else if (move.i === 10 || move.i === 11) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♞ ' + "dead"; // knight
	    				} else if (move.i === 12 || move.i === 13) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♝ ' + "dead"; // bishop
	    				} else if (move.i === 14) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♛ ' + "dead"; // queen
	    				} else if (move.i === 15) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♚ ' + "dead"; // king
	    				}
	    			}else if (move.PorC==-1){
	    				li.classList.add("border-solid", "border-l-4", "border-white", "hidden");
	    				if (move.i >= 0 && move.i <= 7) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♙ ' + "dead"; // pawn
	    				} else if (move.i === 8 || move.i === 9) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♖ ' + "dead"; // rook
	    				} else if (move.i === 10 || move.i === 11) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♘ ' + "dead"; // knight
	    				} else if (move.i === 12 || move.i === 13) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♗ ' + "dead"; // bishop
	    				} else if (move.i === 14) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♕ ' + "dead"; // queen
	    				} else if (move.i === 15) {
	    				    span.textContent = String(currentIndex+".").padEnd(4," ") + ' ♔ ' + "dead"; // king
	    				}

	    			}
	    			li.appendChild(span);
	    			movesList.appendChild(li);
    			}
    		});
    		moves = document.querySelectorAll(".move-item");
    		totalMoves = moves.length - 1;
    	}
        let currentMove = -1;
        let moves;
        let totalMoves;

        function highlightMove(index) {
            moves.forEach(move => move.classList.remove("bg-gray-600"));
            if (index >= 0 && index <= totalMoves) {
                moves[index].classList.add("bg-gray-600");
                moves[index].scrollIntoView({ behavior: "smooth", block: "nearest" });
            }
        }

     /*    document.getElementById("firstMove").addEventListener("click", () => {
            currentMove = 0;
            highlightMove(currentMove);
        });

        document.getElementById("lastMove").addEventListener("click", () => {
            currentMove = totalMoves;
            highlightMove(currentMove);
        }); */

        document.getElementById("prevMove").addEventListener("click", () => {
            if (currentMove > 0) {
                currentMove--;
                highlightMove(currentMove);
                BoardAtPrev(currentMove,StackMove);
            }
        });

        document.getElementById("nextMove").addEventListener("click", () => {
            if (currentMove < totalMoves) {
                currentMove++;
                highlightMove(currentMove);
                BoardAtNext(currentMove,StackMove);
                setTimeout(() => {
                	if (currentMove== totalMoves){
                    	let result=`${HistoryMoveOfGame.result}`;
                    	if (result=="win"){
                    		alert("white win!");
                    	}else if(result=="lose"){
                    		alert("black win!");
                    	}else {
                    		alert("draw");
                    	}
                    }
                	}, 50);
            }
        });
        function BoardAtNext(currentMove, moveInStack){
        	console.log("NEXT");
        	let movesList = document.getElementById('movesList');
        	let listLi = movesList.querySelectorAll('li');
        	let currentLi = Array.from(listLi).find(function(element){
        		return element.getAttribute('data-move')== currentMove;
        	});
        	let beforeIndex;
        	console.log(currentLi);
        	if (currentMove>0){
	        	let beforeLi = Array.from(listLi).find(function(element){
	        		return element.getAttribute('data-move')== currentMove-1;
	        	});		
        		beforeIndex = parseInt(beforeLi.getAttribute('index-stack'), 10);
        	}else{
        		beforeIndex = -1;
        	}
        	let currentIndex = parseInt(currentLi.getAttribute('index-stack'), 10);
        	for (let i=beforeIndex+1; i<=currentIndex;i++){
        		let move =moveInStack[i];
        		console.log(move);
        		let squareIdBegin = (String.fromCharCode(97 + move.begin.x)+""+(8-move.begin.y)) ; 
        		let squareBegin = document.getElementById(squareIdBegin);
        		let squareEnd;
        		let pieceItem;
        		if (move.end.x==-100){
        			squareEnd = document.getElementById("deadPlace");
        			
        		}else{
        			let squareIdBegin = (String.fromCharCode(97 + move.end.x)+""+(8-move.end.y));
        			squareEnd = document.getElementById(squareIdBegin);
        		}
        		console.log(squareBegin);
        		console.log(squareEnd);
        		let children = squareBegin.children;
        		console.log(children);
        		for (let i = children.length-1; i >= 0; i--) {
        			if (!children[i].classList.contains('coordinate')){
        		    	console.log(children[i]);
        				pieceItem = document.getElementById(children[i].id);
        			}   
        		}
        		pieceItem.remove();
        		squareEnd.appendChild(pieceItem);
        		
        	}
        }
        
        function BoardAtPrev(currentMove, moveInStack){
        	console.log("BACK");
        	let movesList = document.getElementById('movesList');
        	let listLi = movesList.querySelectorAll('li');
        	let currentLi = Array.from(listLi).find(function(element){
        		return element.getAttribute('data-move')== currentMove;
        	});
        	let AfterLi = Array.from(listLi).find(function(element){
        		return element.getAttribute('data-move')== currentMove+1;
        	});
        	let currentIndex = parseInt(currentLi.getAttribute('index-stack'), 10);
        	let afterIndex = parseInt(AfterLi.getAttribute('index-stack'), 10);
        	for (let i=afterIndex;i>=currentIndex+1;i--){
        		let move =moveInStack[i];
        		console.log(move);
        		let pieceId = findPieceId(move.PorC,move.i);
        		pieceItem = document.getElementById(pieceId);
        		console.log(pieceItem);
        		let SquareBackId = (String.fromCharCode(97 + move.begin.x)+""+(8-move.begin.y));
        		let SquareBack = document.getElementById(SquareBackId);
        	
        		pieceItem.remove();
        		SquareBack.appendChild(pieceItem);
        	}
        }
        
        function findPieceId(index, i){
        	let pieceId;
        	if (index==1){
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
        			pieceId=="kinge8"
        		}
        	}
        	else if(index == -1){
        		if (i<=7&&i>=0){
        			pieceId="pawn"+ (String.fromCharCode(97 + i)+"2");
        		}else if (i==8||i==9){
        			if (i==8){
        				pieceId="rooka1";
        			}else {
        				pieceId="rookh1";
        			}
        		}else if (i==10||i==11){
        			if (i==10){
        				pieceId="knightb1";
        			}else{
        				pieceId="knightg1";
        			}
        		}else if (i==12||i==13){
        			if (i==12){
        				pieceId="bishopc1";
        			}else{
        				pieceId="bishopf1";
        			}
        		}else if(i==14){
        			pieceId="queend1";
        		}else if (i==15){
        			pieceId=="kinge1"
        		}
        	}
        	return pieceId;
        }
    </script>
</body>
</html>