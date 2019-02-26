package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import models.Publisher;

public class FPCPServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		String potp = request.getParameter("otp");
		String newpass = request.getParameter("newpass");
		String otp = (String)session.getAttribute("otp");
		Publisher publisher = (Publisher)session.getAttribute("publisher");
		String flag = "false";
		if(publisher!=null){
			if(potp.equals(otp)){
				publisher.setPassword(newpass);
				if(publisher.changePassword()){
					flag ="true";	
				}
			}
		}
		response.getWriter().write(flag);
	}
}