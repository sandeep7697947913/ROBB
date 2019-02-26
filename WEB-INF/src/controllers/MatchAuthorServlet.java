package controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.io.IOException;

import models.Book;

public class MatchAuthorServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws  ServletException,IOException{
		String auth = request.getParameter("auth");
		ArrayList<String> authors = Book.getMatchAuthors(auth);
		response.getWriter().write(new Gson().toJson(authors));
	}
}