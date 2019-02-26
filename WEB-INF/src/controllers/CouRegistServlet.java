package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Courier;
import models.City;

public class CouRegistServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String address = request.getParameter("address");
		Integer cityId = Integer.parseInt(request.getParameter("cityId"));
		String regKey = request.getParameter("regkey");
		HttpSession session = request.getSession();
		String ekey = (String)session.getAttribute("ekey");
		
		String nextPage = "registration.jsp";
		if(ekey.equals(regKey)){
			Courier courier = new Courier(name,email,password,new City(cityId),address);
			if(courier.registerCourier()){
				nextPage = "RegistrationSuccessfull.jsp";
			}
		}

		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}