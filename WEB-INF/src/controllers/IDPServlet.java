package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Book;

public class IDPServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		
		String nextPage = "notfounderror.jsp";
		
		Integer bookId = Integer.parseInt(request.getParameter("bookId"));

		Book book = Book.findBookBookId(bookId);

		if(book!=null){
			request.setAttribute("book",book);
			nextPage = "itemdetail.jsp";
		}
		
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}