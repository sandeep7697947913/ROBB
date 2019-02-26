package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.ArrayList;

import models.City;

import com.google.gson.Gson;

public class AjaxCollectCitiesServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		Integer stateId = Integer.parseInt(request.getParameter("stateId"));
		
		ArrayList<City> cities = City.collectCitiesState(stateId);
		
		String resp = new Gson().toJson(cities);
		response.getWriter().write(resp);
	}
}