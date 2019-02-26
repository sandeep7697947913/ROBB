<html>
	<head>
		<title>Couriers</title>
		<link rel='stylesheet' type='text/css' href='css/courierlogin.css'/>
	</head>
	<body>
		<div id="main">
			<div class='logoimg' >
				<img id='logo' src='images/logo.png' />
			</div>
			<div class='form'>
				<div class='label'>Courier Login :- </div>
				<form action='courierlogin.do'>
					<input class='field' placeholder="E-mail" type='text' name='email'>
					<br />
					<input class='field' placeholder="password" type='password' name='password'/>
					<br />
					<input class='btn' type='submit' value='submit' />
				</form>
				<a href='forgetpassword.jsp'>Forget Password</a>
			</div>
		</div>
		<script src='js/courierlogin.js'></script>
	</body>
</html>