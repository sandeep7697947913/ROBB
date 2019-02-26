package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import java.io.IOException;

import models.Seller;
import models.City;

public class SellerRegistrationServlet extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		HttpSession session = request.getSession();
		String nextPage = "registration.jsp";
		String ekey;
		Seller seller;
		if((seller = (Seller)session.getAttribute("seller"))!=null){
			ekey = (String)session.getAttribute("ekey");
			if(ekey.equals(request.getParameter("pskey"))){
				seller = (Seller)session.getAttribute("seller");
				seller.setSellerName(request.getParameter("seller"));
				seller.setGstin(request.getParameter("gstin"));
				seller.setPancard(request.getParameter("pancard"));
				seller.setContact(request.getParameter("contact"));
				seller.setTollfree(request.getParameter("tollfree"));
				seller.setAccountNo(request.getParameter("accountno"));
				seller.setIfsc(request.getParameter("ifsc"));
				seller.setAddress(request.getParameter("address"));
				seller.setCity(new City(Integer.parseInt(request.getParameter("cityId"))));

				if(seller.registerSeller()){
					nextPage="RegistrationSuccessfull.jsp";
				}else{
					request.setAttribute("emesaage","some internal problems occcurs");
				}

			}else{
				request.setAttribute("emessage","Key is wrong");
			}

		}else{
			request.setAttribute("emessage","session is got expired");
		}
		RequestDispatcher rd = request.getRequestDispatcher(nextPage);
		rd.forward(request,response);
	}
}