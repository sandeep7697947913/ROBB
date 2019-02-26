<%@page import="models.Publisher,models.Seller" %>
<div id='header'>
	<% Publisher publisher = (Publisher)session.getAttribute("publisher"); %>
	<% Seller seller = (Seller)session.getAttribute("seller"); %>
	<link rel='stylesheet' type='text/css' href='css/sellerpublisherheader.css' />
	<div class="head">
		<img id='logo' src='images/logo.png' />
	</div>
	<div id="headcontent">
		<% if(publisher!=null||seller!=null){ %>
			${publisher.publication}
			${seller.sellerName}
			<div id="buttons"> 
			<input class="btn" onclick="logout()" type="button" value="logout" />
			</div>
		<% } %>
	</div>
	<script src='js/sellerpublisherheader.js'></script>
</div>