package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Seller;

public class SCBPFServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		HttpSession session = request.getSession();
		Seller seller = (Seller)session.getAttribute("seller");
		String nextPage = "login.jsp";
		if(seller!=null){
			nextPage = "sellchebook.jsp";
		}else{
			request.setAttribute("emessage","Session is gone");
		}

		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}