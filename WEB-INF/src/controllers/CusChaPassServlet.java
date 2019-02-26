package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import models.Customer;

public class CusChaPassServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		String pass = request.getParameter("pass");
		String newpass = request.getParameter("newpass");
		String flag = "sessiongone";
		Customer customer = (Customer)session.getAttribute("customer");
		if(customer!=null){
			if(pass.equals(customer.getPassword())){
				customer.setPassword(newpass);
				if(customer.changePassword()){
					flag ="true";	
				}
			}else{
				flag="false";
			}
		}

		response.getWriter().write(flag);
	}
}