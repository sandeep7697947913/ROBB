package models;

public class Status{
	Integer statusId;
	String status;

	//constructors

	public Status(){
	
	}

	public Status(Integer statusId,String status){
		this.statusId = statusId;
		this.status = status;
	}

	//setter methods

	public void setStatusId(Integer statusId){
		this.statusId = statusId;
	}

	public void setStatus(String status){
		this.status = status;
	}

	//getter methods

	public Integer getStatusId(){
		return statusId;
	}

	public String getStatus(){
		return status;
	}
}
