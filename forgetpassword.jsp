<html>
	<head>
		<title>
			Forget Password 
		</title>
		<link rel='stylesheet' type='text/css' href='css/forgetpassword.css' />
		<script src='js/jquery/jquery.js'></script>
	</head>
	<body>
		<div id='main'>
			<div id='logoimg'>
				<img src='images/logo.png' />
			</div>
			<div id='buttoncoll'>
				<button class='btnactive'>Customer</button>
				<button>Publisher</button>
				<button>Seller</button>
				<button>Courier</button>
			</div>
			<fieldset>
				<legend>Customer</legend>
				<input id='email' type='email' placeholder='email' />
				<input id='emailsend' type='button' value='Send OTP'/>
				<span id='emessage'></span><br />
				<div id='esuc'>
					<input id='otp' type='text' placeholder='OTP' /><br />
					<input id='newpass' type='password' placeholder='Password' /><br />
					<input type='button' id='uppass' value='Set Password'/><br />
				</div>
			</fieldset>
		</div>
		<script src='js/forgetpassword.js'></script>
	</body>
</html>