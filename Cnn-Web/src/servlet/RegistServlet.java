package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.naming.InitialContext;
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
 * Servlet implementation class RegistServlet
 */
@WebServlet("/RegistServlet")
public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	NewsBeanRemote nbr;
	@EJB
	UserBeanRemote ubr;

	/**
	 * Default constructor.
	 */
	public RegistServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int control = 0;
		Boolean regist;
		RequestDispatcher dispatcher = null;
		HttpSession session;

		if (request.getParameterMap().isEmpty() == false) {
			String button = request.getParameter("SUBMIT");
			if (button != null) {
				if (button.equals("Regist Account")) {
					String username = request.getParameter("username");
					String password = request.getParameter("password");
					String name = request.getParameter("name");
					String email = request.getParameter("email");
					if (username != "" && password != "" && name != "" && email != "") {
						if (ubr.register(username, password, name, email) == true) {
							dispatcher = request.getRequestDispatcher("/Login.jsp");
						} else {
							PrintWriter out = response.getWriter();
							response.setContentType("text/html");
							out.println("<script type=\"text/javascript\">");
							out.println("alert('Falha ao efetuar registo! Possibilidade de utilizador já se encontrar existente!');");
							out.println("</script>");
							dispatcher = request.getRequestDispatcher("/Regist.jsp");
						}
					}
					dispatcher.forward(request, response);
				} 
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
