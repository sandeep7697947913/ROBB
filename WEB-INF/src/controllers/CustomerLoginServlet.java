package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Customer;
import models.SellerBook;
import models.Cart;

public class CustomerLoginServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		String nextPage = "login.jsp";

		String email = request.getParameter("email");
		String password = request.getParameter("password");

		Customer customer = new Customer(email,password);
		if(customer.loginCustomer()){
			session.setAttribute("customer",customer);
			nextPage = "index.jsp";
			
			Cookie[] cookies = request.getCookies();
			if(cookies!=null){
				Integer quantity = null;
				Integer sellerBookId = null;
				String page = null;
				for(Cookie cookie : cookies){
					if("quantity".equals(cookie.getName())){
						quantity = Integer.parseInt(cookie.getValue());
						Cookie ck = new Cookie("quantity","");
						ck.setMaxAge(0);
						response.addCookie(ck);
					}else if("sellerBookId".equals(cookie.getName())){
						sellerBookId = Integer.parseInt(cookie.getValue());
						Cookie ck = new Cookie("sellerBookId","");
						ck.setMaxAge(0);
						response.addCookie(ck);
					}else if("nextPage".equals(cookie.getName())){
						page = cookie.getValue();
						Cookie ck = new Cookie("nextPage","");
						ck.setMaxAge(0);
						response.addCookie(ck);
					}
				}
		
				if((quantity!=null)&&(sellerBookId!=null)){
					nextPage = "ccfpage.cpf";
					Cart cart = new Cart(customer,new SellerBook(sellerBookId),quantity);
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
				}else if(page!=null){
					nextPage = "ccfpage.cpf";
				}
			}
		}else{
			request.setAttribute("emessage","Either email or password is incorrect");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request,response);
	}
}