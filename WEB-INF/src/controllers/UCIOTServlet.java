package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Seller;
import models.Order;
import models.Courier;

public class UCIOTServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		Seller seller = (Seller)session.getAttribute("seller");
		String resp = "false";
		if(seller!=null){
			Integer courierId = Integer.parseInt(request.getParameter("courierId"));
			Integer orderId =  Integer.parseInt(request.getParameter("orderId"));
			Order order = new Order(orderId,new Courier(courierId));
			if(order.setCourier()){
				resp = "true";
			}
		}else{
			resp="sessionisgone";	
		}
		response.getWriter().write(resp);
	}
}