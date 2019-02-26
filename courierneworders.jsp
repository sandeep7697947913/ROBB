<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
	<head>
		<title>Courier Orders</title>
		<link rel='stylesheet' type='text/css' href='css/common.css' />
		<link rel='stylesheet' type='text/css' href='css/courierneworders.css' />
		<script src='js/jquery/jquery.js' ></script>
	</head>
	<body>
		<%@ include file='courierheader.jsp'%>
		<%@ include file='couriernavbar.jsp'%>
		<div id='main'>
			<div class='table'>
				<table>
					<thead>
						<tr>
							<th class='orderId'>order Id</th>
							<th class='seller'>Seller</th>
							<th class='book'>Book</th>
							<th class='price'>price</th>
							<th class='quantity'>Quantity</th>
							<th class='customer'>Customer</th>
							<th class='email'>email</th>
							<th class='state'>State</th>
							<th class='city'>City</th>
							<th class='address'>Address</th>
							<th class='date'>Date</th>
							<th class='butn'>Dispatch</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="order" items="${orders}">
						<tr>
							<td class='orderId'>${order.orderId}</td>
							<td class='seller'>${order.cart.sellerBook.seller.sellerName}</td>
							<td class='book'>${order.cart.sellerBook.book.bookName}</td>
							<td class='price'>${order.cart.sellerBook.price} rs</td>
							<td class='quantity'>${order.quantity}</td>
							<td class='customer'>${order.cart.customer.customerName}</td>
							<td class='email'>${order.cart.customer.email}</td>
							<td class='state'>${order.cart.customer.city.state.stateName}</td>
							<td class='city'>${order.cart.customer.city.cityName}</td>
							<td class='address'>${order.cart.customer.address}</td>
							<td class='date'>${order.date}</td>
							<td class='butn'><button class='btn' disabled>Dispatch</button></td>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<script src='js/courierneworders.js'></script>
	</body>
</html>