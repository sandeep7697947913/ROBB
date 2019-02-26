<%@ page import ="models.Order" %> 
<html>
	<head>
		<title>Bill</title>
		<link rel='stylesheet' type='text/css' href='css/bill.css' />
	</head>
	<body>
		<%Order order = (Order) request.getAttribute("order");%>
		<div id='main'>
			<img src='images/logo.png' />
			<div id='status'>${order.orderPayStatus.status}</div>
			<table id='table1'>
				<caption>Details</caption>
				<tr>
					<td>Date </td>
					<td> => </td>
					<td>${order.date}</td>
				</tr>
				<tr>
					<td>Name </td>
					<td> => </td>
					<td>${order.cart.customer.customerName}</td>
				</tr>
				<tr>
					<td>Phone </td>
					<td> => </td>
					<td>${order.cart.customer.phoneNo}</td>
				</tr>
				<tr>
					<td>Email </td>
					<td> => </td>
					<td>${order.cart.customer.email}</td>
				</tr>
				<tr>
					<td>Address </td>
					<td> => </td>
					<td>${order.cart.customer.address},
					 ${order.cart.customer.city.cityName},
					 ${order.cart.customer.city.state.stateName},</td>
				</tr>
			</table>
			<table  id='table2'>
				<caption>Items</caption>
				<tr>
					<th class='orderId'>Order Id</th>
					<th class='bookname'>Book Name </th>
					<th class='quantity'>quantity</th>
					<th class='price'>Price</th>
				</tr>
				<tr>
					<td class='orderId'>${order.orderId}</td>
					<td class='bookname'>${order.cart.sellerBook.book.bookName}</td>
					<td class='quantity'>${order.quantity} </td>
					<td class='price'><%= (order.getQuantity())*(order.getCart().getSellerBook().getPrice()) %> Rs.</td>
				</tr>
				<tr>
					<th class='total' colspan=2>Total = </th>
					<th  class='quantity'>${order.quantity} </th>
					<th class='price'><%= (order.getQuantity())*(order.getCart().getSellerBook().getPrice()) %> Rs. </th>
				</tr>
			</table>
			<div class='foottext'> * This bill released by ROOB !</div>
		</div>
	</body>
</html>