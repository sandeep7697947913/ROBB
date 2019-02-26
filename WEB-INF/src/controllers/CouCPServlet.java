package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Courier;

public class CouCPServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		String oldpass = request.getParameter("oldpass");
		String newpass = request.getParameter("newpass");
		Courier courier = (Courier)session.getAttribute("courier");
		String flag = "sessiongone";
		if(oldpass.equals(courier.getPassword())){
			courier.setPassword(newpass);
			if(courier.changePassword()){
				flag ="true";	
			}
		}else{
			flag="false";
		}
		response.getWriter().write(flag);
	}
}
