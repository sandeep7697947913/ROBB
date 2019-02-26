package controllers;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;

import java.io.IOException;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import java.util.HashMap;

import models.Publisher;
import models.Category;
import models.Book;

public class BookPublishServlet extends HttpServlet{
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		HttpSession session = request.getSession();
		Publisher publisher = (Publisher)session.getAttribute("publisher");
		String nextPage = "login.jsp";

		HashMap<String,String> params = new HashMap<String,String>();

		//String path = getServletContext().getRealPath("/WEB-INF/data/books");
		String path = getServletContext().getRealPath("/data/booksimages");
		if(publisher!=null){
			if(ServletFileUpload.isMultipartContent(request)){
				DiskFileItemFactory dfif = new DiskFileItemFactory();
				ServletFileUpload sfu = new ServletFileUpload(dfif);
				try{
					List<FileItem> fileItems = sfu.parseRequest(request);
					Iterator<FileItem> itr = fileItems.iterator();
					FileItem ftimage = null;
					//FileItem ftimage = new FileItem();
					while(itr.hasNext()){
						FileItem fileItem = itr.next();
						if(fileItem.isFormField()){
							params.put(fileItem.getFieldName(),fileItem.getString());
						}else{
							ftimage = fileItem;
						}
					}

					Book book = new Book(
								params.get("bookname"),
								params.get("isbn"),
								Integer.parseInt(params.get("price")),
								params.get("author"),
								publisher,
								new Category(Integer.parseInt(params.get(("categoryId")))),
								Integer.parseInt(params.get("edition"))
								);

					if(book.savePPBook()){
						File file = new File(path,book.getBookId()+".jpg");
						ftimage.write(file);
						nextPage = "publisherpbook.jsp";
					}else{
						
					}
				}catch(FileUploadException e){
					e.printStackTrace();
				}catch(Exception e){
					e.printStackTrace();
				} 
			}else{
			
			}
		}else{
			request.setAttribute("emessage","session is expired");
		}

		request.getRequestDispatcher(nextPage).forward(request,response);
	}
}