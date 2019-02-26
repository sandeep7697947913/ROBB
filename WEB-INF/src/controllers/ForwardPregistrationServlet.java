package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import java.io.IOException;

import utils.RandomOTPS;
import utils.EmailSender;

import models.Publisher;

public class ForwardPregistrationServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		String htmlMessage = "<div><div style='background-color:#ab917e;color:white;padding:4px 16px;font-size:24px;'>ROBB <br />your verification key is :-</div><div style='text-align:center;background-color:#c0c0c0;font-size:36px;padding:50px;font-weight:bold'>$key$</div></div>";
		String ekey = RandomOTPS.randomString();

		Publisher publisher = new Publisher(email,password);
		
		session.setAttribute("publisher",publisher);
		session.setAttribute("ekey",ekey);

		EmailSender.sendRegistrationKey(email,htmlMessage.replace("$key$",ekey));
		
		RequestDispatcher rd = request.getRequestDispatcher("Pregistrationdetail.jsp");
		rd.forward(request,response);
	}
}