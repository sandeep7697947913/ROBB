package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.ArrayList;

import models.Courier;
import models.Notification;

public class COULoginServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		String nextPage = "courierlogin.jsp";
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		Courier courier = new Courier(email,password);
		if(courier.loginCourier()){
			session.setAttribute("courier",courier);
			ArrayList<Notification> notifications = Notification.getNotifications();
			request.setAttribute("notifications",notifications);
			nextPage = "courierhome.jsp";
		}else{
			request.setAttribute("emessage","Either email or password incorrect");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}