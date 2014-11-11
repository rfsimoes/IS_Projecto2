package servlet;

import java.io.IOException;

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
	NewsBeanRemote nbr;
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
		HttpSession session;
		
		String newPassword = request.getParameter("password");
		String newName = request.getParameter("name");
		String newEmail = request.getParameter("email");
		
		User user = (User) session.getAttribute("user");
		
		String currEmail = user.getEmail();
		
		if(newPassword.isEmpty()){
			newPassword = user.getPassword();
		}
		if(newName.isEmpty()){
			newName = user.getName();
		}
		if(newEmail.isEmpty()){
			newEmail = currEmail;
		}
		
		User atualizado = ubr.editAccount(user, newPassword, newName,currEmail, newEmail);
		if(atualizado != null){
			session.setAttribute("user", atualizado);
			dispatcher = request.getRequestDispatcher("/EditProfile.jsp?success=1");
		}
		else{
			dispatcher = request.getRequestDispatcher("/EditProfile.jsp?success=0");
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
