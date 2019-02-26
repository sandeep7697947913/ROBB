package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import models.Customer;

public class FCCPServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		String potp = request.getParameter("otp");
		String newpass = request.getParameter("newpass");
		String otp = (String)session.getAttribute("otp");
		Customer customer = (Customer)session.getAttribute("customer");
		String flag = "false";
		if(customer!=null){
			if(potp.equals(otp)){
				customer.setPassword(newpass);
				if(customer.changePassword()){
					flag ="true";	
				}
			}
		}
		response.getWriter().write(flag);
	}
}