package models;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

import java.util.ArrayList;

import conpool.DBPool;

public class Notification{
	private Integer notificationId;
	private String notification;
	private java.sql.Date date;

	public static ArrayList<Notification> getNotifications(){
		ArrayList<Notification> notifications = new ArrayList<Notification>();
		try{
			Connection con = DBPool.getConnection();
			String q1 ="select * from notification limit 10";
			PreparedStatement ps = con.prepareStatement(q1);
			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				notifications.add(
					new Notification(rs.getInt(1),rs.getString(2),rs.getDate(3))
				);
			}
			con.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return notifications;
	}

	public Notification(Integer notificationId,String notification,java.sql.Date date){
		this.notificationId = notificationId;
		this.notification = notification;
		this.date = date;
	}

	public Integer getNotificationId(){
		return notificationId; 
	}

	public String getNotification(){
		return notification; 
	}

	public java.sql.Date getDate(){
		return date;
	}

	public void setNotificationId(Integer notificationId){
		this.notificationId = notificationId;
	}

	public void setNotification(String notification){
		this.notification = notification;
	}

	public void setDate(java.sql.Date date){
		this.date = date;
	}
}