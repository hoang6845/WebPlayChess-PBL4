<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title><dec:title default="Trang chủ" /></title>
<style>
body, html {
	margin: 0;
	padding: 0;
	height: 100%;
	overflow: hidden; /* Ẩn thanh cuộn của toàn bộ trang */
}
</style>
<link
	href="<c:url value='template/web/bootstrap/css/tailwind.min.css'></c:url>"
	rel="stylesheet" type="text/css" media="all">
<%--     	<link rel="stylesheet" href="<c:url value='template/web/bootstrap/css/all.min.css'></c:url>"> --%>
<script type="text/javascript"
	src="<c:url value='/template/web/jquery/jquery.min.js'/>"></script>
<script src="<c:url value='/template/web/bootstrap/js/3.4.5' />"></script>
<link href="<c:url value='template/web/css/style.css'></c:url>"
	rel="stylesheet" type="text/css" media="all">
<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value='/template/web/bootstrap/css/all.min.css'/>">
<link rel="stylesheet" type="text/css" media="all"
	href="<c:url value='/template/fontawesome-free-6.6.0-web/css/all.min.css'/>">
<link href="<c:url value='template/web/ChessBoard/style.css'></c:url>"
	rel="stylesheet" type="text/css" media="all">

<script type="text/javascript">
		 class Model {
			 constructor(id, fullname,elo, totalMatches)
			{
				this.id = id;
				this.fullname = fullname;
				this.elo = elo;
				this.totalMatches = totalMatches;
			} 
		 }
		 class Message {
             constructor(room, type, sender, content){
            	 this.room = room;
              	 this.type= type;
                 this.sender = sender;
                 this.content = content;
      //         this.whiteModel = whiteModel? new Model(whiteModel.id, whiteModel.fullname, whiteModel.elo, whiteModel.totalMatches):null;
      //       	 this.blackModel = blackModel? new Model(blackModel.id, blackModel.fullname, blackModel.elo, blackModel.totalMatches):null;
             }
         }

		</script>


</head>
<body style="overflow: auto">
	<%@ include file="/common/web/header.jsp"%>

	<div
		class="bg-[#6b7280]  font-sans flex items-center justify-center min-h-screen home_body">
		<dec:body />
	</div>

</body>
</html>
