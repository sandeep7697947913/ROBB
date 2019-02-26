<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Book Rent</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/pcommon.css' />
		<link rel='stylesheet' type='text/css' href='css/publisherprofile.css' />
		<link rel='stylesheet' type='text/css' href='css/jquery-ui.css' />
		<script src='js/jquery/jquery.js'></script>
		<script src='js/jquery/jquery-ui.js'></script>
	</head>
	<body>
		<%@ include file='sellerpublisherheader.jsp' %>
		<%@ include file='publishermenu.jsp' %>
		<div id="main">
			<table class='tb1'>
				<caption>Profile Details :-</caption>
				<tr>
					<th>GSTIN</th>
					<td>:</td>
					<td>${publisher.gstin}</td>
				</tr>
				<tr>
					<th>E-mail</th>
					<td>:</td>
					<td>${publisher.email}</td>
				</tr>
				<tr>
					<th>Link</th>
					<td>:</td>
					<td>${publisher.link}</td>
				</tr>
				<tr>
					<th>Address</th>
					<td>:</td>
					<td>${publisher.address},${publisher.city.cityName},${publisher.city.state.stateName}</td>
				</tr>
				<tr>
					<th>Toll Free</th>
					<td>:</td>
					<td>${publisher.tollfree}</td>
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
		<%@ include file='footer.jsp' %>
		<script src="js/publisherprofile.js"></script>
	</body>
</html>