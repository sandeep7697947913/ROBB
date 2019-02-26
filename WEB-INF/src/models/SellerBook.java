package models;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

import conpool.DBPool;

public class SellerBook{
	private Integer sellerBookId;
	private Seller seller;
	private Book book;
	private Integer quantity;
	private Integer price;

	public static ArrayList<SellerBook> findBookForCustomer(String bookname){
		ArrayList<SellerBook> books = new ArrayList<SellerBook>();
		
		try{
			Connection con = DBPool.getConnection();
			String q1 = "select sb.book_id,sb.seller_book_id,book_name,mrp,author,price,seller_name";
			String q2 = " from seller_books as sb inner join books as b inner join sellers as sl";
			String q3 = " where b.book_id=sb.book_id and sl.seller_id = sb.seller_id";
			String q4 = " and book_name like ?";
			PreparedStatement ps = con.prepareStatement(q1+q2+q3+q4);
			ps.setString(1,"%"+bookname+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				SellerBook sb = new SellerBook(
									rs.getInt(2),
									new Book(
										rs.getInt(1),
										rs.getString(3),
										rs.getInt(4),
										rs.getString(5)
									),
									rs.getInt(6)
								);
				sb.setSeller(new Seller(rs.getString(7)));
				books.add(sb);
			}
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}

		return books;
	}

	public static ArrayList<String> getSellingBookNames(){
		ArrayList<String> bookNames = new ArrayList<String>();
		try{
			Connection con = DBPool.getConnection();
			String q1 = "select distinct book_name from books";
			String q2 = " as b inner join seller_books as sb on";
			String q3 = " b.book_id=sb.book_id";
			PreparedStatement ps = con.prepareStatement(q1+q2+q3);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				bookNames.add(rs.getString(1));
			}
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return bookNames;
	}

