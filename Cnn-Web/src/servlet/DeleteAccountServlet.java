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
 * Servlet implementation class DeleteAccountServlet
 */
@WebServlet("/DeleteAccountServlet")
public class DeleteAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	UserBeanRemote ubr;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteAccountServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession(true);
		
		// Se for o administrador
		if(request.getParameter("admin") != null){
			// Ir buscar o utilizador a apagar
			User userToRemove = ubr.getUser(request.getParameter("admin"));
			
			ubr.deleteAccount(userToRemove);
			
			// Atualizar lista de utilizadores na sess�o
			List<User> utilizadores = ubr.getAllUsers();
			session.setAttribute("utilizadores", utilizadores);
			
			dispatcher = request.getRequestDispatcher("/MenuAdmin.jsp");
		}
		// Se for um utilizador
		else{
			User user = (User) session.getAttribute("user");
			
			ubr.deleteAccount(user);
			
			dispatcher = request.getRequestDispatcher("/Logout.jsp");
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
