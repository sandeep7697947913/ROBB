package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import models.Courier;

public class FCouCPServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		String potp = request.getParameter("otp");
		String newpass = request.getParameter("newpass");
		String otp = (String)session.getAttribute("otp");
		Courier courier = (Courier)session.getAttribute("courier");
		String flag = "false";
		if(courier!=null){
			if(potp.equals(otp)){
				courier.setPassword(newpass);
				if(courier.changePassword()){
					flag ="true";	
				}
			}
		}
		response.getWriter().write(flag);
	}
}