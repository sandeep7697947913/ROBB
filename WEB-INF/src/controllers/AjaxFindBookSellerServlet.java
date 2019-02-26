package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.ArrayList;

import models.SellerBook;

import com.google.gson.Gson;

public class AjaxFindBookSellerServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));
		
		ArrayList<SellerBook> bookSellersList = SellerBook.getBookSellers(bookId);
		
		String resp = new Gson().toJson(bookSellersList);

		response.getWriter().write(resp);
	}
}