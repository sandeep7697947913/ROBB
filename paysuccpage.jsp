<html>
	<head>
		<title>Payment Info</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/ccommon.css' />
		<link rel='stylesheet' type='text/css' href='css/paysuccpage.css' />
	</head>
	<body>
		<%@ include file='header.jsp' %>
		<div id='main'>
			<div id='msg'>
				<div class='msgbox'><h1>Payment Successfull !</h1></div>
				<div class='msgbox'><h3>Order is placed.</h3></div>
				<div class='msgbox'><h5>you will be redirected to home page in <span id='time'></span> seconds</h5></div>
				<div class='msgbox'>Click here for <a href='index.jsp'> Home </a>Page</div>
			</div>
		</div>
		<%@ include file='footer.jsp' %>
		<script></script>
		<script src='js/paysuccpage.js'></script>
	</body>
</html>