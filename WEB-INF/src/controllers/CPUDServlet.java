package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Cookie;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Iterator;
import java.util.HashMap;
import java.util.ArrayList;

import models.Customer;
import models.City;
import models.Cart;

public class CPUDServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		HashMap<String,String> params = new HashMap<String,String>(); 
		Customer customer = (Customer)session.getAttribute("customer");
		String nextPage = "index.jsp";
		if(customer!=null){
			if(ServletFileUpload.isMultipartContent(request)){
				DiskFileItemFactory dfif = new DiskFileItemFactory();
				ServletFileUpload sfu = new ServletFileUpload(dfif);
				try{
					List <FileItem> list = sfu.parseRequest(request);
					Iterator<FileItem> itr = list.iterator();
					FileItem picFile = null;
					while(itr.hasNext()){
						FileItem fileItem = itr.next();
						if(fileItem.isFormField()){
							params.put(fileItem.getFieldName(),fileItem.getString());
						}else{
							picFile = fileItem;
						}
					}
					String path = getServletContext().getRealPath("/data/customerimages");
					File file = new File(path,customer.getCustomerId()+".jpg");
					picFile.write(file);
				}catch(FileUploadException e){
					e.printStackTrace();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			// jdbc saving
			customer.setCustomerName(params.get("name"));
			customer.setPhoneNo(params.get("phoneno"));
			customer.setCity(new City(Integer.parseInt(params.get("cityId"))));
			customer.setAddress(params.get("address"));
			customer.setPicPath((customer.getCustomerId()+".jpg"));
			
			if(customer.saveCustomerDetails()){
				Cookie[] cookies = request.getCookies();
				for(Cookie cookie : cookies){
					if("nextPage".equals(cookie.getName())){
						nextPage = "cuspaypage.jsp";
						//nextPage = "paymentpage.cpf";
						//#############3
	/*start*/				ArrayList<Cart> carts = Cart.getCartItems(customer);
							int totalAmmount = 0;
							int bookQuantity = 0;
								
							for(Cart cart : carts){
								int tmpBook = cart.getQuantity();
								bookQuantity += tmpBook;
								totalAmmount += tmpBook*cart.getSellerBook().getPrice();
							}
							request.setAttribute("totalAmmount",totalAmmount);
							request.setAttribute("bookQuantity",bookQuantity);
	/* end */				request.setAttribute("carts",carts);
						//########3
						System.out.println(cookie.getValue() +"Cookie ##");
						System.out.println(nextPage +" CPUDServlet ##");
						Cookie ck = new Cookie("nextPage","");
						ck.setMaxAge(0);
						response.addCookie(ck);
					}
				}
			}else{
				nextPage = "notfounderror.jsp";
			}
		}else{
			request.setAttribute("emessage","session is expired");
		}
		System.out.println("hellow from CPUDServlet");
		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}