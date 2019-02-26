package listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContext;

import java.util.ArrayList;

import models.State;
import models.Book;
import models.SellerBook;


public class AppListener implements ServletContextListener{
	public void contextInitialized(ServletContextEvent ev){
		ServletContext context = ev.getServletContext();

		ArrayList<State> states = State.collectStates();
		ArrayList<SellerBook> recAddBooks = SellerBook.recentlyAddedBooks();
		/*
		System.out.println("####################################");
		System.out.println("####################################");
		System.out.println("####################################");
		System.out.println("####################################");
		System.out.println("####################################");
		System.out.println(recAddBooks);
		System.out.println("####################################");
		System.out.println("####################################");
		System.out.println("####################################");
		System.out.println("####################################");
		System.out.println("####################################");
		*/
		context.setAttribute("states",states);
		context.setAttribute("recAddBooks",recAddBooks);
	}

	public void contextDestroyed(ServletContextEvent ev){
	
	}
}