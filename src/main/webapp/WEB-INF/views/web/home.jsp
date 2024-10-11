<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
</head>
<body >
    <main class="container mx-auto px-4">
        <div class="grid md:grid-cols-2 gap-8">
            <div class="bg-white rounded-lg shadow-lg p-6 transform hover:scale-105 transition duration-300 relative overflow-hidden">
                <div class="absolute -top-10 -left-10 w-40 h-40 bg-gray-200 rounded-full opacity-50 z-0"></div>
                <h2 class="text-2xl font-bold mb-4 text-gray-600 relative z-10 flex items-center">
                    <div class="w-12 h-12 mr-4 relative">
                        <div class="absolute inset-0 bg-gradient-to-br from-gray-400 to-gray-600 rounded-lg shadow-inner transform rotate-45"></div>
                        <i class="fas fa-chess-knight text-white absolute inset-0 flex items-center justify-center text-2xl"></i>
                    </div>
                    Play with Computer
                </h2>
                <p class="mb-4 text-gray-600 relative z-10">Choose your difficulty:</p>
                <div class="flex flex-col space-y-4 relative z-10">
                    <div class="flex items-center justify-between bg-gray-100 p-3 rounded-lg cursor-pointer transform transition duration-300 hover:bg-gray-200 hover:shadow-md">
                        <span class="text-lg font-semibold">Easy</span>
                        <div class="flex items-center">
                            <i class="fas fa-star text-yellow-400 text-3xl"></i>
                        </div>
                    </div>
                    <div class="flex items-center justify-between bg-gray-100 p-3 rounded-lg cursor-pointer transform transition duration-300 hover:bg-gray-200 hover:shadow-md">
                        <span class="text-lg font-semibold">Medium</span>
                        <div class="flex items-center">
                            <i class="fas fa-star text-yellow-400 text-3xl mr-1"></i>
                            <i class="fas fa-star text-yellow-400 text-3xl mr-1"></i>
                            <i class="fas fa-star text-yellow-400 text-3xl"></i>
                        </div>
                    </div>
                    <div class="flex items-center justify-between bg-gray-100 p-3 rounded-lg cursor-pointer transform transition duration-300 hover:bg-gray-200 hover:shadow-md">
                        <span class="text-lg font-semibold">Hard</span>
                        <div class="flex items-center">
                            <i class="fas fa-star text-yellow-400 text-3xl mr-1"></i>
                            <i class="fas fa-star text-yellow-400 text-3xl mr-1"></i>
                            <i class="fas fa-star text-yellow-400 text-3xl mr-1"></i>
                            <i class="fas fa-star text-yellow-400 text-3xl mr-1"></i>
                            <i class="fas fa-star text-yellow-400 text-3xl"></i>
                        </div>
                    </div>
                </div>
            </div>

            <div class="bg-white rounded-lg shadow-lg p-6 transform hover:scale-105 transition duration-300 relative overflow-hidden">
                <div class="absolute -top-10 -right-10 w-40 h-40 bg-gray-200 rounded-full opacity-50 z-0"></div>
                <h2 class="text-2xl font-bold mb-4 text-gray-600 relative z-10 flex items-center">
                    <div class="w-12 h-12 mr-4 relative">
                        <div class="absolute inset-0 bg-gradient-to-br from-gray-400 to-gray-600 rounded-lg shadow-inner transform rotate-45"></div>
                        <i class="fas fa-chess-board text-white absolute inset-0 flex items-center justify-center text-2xl"></i>
                    </div>
                    Play Online
                </h2>
                <div class="space-y-6 relative z-10">
                    <div>
                        <h3 class="text-lg font-semibold mb-2 text-gray-700">Quick Play</h3>
                        <button class="w-full bg-gradient-to-r from-gray-400 to-gray-600 text-white py-2 rounded-lg hover:from-gray-500 hover:to-gray-700 transition duration-300 shadow-md transform hover:scale-105" >Find Match</button>
                    </div>
                    <div>
                        <h3 class="text-lg font-semibold mb-2 text-gray-700">Join Table</h3>
                        <div class="space-y-2 max-h-36 overflow-y-auto">
                            <div class="flex justify-between items-center bg-gray-100 p-2 rounded-lg shadow cursor-pointer transform transition duration-300 hover:bg-gray-200 hover:shadow-md">
                                <span>Table #1 (1750+)</span>
                                <button onclick="chuyenTrang('<c:url value="/PvP"></c:url>')" class="bg-gradient-to-r from-gray-400 to-gray-600 text-white px-4 py-1 rounded-lg hover:from-gray-500 hover:to-gray-700 transition duration-300 shadow transform hover:scale-105">Join</button>
                            </div>
                            <div class="flex justify-between items-center bg-gray-100 p-2 rounded-lg shadow cursor-pointer transform transition duration-300 hover:bg-gray-200 hover:shadow-md">
                                <span>Table #2 (1500-1800)</span>
                                <button class="bg-gradient-to-r from-gray-400 to-gray-600 text-white px-4 py-1 rounded-lg hover:from-gray-500 hover:to-gray-700 transition duration-300 shadow transform hover:scale-105">Join</button>
                            </div>
                            <div class="flex justify-between items-center bg-gray-100 p-2 rounded-lg shadow cursor-pointer transform transition duration-300 hover:bg-gray-200 hover:shadow-md">
                                <span>Table #3 (Open)</span>
                                <button class="bg-gradient-to-r from-gray-400 to-gray-600 text-white px-4 py-1 rounded-lg hover:from-gray-500 hover:to-gray-700 transition duration-300 shadow transform hover:scale-105">Join</button>
                            </div>
                        </div>
                    </div>
                    <div>
                        <h3 class="text-lg font-semibold mb-2 text-gray-700">Create Table</h3>
                        <button class="w-full bg-gradient-to-r from-gray-400 to-gray-600 text-white py-2 rounded-lg hover:from-gray-500 hover:to-gray-700 transition duration-300 shadow-md transform hover:scale-105">Create New Table</button>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <script type="text/javascript">
         function chuyenTrang(url) {
        window.location.assign(url); // Chuyển hướng đến trang_moi.html
    }
    </script>
</body>
</html>