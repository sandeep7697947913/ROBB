package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;

import java.io.IOException;
import java.util.ArrayList;

import models.Publisher;
import models.Notification;


public class PublisherLoginServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		HttpSession session = request.getSession();
		Publisher publisher = new Publisher(request.getParameter("email"),request.getParameter("password"));
		String nextPage = "login.jsp";
		if(publisher.loginPublisher()){
			session.setAttribute("publisher",publisher);
			ArrayList<Notification> notifications = Notification.getNotifications();
			request.setAttribute("notifications",notifications);
			nextPage = "publisherhome.jsp";
		}else{
			request.setAttribute("emessage","Either email or password is incorrect");
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request,response);
	}
}