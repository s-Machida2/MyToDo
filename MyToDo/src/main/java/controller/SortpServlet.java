package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Services.MyToDoService;

/**
 * Servlet implementation class SortpServlet
 */
@WebServlet("/SortpServlet")
public class SortpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SortpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
	
		MyToDoService.priorityOrder();
		
		String rf = request.getHeader("REFERER");
		
		if(rf.matches("http://localhost:8080/sample/DelayServlet.*")) {
			response.sendRedirect("DelayServlet");
		}else if(rf.matches("http://localhost:8080/sample/DoingServlet.*")){
			response.sendRedirect("DoingServlet");
		}else if(rf.matches("http://localhost:8080/sample/CompletedServlet.*")){
			response.sendRedirect("CompletedServlet");
		}else {
			response.sendRedirect("TaskServlet");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
