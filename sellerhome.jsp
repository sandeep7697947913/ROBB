<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<head>
		<title>Book Rent</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/sellerhome.css' />
		<link rel='stylesheet' type='text/css' href='css/scommon.css' />
	</head>
	<body>
		<%@ include file='sellerpublisherheader.jsp' %>
		<%@ include file='sellermenu.jsp' %>
		<div id="main">
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
		<%@ include file='footer.jsp' %>
	</body>
</html>