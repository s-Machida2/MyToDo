package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Services.MyToDoService;
import model.beans.MyToDoBean;
import model.beans.UsersBean;

/**
 * Servlet implementation class CompletedServlet
 */
@WebServlet("/CompletedServlet")
public class CompletedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CompletedServlet() {
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
		
		request.setAttribute("markd",MyToDoService.getMarkd() );
		request.setAttribute("markp",MyToDoService.getMarkp() );
		
		MyToDoService ms = new MyToDoService();

		HttpSession session = request.getSession();
		UsersBean ub = (UsersBean) session.getAttribute("user");
		int user_id = ub.getUser_id();
		
		
		//int user_id = Integer.parseInt(request.getParameter("user_id"));
		
		
		ArrayList<MyToDoBean> taskList = ms.completedTask(user_id);
		request.setAttribute("completedTask",taskList );
		
		this.getServletContext().getRequestDispatcher("/Completed.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
