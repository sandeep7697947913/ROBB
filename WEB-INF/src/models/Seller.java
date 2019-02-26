package models;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conpool.DBPool;

public class Seller{
	private Integer sellerId;
	private String sellerName;
	private String gstin;
	private String pancard;
	private String email;
	private String contact;
	private String password;
	private String tollfree;
	private String accountNo;
	private String ifsc;
	private String address;
	private City city;
	private Status status;

	// other methods

	public Boolean isExists(){
		Boolean flag = false;
		try{
			Connection con = DBPool.getConnection();
			String q1 = "select seller_id from sellers where email=?";
			PreparedStatement ps = con.prepareStatement(q1);
			ps.setString(1,email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				sellerId = rs.getInt(1);
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
			String q1 = "update sellers set password=? where seller_id=?";
			PreparedStatement ps = con.prepareStatement(q1);
			ps.setString(1,password);
			ps.setInt(2,sellerId);
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
	
	public Boolean loginSeller(){
		Boolean flag = false;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookrent?user=root&password=1234");
			
			String q1 ="select seller_id,seller_name,gstin,pancard,contact,tollfree,account_no,ifsc,address,s.city_id,c.city_name,st.state_id,st.state_name,s.status_id,status";
			String q2 =" from sellers as s inner join cities as c inner join states as st inner join status as sta where s.city_id=c.city_id and st.state_id=c.state_id and";
			String q3 =" s.status_id=sta.status_id and email=? and password=?";

			PreparedStatement ps = con.prepareStatement(q1+q2+q3);
			ps.setString(1,email);
			ps.setString(2,password);

			ResultSet rs = ps.executeQuery();

			if(rs.next()){
				sellerId = rs.getInt(1);
				sellerName = rs.getString(2);
				gstin = rs.getString(3);
				pancard = rs.getString(4);
				contact = rs.getString(5);
				tollfree = rs.getString(6);
				accountNo = rs.getString(7);
				ifsc = rs.getString(8);
				address = rs.getString(9);
				city = new City(rs.getInt(10),rs.getString(11),new State(rs.getInt(12),rs.getString(13)));
				status = new Status(rs.getInt(14),rs.getString(15));
				flag = true;
			}
			
			con.close();
		}catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

	public Boolean registerSeller(){
		Boolean flag = false;

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookrent?user=root&password=1234");

			String q1 = "insert into sellers (seller_name,gstin,pancard,email,contact,password,tollfree,account_no,ifsc,address,city_id)";
			String q2 = " value (?,?,?,?,?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(q1+q2);
			ps.setString(1,sellerName);
			ps.setString(2,gstin);
			ps.setString(3,pancard);
			ps.setString(4,email);
			ps.setString(5,contact);
			ps.setString(6,password);
			ps.setString(7,tollfree);
			ps.setString(8,accountNo);
			ps.setString(9,ifsc);
			ps.setString(10,address);
			ps.setInt(11,city.getCityId());

			int i = ps.executeUpdate();
			
			if(i!=0){
				flag = true;
			}

			con.close();
		}catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();
		}

		return flag;
	}

	//constructors
	public Seller(){
	
	}

	public Seller(String sellerName){
		this.sellerName = sellerName;
	}

	public Seller(String email,String password){
		this.email = email;
		this.password = password;
	}

	public Seller(Integer sellerId,String sellerName,String gstin,String pancard,String email,String contact,String password,String tollfree,String accountNo,String ifsc,String address,City city,Status status){
		this.sellerId = sellerId;
		this.sellerName = sellerName;
		this.gstin = gstin;
		this.pancard = pancard;
		this.email = email;
		this.contact = contact;
		this.password = password;
		this.tollfree = tollfree;
		this.accountNo = accountNo;
		this.ifsc = ifsc;
		this.address = address;
		this.city = city;
		this.status = status;
	}

	//setter methods 

	public void setSellerId(Integer sellerId){
		this.sellerId = sellerId;
	}

	public void setSellerName(String sellerName){
		this.sellerName = sellerName;
	}

	public void setGstin(String gstin){
		this.gstin = gstin;
	}

	public void setPancard(String pancard){
		this.pancard = pancard;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setContact(String contact){
		this.contact = contact;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public void setTollfree(String tollfree){
		this.tollfree = tollfree;
	}

	public void setAccountNo(String accountNo){
		this.accountNo = accountNo;
	}

	public void setIfsc(String ifsc){
		this.ifsc = ifsc;
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

	//getter methods
	public Integer getSellerId(){
		return sellerId;
	}

	public String getSellerName(){
		return sellerName;
	}

	public String getGstin(){
		return gstin;
	}

	public String getPancard(){
		return pancard;
	}

	public String getEmail(){
		return email;
	}

	public String getContact(){
		return contact;
	}

	public String getPassword(){
		return password;
	}

	public String getTollfree(){
		return tollfree;
	}

	public String getAccountNo(){
		return accountNo;
	}

	public String getIfsc(){
		return ifsc;
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
}