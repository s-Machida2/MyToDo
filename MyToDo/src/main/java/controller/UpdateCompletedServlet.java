package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Services.MyToDoService;
import model.beans.MyToDoBean;

/**
 * Servlet implementation class CompletedServlet
 */
@WebServlet("/UpdateCompletedServlet")
public class UpdateCompletedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCompletedServlet() {
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
		
		MyToDoService ms = new MyToDoService();
		int id = Integer.parseInt(request.getParameter("id"));
		int completed = Integer.parseInt(request.getParameter("completed"));
		
		ArrayList<MyToDoBean> taskList = ms.completed(id,completed);
		System.out.println(taskList.size());
		request.setAttribute("Task",taskList );
		
        String rf = request.getHeader("REFERER");
		
		System.out.println(rf);
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
