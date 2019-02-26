package models;

import java.util.ArrayList;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class City{
	private Integer cityId;
	private String cityName;
	private State state;

	public static ArrayList<City> collectCitiesState(Integer stateId){
		ArrayList<City> cities = new ArrayList<City>();

		try{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookrent?user=root&password=1234");

			String str = "select city_id,city_name from cities where state_id=?";

			PreparedStatement ps = con.prepareStatement(str);
			ps.setInt(1,stateId);

			ResultSet rs = ps.executeQuery();

			while(rs.next()){
				cities.add(new City(rs.getInt(1),rs.getString(2)));
			}
			con.close();
		}catch(ClassNotFoundException|SQLException e){
			e.printStackTrace();
		}
		return cities;
	} 
	
	//constructors 

	public City(){
	
	}

	public City(Integer cityId){
		this.cityId = cityId;
	}

	public City(String cityName,State state){
		this.cityName = cityName;
		this.state = state;
	}

	public City(Integer cityId,String cityName){
		this.cityId = cityId;
		this.cityName = cityName;
	}

	public City(Integer cityId,String cityName,State state){
		this.cityId = cityId;
		this.cityName = cityName;
		this.state = state;
	}

	//setter methods 
	public void setCityId(Integer cityId){
		this.cityId = cityId;
	}

	public void setCityName(String cityName){
		this.cityName = cityName;
	}

	public void setState(State state){
		this.state = state;
	}

	// getter methods

	public Integer getCityId(){
		return cityId;
	}

	public String getCityName(){
		return cityName;
	}

	public State getState(){
		return state;
	}
}