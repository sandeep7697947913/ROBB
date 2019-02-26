<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Book Rent</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/pcommon.css' />
		<link rel='stylesheet' type='text/css' href='css/publisherAllBooks.css' />
		<link rel='stylesheet' type='text/css' href='css/jquery-ui.css' />
		<script src='js/jquery/jquery.js'></script>
		<script src='js/jquery/jquery-ui.js'></script>
	</head>
	<body>
		<%@ include file='sellerpublisherheader.jsp' %>
		<%@ include file='publishermenu.jsp' %>
		<div id="main">
		<table>
			<caption>Book List -</caption>
			<thead>
				<tr>
					<th>S.No.</th>
					<th>book name </th>
					<th>ISBN</th>
					<th>Author</th>
					<th>Category</th>
					<th>Edition</th>
					<th>Mrp</th>
				</tr>
			</thead>
			<tbody>
			<% int i=1; %>
			<c:forEach var="book" items="${books}" >
				<tr>
					<td><%= i++ %></td>
					<td>${book.bookName}</td>
					<td>${book.isbn}</td>
					<td>${book.author}</td>
					<td>${book.category.category}</td>
					<td>${book.edition} Edition</td>
					<td>${book.mrp} Rs.</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		</div>
		<%@ include file='footer.jsp' %>
		<script src="js/publisherAllBooks.js"></script>
	</body>
</html>