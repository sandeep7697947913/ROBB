package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Publisher;
import models.City;

public class PublisherRegistrationServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		
		String nextPage ="registration.jsp";
		String ekey;
		Publisher publisher;
		if((publisher=(Publisher)session.getAttribute("publisher"))!=null){
			ekey=(String)session.getAttribute("ekey");
			if(ekey.equals(request.getParameter("pskey"))){
				publisher.setPublication(request.getParameter("publication"));
				publisher.setGstin(request.getParameter("gstin"));
				publisher.setLink(request.getParameter("link"));
				publisher.setTollfree(request.getParameter("tollfree"));
				publisher.setAddress(request.getParameter("address"));
				publisher.setCity(new City(Integer.parseInt(request.getParameter("cityId"))));

				if(publisher.registerPublisher()){
					session.invalidate();
					nextPage="RegistrationSuccessfull.jsp";
				}else{
					request.setAttribute("emesaage","some internal problems occcurs");	
				}
			}else{
				request.setAttribute("emesaage","key is wrong");	
			}
		}else{
			request.setAttribute("emesaage","Session got expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}