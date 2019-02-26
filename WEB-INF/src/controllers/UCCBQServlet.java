package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Customer;
import models.Cart;
import models.SellerBook;

public class UCCBQServlet extends HttpServlet{//update customer cart book quantity
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		HttpSession session = request.getSession();
		String resp = "sessionGone";
		Customer customer = (Customer)session.getAttribute("customer");
		Integer SBId = Integer.parseInt(request.getParameter("SBId"));
		Integer quantity = Integer.parseInt(request.getParameter("quantity"));

		if(customer!=null){
			Cart cart = new Cart(customer,new SellerBook(SBId),quantity);
			if(cart.updateBookQuantity()){
				resp = "successful";
			}else{
				resp = "unsuccessful";
			}
		}else{
		
		}
		
		response.getWriter().write(resp);
	}
}