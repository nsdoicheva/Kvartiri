package servlets;


import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import source.Administrator;
import source.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session= request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String htmlFile;
		if(UserDAO.getInstance().loginUser(username, password)){
			htmlFile = "indexUser.jsp";
			session.setAttribute("username", username);
		}else{
			htmlFile = "loginFail.html";
		}
		
		RequestDispatcher view = request.getRequestDispatcher(htmlFile);
		view.forward(request, response);
	}

}
