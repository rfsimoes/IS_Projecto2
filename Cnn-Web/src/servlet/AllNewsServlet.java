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
 * Servlet implementation class AllNewsServlet
 */
@WebServlet("/AllNewsServlet")
public class AllNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	NewsBeanRemote nbr;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AllNewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession(true);
		
		List<String> regioes = new ArrayList<String>(); // Lista de regi�es
        List<News> allNews = nbr.getNews(); // Lista de not�cias
        
        // Percorrer lista de not�cias para preencher lista de regi�es
        for(int i=0; i<allNews.size();i++){
        	if(!regioes.contains(allNews.get(i).getRegion())){
        		regioes.add(allNews.get(i).getRegion());
        	}
        }
		
        session.setAttribute("regioes", regioes);
		dispatcher = request.getRequestDispatcher("/AllNews.jsp");
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}