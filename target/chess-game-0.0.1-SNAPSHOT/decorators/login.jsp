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
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<link href='<c:url value='template/web/css/style.css'></c:url>' rel="stylesheet" type="text/css">
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.6.0/css/all.min.css" integrity="sha512-Kc323vGBEqzTmouAECnVceyQqyqdsSiqLQISBL29aUW4U/M7pSPA/gEUZQqv1cwx4OnYxTxve5UMg5GT6L4JJg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <style>
        body {
            background-image: url("https://images.pexels.com/photos/131616/pexels-photo-131616.jpeg");
            background-size: cover;
            background-repeat: no-repeat;
        }
    </style>
    
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
   
    	<dec:body/>

</body>
</html>