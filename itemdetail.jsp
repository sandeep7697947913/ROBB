<%@page import="models.Book" %>
<html>
	<head>
		<title>Book Rent</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/ccommon.css' />
		<link rel='stylesheet' type='text/css' href='css/itemdetail.css' />
	</head>
	<body>
		<%@ include file='header.jsp' %>
		<% Book book = (Book)request.getAttribute("book"); %>
		<div id='main'>
			<h1>Book Details</h1>
			<div id='container'>
				<div id='imagediv'>  
					<img src='data/booksimages/${book.bookId}.jpg'>
				</div>
				<form action='addtocc.cps' method='get'>
					<div class='detail'><b>Name :</b>&nbsp;&nbsp;&nbsp;&nbsp;${book.bookName}</div>
					<div class='detail'><b>Author :</b>&nbsp;&nbsp;&nbsp;&nbsp;${book.author}</div>
					<div class='detail'><b>Category :</b>&nbsp;&nbsp;&nbsp;&nbsp;${book.category.category}</div>
					<div class='detail'><b>Edition :</b>&nbsp;&nbsp;&nbsp;&nbsp;${book.edition} edition</div>
					<div class='detail'><b>Mrp :</b>&nbsp;&nbsp;&nbsp;&nbsp;${book.mrp} Rs</div>
					<div class='detail'><b>Seller :</b>&nbsp;&nbsp;&nbsp;&nbsp;
						<select name='sellerBookId' id='sellerselect'>
							
						</select>
						<span id='moresellertext'></span>
					</div>
					<div class='detail' id='bookPrice'><b>Price :</b>&nbsp;&nbsp;&nbsp;&nbsp;</div>
					<div class='detail'><b>Quantity :</b>&nbsp;&nbsp;&nbsp;&nbsp;<select name="quantity"/>
						<option value='1'>1</option>
						<option value='2'>2</option>
						<option value='3'>3</option>
					</select></div>
					<div class='detail'><input type='submit' value='Add To Bag'/>
					<input formaction='buynow.cps' type='submit' value='Buy Now' hidden/>
					</div>
				</form>
			</div>
		</div><!-- main -->
		<%@ include file='footer.jsp' %>
		<script>
			var bookId = "<%= book.getBookId() %>";
			var bookMRP = "<%= book.getMrp() %>";
		</script>
		<script src='js/itemdetail.js'></script>
	</body>
</html>