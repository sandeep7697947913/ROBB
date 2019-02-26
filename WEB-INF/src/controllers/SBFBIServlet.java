package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;

import models.Seller;
import models.Book;
import models.SellerBook;

import com.google.gson.Gson;

public class SBFBIServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		String isbn = request.getParameter("isbn");
		Seller seller = (Seller)session.getAttribute("seller");
		String resp = "sessiongone";
		if(seller!=null){	
			Book book = new Book();
			book.setIsbn(isbn);
			SellerBook sellerBook = new SellerBook(seller,book);
			if(sellerBook.getSSBDetails()){
				resp = new Gson().toJson(sellerBook);
			}else{
				resp = "norecord";
			}
		}
		response.getWriter().write(resp);
	}
}