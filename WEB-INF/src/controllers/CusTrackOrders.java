package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import models.Customer;
import models.Order;

public class CusTrackOrders extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		Customer customer = (Customer) session.getAttribute("customer");

		if(customer!=null){
			ArrayList<Order> orders = Order.TrackOrders(customer.getCustomerId());
			request.setAttribute("orders",orders);
			request.getRequestDispatcher("custrackorders.jsp").forward(request,response);
		}else{
			String result = "<script>location.href="+"'login.jsp'"+"</script>";
			response.getWriter().write(result);
		}
	}
}