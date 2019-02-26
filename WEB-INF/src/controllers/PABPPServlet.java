package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.ArrayList;

import models.Publisher;
import models.Book;

public class PABPPServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String nextPage = "login.jsp";
		HttpSession session = request.getSession();
		Publisher publisher = (Publisher)session.getAttribute("publisher");

		if(publisher!=null){
			nextPage = "publisherAllBooks.jsp";
			ArrayList<Book> books = Book.getAllBookPublished(publisher.getPublisherId());
			request.setAttribute("books",books);
		}else{
			request.setAttribute("emessage","session is expired");
		}

		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}