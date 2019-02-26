package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;

import models.Customer;
import models.Cart;
import models.SellerBook;

public class RIFCartServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		Integer SBId = Integer.parseInt(request.getParameter("SBId"));
		Customer customer = (Customer)session.getAttribute("customer");
		
		String message = "unsuccessfull";

		if(customer!=null){
			Cart cart = new Cart(customer,new SellerBook(SBId));
			if(cart.removeItem()){
				message = "successfull";
			}else{
				message = "Internal_Error";
			}
		}

		response.getWriter().write(message);
	}
}