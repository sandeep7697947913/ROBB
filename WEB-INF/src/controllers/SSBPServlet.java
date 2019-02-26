package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import models.Seller;

import java.io.IOException;

public class SSBPServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		Seller seller = (Seller)session.getAttribute("seller");
		String nextPage = "login.jsp";
		if(seller!=null){
			nextPage="sellersellbook.jsp";
		}else{
			request.setAttribute("emessage","session is expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}