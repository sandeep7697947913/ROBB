<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Log Page </title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/login.css' />
	</head>
	<body>
		<%@ include file='headerregisterlogin.jsp' %>
		<form id="form" action='clogin.do'>
			<table>
				<caption>Customers login</caption>
				<tr>
					<td class="lbl">E-mail </td>
					<td class="sep"> : </td>
					<td class="fld"><input id="lblname" type="email"
					name="email" required  placeholder="abc@gmail.com" autocomplete="off" /></td>
				</tr>
				<tr>
					<td class="lbl">Password </td>
					<td class="sep"> : </td>
					<td class="fld"><input type="password" name="password" required /></td>
				</tr>
				<tr>
					<td class="fld" colspan="3"><input id="logingbotton"  type="submit" value="Sign In" /></td>
				</tr>
			</table>
		</form>
		<ul class="llinks"><li id="clogin" onclick="clogin()">customer</li><li  id="slogin" onclick="slogin()">sellers</li><li id="plogin" onclick="plogin()">publishers</li></ul>
		<ul class="llinks">
			<li id="fp" onclick="forgotpass()">Forgot Password</li>
			<li id="ca" onclick="createaccount()">create an account</li>
		</ul>
		<ul class="llinks">
			<li id="cour" onclick="courlogin()">Courier</li>
		</ul>
		<script src="js/login.js"></script>
	</body>
</html>