<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.ArrayList,models.Order" %>

<%
	ArrayList<Order> orders = (ArrayList)request.getAttribute("orders");
	int Tprice = 0;
	int qauntity = 0;
	int i = 0;
%>

<c:forEach var="order" items="${orders}" >
<div class='border'>
	<div class='oheader'>
		<div><b>Ordered Date</b><br />
		${order.date}</div>
		<div><b>Total</b><br />
		<%Order order = orders.get(i);%>
	<% Tprice = orders.get(i).getCart().getSellerBook().getPrice() *  orders.get(i++).getQuantity(); %>
		<%= Tprice %> Rs.
		</div>
		<div><b>Order Id</b><br />
		${order.orderId}</div>
	</div>
	<div>
		<div class='imgdiv'>
			<img bookId='${order.cart.sellerBook.book.bookId}'; src='data/booksimages/${order.cart.sellerBook.book.bookId}.jpg' />
		</div>
		<div class='odetails'>
			${order.cart.sellerBook.book.bookName} <span class='helptext'> (book)</span>
		</div>
		<div class='odetails'>
			${order.cart.sellerBook.seller.sellerName} <span class='helptext'> (seller)</span>
		</div>
		<div class='odetails'>
			${order.cart.sellerBook.price} Rs. <span class='helptext'> (price)</span>
		</div>
		<div class='odetails'>
			${order.cart.sellerBook.book.author} <span class='helptext'> (author)</span>
		</div>
		<div class='odetails'>
			${order.quantity} <span class='helptext'> (quantity)</span>
		</div>
		<div class='extra'>
			Package reached: 
			<span>
			<% if(order.getCity().getCityName()!=null){%>
			${order.city.cityName}, ${order.city.state.stateName}
			<%}else{%>
				Details Not Available
			<%}%>
			</span>
		</div>
		<div class='extra'>
			Payment Status : <span>${order.orderPayStatus.status}</span>
		</div>
	</div>
</div>
</c:forEach>
<link rel='stylesheet' type='text/css' href='css/custrackorders.css' />
