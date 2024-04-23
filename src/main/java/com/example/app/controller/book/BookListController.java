package com.example.app.controller.book;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.app.controller.SubController;
import com.example.app.domain.common.dao.common.ConnectionPool;
import com.example.app.domain.common.dto.BookDto;
import com.example.app.domain.common.dto.Criteria;
import com.example.app.domain.common.service.BookService;
import com.example.app.domain.common.service.BookServiceImpl;

public class BookListController  implements SubController{
	private BookService bookService;
	private ConnectionPool connectionPool;
	
	public BookListController(){
		
		try {
			
			bookService = BookServiceImpl.getInstance();
			connectionPool = ConnectionPool.getInstance();
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("BookListController's execute() invoke");
		
		
		
		try {
			
			String method = request.getMethod();
			String type = request.getParameter("type");
			String keyword = request.getParameter("keyword");
			String pageNo = request.getParameter("pageNo");
			//1. 1000권 -> 한페이지에 10건 -> 100 페이지
			//PageNo : 1
			//Amount : 10권
			System.out.println("pageNo : " + pageNo);
			Criteria criteria = null;
			if(pageNo == null) {
					criteria = new Criteria(); // pageNo = 1 amount = 10;
			}
			else {
				
				if(type == null ||type.isEmpty() || keyword.isEmpty()) {
					criteria = new Criteria(Integer.parseInt(pageNo), 10);
				}else{
					criteria = new Criteria(Integer.parseInt(pageNo), 10, type, keyword);
				}
			}
			//2. 유효성 체크  생략
			
			//3.
			Map<String, Object> returnValue = bookService.getAllBooks(criteria);
			
			//4.
			request.setAttribute("list", returnValue.get("list"));
			request.setAttribute("pageDto", returnValue.get("pageDto"));
			request.setAttribute("count", returnValue.get("count"));
			
			request.getRequestDispatcher("/WEB-INF/view/book/list.jsp").forward(request, response);
	
		
			
		}catch(Exception e) {
			e.printStackTrace();
			//예외페이지로 넘기기..or new ServletException("message") 처ㅣ
		}
		
	}

}
