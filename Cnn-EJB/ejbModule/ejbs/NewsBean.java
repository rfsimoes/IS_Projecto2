package ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
    
    
    // Método para ir buscar todas as notícias
    // Devolve lista de notícias
    public List<News> getNews(){
    	Query query = em.createQuery("FROM News n");
    	
    	 @SuppressWarnings("unchecked")
		List<News> news = query.getResultList();
    	
		return news;
    }
    
   
    // Método para ir buscar as notícias de uma determinada região ordenadas por data (+ recentes primeiro)
    // Recebe o nome da região
    // Devolve lista de notícias ordenadas
    public List<News> newsSortedByDate(String region){
    	Query query = em.createQuery("FROM News n WHERE region = :r ORDER BY date DESC");
    	query.setParameter("r", region);
    	
    	 @SuppressWarnings("unchecked")
		List<News> news = query.getResultList();
    	System.out.println(news.get(0).toString());
		return news;
    }
    
   
    // Método para ir buscar as notícias de um autor
    // Recebo o nome do autor
    // Devolde lista de notícias ordenadas
    public List<News> newsFromAuthor(String author){
    	Query query = em.createQuery("SELECT n FROM News n INNER JOIN n.authors a WHERE upper(a.name) LIKE upper(:author) ORDER BY date DESC");
    	query.setParameter("author", "%"+author+"%");
    	
    	@SuppressWarnings("unchecked")
		List<News> news = query.getResultList();
    	
		return news;
    }
    
    // Método para ir buscar as notícias de um autor de uma determinada região
    // Recebe o nome do autor e da região
    // Devolve lista de notícias ordenadas
    public List<News> newsFromAuthor(String author, String region){
    	Query query = em.createQuery("SELECT n FROM News n INNER JOIN n.authors a WHERE n.region LIKE :r AND upper(a.name) LIKE upper(:author) ORDER BY date DESC");
    	query.setParameter("r", region);
    	query.setParameter("author", "%"+author+"%");
    	
    	@SuppressWarnings("unchecked")
		List<News> news = query.getResultList();
    	
		return news;
    }
    
    
    // Método para ir buscar as notícias mais recentes que uma data
    // Recebe a data
    // Devolve lista de notícias ordenadas
    public List<News> newsMoreRecentThan(String date){
    	Query query = em.createQuery("SELECT n FROM News n WHERE n.date > '"+date+"' ORDER BY date DESC");
    	
    	@SuppressWarnings("unchecked")
		List<News> news = query.getResultList();
    	
		return news;
    }
    
    // Método para ir buscar as notícias mais recentes que uma data de uma dada região
    // Recebe a data e o nome da região
    // Devolve lista de notícias ordenadas
    public List<News> newsMoreRecentThan(String date, String region){
    	Query query = em.createQuery("SELECT n FROM News n WHERE n.region LIKE :r AND n.date > '"+date+"' ORDER BY date DESC");
    	query.setParameter("r", region);
    	
    	@SuppressWarnings("unchecked")
		List<News> news = query.getResultList();
    	
		return news;
    }
    
}
