package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;

import java.io.IOException;

import com.google.gson.Gson;

import models.Courier;
import models.Order;

public class COUGOCDServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		Integer orderId = Integer.parseInt(request.getParameter("orderId"));
		HttpSession session = request.getSession();
		Courier courier = (Courier)session.getAttribute("courier");
		String resp = "false";
		if(courier!=null){
			Order order = new Order(orderId,courier);
			if(order.getOrderCustomerDetail()){
				resp = new Gson().toJson(order);
			}
		}else{
			resp = "sessiongone";
		}
		response.getWriter().write(resp);
	}
}