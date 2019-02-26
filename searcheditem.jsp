<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@page import="java.util.ArrayList,models.SellerBook" %>
<html>
	<head>
		<title>Book Rent</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/ccommon.css' />
		<link rel='stylesheet' type='text/css' href='css/searcheditem.css' />
	</head>
	<body>
		<%@ include file='header.jsp' %>
		<div id='main'>
			<c:forEach var="sellerBook" items="${sellerBooks}">
			<div class='bookbox' rf='${sellerBook.book.bookId}'>
				<img src='data/booksimages/${sellerBook.book.bookId}.jpg' />
				<div class='bp bheading'>Book Name :-</div>
				<div class='bp bcontent'>${sellerBook.book.bookName}</div>
				<div class='bp bheading'>Author :-</div>
				<div class='bp bcontent'>${sellerBook.book.author}</div>
				<div class='bp bheading'>Mrp :-</div>
				<div class='bp bcontent'>${sellerBook.book.mrp} Rs.</div>
				<div class='bp bheading'>Price :-</div>
				<div class='bp bcontent'>${sellerBook.price} Rs.</div>
				<div class='bp bheading'>Seller :-</div>
				<div class='bp bcontent'>${sellerBook.seller.sellerName}</div>
			</div>
			</c:forEach>
		</div>
		<%@ include file='footer.jsp' %>
		<script src='js/searcheditem.js'></script>
	</body>
</html>