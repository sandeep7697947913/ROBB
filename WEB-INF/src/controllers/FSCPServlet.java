package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import java.io.IOException;

import models.Seller;

public class FSCPServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		String potp = request.getParameter("otp");
		String newpass = request.getParameter("newpass");
		String otp = (String)session.getAttribute("otp");
		Seller seller = (Seller)session.getAttribute("seller");
		String flag = "false";
		if(seller!=null){
			if(potp.equals(otp)){
				seller.setPassword(newpass);
				if(seller.changePassword()){
					flag ="true";	
				}
			}
		}
		response.getWriter().write(flag);
	}
}