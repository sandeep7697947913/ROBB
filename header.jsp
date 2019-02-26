<%@page import="models.Customer"%>
<link rel='stylesheet' type='text/css' href='css/jquery-ui.css' />
<script src='js/jquery/jquery.js'></script>
<script src='js/jquery/jquery-ui.js'></script>
<div id='header'>
	<link rel='stylesheet' type='text/css' href='css/header.css' />
	<img id='hlogo' src='images/logo.png' />
	<div id='navbar'>
		
		<img id='hhome' class='navimage' title='Home' src='images/home.png' />
		<img id='habout' class='navimage' title='About' src='images/about.png' />
		<img id='hhelp' class='navimage' title='Help' src='images/help.png' />
		
		<% Customer customer = (Customer)session.getAttribute("customer");%>
	
		<img id='huser' class='navimage' title='Account' 
			
			<% if(customer!=null){ %>
				<% if(customer.getPicPath()!=null){ %>
					src='data/customerimages/<%=customer.getPicPath()%>'
				<% }else{ %> 
					src='images/user1.png'
				<% } %>
			<% }else{ %>
				src='images/user.png'
			<% } %>
		/>

		<input id='hsearch' class='hab' type='text' />	
			<button id='hsearchb' class='hab'>search</button>
	</div>
	<div id='bag'> 
		<a href='ccfpage.cpf'><img src='images/bag.png'/></a>
	</div>
	<script src='js/header.js'></script>
</div>