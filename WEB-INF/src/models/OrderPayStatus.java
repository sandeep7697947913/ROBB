package models;

public class OrderPayStatus{
	Integer orderPayStatusId;
	String status;

	public OrderPayStatus(String status){
		this.status = status;
	}

	public void setOrderPayStatusId(Integer orderPayStatusId){
		this.orderPayStatusId = orderPayStatusId;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public Integer getOrderPayStatusId(){
		return orderPayStatusId;
	}

	public String getStatus(){
		return status;
	}
}