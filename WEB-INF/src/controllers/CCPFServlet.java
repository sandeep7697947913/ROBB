package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.ArrayList;

import models.Customer;
import models.Cart;

public class CCPFServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		HttpSession session = request.getSession();
		Customer customer = (Customer)session.getAttribute("customer");
		String nextPage="login.jsp";

		if(customer!=null){
			nextPage = "ccart.jsp";
			ArrayList<Cart> cartItems = Cart.getCartItems(customer);
			request.setAttribute("cartItems",cartItems);
		}else{
			Cookie ck = new Cookie("nextPage","customerBag");
			ck.setMaxAge(300);
			ck.setComment("customer demand for cart without login");
			response.addCookie(ck);
		}

		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}