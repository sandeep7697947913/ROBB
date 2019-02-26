package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;

import java.io.IOException;

import models.Customer;
import models.Cart;
import models.SellerBook;

public class ACCPServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		HttpSession session = request.getSession();
		
		String nextPage = "login.jsp";

		String quantity = request.getParameter("quantity");
		String sellerBookId = request.getParameter("sellerBookId");

		Customer customer = (Customer)session.getAttribute("customer");

		if(customer!=null){
			nextPage = "ccfpage.cpf";//sellerBookId,quantity
			Cart cart = new Cart(customer,new SellerBook(Integer.parseInt(sellerBookId)),Integer.parseInt(quantity));
			switch(cart.addBookToCart()){
				case("true"):{
					request.setAttribute("smessage","Book added to Bag");
					break;
				}
				case("false"):{
					request.setAttribute("emessage","book cannot not added in Bag");
					break;
				}
				case("duplicate"):{
					request.setAttribute("noticemessage","Book already in the Bag");
					break;
				}
			}
		}else{
			Cookie c1 = new Cookie("sellerBookId",sellerBookId);
			c1.setComment("for customer if customer did not login and wanna add to cart");
			c1.setMaxAge(300);
			Cookie c2 = new Cookie("quantity",quantity);
			c2.setComment("for customer if customer did not login and wanna add to cart");
			c2.setMaxAge(300);
			response.addCookie(c1);
			response.addCookie(c2);
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request,response);
	}
}