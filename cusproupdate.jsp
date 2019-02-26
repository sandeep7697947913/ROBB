<%@ page import ="models.State,java.util.ArrayList"%>
<html>
	<head>
		<title>Book Rent</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/ccommon.css' />
		<link rel='stylesheet' type='text/css' href='css/cusproupdate.css' />
	</head>
	<body>
		<%@ include file='header.jsp' %>
		<% ArrayList<State> states = (ArrayList)application.getAttribute("states");%>
		<div id='main'>
			<h1 class='pageheading'>Profile :-</h1>
			<div id='imagediv'>
				<img id='profileimage' src='images/b1.jpg'>
			</div>
			<form action='CPUdetails.cpf' method="post" enctype="multipart/form-data">
				Name :- <br/><input type='text' class='field' required name='name'><br/>
				PhoneNo :- <br/><input type='text' class='field' required name='phoneno'><br/>
				State :- <br/>
				<select class='field' id='states'>
					<option value='0'>-select-</option>
					<%for(State state : states){%>
						<option value='<%=state.getStateId()%>'><%=state.getStateName()%></option>
					<%}%>
				</select><br/>
				Cities :- <br/>
				<select  class='field' id='cities' name='cityId'>
					<option value='0'>-select-</option>
				</select><br />
				Address :-</br><textarea class='field' required name='address' rows='2' cols='20'></textarea><br/>
				profile pic :- <br /><input class='field' name='pic' required type='file' /><br/>
				<input class='field' type='submit'/>
			</form>
		</div>
		<%@ include file='footer.jsp' %>
		<script src='js/cusproupdate.js'></script>
	</body>
</html>