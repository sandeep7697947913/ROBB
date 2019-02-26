package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;

import java.io.IOException;

import models.Seller;

public class SPPFServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		HttpSession session = request.getSession();
		Seller seller = (Seller)session.getAttribute("seller");
		String nextPage = "login.jsp";
		if(seller!=null){
			nextPage = "sellerprofile.jsp";
		}else{
			request.setAttribute("emessage","session is expired");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request,response);
	}
}