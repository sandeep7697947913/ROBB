<html>
	<head>
		<title>Payment Info</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/ccommon.css' />
		<link rel='stylesheet' type='text/css' href='css/orderfail.css' />
	</head>
	<body>
		<%@ include file='header.jsp' %>
		<div id='main'>
			<div id='msg'>
				<div class='msgbox' id='payfail' ><h1>Can not place Order ! </h1></div>
				<div class='msgbox'><h5>you will be redirected to home page in <span id='time'></span> seconds</h5></div>
				<div class='msgbox'>Click here for <a href='index.jsp'> Home </a>Page</div>
			</div>
		</div>
		<%@ include file='footer.jsp' %>
		<script></script>
		<script src='js/orderfail.js'></script>
	</body>
</html>