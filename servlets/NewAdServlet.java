package servlets;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import source.Ad;
import source.AdDAO;
import source.Administrator;
import source.User;

/**
 * Servlet implementation class NewAdServlet
 */
@WebServlet("/NewAdServlet")
public class NewAdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewAdServlet() {
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
		
		User uploadUser = null;
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String description = request.getParameter("description");
		String type= request.getParameter("type");
		double price = Double.parseDouble(request.getParameter("price"));
		String neighborhood = request.getParameter("neighborhood");
		double squareMeters = Double.parseDouble(request.getParameter("squareMeters"));
		int rooms = Integer.parseInt(request.getParameter("rooms"));
		
		for (User user : Administrator.getInstance().getAllUsersFromDB()) {
			if (user.getUserName().compareTo(username)==0) {
				uploadUser=user;
			}
		}
		
		Ad ad = new Ad(name, address, description, price, type, neighborhood, uploadUser, rooms, squareMeters);
		if (!AdDAO.getInstance().saveAd(ad, username)) {
			RequestDispatcher view = request.getRequestDispatcher("newAd.jsp");
			view.forward(request, response);
			return;
		} else {
			RequestDispatcher view = request.getRequestDispatcher("indexUser.jsp");
			view.forward(request, response);
			return;
		}
	}
}
