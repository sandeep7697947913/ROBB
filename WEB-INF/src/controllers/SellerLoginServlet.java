package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.ArrayList;

import models.Seller;
import models.Notification;

public class SellerLoginServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		HttpSession session = request.getSession();
		Seller seller = new Seller(request.getParameter("email"),request.getParameter("password"));
		String nextPage = "login.jsp";
		if(seller.loginSeller()){
			session.setAttribute("seller",seller);
			ArrayList<Notification> notifications = Notification.getNotifications();
			request.setAttribute("notifications",notifications);
			nextPage="sellerhome.jsp";
		}else{
			request.setAttribute("emessage","Either email or password is incorrect");	
		}

		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request,response);
	}
}