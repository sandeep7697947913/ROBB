package models;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import conpool.DBPool;

public class Book{
	private Integer bookId;
	private String bookName;
	private String isbn;
	private Integer mrp;
	private String author;
	private Publisher publisher;
	private Category category;
	private Integer edition;

	public static ArrayList<Book> getAllBookPublished(Integer publisherId){
		ArrayList<Book> books = new ArrayList<Book>();
		try{
			Connection con = DBPool.getConnection();
			String q1 = "select book_name,isbn,mrp,author,publication,category,edition";
			String q2 = " from books as b inner join publishers as p on p.publisher_id=b.publisher_id"; 
			String q3 = " inner join categories as c on c.category_id = b.category_id where b.publisher_id=?";
			PreparedStatement ps = con.prepareStatement(q1+q2+q3);
			ps.setInt(1,publisherId);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				books.add(
					new Book(
						rs.getString(1),//bookname	
						rs.getString(2),//isbn	
						rs.getInt(3),//mrp	
						rs.getString(4),//author
						new Publisher(rs.getString(5)),//publication
						new Category(rs.getString(6)),//category
						rs.getInt(7)//edition
					)	
				);
			}
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return books;
	}

	public ArrayList<Book> getCategoryBooks(){
		ArrayList<Book> books = new ArrayList<Book>();
		try{
			Connection con = DBPool.getConnection();
			String q1 = "select book_name,isbn,author,mrp from books";
			String q2 = " inner join categories on categories.category_id=books.category_id";
			String q3 = " where categories.category=? and publisher_id=?";
			PreparedStatement ps = con.prepareStatement(q1+q2+q3);
			ps.setString(1,category.getCategory());
			ps.setInt(2,publisher.getPublisherId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Book book = new Book(rs.getString(1),rs.getString(2));//name ,isbn	
				book.setAuthor(rs.getString(3));//auhtor
				book.setMrp(rs.getInt(4));
				books.add(book);
			}
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}	
		return books;
	}

	public ArrayList<Book> getAuthorBooks(){
		ArrayList<Book> books = new ArrayList<Book>();
		try{
			Connection con = DBPool.getConnection();
			String q1 = "select book_name,isbn,category,mrp from books";
			String q2 = " inner join categories on categories.category_id=books.category_id";
			String q3 = " where author=? and publisher_id=?";
			PreparedStatement ps = con.prepareStatement(q1+q2+q3);
			ps.setString(1,author);
			ps.setInt(2,publisher.getPublisherId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Book book = new Book(rs.getString(1),rs.getString(2));//name ,isbn	
				book.setCategory(new Category(rs.getString(3)));
				book.setMrp(rs.getInt(4));
				books.add(book);
			}
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}	
		return books;
	}

	public static ArrayList<String> getMatchAuthors(String auth){
		ArrayList<String> authors = new ArrayList<String>();
		try{
			Connection con = DBPool.getConnection();
			String q1 = "select distinct author from books where author like ?";
			PreparedStatement ps = con.prepareStatement(q1);
			ps.setString(1,auth+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				authors.add(rs.getString(1));
			}
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return authors;
	}

	public Boolean getPublisherBook(){
		Boolean flag = false;
		try{
			Connection con = DBPool.getConnection();
			String q1 = "select book_id,book_name,mrp,author,category,edition";
			String q2 = " from books inner join categories on categories.category_id=books.category_id";
			String q3 = " where books.publisher_id=? and isbn=?";
		
			PreparedStatement ps = con.prepareStatement(q1+q2+q3);

			ps.setInt(1,publisher.getPublisherId());
			ps.setString(2,isbn);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				bookId = rs.getInt(1);
				bookName = rs.getString(2);
				mrp = rs.getInt(3);
				author = rs.getString(4);
				category = new Category(rs.getString(5));
				edition = rs.getInt(6);
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

	public static Book findBookIsbn(String isbn){
		Book book = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookrent?user=root&password=1234");
			
			String q1 = "select book_id,book_name,isbn,mrp,author,publication,category,edition";
			String q2 = " from books as b inner join categories as c inner join publishers as p";
			String q3 = " where isbn=? and b.category_id=c.category_id and b.publisher_id=p.publisher_id";
			
			PreparedStatement ps = con.prepareStatement(q1+q2+q3);
			ps.setString(1,isbn);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				book = new Book(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getString(5),new Publisher(rs.getString(6)),new Category(rs.getString(7)),rs.getInt(8));
			}
			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}
		return book;
	}
	
	public static Book findBookBookId(Integer bookId){
		Book book = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookrent?user=root&password=1234");
			
			String q1 = "select book_id,book_name,mrp,author,category,edition";
			String q2 = " from books as b inner join categories as c where";
			String q3 = " b.category_id=c.category_id and b.book_id = ? ";
			
			PreparedStatement ps = con.prepareStatement(q1+q2+q3);

			ps.setInt(1,bookId);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				book = new Book(
					rs.getInt(1),
					rs.getString(2),
					rs.getInt(3),
					rs.getString(4),
						new Category(rs.getString(5)),
					rs.getInt(6)
				);
			}
			con.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return book;
	}

