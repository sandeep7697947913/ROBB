<%@page import="java.util.ArrayList,models.SellerBook"%>
<html>
	<head>
		<title>Sell Book</title>
		<link rel="stylesheet" type="text/css" href="css/sellersellingbook.css" />
		<link rel="stylesheet" type="text/css" href="css/common.css" />
		<link rel="stylesheet" type="text/css" href="css/scommon.css" />
	</head>
	<body>
		<% ArrayList<SellerBook> bookList = (ArrayList)request.getAttribute("bookList"); %>
		<%@ include file="sellerpublisherheader.jsp" %>
		<%@ include file="sellermenu.jsp" %>
		<div id='bookImageContainer'>
		<img id='closeImage' src='images/close.png' />
			<div id='ImageContainer'>
				<img id='bimage' src='' /> 
			</div>
		</div>
		<div id="main">
			<table>	 
				<tr>
					<th>S.No.</th>
					<th>Name</th>
					<th>ISBN</th>
					<th>Author</th>
					<th>Category</th>
					<th>Price/MRP</th>
					<th>Quantity</th>
					<th>Image</th>
				</tr>
				<% int sno=1; %>
				<%for(SellerBook sellerBook : bookList){%>
				<tr id = 'sandeep' bookId ="<%= sellerBook.getBook().getBookId()%>">
					<td class='rec' ><%= sno++ %></td>
					<td class='rec' ><%= sellerBook.getBook().getBookName() %></td>
					<td class='rec' ><%= sellerBook.getBook().getIsbn() %></td>
					<td class='rec' ><%= sellerBook.getBook().getAuthor() %></td>
					<td class='rec' ><%= sellerBook.getBook().getCategory().getCategory() %></td>
					<td class='rec' ><%= sellerBook.getPrice()+"/"+sellerBook.getBook().getMrp() %> Rs</td>
					<td class='rec' ><%= sellerBook.getQuantity() %></td>
					<td  class='rec' ><input type="button" class='button' value="show" ></td>
				</tr>
				<% } %>
			</table>
		</div>
		<script src="js/sellersellingbook.js"></script>
		<%@ include file="footer.jsp" %>
	</body>
</html>