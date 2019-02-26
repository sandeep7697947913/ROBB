package models;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.util.ArrayList;

import conpool.DBPool;

public class Order{
	private Integer orderId;
	private Cart cart;
	private Courier courier;
	private Integer quantity;
	private OrderPayStatus orderPayStatus;
	private OrderStatus orderStatus;
	private City city;
	private java.sql.Date date;

	public static ArrayList<Order> TrackOrders(Integer customerId){
		ArrayList<Order> orders = new ArrayList<Order>();
		try{
			Connection con = DBPool.getConnection();
			
			String q1 = "select o.order_id,sl.seller_name,b.book_id,b.book_name,b.author,sb.price,";
			String q2 = "o.quantity,o.date,ops.status,os.status,ct.city_name,st.state_name";
			String q3 = " from orders as o inner join carts as crt inner join customers as cus";
			String q4 = " inner join order_status as os inner join order_pay_status as ops";
			String q5 = " inner join books as b inner join seller_books as sb inner join sellers as sl";
			String q6 = " left join cities as ct on o.city_id=ct.city_id left join states as st on st.state_id = ct.state_id";
			String q7 = " where o.cart_id = crt.cart_id and crt.customer_id = cus.customer_id";
			String q8 = " and crt.seller_book_id = sb.seller_book_id and sb.seller_id = sl.seller_id";
			String q9 = " and sb.book_id = b.book_id and o.order_status_id = os.order_status_id";
			String q10 = " and o.order_pay_status_id = ops.order_pay_status_id";
			String q11 = " and cus.customer_id=? and os.order_status_id in (1,2,3)";
			
			PreparedStatement ps = con.prepareStatement(q1+q2+q3+q4+q5+q6+q7+q8+q9+q10+q11);
			ps.setInt(1,customerId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				orders.add(
					new Order(
						rs.getInt(1),
						new Cart(
							new SellerBook(
								new Seller(rs.getString(2)),//seller
								new Book(
									rs.getInt(3),//bookid
									rs.getString(4),//bookname
									rs.getString(5)//author
								),//book
								rs.getInt(6)//price
							)//sellerBook
						),//cart
						rs.getInt(7),//oquantity
						rs.getDate(8),//odate
						new OrderPayStatus(rs.getString(9)),//opy.status
						new OrderStatus(rs.getString(10)),//os.status
						new City(
							rs.getString(11),//cityName
							new State(rs.getString(12))//st.stateName
						)//city
					)//order	
				);
			}

			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return orders;
	}

	public static ArrayList<Order> getCustOrders(Integer customerId){
		ArrayList<Order> orders = new ArrayList<Order>();
		try{
			Connection con = DBPool.getConnection();
			String q1 = "select o.order_id,s.seller_name,b.book_id,b.book_name,";
			String q2 = "b.author,sb.price,o.quantity,o.date from orders as o";
			String q3 = " inner join carts as crt inner join customers as cus";
			String q4 = " inner join seller_books as sb inner join sellers as s";
			String q5 = " inner join books as b where crt.cart_id = o.cart_id";
			String q6 = " and crt.customer_id = cus.customer_id";
			String q7 = " and crt.seller_book_id = sb.seller_book_id";
			String q8 = " and sb.seller_id = s.seller_id and sb.book_id = b.book_id";
			String q9 = " and order_status_id=4 and cus.customer_id=?"; 
			PreparedStatement ps = con.prepareStatement(q1+q2+q3+q4+q5+q6+q7+q8+q9);
			ps.setInt(1,customerId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				orders.add(
					new Order(
						rs.getInt(1),
						new Cart(
							new SellerBook(
								new Seller(rs.getString(2)),//seller
								new Book(
									rs.getInt(3),//bookid
									rs.getString(4),//bookname
									rs.getString(5)//author
								),//book
								rs.getInt(6)//price
							)//sellerBook
						),//cart
						rs.getInt(7),//oquantity
						rs.getDate(8)//odate
					)//order
				);
			}
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return orders;
	}

	public Boolean delivered(){
		Boolean flag = false;
		
		try{
			Connection con = DBPool.getConnection();
			
			String q1 = "update orders set order_status_id=4,";
			String q2 ="order_pay_status_id=2 where order_id=?";

			PreparedStatement ps = con.prepareStatement(q1+q2);
			ps.setInt(1,orderId);
			int i = ps.executeUpdate();
			if(i==1){
				flag = true;
			}
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}

		return flag;
	}

	public Boolean updateCity(){
		Boolean flag = false;
		try{
			Connection con = DBPool.getConnection();
			String q1 = "update orders set city_id=? where order_id=?";
			PreparedStatement ps = con.prepareStatement(q1);
			ps.setInt(1,city.getCityId());
			ps.setInt(2,orderId);
			int i = ps.executeUpdate();
			if(i==1){
				flag = true;
			}
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	public Boolean getOrderCustomerDetail(){
		Boolean flag = false;
		try{
			Connection con = DBPool.getConnection();
			String q1 = "select date,customer_name,cus.email,cus.address,city_name,state_name";
			String q2 = " from orders as o inner join carts as crt inner join customers as cus";
			String q3 = " inner join cities as ct inner join states as st";
			String q4 = " inner join couriers as crr where";
			String q5 = " o.cart_id = crt.cart_id and crt.customer_id = cus.customer_id";
			String q6 = " and cus.city_id = ct.city_id and st.state_id = ct.state_id";
			String q7 = " and crr.courier_id = o.courier_id and o.order_status_id=3";
			String q8 = " and order_id=? and crr.courier_id=?";
			
			PreparedStatement ps = con.prepareStatement(q1+q2+q3+q4+q5+q6+q7+q8);
			ps.setInt(1,orderId);
			ps.setInt(2,courier.getCourierId());

			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				date = rs.getDate(1);
				cart = new Cart(
					new Customer(
						rs.getString(2),//name
						rs.getString(3),//email
						rs.getString(4),//address
						new City(
							rs.getString(5),//city Name
							new State(
								rs.getString(6)//statename
							)
						)//city
					)//customer
				);// cart
				flag = true;
			}
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		return flag;
	}

	public Boolean updateOrderStatus(){
		Boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookrent?user=root&password=1234");
			String q1 = "update orders set order_status_id=3 where order_id=?";
			PreparedStatement ps = con.prepareStatement(q1);
			ps.setInt(1,orderId);
			int i = ps.executeUpdate();
			if(i==1){
				flag = true;
			}
			con.close();
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

	public static Order getOrderDetail(Integer orderId,Courier courier){
		Order order = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookrent?user=root&password=1234");
			
			String q1 = "select customer_name,cus.email,cus.phone_no,cus.address,";
			String q2 = "ct.city_name,sta.state_name,book_name,sb.price,o.quantity,o.date,ops.status from orders as o";
			String q3 = " inner join carts as c inner join seller_books as sb inner join books as book";
			String q4 = " inner join sellers as sel inner join couriers as cou inner join customers as cus";
			String q5 = " inner join cities as ct inner join states as sta inner join order_pay_status as ops";
			String q6 = " where c.cart_id=o.cart_id and c.seller_book_id = sb.seller_book_id and sb.book_id = book.book_id";
			String q7 = " and sb.seller_id = sel.seller_id and cus.customer_id = c.customer_id and cus.city_id = ct.city_id";
			String q8 = " and sta.state_id = ct.state_id and o.order_status_id=2";
			String q9 = " and ops.order_pay_status_id = o.order_pay_status_id and cou.courier_id=o.courier_id and";
			String q10 = " cou.courier_id=? and o.order_id=?";
			
			PreparedStatement ps = con.prepareStatement(q1+q2+q3+q4+q5+q6+q7+q8+q9+q10);
			ps.setInt(1,courier.getCourierId());
			ps.setInt(2,orderId);

			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				order = new Order(
					orderId,
					new Cart(
						new Customer(
							rs.getString(1),//name
							rs.getString(2),//email
							rs.getString(3),//phone no
							rs.getString(4),//addres
							new City(
								rs.getString(5),//city name
								new State(rs.getString(6))//state name
							)// city
						),
						new SellerBook(
							new Book(rs.getString(7)),// book_name
							rs.getInt(8)//price
						)
					),// cart
					rs.getInt(9),//quantity
					rs.getDate(10),
					new OrderPayStatus(rs.getString(11))
				);
			}
			con.close();
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}

		return order;
	}

	public Boolean setCourier(){
		Boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.01:3306/bookrent?user=root&password=1234");
			String query = "update orders set courier_id=?,order_status_id=2 where order_id=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setInt(1,courier.getCourierId());
			ps.setInt(2,orderId);
			int i = ps.executeUpdate();
			if(i==1){
				flag = true;
			}
			con.close();
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

	static public ArrayList<Order> getSellerOrders(Integer sellerId){
		ArrayList<Order> orders = new ArrayList<Order>();
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookrent?user=root&password=1234");
				String q1 = "select order_id,crts.cart_id,customer_name,email,address,";
				String q2 = "city_name,state_name,b.book_name,isbn,sb.price,o.quantity,o.date from orders as o";
				String q3 = " inner join carts as crts inner join seller_books as sb";
				String q4 = " inner join books as b inner join customers as cs";
				String q5 = " inner join cities as cts inner join states as sts where o.order_status_id=1";
				String q6 = " and crts.cart_id=o.cart_id and crts.seller_book_id=sb.seller_book_id";
				String q7 = " and sb.seller_id=? and b.book_id = sb.book_id and";
				String q8 = " crts.customer_id=cs.customer_id and cts.city_id = cs.city_id";
				String q9 = " and sts.state_id = cts.state_id order by order_id asc";
				PreparedStatement ps = con.prepareStatement(q1+q2+q3+q4+q5+q6+q7+q8+q9);
				ps.setInt(1,sellerId);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					orders.add(
						new Order(
							rs.getInt(1),//orderId
							new Cart(
								rs.getInt(2),//cartId
								new Customer(
									rs.getString(3),//name
									rs.getString(4),//email
									rs.getString(5),//address
									new City(rs.getString(6),//cityName
										new State(
											rs.getString(7)//stateName
										)//state
									)//cityName
								),//customer
								new SellerBook(
									new Book(
										rs.getString(8),//bookName
										rs.getString(9)//ISBN
									),
									rs.getInt(10)//seling price
								)//sellerBook
							),//cart
							rs.getInt(11),//qunatity
							rs.getDate(12)// date
						)//order construct
					);//ordersadd
				}
				con.close();
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}catch(SQLException e){
				e.printStackTrace();
			}
		return orders;
	}

	public static Boolean aCODOrdersOfCarts(ArrayList<Cart> carts){
		Boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con =  DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookrent?user=root&password=1234");
			String query = "insert into orders (cart_id,quantity) value (?,?)";
			for(Cart cart : carts){
				PreparedStatement ps = con.prepareStatement(query);
				ps.setInt(1,cart.getCartId());
				ps.setInt(2,cart.getQuantity());
				int i = ps.executeUpdate();
				if(i==1){
					if(cart.changeCartStatus()){
						flag = true;
					}
				}
			}
			con.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

	//constructors

	public Order(){
	
	}

	public Order(Integer orderId){
		this.orderId = orderId;
	}

	public Order(Integer orderId,Courier courier){
		this.orderId = orderId;
		this.courier = courier;
	}

	public Order(Integer orderId,Cart cart,Integer quantity,java.sql.Date date){
		this.orderId = orderId;
		this.cart = cart;
		this.quantity = quantity;
		this.date = date;
	}

	public Order(Integer orderId,Cart cart,Integer quantity,java.sql.Date date,OrderPayStatus orderPayStatus){
		this.orderId = orderId;
		this.cart = cart;
		this.quantity = quantity;
		this.date = date;
		this.orderPayStatus = orderPayStatus;
	}

	public Order(Integer orderId,Cart cart,Integer quantity,java.sql.Date date,OrderPayStatus orderPayStatus,OrderStatus orderStatus,City city){
		this.orderId = orderId;
		this.cart = cart;
		this.quantity = quantity;
		this.date = date;
		this.orderPayStatus = orderPayStatus;
		this.orderStatus = orderStatus;
		this.city = city;
	}

	public Order(Integer orderId,Cart cart,Courier courier,Integer quantity,java.sql.Date date,OrderStatus orderStatus,City city){
		this.orderId = orderId;
		this.cart = cart;
		this.courier = courier;
		this.quantity = quantity;
		this.date = date;
		this.orderStatus = orderStatus;
		this.city = city;
	}

	//setter methods

	public void setOrderId(Integer orderId){
		this.orderId = orderId;
	}

	public void setCart(Cart cart){
		this.cart = cart;
	}

	public void setCourier(Courier courier){
		this.courier = courier;
	}

	public void setQuantity(Integer quantity){
		this.quantity = quantity;
	}

	public void setOrderStatus(OrderStatus orderStatus){
		this.orderStatus = orderStatus;
	}

	public void setCity(City city){
		this.city = city;
	}

	public void setDate(java.sql.Date date){
		this.date  = date;
	}

	public void setOrderPayStatus(OrderPayStatus orderPayStatus){
		this.orderPayStatus = orderPayStatus;	
	}

	//getter methods

	public Integer getOrderId(){
		return orderId;
	}

	public Cart getCart(){
		return cart;
	}

	public Courier getCourier(){
		return courier;
	}

	public Integer getQuantity(){
		return quantity;
	}

	public OrderStatus getOrderStatus(){
		return orderStatus;
	}
	
	public City getCity(){
		return city;
	}

	public java.sql.Date getDate(){
		return date;
	}

	public OrderPayStatus getOrderPayStatus(){
		return orderPayStatus;
	}
}