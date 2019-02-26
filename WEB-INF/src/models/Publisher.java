package models;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import conpool.DBPool;

public class Publisher{
	private Integer publisherId;
	private String publication;
	private String gstin;
	private String email;
	private String link;
	private String password;
	private String address;
	private String tollfree;
	private City city;
	private Status status;

	public Boolean isExists(){
		Boolean flag = false;
		try{
			Connection con = DBPool.getConnection();
			String q1 = "select publisher_id from publishers where email=?";
			PreparedStatement ps = con.prepareStatement(q1);
			ps.setString(1,email);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				publisherId = rs.getInt(1);
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
			String q1 = "update publishers set password=? where publisher_id=?";
			PreparedStatement ps = con.prepareStatement(q1);
			ps.setString(1,password);
			ps.setInt(2,publisherId);
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

	public Boolean loginPublisher(){
		Boolean flag= false;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookrent?user=root&password=1234");
			String q1 ="select publisher_id,publication,gstin,link,address,p.city_id,city_name,st.state_id,state_name,tollfree,p.status_id,status";
			String q2 = " from publishers as p inner join cities as c inner join status as s  inner join states as st";
			String q3 =" where c.city_id=p.city_id and st.state_id=c.state_id and s.status_id=p.status_id and email=? and password=?";
			
			PreparedStatement ps = con.prepareStatement(q1+q2+q3);
			ps.setString(1,email);
			ps.setString(2,password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				publisherId = rs.getInt(1);
				publication = rs.getString(2);
				gstin = rs.getString(3);
				link = rs.getString(4);
				address = rs.getString(5);
				city = new City(rs.getInt(6),rs.getString(7),new State(rs.getInt(8),rs.getString(9)));
				tollfree = rs.getString(10);
				status = new Status(rs.getInt(11),rs.getString(12));
				flag = true;
			}
			con.close();
		}catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();
		}
		return flag;
	}

	public Boolean registerPublisher(){
		boolean flag = false;
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookrent?user=root&password=1234");
			
			String q1 = "insert into publishers (publication,gstin,email,link,password,address,city_id,tollfree)";
			String q2 = " value (?,?,?,?,?,?,?,?)";

			PreparedStatement ps = con.prepareStatement(q1+q2);
			ps.setString(1,publication);
			ps.setString(2,gstin);
			ps.setString(3,email);
			ps.setString(4,link);
			ps.setString(5,password);
			ps.setString(6,address);
			ps.setInt(7,city.getCityId());
			ps.setString(8,tollfree);
			
			int i = ps.executeUpdate();

			if(i!=0){
				flag = true;
			}

			con.close();
		}catch(SQLException|ClassNotFoundException e){
			e.printStackTrace();
		}	
		return flag;
	}

	// constructors 

	public Publisher(){
		
	}

	public Publisher(String publication){
		this.publication = publication;
	}

	public Publisher(Integer publisherId,String publication){
		this.publisherId = publisherId;
		this.publication = publication;
	}

	public Publisher(String email,String password){
		this.email = email;
		this.password = password;
	}

	public Publisher(Integer publisherId,String publication,String gstin,String email,String link,String password,String address,City city,String tollfree,Status status){
		this.publisherId = publisherId;
		this.publication = publication;
		this.gstin = gstin;
		this.email = email;
		this.link = link;
		this.password =password;
		this.tollfree = tollfree;
		this.city = city;
		this.status = status;
	}

	//setter methods

	public void setPublisherId(Integer publisherId){
		this.publisherId = publisherId;
	}

	public void setPublication(String publication){
		this.publication = publication;
	}

	public void setGstin(String gstin){
		this.gstin = gstin;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public void setLink(String link){
		this.link = link;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public void setAddress(String address){
		this.address = address;
	}

	public void setCity(City city){
		this.city = city;
	}

	public void setTollfree(String tollfree){
		this.tollfree = tollfree;
	}

	public void setStatus(Status status){
		this.status = status;
	}

	//getter methods

	public Integer getPublisherId(){
		return publisherId;
	}

	public String getPublication(){
		return publication;
	}

	public String getGstin(){
		return gstin;
	}

	public String getEmail(){
		return email;
	}

	public String getLink(){
		return link;
	}

	public String getPassword(){
		return password;
	}

	public String getAddress(){
		return address;
	}

	public City getCity(){
		return city;
	}

	public String getTollfree(){
		return tollfree;
	}

	public Status getStatus(){
		return status;
	}
}