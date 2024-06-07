package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Services.MyToDoService;
import model.beans.UsersBean;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		session.invalidate();
		System.out.println("セッション削除");
		this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		String e_mail = request.getParameter("e_mail");
		String password = request.getParameter("password");

		MyToDoService ms = new MyToDoService();

		if (ms.login(e_mail, password) != null) {
			
			//セッション
			HttpSession session = request.getSession();
			session.setAttribute("user",ms.login(e_mail, password) );
			UsersBean u = (UsersBean) session.getAttribute("user");
			System.out.println("セッションにセット");
			
			
			int user_id = u.getUser_id();
			String name = u.getName();
			e_mail = u.getE_mail();
			password = u.getPassword();
			
			ms.logniLog(user_id, name, e_mail, password);
			System.out.println("ログインのログ記録");
			
			response.sendRedirect("TaskServlet");
		} else {
			//System.out.println("loginmiss");
			this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
			
		}
	}

}
