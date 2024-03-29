package servlet;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.User;
import ejbs.UserBeanRemote;

/**
 * Servlet implementation class CreateUserServlet
 */
@WebServlet("/CreateUserServlet")
public class CreateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	UserBeanRemote ubr;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateUserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession(true);
		
		/// Ir buscar informa��o do form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		
		// Se a cria��o do utilizador correr bem
		if (ubr.register(username, password, name, email) == true) {
			User userData = (User) session.getAttribute("user");
			
			// Ir buscar todos os utilizadores
			List<User> utilizadores = ubr.getAllUsers(userData.getUsername(), userData.getPassword());
			
			// Se o utilizador tiver autoriza��o para efectuar a ac��o
			if(utilizadores != null){
				session.setAttribute("utilizadores", utilizadores);
				dispatcher = request.getRequestDispatcher("/MenuAdmin.jsp");
			}
			// Se estiver a aceder ao m�todo sem autoriza��o
			else{
				dispatcher = request.getRequestDispatcher("/Login.jsp?unauthorized=1");
			}
		}
		// Caso a cria��o do utilizador falhe (por email j� existente)
		else {
			dispatcher = request.getRequestDispatcher("/CreateUser.jsp?fail=1");
		}
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
