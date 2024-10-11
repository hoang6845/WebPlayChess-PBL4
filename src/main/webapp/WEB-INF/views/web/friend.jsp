<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="Friendurl" value="/friend"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Friend</title>
</head>
<body class="bg-background text-white">
 <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        primary: "#4b5563",
                        secondary: "#5f6c80",
                        tertiary: "#374151",
                        quaternary: "#6b7280",
                        background: "#e5e7eb",
                        accent1: "#ef4444",
                        accent2: "#fbbf24"
                    }
                }
            }
        }
    </script>

 <div class="container mx-auto px-4 py-8">
 <div class="mb-8">
            <h2 class="text-2xl font-semibold text-white mb-4">Find Friends</h2>
            <div class="relative">
            	<form action='<c:url value='/friend'></c:url>' method="POST" id="searchFriend">
            	<input type=hidden name="page" value="friend">
                <input type="text" id="friendSearch" name="friendName" placeholder="Enter friend's name" class="w-full p-2 rounded-md border-2 border-tertiary focus:outline-none focus:ring-2 focus:ring-tertiary bg-quaternary text-white placeholder-gray-300">
                <input type="submit" class="absolute right-0 top-0 bottom-0 bg-accent1 text-white px-4 py-2 rounded-r-md hover:bg-red-600 transition duration-300" value="Search"></input>            	
            	</form>
            </div>
            <ul id="searchSuggestions" class="mt-2 bg-quaternary rounded-md shadow-lg hidden"></ul>
            <ul id="searchResults" class="mt-4 space-y-2 max-h-48 overflow-y-auto bg-secondary rounded-md p-3 text-base"></ul>
        </div>
 
        <div class="grid grid-cols-1 md:grid-cols-2 gap-8">
            <div class="bg-secondary rounded-lg shadow-md p-6">
                <h2 class="text-2xl font-semibold text-primary mb-4 text-white">Friend Requests</h2>
                <ul class="space-y-4 h-64 overflow-y-auto">
                	<c:forEach var="item" items="${USERMODEL.getFriendList() }">
                		<c:if test="${item.status == 'pending' }">
                			<li class="flex items-center justify-between p-2 rounded-md transition duration-300 hover:bg-gray-600 cursor-pointer">
                        <div class="flex items-center">
                            <img src="https://images.unsplash.com/photo-1518020382113-a7e8fc38eac9?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=100&q=80" alt="Alice" class="w-12 h-12 rounded-full mr-4 transition-transform duration-300 hover:scale-110">
                            <span class="font-medium text-white">${item.getNameFriend()}</span>
                        </div>
                        <div>
                            <button class="bg-green-400 text-white px-3 py-1 rounded-md mr-2 hover:bg-opacity-80 transition duration-300 active:scale-95" aria-label="Accept friend request from Alice">Accept</button>
                            <button class="bg-red-400 text-white px-3 py-1 rounded-md hover:bg-red-500 transition duration-300 active:scale-95" aria-label="Reject friend request from Alice">Reject</button>
                        </div>
                    </li>
                		</c:if>
                	</c:forEach>
                    
                    <!-- Add more friend requests here to test scrolling -->
                </ul>
            </div>
            
            <div class="bg-secondary rounded-lg shadow-md p-6">
                <h2 class="text-2xl font-semibold text-primary mb-4 text-white">Friend List</h2>
                <ul class="space-y-4 h-64 overflow-y-auto">
                	<c:forEach var="item" items="${USERMODEL.getFriendList() }">
                		<c:if test="${item.status == 'accepted' }">
                			 <li class="flex items-center justify-between hover:bg-gray-600 p-2 rounded-md transition duration-300 cursor-pointer">
                        <div class="flex items-center">
                            <img src="https://images.unsplash.com/photo-1645378999013-95abebf5f3c1?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=100&q=80" alt="Charlie" class="w-12 h-12 rounded-full mr-4 transition-transform duration-300 hover:scale-110">
                            <span class="font-medium text-white">${item.getNameFriend()}</span>
                        </div>
                        <div>
                            <button class="bg-green-300 text-white px-3 py-1 rounded-md mr-2 hover:bg-opacity-80 transition duration-300 active:scale-95" aria-label="View Charlie's profile">Profile</button>
                            <button class="bg-yellow-400 text-white px-3 py-1 rounded-md mr-2 hover:bg-yellow-500 transition duration-300 active:scale-95" aria-label="Challenge Charlie to a game">Challenge</button>
                            <button class="bg-gray-400 text-white px-3 py-1 rounded-md hover:bg-gray-500 transition duration-300 active:scale-95" aria-label="Unfriend Charlie" onclick="showUnfriendConfirmation('Charlie')">Unfriend</button>
                        </div>
                    </li>
                		</c:if>
                	</c:forEach>
                	
                   
                    <!-- Add more friends here to test scrolling -->
                </ul>
            </div>
        </div>
    </div>

    <!-- Unfriend Confirmation Modal -->
    <div id="unfriendModal" class="fixed inset-0 bg-black bg-opacity-50 hidden items-center justify-center">
        <div class="bg-white p-6 rounded-lg shadow-xl">
            <h3 class="text-xl font-bold text-gray-900 mb-4">Xác nhận xóa bạn</h3>
            <p class="text-gray-700 mb-4">Bạn có chắc muốn xóa <span id="friendName" class="font-semibold"></span> khỏi danh sách bạn bè?</p>
            <div class="flex justify-end space-x-4">
                <button onclick="closeUnfriendModal()" class="px-4 py-2 bg-gray-300 text-gray-800 rounded hover:bg-gray-400 transition duration-300">Không</button>
                <button onclick="unfriendConfirmed()" class="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600 transition duration-300">Có, xóa bạn</button>
            </div>
        </div>
    </div>

    <script>
    	$('#searchFriend').on('submit',function(e){
    	    e.preventDefault(); 
    	    
    	    const friendNameSearch={dataStr: $('#friendSearch').val()};
    	    console.log(friendNameSearch);
    	    searchFriend(friendNameSearch);
    
    	})
    	
    	function searchFriend(data) {
        $.ajax({
            url: '${Friendurl}',
            type: 'POST',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (results) {
            	console.log(results);
                results.forEach(result => {
                	console.log(result);
                	console.log(typeof result.nameFriend); // In ra kiểu dữ liệu
                	const name = result.nameFriend ||"Tên không xác định";
                	console.log(name);
                	const test=result.status;
                	console.log(test);
                    const li=document.createElement('li');
                    li.className = "flex items-center justify-between bg-secondary p-2 rounded-md border border-tertiary";
                    li.innerHTML = `
                        <div class="flex items-center">
                            <img src="https://images.unsplash.com/photo-1518020382113-a7e8fc38eac9?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=100&q=80" alt="${result.nameFriend}" class="w-10 h-10 rounded-full mr-3">
                            <span class="text-white"></span>
                            
                        </div>                    
                    `;
                    if (result.status == null){
                        li.innerHTML +=` 
                    	<button /* onclick="addFriend('${result.nameFriend}', this)" */ class="bg-blue-400 text-white px-3 py-1 rounded-md hover:bg-blue-600 transition duration-300">Add Friend</button>
					`;
                    }else if (result.status == 'pending'){
                    	 li.innerHTML +=` 
                    	 <div>
                         	<button class="bg-green-400 text-white px-3 py-1 rounded-md hover:bg-green-600 transition duration-300">Chấp nhận</button>
                         	<button class="bg-accent1 text-white px-3 py-1 rounded-md hover:bg-red-600 transition duration-300">Từ chối</button>
     					<div>
                         	`;
                    }else if (result.status == 'accepted'){
                    	 li.innerHTML +=`
                    	 <div>
                          	<button  class="bg-gray-400 text-white px-3 py-1 rounded-md hover:bg-gray-500 transition duration-300">Bạn bè  	<i class="fa-solid fa-user-group"></i></button>
                          	<button  class="bg-yellow-400 text-white px-3 py-1 rounded-md hover:bg-yellow-500 transition duration-300">Thách đấu</button>
                       	 <div>
                          	`;
                    }else if (result.status == 'pended'){
                    	 li.innerHTML +=` 
                    	 <div>
                         	<button class="bg-gray-500 text-white px-3 py-1 rounded-md transition duration-300 cursor-not-allowed" disables>Đã gửi lời mời kết bạn</button>
                         
     					<div>
                         	`;
                    }
                    const span = li.querySelector('span');
        			span.textContent = result.nameFriend;;
        			
                    searchResults.appendChild(li); 
                    /* var h2 = document.createElement("h2");	

                    h2.innerHTML = result.nameFriend;
                    searchResults.appendChild(h2);  */
                });
            	
            },
            error: function (error) {
            	//window.location.href = "${NewURL}?type=list&maxPageItem=2&page=1&message=error_system";
            	 console.log(error);
            }
        });
    }
    
        let currentFriend = '';

        function showUnfriendConfirmation(friendName) {
            currentFriend = friendName;
            document.getElementById('friendName').textContent = friendName;
            document.getElementById('unfriendModal').classList.remove('hidden');
            document.getElementById('unfriendModal').classList.add('flex');
        }

        function closeUnfriendModal() {
            document.getElementById('unfriendModal').classList.add('hidden');
            document.getElementById('unfriendModal').classList.remove('flex');
        }

        function unfriendConfirmed() {
            // Here you would typically make an API call to unfriend the user
            console.log(`Unfriended ${currentFriend}`);
            // For demonstration, let's just close the modal
            closeUnfriendModal();
            // You might want to remove the friend from the list or update the UI here
        }
        /* function searchFriends() {
            const searchTerm = document.getElementById('friendSearch').value.toLowerCase();
            const searchResults = document.getElementById('searchResults');
            searchResults.innerHTML = '';
       

            const filteredResults = mockResults.filter(result => result.name.toLowerCase().includes(searchTerm));

            filteredResults.forEach(result => {
                const li = document.createElement('li');
                li.className = 'flex items-center justify-between bg-secondary p-2 rounded-md border border-tertiary';
                li.innerHTML = `
                    <div class="flex items-center">
                        <img src="${result.img}" alt="${result.name}" class="w-10 h-10 rounded-full mr-3">
                        <span class="text-white">${result.name}</span>
                    </div>
                    <button onclick="addFriend('${result.name}', this)" class="bg-accent1 text-white px-3 py-1 rounded-md hover:bg-red-600 transition duration-300">Add Friend</button>
                `;
                searchResults.appendChild(li);
            });

            if (filteredResults.length === 0) {
                searchResults.innerHTML = '<li class="text-center text-white">No results found</li>';
            }
        } */

        function addFriend(name, button) {
            console.log(`Friend request sent to ${name}`);
            button.textContent = "Đã gửi lời mời kết bạn";
            button.classList.remove("bg-blue-400", "hover:bg-blue-600");
            button.classList.add("bg-gray-500", "cursor-not-allowed");
            button.disabled = true;
        }

        
        document.getElementById('friendSearch').addEventListener('input', function(e) {
            const searchTerm = e.target.value.toLowerCase();
            const suggestions = document.getElementById('searchSuggestions');
            suggestions.innerHTML = '';

            if (searchTerm.length > 0) {
                const matches = friendSuggestions.filter(name => name.toLowerCase().startsWith(searchTerm));
                if (matches.length > 0) {
                    matches.forEach(name => {
                        const li = document.createElement('li');
                        li.textContent = name;
                        li.className = 'p-2 hover:bg-tertiary cursor-pointer';
                        li.onclick = function() {
                            document.getElementById('friendSearch').value = name;
                            suggestions.innerHTML = '';
                            suggestions.classList.add('hidden');
                            searchFriends();
                        };
                        suggestions.appendChild(li);
                    });
                    suggestions.classList.remove('hidden');
                } else {
                    suggestions.classList.add('hidden');
                }
            } else {
                suggestions.classList.add('hidden');
            }
        });

        document.addEventListener('click', function(e) {
            if (e.target.id !== 'friendSearch') {
                document.getElementById('searchSuggestions').classList.add('hidden');
            }
        });
    </script>
    
    
    <script>
        tailwind.config = {
            theme: {
                extend: {
                    colors: {
                        primary: "#4b5563",
                        secondary: "#5f6c80",
                        tertiary: "#374151",
                        quaternary: "#6b7280",
                        background: "#e5e7eb",
                        accent1: "#ef4444",
                        accent2: "#fbbf24"
                    }
                }
            }
        }
    </script>


</body>
</html>