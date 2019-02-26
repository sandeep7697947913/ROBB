package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.io.IOException;

import models.Category;

public class MatchCategoryServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws  ServletException,IOException{
		String category = request.getParameter("category");
		ArrayList<String> categories = Category.getMatchCategories(category);
		response.getWriter().write(new Gson().toJson(categories));
	}
}