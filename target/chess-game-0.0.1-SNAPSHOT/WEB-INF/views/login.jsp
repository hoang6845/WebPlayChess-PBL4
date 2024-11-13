<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="RegisterUrl" value="/dang-ky"/>
<c:url var="LoginUrl" value="/dang-nhap"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Đăng nhập</title>
    <script src='<c:url value='/template/web/bootstrap/js/3.4.5'></c:url>'></script>
    <style>
        body {
            background-image: url("https://images.pexels.com/photos/131616/pexels-photo-131616.jpeg");
            background-size: cover;
            background-repeat: no-repeat;
        }
    </style>
    
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
	<c:if test="${not empty message}">
			<div class="alert alert-${alert} bem" role="alert">${message}</div>
		</c:if>
    <div class="container mx-auto flex bg-white rounded-xl shadow-lg overflow-hidden max-w-5xl">
        
        <div class="w-1/2 bg-gray-800 p-8 flex items-center justify-center">
            <div id="chessBoard" class="grid grid-cols-8 gap-0.5 w-64 h-64">
               
            </div>
        </div>

       
        <div class="w-1/2 p-8">
           
            <form action='<c:url value ='/dang-nhap'></c:url>' method="POST" id="loginForm" class="space-y-6">
                <h2 class="text-3xl font-bold text-center text-gray-800 mb-8">Login to Chess Game</h2>
                <div>
                    <label for="loginUsername" class="block text-sm font-medium text-gray-700">Username</label>
                    <input type="text" id="loginUsername" name="username" required class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
                </div>
                <div>
                    <label for="loginPassword" class="block text-sm font-medium text-gray-700">Password</label>
                    <input type="password" id="loginPassword" name="password" required class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
                </div>
                <div>
                    <input type="submit" value="Đăng nhập" class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500"></input>
                	<input type="hidden" name="action" value="login">
                </div>
                
            </form>

         
            <form id="registerForm" action='<c:url value="/dang-ky"></c:url>' method="POST" class="space-y-6 hidden">
                <h2 class="text-3xl font-bold text-center text-gray-800 mb-8">Register for Chess Game</h2>
                <div>
                    <label for="email" class="block text-sm font-medium text-gray-700">Email</label>
                    <input type="email" id="email" name="email" required class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
                </div>
                <div>
                    <label for="registerUsername" class="block text-sm font-medium text-gray-700">Username</label>
                    <input type="text" id="registerUsername" name="username" required class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
                </div>
                <div>
                    <label for="registerPassword" class="block text-sm font-medium text-gray-700">Password</label>
                    <input type="password" id="registerPassword" name="password" required class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
                </div>
                  <div class="relative">
                    <label for="confirmPassword" class="block text-sm font-medium text-gray-700">Confirm password</label>
                    <input type="password" id="confirmPassword" name="confirmpassword" required class="mt-1 block w-full px-3 py-2 bg-white border border-gray-300 rounded-md shadow-sm focus:outline-none focus:ring-indigo-500 focus:border-indigo-500">
                		<i class="fa-solid fa-x absolute hidden" id="password-error" style="top: 42px; right: -24px;"></i>
                </div>
                <div>
                    <input type="submit" class="w-full flex justify-center py-2 px-4 border border-transparent rounded-md shadow-sm text-sm font-medium text-white bg-green-600 hover:bg-green-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-green-500" value="Register" id="registerBtn">
                </div>
                	<input type="hidden" name="action" value="register">
            </form>

            <div class="mt-4 text-center">
                <button id="toggleForm" class="text-sm text-indigo-600 hover:text-indigo-500">New user? Register here</button>
            </div>
        </div>
    </div>

    <script>

        const chessBoard = document.getElementById('chessBoard');
        const pieces = ['♜', '♞', '♝', '♛', '♚', '♝', '♞', '♜', '♟', ''];
        let currentPosition = 0;

        function createChessBoard() {
            for (let i = 0; i < 64; i++) {
                const square = document.createElement('div');
                square.classList.add('w-8', 'h-8', 'flex', 'items-center', 'justify-center', 'text-2xl');
                square.classList.add((i + Math.floor(i / 8)) % 2 === 0 ? 'bg-gray-300' : 'bg-gray-600');
                chessBoard.appendChild(square);
            }
        }

        function movePieces() {
            const squares = chessBoard.children;
            for (let i = 0; i < 64; i++) {
                const pieceIndex = (i + currentPosition) % pieces.length;
                squares[i].textContent = pieces[pieceIndex];
                squares[i].classList.toggle('text-white', pieceIndex < 10);
            }
            currentPosition = (currentPosition + 1) % pieces.length;
        }

        createChessBoard();
        setInterval(movePieces, 1000);


        const loginForm = document.getElementById('loginForm');
        const registerForm = document.getElementById('registerForm');
        const toggleFormBtn = document.getElementById('toggleForm');

        toggleFormBtn.addEventListener('click', () => {
            loginForm.classList.toggle('hidden');
            registerForm.classList.toggle('hidden');
            if (loginForm.classList.contains('hidden')) {
                toggleFormBtn.textContent = 'Already have an account? Login here';
            } else {
                toggleFormBtn.textContent = 'New user? Register here';
            }
        });
        
        $(document).ready(function(){
        	$('#confirmPassword').on('input',function(){
        		let password = $('#registerPassword').val();
        		let confirmPass = $('#confirmPassword').val();
        		if (password != confirmPass){
        			$('#password-error').attr('class','fa-solid fa-x absolute text-red-600');
        		}else {
        			$('#password-error').attr('class','fa-solid fa-check absolute text-green-600');
        		}
        	});
        });
        
        $('#registerBtn').on('click',function(event){
        	event.preventDefault();
        	if ($('#registerPassword').val()==$('#confirmPassword').val()){
				$.ajax({
					url: '${RegisterUrl}',
					type: 'POST',
		            contentType: 'application/json',
		            data: JSON.stringify({
		            	type: "register",
		                username: $('#registerUsername').val(),
		                password: $('#registerPassword').val(),
		                email: $('#email').val()
		            }),
		            dataType: 'json',
		            success: function (result) {
		            	console.log(result);
		            	if (result.type =="register"){
		            		if (result.result=="success"){
		            			 window.location.href = "${LoginUrl}?action=login&message=register_success&alert=success";
		            		}
		            		else{
		            			$('body').append('<div class="alert alert-danger bem" role="alert">Account already exists</div>');
		            		}
		            	}
		            	
		            },
		            error: function (error) {
		            	 console.log(error);
		            }
				})        		
        	}else{
        		console.log("nhập sai pass & confimpass");
        	}
        })
        
    </script>
</body>
</html>