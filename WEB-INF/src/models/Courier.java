package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Driver;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import conpool.DBPool;

public class Courier{
	private Integer courierId;
	private String name;
	private String email;
	private String password;
	private City city;
	private String address;
	private Status status;

	public Boolean registerCourier(){
		Boolean flag = false;
		try{
			Connection con = DBPool.getConnection();
			String q1 = "insert into couriers (name,email,password,city_id,address)";
			String q2 = " value (?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(q1+q2);
			ps.setString(1,name);
			ps.setString(2,email);
			ps.setString(3,password);
			ps.setInt(4,city.getCityId());
			ps.setString(5,address);
			int i = ps.executeUpdate();
			if(i==1){
				flag = true;
			}
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

	public Boolean changePassword(){
		Boolean flag = false;
		try{
			Connection con = DBPool.getConnection();
			String q1 = "update couriers set password=? where courier_id=?";
			PreparedStatement ps = con.prepareStatement(q1);
			ps.setString(1,password);
			ps.setInt(2,courierId);
			int i = ps.executeUpdate();
			if(i==1){
				flag = true;
			}
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

	public Boolean isExists(){
		Boolean flag = false;
		try{
			Connection con = DBPool.getConnection();
			String q1 = "select courier_id from couriers where email=?";
			PreparedStatement ps = con.prepareStatement(q1);
			ps.setString(1,email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				courierId = rs.getInt(1);
				flag = true;
			}
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

	public ArrayList<Order> getNewOrders(){
		ArrayList<Order> orders = new ArrayList<Order>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookrent?user=root&password=1234");
			
			String q1 = "select o.order_id,customer_name,cus.email,cus.address,";
			String q2 = "ct.city_name,sta.state_name,sel.seller_name,book_name,sb.price,";
			String q3 = "o.quantity,o.date from orders as o";
			String q4 = " inner join carts as c";
			String q5 = " inner join seller_books as sb";
			String q6 = " inner join books as book";
			String q7 = " inner join sellers as sel";
			String q8 = " inner join couriers as cou";
			String q9 = " inner join customers as cus";
			String q10 = " inner join cities as ct";
			String q11 = " inner join states as sta";
			String q12 = " where c.cart_id=o.cart_id";
			String q13 = " and c.seller_book_id = sb.seller_book_id";
			String q14 = " and sb.book_id = book.book_id";
			String q15 = " and sb.seller_id = sel.seller_id";
			String q16 = " and cus.customer_id = c.customer_id";
			String q17 = " and cus.city_id = ct.city_id";
			String q18 = " and sta.state_id = ct.state_id";
			String q19 =" and o.order_status_id=2";
			String q20 = " and cou.courier_id=o.courier_id and cou.courier_id=?";
			
			PreparedStatement ps = con.prepareStatement(q1+q2+q3+q4+q5+q6+q7+q8+q9+q10+q11+q12+q13+q14+q15+q16+q17+q18+q19+q20);
			ps.setInt(1,courierId);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				orders.add(
					new Order(
						rs.getInt(1),
						new Cart(
							new Customer(
								rs.getString(2),
								rs.getString(3),
								rs.getString(4),
								new City(
									rs.getString(5),
									new State(rs.getString(6))//state
								)//City
							),//customer
							new SellerBook(
								new Seller(rs.getString(7)),//seller
								new Book(rs.getString(8)),//book
								rs.getInt(9)
							)//SellerBook
						),//cart
						rs.getInt(10),
						rs.getDate(11)
					)//order	
				);//add
			}

			con.close();
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}
		return orders;
	}

	static public ArrayList<Courier> getAllCouriers(){
		ArrayList<Courier> couriers = new ArrayList<Courier>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookrent?user=root&password=1234");
			String q1 = "select courier_id,name from couriers";
			PreparedStatement ps = con.prepareStatement(q1);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				couriers.add(
					new Courier(rs.getInt(1),rs.getString(2))
				);	
			}

			con.close();
		}catch(ClassNotFoundException | SQLException e){
			e.printStackTrace();
		}

		return couriers;
	}

	public Boolean loginCourier(){
		Boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookrent?user=root&password=1234");
			String q1 = "select courier_id,name,c.city_id,city_name,address,state_name";
			String q2 = " from couriers c inner join cities as ct inner join states as st";
			String q3 = " where st.state_id = ct.state_id and";
			String q4 = " email=? and password=? and ct.city_id=c.city_id";
			PreparedStatement ps = con.prepareStatement(q1+q2+q3+q4);
			ps.setString(1,email);
			ps.setString(2,password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				courierId = rs.getInt(1);
				name = rs.getString(2);
				city = new City(rs.getInt(3),rs.getString(4),new State(rs.getString(6)));
				address = rs.getString(5);

				flag = true;
			}
			con.close();
		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return flag;
	}

	//constructors
	
	public Courier(){
		
	}

	public Courier(Integer courierId){
		this.courierId = courierId;
	}

	public Courier(Integer courierId,String name){
		this.courierId = courierId;
		this.name = name;
	}

	public Courier(String email,String password){
		this.email = email;
		this.password = password;
	}

	public Courier(String name,String email,String password,City city,String address){
		this.name = name;
		this.email = email;
		this.password = password;
		this.city = city;
		this.address = address;
	}

	public Courier(Integer courierId,String name,String email,String password,City city,String address,Status status){
		this.courierId = courierId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.city = city;
		this.address = address;
		this.status = status;
	}

	//setter methods

	public void setCourierId(Integer courierId){
		this.courierId = courierId;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public void setCity(City city){
		this.city = city;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public void setStatus(Status status){
		this.status = status;
	}

	//getter methods

	public Integer getCourierId(){
		return courierId;
	}

	public String getName(){
		return name;
	}

	public String getEmail(){
		return email;
	}

	public String getPassword(){
		return password;
	}

	public City getCity(){
		return city;
	}

	public String getAddress(){
		return address;
	}

	public Status getStatus(){
		return status;
	}
}