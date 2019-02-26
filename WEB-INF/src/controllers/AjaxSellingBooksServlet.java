package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.ArrayList;

import com.google.gson.Gson;

import models.SellerBook;

public class AjaxSellingBooksServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		ArrayList<String> bookNames = SellerBook.getSellingBookNames();
		String resp = new Gson().toJson(bookNames);
		response.getWriter().write(resp);
	}
}