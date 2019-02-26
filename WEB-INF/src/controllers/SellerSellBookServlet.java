package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import com.google.gson.Gson;

import java.io.IOException;

import models.Seller;
import models.SellerBook;
import models.Book;

public class SellerSellBookServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();

		Seller seller = (Seller)session.getAttribute("seller");
		Book book = new Gson().fromJson(request.getParameter("book"),Book.class);
		if(seller!=null){
			SellerBook sellerBook = new SellerBook(seller,book,Integer.parseInt(request.getParameter("quantity")),Integer.parseInt(request.getParameter("price")));
			switch(sellerBook.sellBook()){
				case("true"):{
					response.getWriter().write("true");
					break;
				}
				case("false"):{
					response.getWriter().write("false");
					break;
				}
				case("duplicate"):{
					response.getWriter().write("duplicate");
					break;
				}
			}

		}else{
			request.setAttribute("emessage","session is expired");
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
	}
}
