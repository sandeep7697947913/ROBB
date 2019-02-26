package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import models.Courier;
import models.Order;

public class COOPFServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		HttpSession session = request.getSession();
		Courier courier = (Courier)session.getAttribute("courier");
		String nextPage = "courierlogin.jsp"; 
		if(courier!=null){
			nextPage = "courierneworders.jsp";
			ArrayList<Order> orders = courier.getNewOrders();
			request.setAttribute("orders",orders);
		}else{
			request.setAttribute("emessage","session is expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}