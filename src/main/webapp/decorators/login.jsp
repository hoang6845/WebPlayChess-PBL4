<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
  <title><dec:title default="Đăng nhập" /></title>
    <script src='<c:url value='/template/web/bootstrap/js/3.4.5'></c:url>'></script>
    <link href='<c:url value='template/login/login.css'></c:url>' rel="stylesheet" type="text/css">
	<script src='<c:url value='template/web/jquery/jquery.min.js'></c:url>'></script>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<link href='<c:url value='template/web/css/style.css'></c:url>' rel="stylesheet" type="text/css">
	<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value='/template/fontawesome-free-6.6.0-web/css/all.min.css'/>">
    <style>
        body {
            background-image: url("http://res.cloudinary.com/dgubksrvu/image/upload/v1731930003/xduszbog8gteypm4rmww.jpg");
            background-size: cover;
            background-repeat: no-repeat;
        }
    </style>
    
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
   
    	<dec:body/>

</body>
</html>