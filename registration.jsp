<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Regist Page </title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/login.css' />
	</head>
	<body>
		<%@ include file='headerregisterlogin.jsp' %>
		<form id="form" action='csignup.do'>
			<table>
				<caption>Register as Customer</caption>
				<% if(request.getAttribute("emessage")!=null){ %>
					<div>
						<%= request.getAttribute("emessage") %>
					</div>
				<% } %>
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
					<td class="lbl">Retype-Password </td>
					<td class="sep"> : </td>
					<td class="fld"><input type="password" id="repass" required /></td>
				</tr>
				<tr>
					<td class="fld" colspan="3"><input id="logingbotton"  type="submit" value="Proceed" /></td>
				</tr>
			</table>
		</form>
		<ul class="llinks"><li id="csignup" onclick="csignup()">customer</li><li  id="ssignup" onclick="ssignup()">seller</li><li id="psignup" onclick="psignup()">publisher</li></ul>
		<ul class="llinks">
			<%--<li id="nh" onclick="registrationhelp()">need help</li>--%>
			<li id="aha" onclick="createaccount()">already have account</li>
		</ul>
		<ul class="llinks">
			<li id="cour" onclick="courier()">courier</li>
		</ul>
		<script src="js/registration.js"></script>
	</body>
</html>