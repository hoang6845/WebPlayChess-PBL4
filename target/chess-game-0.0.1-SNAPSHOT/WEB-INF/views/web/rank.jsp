<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rank</title>
</head>
<body >
 <div class="container mx-auto px-4 py-8 h-full">
        <div class="bg-white rounded-lg shadow-lg overflow-hidden h-full flex flex-col md:flex-row">
            <div class="w-full md:w-1/3 p-6 bg-gradient-to-br from-gray-700 to-gray-800 flex flex-col h-full">
                <h2 class="text-2xl font-bold text-white mb-6">Your Statistics</h2>
                <div class="grid grid-cols-1 sm:grid-cols-3 gap-4 mb-6">
                    <div class="bg-gray-600 rounded-lg p-4 shadow-md transition-transform hover:scale-105">
                        <h3 class="text-lg font-semibold text-gray-200 mb-2">Your Rank</h3>
                        <p class="text-3xl font-bold text-white">1,234</p>
                    </div>
                    <div class="bg-gray-600 rounded-lg p-4 shadow-md transition-transform hover:scale-105">
                        <h3 class="text-lg font-semibold text-gray-200 mb-2">Matches</h3>
                        <p class="text-3xl font-bold text-white">567</p>
                    </div>
                    <div class="bg-gray-600 rounded-lg p-4 shadow-md transition-transform hover:scale-105">
                        <h3 class="text-lg font-semibold text-gray-200 mb-2">Win Rate</h3>
                        <p class="text-3xl font-bold text-green-400">56.6%</p>
                    </div>
                </div>
                <div class="bg-gray-600 rounded-lg p-6 shadow-lg flex-grow">
                    <h2 class="text-2xl font-bold text-white mb-4">Overall Rank</h2>
                    <div class="flex items-center justify-center">
                        <div class="bg-gray-700 rounded-full w-36 h-36 flex items-center justify-center shadow-lg transition-transform hover:scale-110">
                            <p class="text-6xl font-bold text-white">78</p>
                        </div>
                    </div>
                    <p class="text-center mt-4 text-lg font-semibold text-gray-200">Top 10%</p>
                </div>
            </div>
            <div class="w-full md:w-2/3 p-6 bg-gradient-to-br from-gray-600 to-gray-700 flex flex-col h-full">
                <h2 class="text-4xl font-bold text-white mb-6 text-center">Player Rankings</h2>
                <div class="w-full h-full overflow-y-auto space-y-4 pr-4">
                    <div class="flex justify-between items-center bg-gray-500 rounded-lg p-4 cursor-pointer hover:bg-gray-400 transition-colors duration-300">
                        <span class="text-white text-xl font-bold flex items-center"><span class="bg-white text-gray-500 rounded-full w-8 h-8 flex items-center justify-center mr-3">#1</span>Magnus Carlsen</span>
                        <span class="text-white text-xl font-bold">2842</span>
                    </div>
                    <div class="flex justify-between items-center bg-gray-600 rounded-lg p-4 cursor-pointer hover:bg-gray-500 transition-colors duration-300">
                        <span class="text-white text-xl font-bold flex items-center"><span class="bg-white text-gray-600 rounded-full w-8 h-8 flex items-center justify-center mr-3">#2</span>Ding Liren</span>
                        <span class="text-white text-xl font-bold">2788</span>
                    </div>
                    <div class="flex justify-between items-center bg-gray-700 rounded-lg p-4 cursor-pointer hover:bg-gray-600 transition-colors duration-300">
                        <span class="text-white text-xl font-bold flex items-center"><span class="bg-white text-gray-700 rounded-full w-8 h-8 flex items-center justify-center mr-3">#3</span>Ian Nepomniachtchi</span>
                        <span class="text-white text-xl font-bold">2782</span>
                    </div>
                    <div class="flex justify-between items-center bg-gray-800 rounded-lg p-4 cursor-pointer hover:bg-gray-700 transition-colors duration-300">
                        <span class="text-white text-xl font-bold flex items-center"><span class="bg-white text-gray-800 rounded-full w-8 h-8 flex items-center justify-center mr-3">#4</span>Alireza Firouzja</span>
                        <span class="text-white text-xl font-bold">2759</span>
                    </div>
                    <div class="flex justify-between items-center bg-gray-900 rounded-lg p-4 cursor-pointer hover:bg-gray-800 transition-colors duration-300">
                        <span class="text-white text-xl font-bold flex items-center"><span class="bg-white text-gray-900 rounded-full w-8 h-8 flex items-center justify-center mr-3">#5</span>Fabiano Caruana</span>
                        <span class="text-white text-xl font-bold">2755</span>
                    </div>
                    <div class="flex justify-between items-center bg-gray-500 rounded-lg p-4 cursor-pointer hover:bg-gray-400 transition-colors duration-300">
                        <span class="text-white text-xl font-bold flex items-center"><span class="bg-white text-gray-500 rounded-full w-8 h-8 flex items-center justify-center mr-3">#6</span>Wesley So</span>
                        <span class="text-white text-xl font-bold">2753</span>
                    </div>
                    <div class="flex justify-between items-center bg-gray-600 rounded-lg p-4 cursor-pointer hover:bg-gray-500 transition-colors duration-300">
                        <span class="text-white text-xl font-bold flex items-center"><span class="bg-white text-gray-600 rounded-full w-8 h-8 flex items-center justify-center mr-3">#7</span>Hikaru Nakamura</span>
                        <span class="text-white text-xl font-bold">2750</span>
                    </div>
                    <div class="flex justify-between items-center bg-gray-700 rounded-lg p-4 cursor-pointer hover:bg-gray-600 transition-colors duration-300">
                        <span class="text-white text-xl font-bold flex items-center"><span class="bg-white text-gray-700 rounded-full w-8 h-8 flex items-center justify-center mr-3">#8</span>Leinier Dominguez Perez</span>
                        <span class="text-white text-xl font-bold">2745</span>
                    </div>
                    <div class="flex justify-between items-center bg-gray-800 rounded-lg p-4 cursor-pointer hover:bg-gray-700 transition-colors duration-300">
                        <span class="text-white text-xl font-bold flex items-center"><span class="bg-white text-gray-800 rounded-full w-8 h-8 flex items-center justify-center mr-3">#9</span>Anish Giri</span>
                        <span class="text-white text-xl font-bold">2740</span>
                    </div>
                    <div class="flex justify-between items-center bg-gray-900 rounded-lg p-4 cursor-pointer hover:bg-gray-800 transition-colors duration-300">
                        <span class="text-white text-xl font-bold flex items-center"><span class="bg-white text-gray-900 rounded-full w-8 h-8 flex items-center justify-center mr-3">#10</span>Maxime Vachier-Lagrave</span>
                        <span class="text-white text-xl font-bold">2735</span>
                    </div>
                </div>
            </div>
        </div>
    </div>

</body>
</html>