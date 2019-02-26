package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;
import models.Customer;

public class CPPFServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		Customer customer = (Customer)session.getAttribute("customer");
		String nextPage = "login.jsp";
		if(customer!=null){
			nextPage = "customerprofile.jsp";
		}else{
			request.setAttribute("emessage","Session is Expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}