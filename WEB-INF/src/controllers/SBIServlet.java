package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.ArrayList;

import models.SellerBook;

public class SBIServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String sbi = request.getParameter("sbi");
		String nextPage = "index.jsp";
		if(sbi!=null){
			nextPage = "searcheditem.jsp";
			ArrayList<SellerBook> sellerBooks = SellerBook.findBookForCustomer(sbi);
			request.setAttribute("sellerBooks",sellerBooks);
		}
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}