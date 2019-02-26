<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Courier</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/courierhome.css' />
		<script src='js/jquery/jquery.js' ></script>
	</head>
	<body>
		<%@ include file='courierheader.jsp'%>
		<%@ include file='couriernavbar.jsp'%>
		<div id='main'>
			<div id='noticeheading'>
					<h1>Notice Board :- </h1>
				</div>
				<c:forEach var="notification" items="${notifications}">
				<div class='noticemessage'>
					<div class='notice'>* ${notification.notification}</div>
					<div class='noticedate'> - ${notification.date}</div>
				</div>
				</c:forEach>
		</div>
	</body>
</html>