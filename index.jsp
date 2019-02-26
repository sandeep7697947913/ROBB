<%@page import="java.util.ArrayList,models.SellerBook" %>
<html>
	<head>
		<title>Book Rent</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/ccommon.css' />
		<link rel='stylesheet' type='text/css' href='css/index.css' />
	</head>
	<body>
		<%@ include file='header.jsp' %>
		<% ArrayList<SellerBook> recAddBooks = (ArrayList)application.getAttribute("recAddBooks");%>
		<div id='main'>
			<h1 id='rab'>Recently added books</h1>
			
			<div class='imagerow'>
				<% for(SellerBook sb : recAddBooks){ %>
					<div class='imagetd' bookId='<%=sb.getBook().getBookId()%>'>
						<img src="data/booksimages/<%= sb.getBook().getBookId()%>.jpg"/>
						<div class='bookdetails'>
							<div class='bdetails'>
								<p>
									<b>Name :</b><br/><%= sb.getBook().getBookName()%>
								</p>
								<p>
									<b>Author :</b><br/><%= sb.getBook().getAuthor()%>
								</p>
								<p>
									<b>MRP :</b><%= sb.getBook().getMrp() %> Rs
								</p>
								<p>
									<b>Price :</b><%= sb.getPrice() %> Rs
								</p>
							</div>
						</div>
					</div>
				 <% } %>
			</div>
		</div>
		<%@ include file='footer.jsp' %>
		<script src='js/index.js'></script>
	</body>
</html>