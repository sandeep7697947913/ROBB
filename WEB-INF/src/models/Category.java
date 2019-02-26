package models;

import java.util.ArrayList;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import conpool.DBPool;

public class Category{
	private Integer categoryId;
	private String category;

	//other methods

	public static ArrayList<String> getMatchCategories(String category){
		ArrayList<String> categories = new ArrayList<String>();
		try{
			Connection con = DBPool.getConnection();
			String q1 = "select category from categories where category like ?";
			PreparedStatement ps = con.prepareStatement(q1);
			ps.setString(1,category+"%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				categories.add(rs.getString(1));
			}
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return categories;
	}

	public static ArrayList<Category> collectCategories(){
		ArrayList<Category> categories = new ArrayList<Category>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookrent?user=root&password=1234");
			String q1 = "select * from categories";
			PreparedStatement ps = con.prepareStatement(q1);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				categories.add(new Category(rs.getInt(1),rs.getString(2)));
			}
			con.close();
		}catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();
		}
		return categories;
	}

	//constructors

	public Category(){
	
	}

	public Category(Integer categoryId){
		this.categoryId = categoryId;
	}

	public Category(String category){
		this.category = category;
	}

	public Category(Integer categoryId,String category){
		this.categoryId = categoryId;
		this.category = category;
	}

	//setter methods

	public void setCategoryId(Integer categoryId){
		this.categoryId = categoryId;
	}

	public void setCategory(String category){
		this.category = category;
	}

	//getter methods

	public Integer getCategoryId(){
		return categoryId;
	}

	public String getCategory(){
		return category;
	}
}