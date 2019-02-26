<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Book Rent</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/scommon.css' />
		<link rel='stylesheet' type='text/css' href='css/sellerorderpage.css' />
	</head>
	<body>
		<%@ include file='sellerpublisherheader.jsp' %>
		<%@ include file='sellermenu.jsp' %>
		<div id="main">
			<div id='table'>
				<table>
					<thead>
						<tr>
							<th>Order Id</th>
							<th>Book Name</th>
							<th>cust. Name</th>
							<th>E-mail</th>
							<th>City</th>
							<th>State</th>
							<th>Price</th>
							<th>Quantity</th>
							<th>Ordered Date</th>
						</tr>
					</thead>
					<tbody>
					<c:forEach var="order" items="${orders}">
					<tr>
						<td value="${order.orderId}">${order.orderId}</td>
						<td>${order.cart.sellerBook.book.bookName}</td>
						<td>${order.cart.customer.customerName}</td>
						<td>${order.cart.customer.email}</td>
						<td>${order.cart.customer.city.cityName}</td>
						<td>${order.cart.customer.city.state.stateName}</td>
						<td>${order.cart.sellerBook.price} rs</td>
						<td>${order.quantity}</td>
						<td>${order.date}</td>
					</tr>
					</c:forEach>
					</tbody>
				</table>
			</div>
			<div id='courierbox'>
			Select the courier Service from here 
				<select id='courier'>
					<option value='0'> --select-- </option>
				</select>
				<button disabled id='updrowdet'> Update </button>
			</div>
		</div>
		<script src='js/sellerorderpage.js'></script>
		<%@ include file='footer.jsp' %>
	</body>
</html>