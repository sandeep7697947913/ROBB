<%@page import="models.State,java.util.ArrayList"%>
<% ArrayList<State> states = (ArrayList) request.getServletContext().getAttribute("states");%>
<html>
	<head>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/ccommon.css' />
		<link rel='stylesheet' type='text/css' href='css/customerprofile.css' />
		<script src='js/jquery/jquery.js'></script>
	</head>
	<body>
		<%@ include file = 'header.jsp' %>
		<div id='main'>
			<div id='options'>
				<p class='heading'><b>Options :- </b><button id='logout'>Log Out</button></p>
				<br/>
				<div id='order' class='menubox'>
					<div class='menuimage'>
						<img src='images/order1.png'/>
					</div>
					<div id='tmp' class='menuText'>
						<h3>Orders</h3>
						<p>All Orders details</p>
					</div>
				</div>
				<div id='torder' class='menubox'>
					<div class='menuimage'>
						<img src='images/track1.png'/>
					</div>
					<div class='menuText'>
						<h3>Track Order</h3>
						<p>know the details of recent orders</p>
					</div>
				</div>
				<div id='emailpassword' class='menubox'>
					<div class='menuimage'>
						<img src='images/changepass.png'/>
					</div>
					<div class='menuText'>
						<h3>Email & Password </h3>
						<p>let you to change your password</p>
					</div>
				</div>
				<div id='nameaddress' class='menubox'>
					<div class='menuimage'>
						<img src='data/customerimages/${customer.picPath}'/>
					</div>
					<div class='menuText'>
						<h3>Name & Address</h3>
						<p>change name,address and pic etc. </p>
					</div>
				</div>
			</div>
			<div id='orderstable'>
				<div class='cbtn'>
					<b id='ohead'>Orders</b>
					<img src='images/close.png' />
				</div>
				<div id='orderspage'>
					<%-- handle by the javascript--%>
				</div>
			</div>
			<div id='emailpass'>
				<div class='cbtn'>
					<b >Email & password</b>
					<img src='images/close.png' />
				</div>
				<div class='field'> Email :- </div><input type='text' value='${customer.email}' readonly disabled /><br />
				<div class='field' >Old-Password :- </div><input type='password' id='pass'/><br />
				<div class='field'>New-password : - </div><input type='password' id='ntp'/><br />
				<div class='field'>re-type New password :- </div><input id='rtp' type='password' /><br />
				<div class='field'><button id='chps' class='nrmbutton'>Change Password</button>
				</div> 
				<div id='pmess'>
					
				</div>
			</div>
			<div id='form'>
				<div class='cbtn'>
					<b>Customer Details</b>
					<img src='images/close.png' />
				</div>
				<form action='CPUdetails.cpf' method="post" enctype="multipart/form-data">
					Name :- <br/><input type='text' required name='name'><br/>
					PhoneNo :- <br/><input type='text' required name='phoneno'><br/>
					State :- <br/>
					<select id='states'>
						<option value='0'>-select-</option>
						<%for(State state : states){%>
							<option value='<%=state.getStateId()%>'><%=state.getStateName()%></option>
						<%}%>
					</select><br/>
					Cities :- <br/>
					<select id='cities' name='cityId'>
						<option value='0'>-select-</option>
					</select><br />
					Address :-</br><textarea required name='address' rows='2' cols='20'></textarea><br/>
					profile pic :- <br /><input  name='pic' required type='file' /><br/>
					<input type='submit'/>
				</form>
			</div>
		</div>
		<%@ include file='footer.jsp' %>
		<script src='js/customerprofile.js'></script>
	</body>
</html>