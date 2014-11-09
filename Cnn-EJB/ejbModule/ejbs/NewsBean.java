package ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import common.News;

/**
 * Session Bean implementation class NewsBean
 */
@Stateless
public class NewsBean implements NewsBeanRemote {
	
	@PersistenceContext(name = "TestPersistence")
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public NewsBean() {
        // TODO Auto-generated constructor stub
    }
    
    
    /**
     * Método para ir buscar as notícias ordenadas por data (+ recentes primeiro)
     * @return lista de notícias ordenadas
     */
    public List<News> newsSortedByDate(){
    	String query = "SELECT n FROM News n ORDER BY region, date DESC";
    	List<News> news = null;
    	
    	news = (List<News>) em.createQuery(query).getResultList();
    	
		return news;
    }
    
    
    /**
     * Método para ir buscar as notícias de um autor
     * @param author
     * @return lista de notícias ordenadas
     */
    public List<News> newsFromAuthor(String author){
    	String query = "SELECT n FROM News n INNER JOIN n.authors a WHERE a.name LIKE '%"+author+"%' ORDER BY date DESC";
    	List<News> news = null;
    	
    	news = (List<News>) em.createQuery(query).getResultList();
    	
		return news;
    }
    
    
    /**
     * Método para ir buscar as notícias mais recentes que uma data
     * @param date
     * @return lista de notícias ordenadas
     */
    public List<News> newsMoreRecentThan(String date){
    	String query = "SELECT n FROM News n WHERE n.date > '"+date+"' ORDER BY date DESC";
    	List<News> news = null;
    	
    	news = (List<News>) em.createQuery(query).getResultList();
    	
		return news;
    }
    
    
    /**
     * Método para ir buscar as notícias em que os highlights têm uma determinada palavra.
     * @param word
     * @return lista de notícias ordenadas
     */
    public List<News> newsWithHighlightWord(String word){
    	//String query = "SELECT n FROM News n INNER JOIN n.highlights h WHERE h LIKE '%"+word+"%' ORDER BY date DESC";
    	String query = "select * from news n "
    			+ "inner join newshighlights h on n.newsid=h.newsid and h.highlight like '%murder%' order by date desc";
    	List<News> news = null;
    	
    	
    	//news = (List<News>) em.createQuery(query).getResultList();
    	 news = (List<News>) em.createNativeQuery(query).getResultList();
    	
		return news;
    }
}
