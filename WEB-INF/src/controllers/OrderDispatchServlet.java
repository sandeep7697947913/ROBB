package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Courier;
import models.Order;
import models.SellerBook;

public class OrderDispatchServlet extends HttpServlet {
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		Courier courier = (Courier)session.getAttribute("courier");
		
		String resp = "false";
		if(courier!=null){
			Integer orderId = Integer.parseInt(request.getParameter("orderId"));
			Order order = new Order(orderId);
			if(order.updateOrderStatus()){	
				if(SellerBook.changeQuantity(orderId)){
					resp = "true";
				}
			}
		}else{
			resp = "sessiongone";
		}

		response.getWriter().write(resp);
	}
}