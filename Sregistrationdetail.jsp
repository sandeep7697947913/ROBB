<%@ page import = "java.util.ArrayList,models.State,models.Seller" %>
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
		<form id="form" action='ssignup2.do' onsubmit='return validateForm()'>
			<table>
				<caption>Fill the following details</caption>
				<tr>
					<td class="lbl">Seller-Name</td>
					<td class="sep"> : </td>
					<td class="fld"><input type="text"
					name="seller" required  placeholder="Retail tech" autocomplete="off" /></td>
				</tr>
				<tr>
					<td class="lbl">Gstin No.</td>
					<td class="sep"> : </td>
					<td class="fld"><input type="text"
					name="gstin" required  placeholder="sd456df64d" autocomplete="off" /></td>
				</tr>
				<tr>
					<td class="lbl">Pan Card</td>
					<td class="sep"> : </td>
					<td class="fld"><input type="text"
					name="pancard" required  placeholder="pancard" autocomplete="off" /></td>
				</tr>
				<tr>
					<td class="lbl">B. IFSC</td>
					<td class="sep"> : </td>
					<td class="fld"><input type="text" name="ifsc" required /></td>
				</tr>
				<tr>
					<td class="lbl">Account No.</td>
					<td class="sep"> : </td>
					<td class="fld"><input type="text" name="accountno" required /></td>
				</tr>
				<tr>
					<td class="lbl">Tollfree No.</td>
					<td class="sep"> : </td>
					<td class="fld"><input type="text" name="tollfree" required /></td>
				</tr>
				<tr>
					<td class="lbl">Contact No.</td>
					<td class="sep"> : </td>
					<td class="fld"><input type="text" name="contact" required /></td>
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
		<script src="js/Sregistrationdetail.js"></script>
	</body>
</html>
