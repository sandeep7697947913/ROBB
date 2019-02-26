package models;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

import conpool.DBPool;

public class Customer{
	private Integer customerId;
	private String customerName;
	private String email;
	private String password;
	private String phoneNo;
	private String address;
	private City city;
	private Status status;
	private String picPath;

	// other methods

	public Boolean isExists(){
		Boolean flag = false;
		try{
			Connection con = DBPool.getConnection();
			String q1 = "select customer_id from customers where email=?";
			PreparedStatement ps = con.prepareStatement(q1);
			ps.setString(1,email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				customerId = rs.getInt(1);
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
			String q1 = "update customers set password=? where customer_id=?";
			PreparedStatement ps = con.prepareStatement(q1);
			ps.setString(1,password);
			ps.setInt(2,customerId);

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

	public Boolean saveCustomerDetails(){
		Boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookrent?user=root&password=1234"); 
			String query = "update customers set customer_name=?,phone_no=?,address=?,city_id=?,pic_path=? where email=? and password=?";
			PreparedStatement ps = con.prepareStatement(query);
			ps.setString(1,customerName);
			ps.setString(2,phoneNo);
			ps.setString(3,address);
			ps.setInt(4,city.getCityId());
			ps.setString(5,picPath);
			ps.setString(6,email);
			ps.setString(7,password);

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

	public Boolean loginCustomer(){
		Boolean flag = false;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/bookrent?user=root&password=1234");
			
			String q1 ="select customer_id,pic_path,address from customers where email=? and password=?"; 

			PreparedStatement ps = con.prepareStatement(q1);
			ps.setString(1,email);
			ps.setString(2,password);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				flag = true;
				customerId = rs.getInt(1);
				picPath = rs.getString(2);
				address = rs.getString(3);
			}

			con.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return flag;
	}

	public Boolean registerCustomer(){
		Boolean flag = false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookrent?user=root&password=1234");
			String q1 = "insert into customers (email,password) value (?,?)";
			PreparedStatement ps = con.prepareStatement(q1);
			ps.setString(1,email);
			ps.setString(2,password);

			int i = ps.executeUpdate();

			if(i!=0){
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
	public Customer(){
	
	}

	public Customer(String email,String password){
		this.email = email;
		this.password = password;
	}

	public Customer(String customerName,String email,String address,City city){
		this.customerName = customerName;
		this.email = email;
		this.address = address;
		this.city = city;
	}

	public Customer(String customerName,String email,String phoneNo,String address,City city){
		this.customerName = customerName;
		this.email = email;
		this.phoneNo = phoneNo;
		this.address = address;
		this.city = city;
	}

	public Customer(Integer customerId,String customerName,String email,String password,String phoneNo,String address,City city,Status status){
		this.customerId = customerId;
		this.customerName = customerName;
		this.email = email;
		this.password = password;
		this.phoneNo = phoneNo;
		this.address = address;
		this.city = city;
		this.status = status;
	}

	//setter methods

	public void setCustomerId(Integer customerId){
		this.customerId = customerId;
	}

	public void setCustomerName(String customerName){
		this.customerName = customerName;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public void setPhoneNo(String phoneNo){
		this.phoneNo = phoneNo;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public void setCity(City city){
		this.city = city;
	}

	public void setStatus(Status status){
		this.status = status;
	}

	public void setPicPath(String picPath){
		this.picPath = picPath;
	}

	//getter methods

	public Integer getCustomerId(){
		return customerId;
	}

	public String getCustomerName(){
		return customerName;
	}

	public String getEmail(){
		return email;
	}

	public String getPassword(){
		return password;
	}

	public String getPhoneNo(){
		return phoneNo;	
	}

	public String getAddress(){
		return address;
	}

	public City getCity(){
		return city;
	}

	public Status getStatus(){
		return status;	
	}

	public String getPicPath(){
		return picPath;
	}
}