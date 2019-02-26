package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;

import utils.RandomOTPS;
import utils.EmailSender;
import models.Customer;

public class CECServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String otp = RandomOTPS.randomNumber();
		String flag = "false";
		Customer customer = new Customer();
		customer.setEmail(email);
		if(customer.isExists()){
			String htmlMessage = "<div><div style='background-color:#a29e59;color:white;padding:4px 16px;font-size:24px;'>ROBB <br />your One Time Password is :-</div><div style='text-align:center;background-color:#c0c0c0;font-size:36px;padding:50px;font-weight:bold'>$otp$</div></div>";
			EmailSender.sendOTP(email,htmlMessage.replace("$otp$",otp));
			session.setAttribute("customer",customer);
			session.setAttribute("otp",otp);
			session.setMaxInactiveInterval(900);
			flag = "true";
		}
		response.getWriter().write(flag);
	}
}
