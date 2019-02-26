package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Customer;

public class RegisterCustomerServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		String nextPage = "registration.jsp";
		String email = request.getParameter("email");
		String password = request.getParameter("password");
			
		Customer customer = new Customer(email,password);

		if(customer.registerCustomer()){
			nextPage = "RegistrationSuccessfull.jsp";
		}else{
			request.setAttribute("emessage","registration failed");
		}

		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}