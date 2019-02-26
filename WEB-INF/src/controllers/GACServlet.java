package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.ArrayList;

import models.Courier;

import com.google.gson.Gson;

public class GACServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		ArrayList<Courier> couriers = Courier.getAllCouriers();	
		String resp = new Gson().toJson(couriers);
		response.getWriter().write(resp);
	}
}