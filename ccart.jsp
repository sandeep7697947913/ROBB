<%@page import='java.util.ArrayList,models.Cart'%>
<html>
	<head>
		<title>Book Rent</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/ccommon.css' />
		<link rel='stylesheet' type='text/css' href='css/ccart.css' />
	</head>
	<body>
		<%@ include file='header.jsp' %>
		<% ArrayList<Cart> cartItems = (ArrayList)request.getAttribute("cartItems"); %>
		<div id='main'>
			<h1>Books In Your Bag:- <button id='chab' class='cosbbtn htbn'>Check Out All Books</button></h3>
			<%for(Cart cart : cartItems){%>
				<div class='items'>
				<div class='left'>
					<img src='data/booksimages/<%=cart.getSellerBook().getBook().getBookId()%>.jpg' />
				</div>
				<div class='right'>
					<div class='property'><b>Name :-</b><br/>&nbsp;&nbsp;&nbsp;
						<%=cart.getSellerBook().getBook().getBookName() %>
					</div>
					<div class='property'><b>Author :-</b></br>&nbsp;&nbsp;&nbsp;
						<%=cart.getSellerBook().getBook().getAuthor() %>
					</div>
					<div class='property'><b>Price :-</b>&nbsp;
						<%=cart.getSellerBook().getPrice()%> Rs/-
					</div>
					<div class='property'><b>Quantity :-</b>&nbsp;
						<select SBId='<%=cart.getSellerBook().getSellerBookId()%>' name='quantity'>
							<% int bookQuantity = cart.getQuantity(); %>
							<% for(int i=1;i<=3;i++ ){ %>
								<% if(bookQuantity==i){ %>
									<option value='<%=i%>' selected><%=i%></option>
								<% }else{ %>
									<option value='<%=i%>'><%=i%></option>
								<% } %>
							<% } %>
						</select>
						<button class='removebtn' SBId='<%=cart.getSellerBook().getSellerBookId()%>'>remove from bag</button>
					</div>
				</div>
			</div>
			<%}%>
		</div><!-- main -->
		<%@ include file='footer.jsp' %>
		<script></script>
		<script src='js/ccart.js'></script>
	</body>
</html>