package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Courier;

public class CouPPFServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		Courier courier = (Courier)session.getAttribute("courier");
		String nextPage = "login.jsp";
		if(courier!=null){
			nextPage = "couprofile.jsp";
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}