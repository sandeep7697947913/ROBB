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

public class CPPServlet extends HttpServlet{
	/*public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		doGet(request,response);
	}*/ // sir code
	
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		Customer customer = (Customer)session.getAttribute("customer");
		String nextPage = "index.jsp";
		if(customer!=null){
			if((customer.getAddress())!=null){
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
				nextPage = "cuspaypage.jsp"; 
			}else{
				Cookie ck = new Cookie("nextPage","paymentpage.cpf");
				//Cookie ck = new Cookie("nextPage","cuspaypage.jsp");
				ck.setMaxAge(300);
				response.addCookie(ck);
				nextPage = "cusproupdate.jsp";
			}
		}else{
			request.setAttribute("emessage","Session is expired");
		}

		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}