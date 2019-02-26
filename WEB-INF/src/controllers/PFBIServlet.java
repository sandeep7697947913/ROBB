package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import com.google.gson.Gson;

import java.io.IOException;

import models.Book;
import models.Publisher;

public class PFBIServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String isbn = request.getParameter("isbn");
		HttpSession session = request.getSession();
		Publisher publisher = (Publisher)session.getAttribute("publisher");
		String resp = "sessiongone";
		if(publisher!=null){
			Book book = new Book(publisher);
			book.setIsbn(isbn);
			if(book.getPublisherBook()){
				resp = new Gson().toJson(book);
			}else{
				resp = "norec";
			}
		}
		response.getWriter().write(resp);
	}
}