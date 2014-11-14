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
 * Servlet implementation class DateNewsServlet
 */
@WebServlet("/DateNewsServlet")
public class DateNewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB
	NewsBeanRemote nbr;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DateNewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = null;
		HttpSession session = request.getSession(true);

		String date = request.getParameter("date");
        
        List<String> regioes = new ArrayList<String>();	// Lista de regiões
        List<News> newsDate = nbr.newsMoreRecentThan(date);	// Lista de notícias
        
        // Percorrer lista de notícias para preencher lista de regiões
        for(int i=0; i<newsDate.size();i++){
        	if(!regioes.contains(newsDate.get(i).getRegion())){
        		regioes.add(newsDate.get(i).getRegion());
        	}
        }
        
        session.setAttribute("regioes", regioes);
		session.setAttribute("date", date);
		
		dispatcher = request.getRequestDispatcher("/DateNews.jsp");
		
		dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
