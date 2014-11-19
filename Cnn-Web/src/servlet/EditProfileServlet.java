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
import ejbs.NewsBeanRemote;
import ejbs.UserBeanRemote;

/**
 * Servlet implementation class EditProfileServlet
 */
@WebServlet("/EditProfileServlet")
public class EditProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	UserBeanRemote ubr;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditProfileServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession(true);;
		
		// Ir buscar informação do form
		String newPassword = request.getParameter("password");
		String newName = request.getParameter("name");
		String newEmail = request.getParameter("email");
		
		// Informação do utilizador que está logado
		User user = (User) session.getAttribute("user");
		
		
		// Se for o administrador
		if(request.getParameter("userToEdit") != null){
			// Ir buscar utilizador a editar
			User userToEdit = ubr.getUser(request.getParameter("userToEdit"));
			
			String currEmail = userToEdit.getEmail();
			
			// Verificar a que campos foram feitas mudanças
			if(newPassword.isEmpty()){
				newPassword = userToEdit.getPassword();
			}
			if(newName.isEmpty()){
				newName = userToEdit.getName();
			}
			if(newEmail.isEmpty()){
				newEmail = currEmail;
			}
			
			// Editar conta
			User atualizado = ubr.editAccount(userToEdit, newPassword, newName,currEmail, newEmail, user.getUsername(), user.getPassword());
			
			// Se o utilizador tiver autorização para aceder ao método editAccount()
			if(atualizado != null){
				// Se o perfil do utilizador foi editado com sucesso
				if(!atualizado.getUsername().equals("")){
					dispatcher = request.getRequestDispatcher("/AdminEdit.jsp?user="+userToEdit.getUsername()+"&success=1");
				}
				// Se a edição do perfil do utilizador falhou (por email já existente)
				else{
					dispatcher = request.getRequestDispatcher("/AdminEdit.jsp?user="+userToEdit.getUsername()+"&success=0");
				}
			}
			// Se estiver a aceder ao método editAccount() sem autorização
			else{
				dispatcher = request.getRequestDispatcher("/Login.jsp?unauthorized=1");
			}
		}
		// Se for o utilizador
		else{
			String currEmail = user.getEmail();
			
			// Verificar a que campos foram feitas mudanças
			if(newPassword.isEmpty()){
				newPassword = user.getPassword();
			}
			if(newName.isEmpty()){
				newName = user.getName();
			}
			if(newEmail.isEmpty()){
				newEmail = currEmail;
			}
			
			// Editar conta
			User atualizado = ubr.editAccount(user, newPassword, newName,currEmail, newEmail, user.getUsername(), user.getPassword());
			
			
			if(atualizado != null){
				// Se o perfil do utilizador foi editado com sucesso
				if(!atualizado.getUsername().equals("")){
					// Atualizar utilizador na sessão
					session.setAttribute("user", atualizado);
					dispatcher = request.getRequestDispatcher("/EditProfile.jsp?success=1");
				}
				// Se a edição do perfil do utilizador falhou (por email já existente)
				else{
					dispatcher = request.getRequestDispatcher("/EditProfile.jsp?success=0");
				}
			}
			// Se estiver a aceder ao método sem autorização
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
