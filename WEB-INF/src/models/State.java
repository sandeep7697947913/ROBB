package models;

import java.util.ArrayList;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class State{
	private Integer stateId;
	private String stateName;

	public static ArrayList<State> collectStates(){
		ArrayList<State> states = new ArrayList<State>();
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookrent?user=root&password=1234");
			String q1 = "select state_id,state_name from states";
			PreparedStatement ps = con.prepareStatement(q1);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				states.add(new State(rs.getInt(1),rs.getString(2)));
			}

			con.close();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return states;
	}

	//constructors
	public State(){
	
	}

	public State(String stateName){
		this.stateName = stateName;
	}

	public State(Integer stateId,String stateName){
		this.stateId = stateId;
		this.stateName = stateName;
	}
	
	//setter methods
	
	public void setStateId(Integer stateId){
		this.stateId = stateId;
	}

	public void setStateName(String stateName){
		this.stateName = stateName;
	}

	//getter methods

	public Integer getStateId(){
		return stateId;
	}
	
	public String getStateName(){
		return stateName;
	}

}