	public static ArrayList<SellerBook> findPublishedBooksSellers(Integer publisherId){
		ArrayList<SellerBook> sellerBooks = new ArrayList<SellerBook>();
		
		try{
			Connection con = DBPool.getConnection();
			String q1 = "select book_name,isbn,mrp,author,category,sl.seller_name,price from books as b";
			String q2 = " inner join seller_books as sb on b.book_id=sb.book_id";
			String q3 = " inner join sellers as sl on sl.seller_id=sb.seller_id";
			String q4 = " inner join publishers as p on p.publisher_id=b.publisher_id";
			String q5 = " inner join categories as c on c.category_id=b.category_id";
			String q6 = " where p.publisher_id=?";
			PreparedStatement ps = con.prepareStatement(q1+q2+q3+q4+q5+q6);
			ps.setInt(1,publisherId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Book book = new Book(rs.getString(1),rs.getString(2));//name,isbn
				book.setMrp(rs.getInt(3));//mrp
				book.setAuthor(rs.getString(4));//author
				book.setCategory(new Category(rs.getString(5)));//category
				sellerBooks.add(
					new SellerBook(
						new Seller(rs.getString(6)),
						book,
						rs.getInt(7)
					)//sellerBook	
				);
			}
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return sellerBooks;
	}

	public Boolean changePriceQuantity(){
		Boolean flag = false;
		try{
			Connection con = DBPool.getConnection();
			String q1 = "update seller_books as sbks,";
			String q2 = " (select sb.quantity from seller_books as sb";
			String q3 = " inner join sellers as sl on sl.seller_id=sb.seller_id";
			String q4 = " where sl.seller_id=? and sb.book_id=?) as sbn";
			String q5 = " set sbks.quantity = (select sum(sbn.quantity+?)),";
			String q6 = "sbks.price=? where sbks.seller_id=? and sbks.book_id=?";
			
			PreparedStatement ps = con.prepareStatement(q1+q2+q3+q4+q5+q6);
			ps.setInt(1,seller.getSellerId());
			ps.setInt(2,book.getBookId());
			ps.setInt(3,quantity);
			ps.setInt(4,price);
			ps.setInt(5,seller.getSellerId());
			ps.setInt(6,book.getBookId());
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

	public Boolean getSSBDetails(){
		Boolean flag = false;
		try{
			Connection con = DBPool.getConnection();
			String q1 = "select sb.book_id,b.book_name,b.author,b.mrp,sb.quantity,sb.price";
			String q2 = " from seller_books as sb inner join sellers as sl on sl.seller_id=sb.seller_id";
			String q3 = " inner join books as b on b.book_id=sb.book_id where b.isbn =? and sl.seller_id=?";
			PreparedStatement ps = con.prepareStatement(q1+q2+q3);
			ps.setString(1,book.getIsbn());
			ps.setInt(2,seller.getSellerId());
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				book.setBookId(rs.getInt(1));
				book.setBookName(rs.getString(2));
				book.setAuthor(rs.getString(3));
				book.setMrp(rs.getInt(4));
				quantity = rs.getInt(5);
				price = rs.getInt(6);
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

	public static Boolean changeQuantity(Integer orderId){
		Boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookrent?user=root&password=1234");
			String q1 = "update seller_books as sbks,";
			String q2 = " (select quantity from orders where order_id=?) as osb,";
			String q3 = " (select sb.quantity,sb.seller_book_id from seller_books as sb";
			String q4 = " inner join orders as o inner join carts as crt";
			String q5 = " where order_id=? and o.cart_id=crt.cart_id and";
			String q6 = " crt.seller_book_id=sb.seller_book_id) as selbok";
			String q7 = " set sbks.quantity = (select sum(selbok.quantity-osb.quantity))";
			String q8 = " where sbks.seller_book_id = selbok.seller_book_id";
			PreparedStatement ps = con.prepareStatement(q1+q2+q3+q4+q5+q6+q7+q8);
			ps.setInt(1,orderId);
			ps.setInt(2,orderId);
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

	//other methods 

	public static ArrayList<SellerBook> getBookSellers(Integer bookId){
		ArrayList<SellerBook> bookSellersList = new ArrayList<SellerBook>();
			
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookrent?user=root&password=1234");
				
				String q1 = "select seller_book_id,seller_name,price from seller_books as sb inner";
				String q2 = " join sellers as s where s.seller_id = sb.seller_id and book_id=? and";
				String q3 = " not quantity=0 order by price asc";

				PreparedStatement ps = con.prepareStatement(q1+q2+q3);
				ps.setInt(1,bookId);
				
				ResultSet rs = ps.executeQuery();

				while(rs.next()){
					bookSellersList.add(new SellerBook(rs.getInt(1),new Seller(rs.getString(2)),rs.getInt(3)));
				}

				con.close();
			}catch(ClassNotFoundException e){
				e.printStackTrace();
			}catch(SQLException e){
				e.printStackTrace();
			}
		return bookSellersList;
	}

	public static ArrayList<SellerBook> getSellerAllBooks(Seller seller){
		ArrayList<SellerBook> bookList = new ArrayList<SellerBook>();

		try{
			Class.forName("com.mysql.jdbc.Driver");

			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookrent?user=root&password=1234");
			String q1 = "select seller_book_id,b.book_id,book_name,isbn,mrp,author,p.publisher_id,p.publication,c.category_id,";
			String q2 = "c.category,edition,quantity,price from books as b inner join seller_books as sb inner join publishers as p ";
			String q3 = "inner join categories as c where b.book_id=sb.book_id and b.category_id=c.category_id ";
			String q4 = "and p.publisher_id=b.publisher_id and seller_id=?";

			PreparedStatement ps = con.prepareStatement(q1+q2+q3+q4);
		
			ps.setInt(1,seller.getSellerId());
			
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				bookList.add(new SellerBook(rs.getInt(1),
						seller,
						new Book(rs.getInt(2),
							rs.getString(3),
							rs.getString(4),
							rs.getInt(5),
							rs.getString(6),
							new Publisher(rs.getInt(7),rs.getString(8)),
							new Category(rs.getInt(9),rs.getString(10)),
							rs.getInt(11)
						),
						rs.getInt(12),
						rs.getInt(13)
					)
				);
			}
 
			con.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return bookList;
	} 

	public static ArrayList<SellerBook> recentlyAddedBooks(){
		ArrayList<SellerBook> books = new ArrayList<SellerBook>();
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookrent?user=root&password=1234");
			
			String q1 = "select distinct sb.book_id,sb.seller_book_id,book_name,mrp,author,price";
			String q2 = " from seller_books as sb inner join books as b where b.book_id=sb.book_id";
			String q3 = " order by sb.seller_book_id desc LIMIT 10";

			PreparedStatement ps = con.prepareStatement(q1+q2+q3);

			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				books.add(new SellerBook(
					rs.getInt(2),
					new Book(
						rs.getInt(1),
						rs.getString(3),
						rs.getInt(4),
						rs.getString(5)
					),
					rs.getInt(6)
				));
			}

			con.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}

		return books;
	}

	//methods 

	public String sellBook(){
		String flag = String.valueOf(false);

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookrent?user=root&password=1234");
			String q1= "insert into seller_books (seller_id,book_id,quantity,price) value (?,?,?,?)" ;
			PreparedStatement ps = con.prepareStatement(q1);
			ps.setInt(1,seller.getSellerId());
			ps.setInt(2,book.getBookId());
			ps.setInt(3,quantity);
			ps.setInt(4,price);
			
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

	// constuctors

	public SellerBook(){
	
	}

	public SellerBook(Integer sellerBookId){
		this.sellerBookId = sellerBookId;
	}

	public SellerBook(Book book,Integer price){
		this.book = book;
		this.price = price;
	}

	
	public SellerBook(Seller seller,Book book){
		this.seller = seller;
		this.book = book;
	}

	public SellerBook(Integer sellerBookId,Seller seller,Integer price){
		this.sellerBookId = sellerBookId;
		this.seller = seller;
		this.price = price;
	}
 
	public SellerBook(Integer sellerBookId,Book book,Integer price){
		this.sellerBookId = sellerBookId;
		this.book = book;
		this.price = price;
	}

	public SellerBook(Seller seller,Book book,Integer price){
		this.seller = seller;
		this.book = book;
		this.price = price;
	}

	public SellerBook(Integer sellerBookId,Seller seller,Book book,Integer price){
		this.sellerBookId = sellerBookId; //  last modifeid
		this.seller = seller;
		this.book = book;
		this.price = price;
	}

	public SellerBook(Seller seller,Book book,Integer quantity,Integer price){
		this.seller = seller;
		this.book = book;
		this.quantity = quantity;
		this.price = price;
	}

	public SellerBook(Integer sellerBookId,Seller seller,Book book,Integer quantity,Integer price){
		this.sellerBookId = sellerBookId;
		this.seller = seller;
		this.book = book;
		this.quantity = quantity;
		this.price = price;
	}

	//setter methods

	public void setSellerBookId(Integer sellerBookId){
		this.sellerBookId = sellerBookId;
	}

	public void setSeller(Seller seller){
		this.seller = seller;
	}

	public void setBook(Book book){
		this.book = book;
	}

	public void setQuantity(Integer quantity){
		this.quantity = quantity;
	}

	public void setPrice(Integer price){
		this.price = price;
	}

	// getter methods

	public Integer getSellerBookId(){
		return sellerBookId;
	}

	public Seller getSeller(){
		return seller;
	}

	public Book getBook(){
		return book;
	}

	public Integer getQuantity(){
		return quantity;
	}

	public Integer getPrice(){
		return price;
	}
}