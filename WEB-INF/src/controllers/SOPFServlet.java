package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import models.Seller;
import models.Order;

public class SOPFServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		Seller seller = (Seller)session.getAttribute("seller");
		String nextPage = "login.jsp";
		if(seller!=null){
			ArrayList<Order> orders = Order.getSellerOrders(seller.getSellerId());
			request.setAttribute("orders",orders);
			nextPage = "sellerorderspage.jsp";
		}else{
			request.setAttribute("emessage","session expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}