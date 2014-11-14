package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import common.News;
import ejbs.NewsBeanRemote;


/**
 * Servlet implementation class AuthorNewsServlet
 */
@WebServlet("/AuthorNewsServlet")
public class AuthorNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB
	NewsBeanRemote nbr;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AuthorNewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		HttpSession session;

		String author = request.getParameter("authorName");

		session = request.getSession(true);
		
		List<String> regioes = new ArrayList<String>();	// Lista de regiões
        List<News> newsAuthor = nbr.newsFromAuthor(author);	// Lista de notícias
        
        // Percorrer lista de notícias para preencher lista de regiões
        for(int i=0; i<newsAuthor.size();i++){
        	if(!regioes.contains(newsAuthor.get(i).getRegion())){
        		regioes.add(newsAuthor.get(i).getRegion());
        	}
        }
		
        session.setAttribute("regioes", regioes);
		session.setAttribute("author", author);
		
		dispatcher = request.getRequestDispatcher("/AuthorNews.jsp");
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
