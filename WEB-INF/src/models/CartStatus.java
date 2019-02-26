package models;

public class CartStatus{
	private Integer cartStatusId;
	private String cartStatus;

	public Integer getCartStatusId(){
		return cartStatusId;
	}

	public String getCartStatus(){
		return cartStatus;
	}

	public void setCartStatusId(Integer cartStatusId){
		this.cartStatusId = cartStatusId;
	}

	public void setCartStatus(String cartStatus){
		this.cartStatus = cartStatus;
	}
}