<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rank</title>
</head>
<body>
	<div class="container mx-auto px-4 py-8  h-screen">
		<div
			class="bg-white rounded-lg shadow-lg overflow-hidden h-full flex flex-col md:flex-row">
			<div
				class="w-full md:w-1/3 p-6 bg-gradient-to-br from-gray-700 to-gray-800 flex flex-col h-full">
				<h2 class="text-2xl font-bold text-white mb-6">Your Statistics</h2>

				<div class="grid grid-cols-1 sm:grid-cols-3 gap-4 mb-6">
					<div
						class="bg-gray-600 rounded-lg p-4 shadow-md transition-transform hover:scale-105">
						<h3 class="text-lg font-semibold text-gray-200 mb-2">Your
							Point</h3>
						<p class="text-3xl font-bold text-white">${RANKMODEL.elo}</p>
					</div>
					<div
						class="bg-gray-600 rounded-lg p-4 shadow-md transition-transform hover:scale-105">
						<h3 class="text-lg font-semibold text-gray-200 mb-2">Matches</h3>
						<p class="text-3xl font-bold text-white">${RANKMODEL.totalMatches}</p>
					</div>
					<div
						class="bg-gray-600 rounded-lg p-4 shadow-md transition-transform hover:scale-105">
						<h3 class="text-lg font-semibold text-gray-200 mb-2">Win Rate</h3>
						<p class="text-3xl font-bold text-green-400">${RANKMODEL.getWinRate()}%</p>
					</div>
				</div>
				<div class="bg-gray-600 rounded-lg p-6 shadow-lg flex-grow">
					<h2 class="text-2xl font-bold text-white mb-4">Overall Rank</h2>
					<div class="flex items-center justify-center">
						<div
							class="bg-gray-700 rounded-full w-36 h-36 flex items-center justify-center shadow-lg transition-transform hover:scale-110">
							<p class="text-6xl font-bold text-white">${RANKMODEL.getRankPosition()}</p>
						</div>
					</div>
					<p class="text-center mt-4 text-lg font-semibold text-gray-200">Top
						${RANKMODEL.getRankPositionPercentage()}%</p>
				</div>
			</div>
			<div
				class="w-full md:w-2/3 p-6 bg-gradient-to-br from-gray-600 to-gray-700 flex flex-col h-full">
				<h2 class="text-4xl font-bold text-white mb-6 text-center">Player
					Rankings</h2>
				<div class="w-full h-full overflow-y-auto space-y-4 pr-4">
					<c:forEach items="${PLAYERRANKING}" var="item" varStatus="status">
						<div
							class="flex justify-between items-center ${status.index % 5 == 0 ? 'bg-gray-500' : 
															          status.index % 5 == 1 ? 'bg-gray-600' : 
															          status.index % 5 == 2 ? 'bg-gray-700' : 
															          status.index % 5 == 3 ? 'bg-gray-800' : 
															          'bg-gray-900'}
           rounded-lg p-4 cursor-pointer hover:bg-gray-400 transition-colors duration-300">
							<span class="text-white text-xl font-bold flex items-center"><span
								class="bg-white text-gray-500 rounded-full w-8 h-8 flex items-center justify-center mr-3">#${status.index+1}</span>${item.fullname}</span>
							<span class="text-white text-xl font-bold">${item.elo}</span>
						</div>
					</c:forEach>
					
				</div>
			</div>
		</div>
	</div>

</body>
</html>