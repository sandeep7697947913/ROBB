package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;

import java.io.IOException;
import java.util.ArrayList;

import models.Publisher;
import models.SellerBook;

public class PBSPServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws IOException,ServletException{
		HttpSession session = request.getSession();
		Publisher publisher = (Publisher)session.getAttribute("publisher");
		String nextPage = "login.jsp";
		if(publisher!=null){
			nextPage = "publishersbooksellers.jsp";
			ArrayList<SellerBook> sellerBooks = SellerBook.findPublishedBooksSellers(publisher.getPublisherId());
			request.setAttribute("sellerBooks",sellerBooks);
		}else{
			request.setAttribute("emessage","session is expired");
		}
		
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request,response);
	}
}