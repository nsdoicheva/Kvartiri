package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import exceptions.WeakPasswordException;
import exceptions.WrongInputException;
import source.Administrator;
import source.Agency;
import source.RegisteredUser;
import source.User;
import source.UserAlreadyExistsException;
import source.UserDAO;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegisterServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("name");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String telephone = request.getParameter("telephone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		try {
			User user = new RegisteredUser(name, username, password, telephone, address, email);
			if (!UserDAO.getInstance().saveUser(user)) {
				RequestDispatcher view = request.getRequestDispatcher("registrationFailedNameEmail.html");
				view.forward(request, response);
				return;
			}
		} catch (WeakPasswordException e) {
			RequestDispatcher view1 = request.getRequestDispatcher("registrationFailedPassword.html");
			view1.forward(request, response);
			return;
		}
		RequestDispatcher view2 = request.getRequestDispatcher("indexUser.jsp");
		view2.forward(request, response);

		// Administrator.getInstance().registerUser(name, username, password,
		// telephone, address, email);

	}

}
