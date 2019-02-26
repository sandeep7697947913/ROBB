package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.ArrayList;

import models.Customer;
import models.Cart;

public class CHABCServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String nextPage = "login.jsp";
		HttpSession session = request.getSession();
		
		Customer customer = (Customer)session.getAttribute("customer");

		if(customer!=null){
			ArrayList<Cart> carts = Cart.getCartItems(customer);
			int totalAmmount = 0;
			int bookQuantity = 0;
			
			for(Cart cart : carts){
				int tmpBook = cart.getQuantity();
				bookQuantity += tmpBook;
				totalAmmount += tmpBook*cart.getSellerBook().getPrice();
			}
			request.setAttribute("totalAmmount",totalAmmount);
			request.setAttribute("bookQuantity",bookQuantity);
			request.setAttribute("carts",carts);
			nextPage = "abcitmes.jsp";//all book cart
		}else{
			request.setAttribute("emessage","Session is expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}