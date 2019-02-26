package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import com.google.gson.Gson;

import java.io.IOException;

import models.Book;

public class AjaxFindBookISDNServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String isbn = request.getParameter("isbn");
		
		Book book = Book.findBookIsbn(isbn);
		String resp = new Gson().toJson(book);
		response.getWriter().write(resp);
	}
}