package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Order;
import models.Courier;

public class BillPageServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		Courier courier = (Courier)session.getAttribute("courier");
		String nextPage = "courierlogin.jsp";
		if(courier!=null){
			Integer orderId = Integer.parseInt(request.getParameter("orderId"));
			Order order = Order.getOrderDetail(orderId,courier);
			request.setAttribute("order",order);
			nextPage = "bill.jsp";
		}else{
			request.setAttribute("emessage","session is gone");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}