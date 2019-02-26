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
import models.Category;

public class CategoriesBooksServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String category = request.getParameter("category");
		HttpSession session = request.getSession();
		Publisher publisher = (Publisher)session.getAttribute("publisher");
		String resp = "fail";
		if(publisher!=null){
			Book book = new Book(publisher);
			book.setCategory(new Category(category));
			ArrayList<Book> books = book.getCategoryBooks();
			resp = new Gson().toJson(books);
		}else{
			resp = "sessiongone";
		}
		response.getWriter().write(resp);
	}
}