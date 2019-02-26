package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;

import java.io.IOException;

public class RegistrationPageForwardServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		RequestDispatcher rd = request.getRequestDispatcher("registration.jsp");
		rd.forward(request,response);
	}
}