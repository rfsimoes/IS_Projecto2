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
import common.User;
import ejbs.NewsBeanRemote;

/**
 * Servlet implementation class HighlightNewsServlet
 */
@WebServlet("/HighlightNewsServlet")
public class HighlightNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	NewsBeanRemote nbr;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HighlightNewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		HttpSession session;

		String word = request.getParameter("word");

		session = request.getSession(true);
		
		User user = (User) session.getAttribute("user");
		
		// Ir buscar informação do form
		List<String> regioes = new ArrayList<String>();	// Lista de regiões
        List<News> allNews = nbr.getNews(user.getUsername(), user.getPassword());	// Lista de todas as notícias
        List<News> highlightNews = new ArrayList<News>();	// Lista de notícias com a palavra
        
        
        // Se o utilizador tiver autorização para aceder ao método getNews()
        if(allNews != null){
        	// Percorrer todas as notícias para preencher a lista de regiões e a lista de notícias com a palavra
            for(int i=0; i<allNews.size();i++){
            	for(int j=0; j<allNews.get(i).getHighlights().size(); j++){
            		if(allNews.get(i).getHighlights().get(j).contains(word)){
            			if(!highlightNews.contains(allNews.get(i))){
            				highlightNews.add(allNews.get(i));
            			}
            			if(!regioes.contains(allNews.get(i).getRegion())){
        	        		regioes.add(allNews.get(i).getRegion());
        	        	}
            		}
            	}
            }
    		
            session.setAttribute("regioes",regioes);
            session.setAttribute("highlightNews", highlightNews);
    		session.setAttribute("word", word);
    		
    		dispatcher = request.getRequestDispatcher("/HighlightNews.jsp");
        }
        // Se o utilizador não tiver autorização
        else{
        	dispatcher = request.getRequestDispatcher("/Login.jsp?unauthorized=1");
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
