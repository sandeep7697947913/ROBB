package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import models.Category;

public class AjaxCollectCategoriesServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		ArrayList<Category> categories = Category.collectCategories();

		String resp = new Gson().toJson(categories);

		response.getWriter().write(resp);
	}
}