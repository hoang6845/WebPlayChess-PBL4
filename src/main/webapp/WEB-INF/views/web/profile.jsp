<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
</head>
<body>
	<div
		class="bg-gradient-to-br from-gray-600 to-gray-700 h-877 w-1920 overflow-hidden text-white">
		<div class="p-8 h-full w-full overflow-y-auto">
			<h1 class="text-4xl font-bold text-center mb-8 text-primary">Chess
				Player Profile</h1>
			<div class="grid grid-cols-1 md:grid-cols-2 gap-8">
				<div class="space-y-6">
					<div class="relative group">
						<img id="avatar"
							src="https://images.unsplash.com/photo-1533237264985-ee62f6d342bb?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=60"
							alt="Profile Avatar"
							class="w-48 h-48 rounded-full mx-auto object-cover transition-all duration-300 group-hover:opacity-75">
						<label for="avatar-upload"
							class="absolute bottom-0 right-0 bg-white rounded-full p-2 shadow-md cursor-pointer opacity-0 group-hover:opacity-100 transition-opacity duration-300">
							<svg xmlns="http://www.w3.org/2000/svg"
								class="h-6 w-6 text-primary" fill="none" viewBox="0 0 24 24"
								stroke="currentColor">
                                <path stroke-linecap="round"
									stroke-linejoin="round" stroke-width="2"
									d="M3 9a2 2 0 012-2h.93a2 2 0 001.664-.89l.812-1.22A2 2 0 0110.07 4h3.86a2 2 0 011.664.89l.812 1.22A2 2 0 0018.07 7H19a2 2 0 012 2v9a2 2 0 01-2 2H5a2 2 0 01-2-2V9z" />
                                <path stroke-linecap="round"
									stroke-linejoin="round" stroke-width="2"
									d="M15 13a3 3 0 11-6 0 3 3 0 016 0z" />
                            </svg>
						</label> <input type="file" id="avatar-upload" class="hidden"
							accept="image/*">
					</div>
					<div class="flex items-center justify-center space-x-2">
						<h2 id="username" class="text-3xl font-semibold text-primary">ChessMaster2000</h2>
						<button class="edit-btn" onclick="editField('username')">
							<svg xmlns="http://www.w3.org/2000/svg"
								class="h-6 w-6 text-primary" fill="none" viewBox="0 0 24 24"
								stroke="currentColor">
                                <path stroke-linecap="round"
									stroke-linejoin="round" stroke-width="2"
									d="M15.232 5.232l3.536 3.536m-2.036-5.036a2.5 2.5 0 113.536 3.536L6.5 21.036H3v-3.572L16.732 3.732z" />
                            </svg>
						</button>
					</div>
					<div
						class="flex items-center justify-center space-x-2 p-4 bg-gray-50 rounded-lg shadow-md">
						<p id="bio" class="text-center text-lg text-stone-950 flex-grow">Chess
							Grandmaster with a passion for strategic gameplay.</p>
						<button class="edit-btn" onclick="editField('bio')">
							<svg xmlns="http://www.w3.org/2000/svg"
								class="h-6 w-6 text-stone-950" fill="none" viewBox="0 0 24 24"
								stroke="currentColor">
                                <path stroke-linecap="round"
									stroke-linejoin="round" stroke-width="2"
									d="M15.232 5.232l3.536 3.536m-2.036-5.036a2.5 2.5 0 113.536 3.536L6.5 21.036H3v-3.572L16.732 3.732z" />
                            </svg>
						</button>
					</div>
				</div>
				<div class="space-y-6">
					<div class="flex items-center space-x-2">
						<div class="flex items-center w-full bg-gray-100 p-3 rounded-md ">
							<svg xmlns="http://www.w3.org/2000/svg"
								class="h-6 w-6 text-primary mr-2 text-stone-950" fill="none"
								viewBox="0 0 24 24" stroke="currentColor">
                                <path stroke-linecap="round"
									stroke-linejoin="round" stroke-width="2"
									d="M8 7V3m8 4V3m-9 8h10M5 21h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v12a2 2 0 002 2z" />
                            </svg>
							<span id="join-date" class="text-lg text-gray-700 ">Joined:
								January 1, 2023</span>
						</div>
					</div>
					<div class="flex items-center bg-gray-100 p-3 rounded-md">
						<svg xmlns="http://www.w3.org/2000/svg"
							class="h-6 w-6 text-primary mr-2 text-stone-950" fill="none"
							viewBox="0 0 24 24" stroke="currentColor">
                            <path stroke-linecap="round"
								stroke-linejoin="round" stroke-width="2"
								d="M3 8l7.89 5.26a2 2 0 002.22 0L21 8M5 19h14a2 2 0 002-2V7a2 2 0 00-2-2H5a2 2 0 00-2 2v10a2 2 0 002 2z" />
                        </svg>
						<span id="email" class="text-lg text-stone-950">chessmaster@example.com</span>
					</div>
					<div class="mt-8">
						<button onclick="showChangePasswordModal()"
							class="w-full px-6 py-3 bg-primary text-white font-semibold rounded-md shadow-md hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-primary focus:ring-offset-2 transition-all duration-300 bg-gray-500">Change
							Password</button>
					</div>
				</div>
			</div>

			<!-- Match History Table -->
			<div class="mt-12">
				<h2 class="text-2xl font-bold mb-4 text-primary">Match History</h2>
				<div class="overflow-x-auto max-h-96 overflow-y-auto">
					<table class="min-w-full bg-white rounded-lg overflow-hidden">
						<thead class="bg-gray-100 sticky top-0">
							<tr>
								<th
									class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Date</th>
								<th
									class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Opponent</th>
								<th
									class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Result</th>
								<th
									class="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ELO
									Change</th>
							</tr>
						</thead>
						<tbody class="divide-y divide-gray-200">
							<c:forEach var="item" items="${listHistory}">
								<tr>
									<td class="px-6 py-4 whitespace-nowrap text-stone-950">${item.createDate}</td>
									<td class="px-6 py-4 whitespace-nowrap text-stone-950">${item.opponentName}</td>
									<td class="px-6 py-4 whitespace-nowrap text-stone-950">${item.getResult()}</td>
									<td
									class="px-6 py-4 whitespace-nowrap <c:if test="${item.eloChange > 0}">text-green-600</c:if>
																	   <c:if test="${item.eloChange < 0}">text-red-600</c:if>
														               <c:if test="${item.eloChange == 0}"> text-gray-600</c:if>">
														               ${item.eloChange} 
														               </td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

	<!-- Change Password Modal -->
	<div id="changePasswordModal"
		class="fixed inset-0 bg-gray-600 bg-opacity-50 overflow-y-auto h-full w-full hidden">
		<div
			class="relative top-20 mx-auto p-5 border w-96 shadow-lg rounded-md bg-white">
			<div class="mt-3 text-center">
				<h3 class="text-lg leading-6 font-medium text-gray-900">Change
					Password</h3>
				<div class="mt-2 px-7 py-3">
					<input type="password" id="current-password"
						placeholder="Current Password"
						class="mt-1 block w-full rounded-md border-gray-300 shadow-sm focus:border-primary focus:ring focus:ring-primary focus:ring-opacity-50">
					<input type="password" id="new-password" placeholder="New Password"
						class="mt-4 block w-full rounded-md border-gray-300 shadow-sm focus:border-primary focus:ring focus:ring-primary focus:ring-opacity-50">
					<input type="password" id="confirm-password"
						placeholder="Confirm New Password"
						class="mt-4 block w-full rounded-md border-gray-300 shadow-sm focus:border-primary focus:ring focus:ring-primary focus:ring-opacity-50">
				</div>
				<div class="items-center px-4 py-3">
					<button id="changePasswordBtn"
						class="px-4 py-2 bg-primary text-white text-base font-medium rounded-md w-full shadow-sm hover:bg-opacity-90 focus:outline-none focus:ring-2 focus:ring-primary focus:ring-offset-2">Change
						Password</button>
				</div>
				<div class="items-center px-4 py-3">
					<button id="cancelBtn" onclick="hideChangePasswordModal()"
						class="px-4 py-2 bg-gray-500 text-white text-base font-medium rounded-md w-full shadow-sm hover:bg-gray-700 focus:outline-none focus:ring-2 focus:ring-gray-300">Cancel</button>
				</div>
			</div>
		</div>
	</div>

	<script>
		function editField(fieldId) {
			const element = document.getElementById(fieldId);
			const currentValue = element.innerText;
			const input = document.createElement('input');
			input.value = currentValue;
			input.className = element.className;
			element.replaceWith(input);
			input.focus();

			input.addEventListener('blur', function() {
				const newValue = input.value;
				const newElement = document.createElement(element.tagName);
				newElement.id = fieldId;
				newElement.innerText = newValue;
				newElement.className = element.className;
				input.replaceWith(newElement);
			});
		}

		function showChangePasswordModal() {
			document.getElementById('changePasswordModal').classList
					.remove('hidden');
		}

		function hideChangePasswordModal() {
			document.getElementById('changePasswordModal').classList
					.add('hidden');
		}

		document.getElementById('changePasswordBtn')
				.addEventListener(
						'click',
						function() {
							const currentPassword = document
									.getElementById('current-password').value;
							const newPassword = document
									.getElementById('new-password').value;
							const confirmPassword = document
									.getElementById('confirm-password').value;

							if (currentPassword === '') {
								alert('Please enter your current password!');
								return;
							}

							if (newPassword !== confirmPassword) {
								alert('New passwords do not match!');
								return;
							}

							// Here you would typically send a request to your server to change the password
							// For this example, we'll just show an alert
							alert('Password changed successfully!');
							hideChangePasswordModal();
						});
	</script>
</body>