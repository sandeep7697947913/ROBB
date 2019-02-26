<%@ page import="java.util.ArrayList,models.Cart"%>
<html>
	<head>
		<title>Book Rent</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/ccommon.css' />
		<link rel='stylesheet' type='text/css' href='css/cuspaypage.css' />
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" crossorigin="anonymous">
	</head>
	<body>
		<%@ include file='header.jsp' %>
		<div id='main'>
			<h1 class='balance'>Your Outstanding amount is ${totalAmmount} rs </h1>
			<div id='pmbs'>
				Please Choose Payment Method
				<button id='pmbc' class='pmb'>COD</button>
				<button id='pmbd' class='pmb'>Debit Card</button>
				<div id='modpayment'>@ Selected mode of payment :- <span id='method'>COD</span></div>
			</div>		
			<div Id='paybox'>
				<b>please fill the following details :-</b>
				<br/>
				<br/>
				<div id='atm'>
					<div class='atmn'>
						ATM NO. :-</br>
						<input type='text' id='atmno'>
					</div>
					<div class='atmcvv'>
						Cvv :-</br>
						<input type='text' id='cvv'>
					</div>
					<div class='atmex'>
						Expiry Date :-</br>
						<input type='date' id='date'>
					</div>
					<button disabled id='proceedb' class='proceedb'>Proceed</button>
				</div>
			</div>
			<div id='paycod'>
				<b>Solve the captcha :-</b>
				</br>
				<div id='other'>
					<div id='code'>
						Loading...
					</div>
					<button id='rcaptcha'><i class="fas fa-sync">Reload</i></button>
					<br/>
					<div class='inputgrp'>
						<input id='captchaans' type='text' />
						<input id='verify' type='button' value='verify' />
					</div>
				</div>
				<p>To place order verify the captcha!</p>	
			</div>
		</div>
		<%@ include file='footer.jsp' %>
		<script src='js/cuspaypage.js'></script>
	</body>
</html>