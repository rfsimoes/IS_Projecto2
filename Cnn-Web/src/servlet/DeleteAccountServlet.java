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
		
		// Informação do utilizador que está logado
		User user = (User) session.getAttribute("user");
		
		// Se for o administrador
		if(request.getParameter("userToDelete") != null){
			// Ir buscar o utilizador a apagar
			User userToRemove = ubr.getUser(request.getParameter("userToDelete"));
			
			int apagado = ubr.deleteAccount(userToRemove, user.getUsername(), user.getPassword());
			
			// Se o utilizador tiver autorização para aceder ao método deleteAccount()
			if(apagado != -1){
				// Ir buscar todos os utilizadores
				List<User> utilizadores = ubr.getAllUsers(user.getUsername(), user.getPassword());
				
				// Se o utilizador tiver autorização para aceder ao método getAllUsers()
				if(utilizadores != null){
					// Atualizar lista de utilizadores na sessão
					session.setAttribute("utilizadores", utilizadores);
					
					dispatcher = request.getRequestDispatcher("/MenuAdmin.jsp");
				}
				// Se estiver a aceder ao método getAllUsers() sem autorização
				else{
					dispatcher = request.getRequestDispatcher("/Login.jsp?unauthorized=1");
				}
			}
			// Se estiver a aceder ao método deleteAccount() sem autorização
			else{
				dispatcher = request.getRequestDispatcher("/Login.jsp?unauthorized=1");
			}
		}
		// Se for um utilizador
		else{
			int apagado = ubr.deleteAccount(user, user.getUsername(), user.getPassword());
			
			// Se o utilizador tiver autorização para aceder ao método deleteAccount()
			if(apagado != -1){
				dispatcher = request.getRequestDispatcher("/Logout.jsp");
			}
			// Se estiver a aceder ao método deleteAccount() sem autorização
			else{
				dispatcher = request.getRequestDispatcher("/Login.jsp?unauthorized=1");
			}
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
