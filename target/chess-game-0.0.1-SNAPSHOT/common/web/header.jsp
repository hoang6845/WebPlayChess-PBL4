<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<nav class="bg-[#4b5563] shadow-lg home_heading">
	<div class="max-w-7xl mx-auto px-4">
		<div class="flex justify-between items-center h-[70px]">
			<c:if test="${ empty USERMODEL }">
				<div class="flex items-center space-x-4">
				<img
					src="https://media.istockphoto.com/id/1017466636/vi/vec-to/bi%E1%BB%83u-t%C6%B0%E1%BB%A3ng-avatar-ng%C6%B0%E1%BB%9Di-d%C3%B9ng-d%E1%BA%A5u-hi%E1%BB%87u-bi%E1%BB%83u-t%C6%B0%E1%BB%A3ng-h%E1%BB%93-s%C6%A1.jpg?s=1024x1024&w=is&k=20&c=EpZRRKOqygEUNZ9aXhRzgalQoFPBuuGJAeiYydMC6ek="
					alt="User avatar"
					class="h-12 w-12 rounded-full border-2 border-white"> <span
					class="text-sm font-medium text-white">Who are you</span>
				</div>
			</c:if>
			
			<c:if test="${not empty USERMODEL }">
				<div class="flex items-center space-x-4">
				<img
					src="https://png.pngtree.com/png-clipart/20190920/original/pngtree-user-flat-character-avatar-png-png-image_4643588.jpg"
					alt="User avatar"
					class="h-12 w-12 rounded-full border-2 border-white"> <span
					class="text-sm font-medium text-white">${USERMODEL.fullname}</span>
				</div>
			</c:if>
			
			<div class="hidden md:flex items-center space-x-8">
				<a href='<c:url value="/trang-chu?page=home"></c:url>' target="content"
					class="nav-item py-4 px-2 text-white font-semibold hover:text-gray-300 transition duration-300 group <c:if test='${page=="home"}'>active</c:if>">
					<i
					class="fas fa-home mr-2 group-hover:scale-110 transition-transform"></i>
					Home 
				</a> <a href='<c:url value="/friend?page=friend"></c:url>' target="content"
					class="nav-item py-4 px-2 text-white font-semibold hover:text-gray-300 transition duration-300 group <c:if test='${page=="friend"}'>active</c:if>">
					<i
					class="fas fa-users mr-2 group-hover:scale-110 transition-transform"></i>
					Friends
				</a> <a href='<c:url value="/rank?page=rank"></c:url>' target="content"
					class="nav-item py-4 px-2 text-white font-semibold hover:text-gray-300 transition duration-300 group  <c:if test='${page=="rank"}'>active</c:if>">
					<i
					class="fas fa-trophy mr-2 group-hover:scale-110 transition-transform"></i>
					Ranking
				</a> <a href="/profile/page=profile" target="content"
					class="nav-item py-4 px-2 text-white font-semibold hover:text-gray-300 transition duration-300 group  <c:if test='${page=="profile"}'>active</c:if>">
					<i
					class="fas fa-user mr-2 group-hover:scale-110 transition-transform"></i>
					Profile
				</a> 
				<c:if test="${not empty USERMODEL }">
					<a href='<c:url value="/dang-nhap?action=logout"></c:url>' target="content"
					class="py-2 px-3 bg-red-500 text-white rounded-md hover:bg-red-600 transition duration-300">
					<i class="fas fa-sign-out-alt mr-2"></i> Log Out
				</a>
				</c:if>
				<c:if test="${empty USERMODEL}">
					<a href='<c:url value="/dang-nhap?action=login"></c:url>' target="content"
					class="py-2 px-3 bg-green-500 text-white rounded-md hover:bg-green-600 transition duration-300 blue_background ">
					<i class="fas fa-sign-out-alt mr-2"></i> Log In
				</a>
				</c:if>
				
				
				
				
			</div>
			<div class="md:hidden flex items-center">
				<button class="outline-none mobile-menu-button">
					<svg class="w-6 h-6 text-white hover:text-gray-300" fill="none"
						stroke-linecap="round" stroke-linejoin="round" stroke-width="2"
						viewBox="0 0 24 24" stroke="currentColor">
                            <path d="M4 6h16M4 12h16M4 18h16"></path>
                        </svg>
				</button>
			</div>
		</div>
	</div>
	<div class="hidden mobile-menu">
		<ul class="">
			<li><a href="" target="content"
				class="mobile-nav-item block text-sm px-2 py-4 text-white hover:bg-[#5f6c7f] transition duration-300">Home</a></li>
			<li><a href="" target="content"
				class="mobile-nav-item block text-sm px-2 py-4 text-white hover:bg-[#5f6c7f] transition duration-300">Friends</a></li>
			<li><a href="" target="content"
				class="mobile-nav-item block text-sm px-2 py-4 text-white hover:bg-[#5f6c7f] transition duration-300">Ranking</a></li>
			<li><a href="" target="content"
				class="mobile-nav-item block text-sm px-2 py-4 text-white hover:bg-[#5f6c7f] transition duration-300">Profile</a></li>
			<c:if test="${not empty USERMODEL }">
			<li><a href="#" target="content"
				class="mobile-nav-item block text-sm px-2 py-4 text-white hover:bg-[#5f6c7f] transition duration-300">Log
					Out</a></li>
			</c:if>
			
			<c:if test="${empty USERMODEL }">
			<li><a href="" target="content"
				class="mobile-nav-item block text-sm px-2 py-4 text-white hover:bg-[#5f6c7f] transition duration-300">Log
					In</a></li>
			</c:if>
		</ul>
	</div>
</nav>

<script>
        const mobileMenuButton = document.querySelector('.mobile-menu-button');
        const mobileMenu = document.querySelector('.mobile-menu');

        mobileMenuButton.addEventListener('click', () => {
            mobileMenu.classList.toggle('hidden');
        });

        // Add active state to menu items
        const navItems = document.querySelectorAll('.nav-item');
        navItems.forEach(item => {
            item.addEventListener('click', () => {
                navItems.forEach(i => i.classList.remove('active'));
                item.classList.add('active');
            });
        });

        // Add active state to mobile menu items
        const mobileNavItems = document.querySelectorAll('.mobile-nav-item');
        mobileNavItems.forEach(item => {
            item.addEventListener('click', () => {
                mobileNavItems.forEach(i => i.classList.remove('mobile-active'));
                item.classList.add('mobile-active');
            });
        });
    </script>

<style>
.nav-item.active {
	border-bottom: 2px solid #ffffff;
	color: #ffffff;
}

.mobile-nav-item.mobile-active {
	background-color: #5f6c7f;
	color: white;
}
</style>


