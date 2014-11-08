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

	public void init() {

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		HttpSession session;

		PrintWriter ou = response.getWriter();
		response.setContentType("text/html");
		ou.println("<script type=\"text/javascript\">");
		ou.println("console.log('chegou ao get');");
		ou.println("</script>");



		String username = request.getParameter("userName");
		String password = request.getParameter("passWord");
		if (username != "" && password != "") {
			ou = response.getWriter();
			response.setContentType("text/html");
			ou.println("<script type=\"text/javascript\">");
			ou.println("console.log('chegou ao get2');");
			ou.println("</script>");
			if (ubr.login(username, password) == true) {
				ou = response.getWriter();
				response.setContentType("text/html");
				ou.println("<script type=\"text/javascript\">");
				ou.println("console.log('chegou ao get3');");
				ou.println("</script>");
				session = request.getSession(true);
				session.setAttribute("userBean", ubr);
				session.setAttribute("newsBean", nbr);
				dispatcher = request.getRequestDispatcher("/Menu.jsp");
			} else {
				dispatcher = request.getRequestDispatcher("/invalidUser.html");
			}
			dispatcher.forward(request, response);
		}
		else{
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Insira username/password');");
			out.println("</script>");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		out.println("<script type=\"text/javascript\">");
		out.println("console.log('chegou ao post');");
		out.println("</script>");
		doGet(request, response);
	}

}
