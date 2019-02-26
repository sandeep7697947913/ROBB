package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.ArrayList;

import models.Customer;
import models.Order;
import models.Cart;

public class CODACIOrderServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		Customer customer = (Customer)session.getAttribute("customer");
		String result = "false";
		if(customer!=null){
			ArrayList<Cart> carts = Cart.getCartItems(customer);
			if(Order.aCODOrdersOfCarts(carts)){
				result = "true";
			}
		}else{
			result = "sessionexpired";
		}

		response.getWriter().write(result);
	}
}