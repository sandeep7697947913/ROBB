<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Book Rent</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/pcommon.css' />
		<link rel='stylesheet' type='text/css' href='css/publishersbooksellers.css' />
		<link rel='stylesheet' type='text/css' href='css/jquery-ui.css' />
		<script src='js/jquery/jquery.js'></script>
		<script src='js/jquery/jquery-ui.js'></script>
	</head>
	<body>
		<%@ include file='sellerpublisherheader.jsp' %>
		<%@ include file='publishermenu.jsp' %>
		<div id="main">
			<table>
				<thead>
					<th class='sno'>S. No.</th>
					<th class='bname'>Book Name</th>
					<th class='bisbn'>ISBN</th>
					<th class='bauthor'>Author</th>
					<th class='bcategory'>Category</th>
					<th class='bseller'>Seller</th>
					<th class='bpm'>price/mrp</th>
				</thead>
				<tbody>
					<% int i=1; %>
					<c:forEach var="sellerBook" items="${sellerBooks}" >
					<tr>
						<td class='sno'><%= i++ %></td>
						<td class='bname'>${sellerBook.book.bookName}</td>
						<td class='bisbn'>${sellerBook.book.isbn}</td>
						<td class='bauthor'>${sellerBook.book.author}</td>
						<td class='bcategory'>${sellerBook.book.category.category}</td>
						<td class='bseller'>${sellerBook.seller.sellerName}</td>
						<td class='bpm'>${sellerBook.price}/${sellerBook.book.mrp} Rs.</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
		<%@ include file='footer.jsp' %>
		<script src="js/publishersbooksellers.js"></script>
	</body>
</html>