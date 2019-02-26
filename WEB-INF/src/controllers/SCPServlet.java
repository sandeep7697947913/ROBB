package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Seller;

public class SCPServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		String oldpass = request.getParameter("oldpass");
		String newpass = request.getParameter("newpass");
		Seller seller = (Seller)session.getAttribute("seller");
		String flag = "sessiongone";
		if(oldpass.equals(seller.getPassword())){
			seller.setPassword(newpass);
			if(seller.changePassword()){
				flag ="true";	
			}
		}else{
			flag="false";
		}
		response.getWriter().write(flag);
	}
}
