<%@ page import = "java.util.ArrayList,models.State,models.Publisher" %>
<html>
	<head>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Details </title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/login.css' />
	</head>
	<body>
		<% 
		ArrayList<State> states = (ArrayList)application.getAttribute("states"); 
		%>

		<%@ include file='headerregisterlogin.jsp' %>
		<div id='emailtext'>
			Please verify your email by providing <a href="#">key</a> that has been sended in email
		</div>
		<form id="form" action='psignup2.do' onsubmit='return validateForm()'>
			<table>
				<caption>Fill the following details</caption>
				<tr>
					<td class="lbl">Publication-Name</td>
					<td class="sep"> : </td>
					<td class="fld"><input type="text"
					name="publication" required  placeholder="shivani publication" autocomplete="off" /></td>
				</tr>
				<tr>
					<td class="lbl">Gstin No.</td>
					<td class="sep"> : </td>
					<td class="fld"><input type="text"
					name="gstin" required  placeholder="sd456df64d" autocomplete="off" /></td>
				</tr>
				<tr>
					<td class="lbl">Link</td>
					<td class="sep"> : </td>
					<td class="fld"><input type="text" name="link" required /></td>
				</tr>
				<tr>
					<td class="lbl">Tollfree No.</td>
					<td class="sep"> : </td>
					<td class="fld"><input type="text" name="tollfree" required /></td>
				</tr>
				<tr>
					<td class="lbl">Secret Key</td>
					<td class="sep"> : </td>
					<td class="fld"><input type="text" name="pskey" required /></td>
				</tr>
				<tr>
					<td class="lbl">State</td>
					<td class="sep"> : </td>
					<td class="fld">
						<select id='selectstate'>
							<option value="0">-select-</option>

							<% for(State state : states ){%>
								<option value='<%=state.getStateId()%>'><%=state.getStateName()%></option>	
							<% } %>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lbl">City</td>
					<td class="sep"> : </td>
					<td class="fld">
						<select name='cityId' id='selectcity' required>
							<option value='0'>-select-</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="lbl">Address</td>
					<td class="sep"> : </td>
					<td class="fld"><input type="text" name="address" required /></td>
				</tr>
				<tr>
					<td class="fld" colspan="3"><input id="logingbotton"  type="submit" value="Sign Up" /></td>
				</tr>
			</table>
		</form>
		<ul class="llinks">
			<li id="nh" onclick="forwardRegistrationPage()"> <- back </li>
		</ul>
		<script src="js/Pregistrationdetail.js"></script>
	</body>
</html>
