<html>
	<head>
		<title>Sell Book</title>
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<link rel="stylesheet" type="text/css" href="css/scommon.css" />
		<link rel="stylesheet" type="text/css" href="css/sellerprofile.css" />
		<script src='js/jquery/jquery.js'></script>
	</head>
	<body>

		<%@ include file="sellerpublisherheader.jsp" %>
		<%@ include file="sellermenu.jsp" %>
		<div id="main">
			<table class='tb1'>
				<caption>Profile Details :-</caption>
				<tr>
					<th>GSTIN</th>
					<td>:</td>
					<td>${seller.gstin}</td>
				</tr>
				<tr>
					<th>E-mail</th>
					<td>:</td>
					<td>${seller.email}</td>
				</tr>
				<tr>
					<th>Pan Card</th>
					<td>:</td>
					<td>${seller.pancard}</td>
				</tr>
				<tr>
					<th>Account No.</th>
					<td>:</td>
					<td>${seller.accountNo}</td>
				</tr>
				<tr>
					<th>Bank Ifsc</th>
					<td>:</td>
					<td>${seller.ifsc}</td>
				</tr>
				<tr>
					<th>Contact</th>
					<td>:</td>
					<td>${seller.contact}</td>
				</tr>
				<tr>
					<th>Address</th>
					<td>:</td>
					<td>${seller.address},${seller.city.cityName},${seller.city.state.stateName}</td>
				</tr>
				<tr>
					<th>Toll Free</th>
					<td>:</td>
					<td>${seller.tollfree}</td>
				</tr>
			</table>
			<button id='cpmenu'>Change Password</button>
			<div id='message'>
			</div>
			<div id='tb2'>
				<table>
					<caption>Change Password :-</caption>
					<tr>
						<th>Old Password</th>
						<td>:</td>
						<td><input id='op' type='password' /></td>
					</tr>
					<tr>
						<th>New-Password</th>
						<td>:</td>
						<td><input id='np' type='password' /></td>
					</tr>
					<tr>
						<th>Confirm-Password</th>
						<td>:</td>
						<td><input id='cp' type='password' /></td>
					</tr>
				</table>
				<button disabled class='disabled' id='up'>Update Password</button>
			</div>
		</div>
		<script src="js/sellerprofile.js"></script>
		<%@ include file="footer.jsp" %>
	</body>
</html>