	public Boolean savePPBook(){
		Boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookrent?user=root&password=1234");
			String q1 = "insert into books (book_name,isbn,mrp,author,publisher_id,category_id,edition)";
			String q2 = " value (?,?,?,?,?,?,?)";
			PreparedStatement ps = con.prepareStatement(q1+q2,Statement.RETURN_GENERATED_KEYS);
			ps.setString(1,bookName);
			ps.setString(2,isbn);
			ps.setInt(3,mrp);
			ps.setString(4,author);
			ps.setInt(5,publisher.getPublisherId());
			ps.setInt(6,category.getCategoryId());
			ps.setInt(7,edition);

			ps.executeUpdate();
			
			ResultSet rs = ps.getGeneratedKeys();
			if(rs.next()){
				bookId = rs.getInt(1);
				flag = true;
			}

			con.close();
		}catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

	//constructors
	public Book(){
	
	}

	public Book(Publisher publisher){
		this.publisher = publisher;
	}

	public Book(String bookName){
		this.bookName = bookName;
	}

	public Book(String bookName,String isbn){
		this.bookName = bookName;
		this.isbn = isbn;
	}

	public Book(Integer bookId,String bookName,String author){
		this.bookId = bookId;
		this.bookName = bookName;
		this.author = author;
	}

	public Book(Integer bookId,String bookName,Integer mrp,String author){
		this.bookId = bookId;
		this.bookName = bookName;
		this.mrp = mrp;
		this.author = author;
	}

	public Book(Integer bookId,String bookName,Integer mrp,String author,Category category,Integer edition){
		this(bookId,bookName,mrp,author);
		this.category = category;
		this.edition = edition;
	}
	
	public Book(String bookName,String isbn,Integer mrp,String author,Publisher publisher,Category category,Integer edition){
		this.bookName = bookName;
		this.isbn = isbn;
		this.mrp = mrp;
		this.author = author;
		this.publisher = publisher;
		this.category = category;
		this.edition = edition;
	}

	public Book(Integer bookId,String bookName,String isbn,Integer mrp,String author,Publisher publisher,Category category,Integer edition){
		this.bookId = bookId;
		this.bookName = bookName;
		this.isbn = isbn;
		this.mrp = mrp;
		this.author = author;
		this.publisher = publisher;
		this.category = category;
		this.edition = edition;
	}

	//setter methods

	public void setBookId(Integer bookId){
		this.bookId = bookId;
	}

	public void setBookName(String bookName){
		this.bookName = bookName;
	}

	public void setIsbn(String isbn){
		this.isbn = isbn;
	}

	public void setMrp(Integer mrp){
		this.mrp = mrp;
	}

	public void setAuthor(String author){
		this.author = author;
	}

	public void setPublisher(Publisher publisher){
		this.publisher = publisher;
	}

	public void setCategory(Category category){
		this.category = category;
	}

	public void setEdition(Integer edition){
		this.edition = edition;
	}

	//getter methods
	public Integer getBookId(){
		return bookId;
	}

	public String getBookName(){
		return bookName;
	}

	public String getIsbn(){
		return isbn;
	}

	public Integer getMrp(){
		return mrp;
	}

	public String getAuthor(){
		return author;
	}

	public Publisher getPublisher(){
		return publisher;
	}

	public Category getCategory(){
		return category;
	}

	public Integer getEdition(){
		return edition;
	}
}