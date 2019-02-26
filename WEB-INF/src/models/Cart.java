package models;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class Cart{
	private Integer cartId;
	private Customer customer;
	private SellerBook sellerBook;
	private Integer quantity;
	private CartStatus cartStatus;

	// methods

	public Boolean changeCartStatus(){
		Boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con =  DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookrent?user=root&password=1234");
			String query = "update carts set cart_status_id=1 where cart_id=?";
			PreparedStatement ps = con.prepareStatement(query); 
			ps.setInt(1,cartId);
			int i = ps.executeUpdate();
			if(i==1){
				flag = true;
			}
		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return flag;
	}
	
	public Boolean updateBookQuantity(){
		Boolean flag = false;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookrent?user=root&password=1234");
			String q1 =  "update carts set quantity=? where customer_id=? and seller_book_id=? and cart_status_id = 2";
			PreparedStatement ps = con.prepareStatement(q1);
			ps.setInt(1,quantity);
			ps.setInt(2,customer.getCustomerId());
			ps.setInt(3,sellerBook.getSellerBookId());
			int i = ps.executeUpdate();
			if(i==1){
				flag = true;
			}
			con.close();
		}catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();
		}

		return flag;
	}

	public static ArrayList<Cart> getCartItems(Customer customer){
		ArrayList<Cart> cartItems = new ArrayList<Cart>();
		
		try{	
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookrent?user=root&password=1234");
			/* works 1
			String q1 ="select crt.seller_book_id,b.book_id,b.book_name,author,sb.price,crt.quantity from carts as crt";
			String q2 =" inner join seller_books as sb inner join books as b where sb.seller_book_id=crt.seller_book_id";
			String q3 =" and sb.book_id=b.book_id and crt.customer_id=?";
			*/
			/*works2
			String q1 ="select crt.seller_book_id,seller_name,b.book_id,b.book_name,mrp,author,sb.price,crt.quantity from carts as crt";
			String q2 =" inner join seller_books as sb inner join books as b inner join sellers as s where sb.seller_book_id=crt.seller_book_id";
			String q3 =" and sb.book_id=b.book_id and s.seller_id = sb.seller_id and crt.customer_id=? and crt.cart_status_id = 2";
			*/
			String q1 ="select crt.cart_id,crt.seller_book_id,seller_name,b.book_id,b.book_name,mrp,author,sb.price,crt.quantity from carts as crt";
			String q2 =" inner join seller_books as sb inner join books as b inner join sellers as s where sb.seller_book_id=crt.seller_book_id";
			String q3 =" and sb.book_id=b.book_id and s.seller_id = sb.seller_id and crt.customer_id=? and crt.cart_status_id = 2";
			
			PreparedStatement ps = con.prepareStatement(q1+q2+q3);
			
			ps.setInt(1,customer.getCustomerId());

			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				cartItems.add( //works 2 
					new Cart(
						rs.getInt(1),
						new SellerBook(
							rs.getInt(2),
							new Seller(rs.getString(3)),
							new Book(rs.getInt(4),rs.getString(5),rs.getInt(6),rs.getString(7)),	
							rs.getInt(8)
						),
						rs.getInt(9)	
					)	
				);
				/*cartItems.add( works 1
					new Cart(
						new SellerBook(
							rs.getInt(1),
							new Book(rs.getInt(2),rs.getString(3),rs.getString(4)),	
							rs.getInt(5)
						),
						rs.getInt(6)	
					)	
				);*/
				/*cartItems.add( //works 2 
					new Cart(
						new SellerBook(
							rs.getInt(1),
							new Seller(rs.getString(2)),
							new Book(rs.getInt(3),rs.getString(4),rs.getInt(5),rs.getString(6)),	
							rs.getInt(7)
						),
						rs.getInt(8)	
					)	
				);*/
			}

			con.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
	
		return cartItems;
	}

	public String addBookToCart(){
		String flag = String.valueOf(false);
		try{
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookrent?user=root&password=1234");
			String q1 = "insert into carts (customer_id,seller_book_id,quantity) value(?,?,?)";
			PreparedStatement ps = con.prepareStatement(q1);
			ps.setInt(1,customer.getCustomerId());
			ps.setInt(2,sellerBook.getSellerBookId());
			ps.setInt(3,quantity);

			int i = ps.executeUpdate();

			if(i==1){
				flag = String.valueOf(true);
			}
			con.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			if(e.getErrorCode()==1062){// 1062 is error code of duplicate entry for mysql 
				flag = "duplicate";
			}
		}

		return flag;
	}

	public Boolean removeItem(){
		Boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookrent?user=root&password=1234");
			
			String q1 = "delete from carts where customer_id=? and seller_book_id=? and cart_status_id = 2";
			
			PreparedStatement ps = con.prepareStatement(q1);
			ps.setInt(1,customer.getCustomerId());
			ps.setInt(2,sellerBook.getSellerBookId());
			
			int i = ps.executeUpdate();
			if(i==1){
				flag = true;
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
	public Cart(){
	
	}

	public Cart(Customer customer){
		this.customer = customer;
	}

	public Cart(SellerBook sellerBook){
		this.sellerBook = sellerBook;
	}

	public Cart(Integer cartId,SellerBook sellerBook,Integer quantity){
		this.cartId = cartId;
		this.sellerBook = sellerBook;
		this.quantity = quantity;
	}

	public Cart(SellerBook sellerBook,Integer quantity){
		this.sellerBook = sellerBook;
		this.quantity = quantity;
	}

	public Cart(Customer customer,SellerBook sellerBook){
		this.customer = customer;
		this.sellerBook = sellerBook;
	}

	public Cart(Customer customer,SellerBook sellerBook,Integer quantity){
		this.customer = customer;
		this.sellerBook = sellerBook;
		this.quantity = quantity;
	}

	public Cart(Integer cartId,Customer customer,SellerBook sellerBook,Integer quantity){
		this.cartId = cartId;
		this.customer = customer;
		this.sellerBook = sellerBook;
		this.quantity = quantity;
	}

	public Cart(Integer cartId,Customer customer,SellerBook sellerBook){
		this.cartId = cartId;
		this.customer = customer;
		this.sellerBook = sellerBook;
	}


	//setter methods

	public void setCartId(Integer cartId){
		this.cartId = cartId;
	}

	public void setCustomer(Customer customer){
		this.customer = customer;
	}

	public void setSellerBook(SellerBook sellerBook){
		this.sellerBook = sellerBook;
	}

	public void setQuantity(Integer quantity){
		this.quantity = quantity;
	}

	public void setCartStatus(CartStatus cartStatus){
		this.cartStatus = cartStatus;
	}

	// getter methods

	public Integer getCartId(){
		return cartId;
	}

	public Customer getCustomer(){
		return customer;
	}

	public SellerBook getSellerBook(){
		return sellerBook;
	}

	public Integer getQuantity(){
		return quantity;
	}

	public CartStatus getCartStatus(){
		return cartStatus;
	}
}