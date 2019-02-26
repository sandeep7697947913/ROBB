package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Publisher;

public class PPBPServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		HttpSession session = request.getSession();
		Publisher publisher = (Publisher)session.getAttribute("publisher");
		String nextPage ="login.jsp";
		if(publisher!=null){
			nextPage="publisherpbook.jsp";
		}else{
			
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}