package models;

public class OrderStatus{
	private Integer orderStatusId;
	private String status;

	//constructors

	public OrderStatus(){
	
	}

	public OrderStatus(String status){
		this.status = status;
	}

	public OrderStatus(Integer orderStatusId,String status){
		this.orderStatusId = orderStatusId;
		this.status = status;
	}

	//setter methhods

	public void setOrderStatusId(Integer orderStatusId){
		this.orderStatusId = orderStatusId;
	}

	public void setStatus(String status){
		this.status = status;
	}

	//getter methods
	public Integer getOrderStatusId(){
		return orderStatusId;
	}

	public String getStatus(){
		return status;
	}
}