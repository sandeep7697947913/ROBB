<html>
	<head>
		<title>Courier</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/courierprofile.css' />
		<script src='js/jquery/jquery.js' ></script>
	</head>
	<body>
		<%@ include file='courierheader.jsp'%>
		<%@ include file='couriernavbar.jsp'%>
		<div id='main'>
			<div id='message'>
				Hi
			</div>
			<div id='tb1'>
				<table>
					<caption>Account Details -</caption>
					<tr>
						<th>Name</th>
						<td>:</td>
						<td>${courier.name}</td>
					</tr>
					<tr>
						<th>Email</th>
						<td>:</td>
						<td>${courier.email}</td>
					</tr>
					<tr>
						<th>State</th>
						<td>:</td>
						<td>${courier.city.state.stateName}</td>
					</tr>
					<tr>
						<th>City</th>
						<td>:</td>
						<td>${courier.city.cityName}</td>
					</tr>
					<tr>
						<th>Address</th>
						<td>:</td>
						<td>${courier.address}</td>
					</tr>
				</table>
			</div>
			<div id='tb2'>
				<input id='chp' type='button' value='Change Password' />
				<table id='tblpass'>
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
					<tr>
						<td colspan='3' class='tdbtn'><button disabled class='disabled' id='up'>Update Password</button>
						</td>	
					</tr>
				</table>
			</div>
		</div>	
		<script src='js/courierprofile.js'></script>
	</body>
</html>