package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.io.PrintWriter;

import models.Order;
import models.Courier;
import models.City;

public class UPCOUOCServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		Courier courier = (Courier)session.getAttribute("courier");
		String resp = "false";
		if(courier!=null){
			Integer orderId = Integer.parseInt(request.getParameter("orderId"));
			Integer cityId = Integer.parseInt(request.getParameter("cityId"));
			Order order = new Order(orderId);
			order.setCity(new City(cityId));
			if(order.updateCity()){
				resp="true";
			}
		}else{
			resp="sessiongone";	
		}
		PrintWriter pw = response.getWriter();
		pw.write(resp);
	}
}