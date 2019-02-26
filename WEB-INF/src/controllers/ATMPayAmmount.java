package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import java.io.IOException;
import java.util.ArrayList;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import models.Customer;
import models.Cart;

public class ATMPayAmmount extends HttpServlet{
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException,ServletException{
		HttpSession session = request.getSession();
		Customer customer = (Customer)session.getAttribute("customer");
		String result = "false";
		System.out.println("servet from atmpay");
		String accountNo = null;
		if(customer!=null){
			String ano = request.getParameter("ano");
			String cno = request.getParameter("cno");
			java.sql.Date dva = java.sql.Date.valueOf(request.getParameter("dva"));
			//********************8 cart items
				ArrayList<Cart> carts = Cart.getCartItems(customer);
				int totalAmmount = 0;
				int bookQuantity = 0;
				
				for(Cart cart : carts){
					int tmpBook = cart.getQuantity();
					bookQuantity += tmpBook;
					totalAmmount += tmpBook*cart.getSellerBook().getPrice();
				}
			//*****************8
			try{
				Class.forName("com.mysql.jdbc.Driver");
				Connection conb = DriverManager.getConnection("jdbc:mysql://localhost:3306/banks?user=root&password=1234");
				Connection conbo = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookrent?user=root&password=1234");

				String q1 = "update accounts as a, (select ammount,account_no";
				String q2 = " from accounts where account_no='99996666333322221111') as a1,";
				String q3 = " (select ammount,account_no from accounts where";
				String q4 = " account_no=(select account_no from bank_branch_users where";
				String q5 = " atmcard=? and cvv=? and atmvalidity=?)) as a2";
				String q6 = " set a.ammount = case a.account_no";
				String q7 = " when a2.account_no then";
				String q8 = " (select sum(a2.ammount-?))";
				String q9 = " when a1.account_no then";
				String q10 = " (select sum(a1.ammount+?))";
				String q11 = " end where a.account_no in (a1.account_no,a2.account_no)";
				
				PreparedStatement ps = conb.prepareStatement(q1+q2+q3+q4+q5+q6+q7+q8+q9+q10+q11);
				
				ps.setString(1,ano);
				ps.setString(2,cno);
				ps.setDate(3,dva);
				ps.setInt(4,totalAmmount);
				ps.setInt(5,totalAmmount);

				int aci = ps.executeUpdate();

				if(aci==2){
					String tq1 = "insert into transactions (bank_branch_user_id,";
					String tq2 = " transaction_type_id,reason,money) values ((select";
					String tq3 = " bank_branch_user_id from bank_branch_users where";
					String tq4 = " atmcard=? and cvv=? and";
					String tq5 = " atmvalidity=?),'1','paid to ROOB',?),";
					String tq6 = " ((select bank_branch_user_id from bank_branch_users";
					String tq7 = " where account_no='99996666333322221111'),";
					String tq8 = " '2','payment from customer',?)";
					PreparedStatement pst = conb.prepareStatement(tq1+tq2+tq3+tq4+tq5+tq6+tq7+tq8);
					pst.setString(1,ano);
					pst.setString(2,cno);
					pst.setDate(3,dva);
					pst.setInt(4,totalAmmount);
					pst.setInt(5,totalAmmount);
					int j = pst.executeUpdate();
					if(j==2){
						String cq1 = "update carts as c, (select cart_id";
						String cq2 = " from carts where customer_id=? and";
						String cq3 = " cart_status_id=2) as cus set";
						String cq4 = " c.cart_status_id=1 where c.cart_id";
						String cq5= " in (cus.cart_id)";
						
						PreparedStatement psc = conbo.prepareStatement(cq1+cq2+cq3+cq4+cq5);
						psc.setInt(1,customer.getCustomerId());

						int k = psc.executeUpdate();
						if(k!=0){
							for(Cart cart : carts){
								String ord1 = "insert into orders (cart_id,quantity,";
								String ord2 = " order_pay_status_id) values (?,?,2)";
								PreparedStatement pso = conbo.prepareStatement(ord1+ord2);
								pso.setInt(1,cart.getCartId());
								pso.setInt(2,cart.getQuantity());
								pso.executeUpdate();
							}
							result = "true";
						}
					}
				}
				conbo.close();
				conb.close();
			}catch(ClassNotFoundException | SQLException e){
				e.printStackTrace();
			}
		}else{
			result = "sessionexpired";
		}
		response.getWriter().write(result);
	}
}