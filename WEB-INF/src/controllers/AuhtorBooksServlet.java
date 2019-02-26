package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;

import models.Publisher;
import models.Book;

public class AuhtorBooksServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String author = request.getParameter("author");
		HttpSession session = request.getSession();
		Publisher publisher = (Publisher)session.getAttribute("publisher");
		String resp = "fail";
		if(publisher!=null){
			Book book = new Book(publisher);
			book.setAuthor(author);
			ArrayList<Book> books = book.getAuthorBooks();
			resp = new Gson().toJson(books);
		}else{
			resp = "sessiongone";
		}
		response.getWriter().write(resp);
	}
}