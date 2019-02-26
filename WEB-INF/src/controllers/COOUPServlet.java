package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import models.Courier;

public class COOUPServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		Courier courier = (Courier)session.getAttribute("courier");
		String nextPage = "courierlogin.jsp";
		if(courier!=null){
			nextPage = "courierorderupdate.jsp";
		}else{
			request.setAttribute("emessage","session is expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}