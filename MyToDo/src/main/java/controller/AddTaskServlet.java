package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Services.MyToDoService;
import model.beans.MyToDoBean;

/**
 * Servlet implementation class AddTask
 */
@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTaskServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		
		MyToDoService ms = new MyToDoService();
		
		String title = request.getParameter("title");
		int priority = Integer.parseInt(request.getParameter("priority"));
		LocalDate deadline = LocalDate.parse(request.getParameter("deadline"));
		String content = request.getParameter("content");
		int user_id = Integer.parseInt(request.getParameter("user_id"));
		
		
		
		ArrayList<MyToDoBean> taskList = ms.insert(title, priority,deadline, content, user_id);
		request.setAttribute("AddTask", taskList);
		
		response.sendRedirect("TaskServlet");
		//this.getServletContext().getRequestDispatcher("/Task.jsp").redirect(request, response);
		
	}

}
