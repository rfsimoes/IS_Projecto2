package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.User;

import ejbs.NewsBeanRemote;
import ejbs.UserBeanRemote;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	NewsBeanRemote nbr;
	@EJB
	UserBeanRemote ubr;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		HttpSession session;

		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if (ubr.login(username, password) == true) {
			session = request.getSession(true);
			// Set the username and password of a new User object and add this object
		    // to the session, using session.setAttribute(...)
			User userData = ubr.getUser(username);
			session.setAttribute("user", userData);
			session.setAttribute("newsBean", nbr);
			session.setAttribute("userBean", ubr);
			dispatcher = request.getRequestDispatcher("/Menu.jsp");
		} else {
			dispatcher = request.getRequestDispatcher("/invalidUser.html");
		}
		
		dispatcher.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
