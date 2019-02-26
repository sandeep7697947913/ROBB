package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.ArrayList;

import models.SellerBook;
import models.Seller;

public class SSLBPServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session =  request.getSession();
		Seller seller = (Seller)session.getAttribute("seller");
		String nextPage = "login.jsp";
		if(seller!=null){
			ArrayList<SellerBook> bookList = SellerBook.getSellerAllBooks(seller);
			request.setAttribute("bookList",bookList);
			nextPage = "sellersellingbook.jsp";
		}else{
			request.setAttribute("emessage","session got expired");
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}