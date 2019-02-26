package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Publisher;

public class PCPServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		String oldpass = request.getParameter("oldpass");
		String newpass = request.getParameter("newpass");
		Publisher publisher = (Publisher)session.getAttribute("publisher");
		String flag = "sessiongone";
		if(oldpass.equals(publisher.getPassword())){
			publisher.setPassword(newpass);
			if(publisher.changePassword()){
				flag ="true";	
			}
		}else{
			flag="false";
		}
		response.getWriter().write(flag);
	}
}
