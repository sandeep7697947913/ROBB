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

import com.google.gson.Gson;

public class UNQNPSBServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session =  request.getSession();
		Seller seller = (Seller)session.getAttribute("seller");
		String resp = "false";
		if(seller!=null){
			String sb = request.getParameter("sellerBook");
			String tquantity = request.getParameter("quantity");
			String tprice = request.getParameter("price");
			SellerBook sellerBook = new Gson().fromJson(sb,SellerBook.class);
			if(tprice!=null){
				Integer price = Integer.parseInt(tprice);
				sellerBook.setPrice(price);
			}

			if(tquantity!=null){
				Integer quantity = Integer.parseInt(tquantity);
				sellerBook.setQuantity(quantity);
			}

			if(sellerBook.changePriceQuantity()){
				resp = "true";
			}
		}else{
			resp = "sessiongone";
		}

		response.getWriter().write(resp);
	}
}