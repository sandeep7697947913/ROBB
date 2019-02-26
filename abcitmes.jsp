<%@page import="models.Cart,models.SellerBook,java.util.ArrayList" %>
<html>
	<head>
		<title>Book Rent</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/ccommon.css' />
		<link rel='stylesheet' type='text/css' href='css/abcitmes.css' />
	</head>
	<body>
		<%@ include file='header.jsp' %>
		<%Integer bookQuantity = (Integer)request.getAttribute("bookQuantity"); %>
		<%Integer totalAmmount = (Integer)request.getAttribute("totalAmmount"); %>
		<%ArrayList<Cart> carts = (ArrayList)request.getAttribute("carts"); %>
		<div id='main'>
			<div class='paymentSection'>
				<h1 class='text'>Your Subtotal is <%=totalAmmount%> Rs for <%=bookQuantity%> Books</h1>
			</div>
			<ul id='items'>
			<%for(Cart cart : carts){%>
				<li>
					<strong><%= cart.getSellerBook().getBook().getBookName()%></strong>
					<div class='books'>
					-<%=cart.getSellerBook().getBook().getAuthor()%></br>
					Quantity: <%=cart.getQuantity()%></br>
					Paperback-</br>
					<strike><%=cart.getSellerBook().getBook().getMrp()+" Rs"%></strike>
					<%=cart.getSellerBook().getPrice() +" Rs"%></br>
					Sold by: <%=cart.getSellerBook().getSeller().getSellerName()%>
					</div>
				</li>
				<hr class='line'></hr>
			<%}%>
			<a href="ccfpage.cpf">Modify Quantity or delete</a>
			</ul>
			<div id='proceedBox'>
				<div id='payContent'>
					<img src='images/pay1.png'>
				</div>
				<button id='paybutton'>Proceed to Pay</button>
			</div>
		</div>
		<%@ include file='footer.jsp' %>
		<script src='js/abcitmes.js'></script>
	</body>
</html>