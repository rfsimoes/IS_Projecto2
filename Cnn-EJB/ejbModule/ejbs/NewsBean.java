package ejbs;

import java.util.List;

import javax.ejb.EJB;
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
	
	@EJB
	UserBeanRemote userBean;

    /**
     * Default constructor. 
     */
    public NewsBean() {
        // TODO Auto-generated constructor stub
    }
    
    
    // M�todo para ir buscar todas as not�cias
    // Devolve lista de not�cias
    public List<News> getNews(String username, String password){
    	// Verificar se o utilizador tem autoriza��o para aceder ao m�todo
    	if(!userBean.login(username, password))
			return null;
    	
    	Query query = em.createQuery("FROM News n");
    	
    	 @SuppressWarnings("unchecked")
		List<News> news = query.getResultList();
    	
		return news;
    }
    
   
    // M�todo para ir buscar as not�cias de uma determinada regi�o ordenadas por data (+ recentes primeiro)
    // Recebe o nome da regi�o
    // Devolve lista de not�cias ordenadas
    public List<News> newsSortedByDate(String region, String username, String password){
    	// Verificar se o utilizador tem autoriza��o para aceder ao m�todo
    	if(!userBean.login(username, password))
			return null;
    	
    	Query query = em.createQuery("FROM News n WHERE region = :r ORDER BY date DESC");
    	query.setParameter("r", region);
    	
    	 @SuppressWarnings("unchecked")
		List<News> news = query.getResultList();
    	System.out.println(news.get(0).toString());
		return news;
    }
    
   
    // M�todo para ir buscar as not�cias de um autor
    // Recebo o nome do autor
    // Devolde lista de not�cias ordenadas
    public List<News> newsFromAuthor(String author, String username, String password){
    	// Verificar se o utilizador tem autoriza��o para aceder ao m�todo
    	if(!userBean.login(username, password))
			return null;
    	
    	Query query = em.createQuery("SELECT n FROM News n INNER JOIN n.authors a WHERE upper(a.name) LIKE upper(:author) ORDER BY date DESC");
    	query.setParameter("author", "%"+author+"%");
    	
    	@SuppressWarnings("unchecked")
		List<News> news = query.getResultList();
    	
		return news;
    }
    
    // M�todo para ir buscar as not�cias de um autor de uma determinada regi�o
    // Recebe o nome do autor e da regi�o
    // Devolve lista de not�cias ordenadas
    public List<News> newsFromAuthor(String author, String region, String username, String password){
    	// Verificar se o utilizador tem autoriza��o para aceder ao m�todo
    	if(!userBean.login(username, password))
			return null;
    	
    	Query query = em.createQuery("SELECT n FROM News n INNER JOIN n.authors a WHERE n.region LIKE :r AND upper(a.name) LIKE upper(:author) ORDER BY date DESC");
    	query.setParameter("r", region);
    	query.setParameter("author", "%"+author+"%");
    	
    	@SuppressWarnings("unchecked")
		List<News> news = query.getResultList();
    	
		return news;
    }
    
    
    // M�todo para ir buscar as not�cias mais recentes que uma data
    // Recebe a data
    // Devolve lista de not�cias ordenadas
    public List<News> newsMoreRecentThan(String date, String username, String password){
    	// Verificar se o utilizador tem autoriza��o para aceder ao m�todo
    	if(!userBean.login(username, password))
			return null;
    	
    	Query query = em.createQuery("SELECT n FROM News n WHERE n.date > '"+date+"' ORDER BY date DESC");
    	
    	@SuppressWarnings("unchecked")
		List<News> news = query.getResultList();
    	
		return news;
    }
    
    // M�todo para ir buscar as not�cias mais recentes que uma data de uma dada regi�o
    // Recebe a data e o nome da regi�o
    // Devolve lista de not�cias ordenadas
    public List<News> newsMoreRecentThan(String date, String region, String username, String password){
    	// Verificar se o utilizador tem autoriza��o para aceder ao m�todo
    	if(!userBean.login(username, password))
			return null;
    	
    	Query query = em.createQuery("SELECT n FROM News n WHERE n.region LIKE :r AND n.date > '"+date+"' ORDER BY date DESC");
    	query.setParameter("r", region);
    	
    	@SuppressWarnings("unchecked")
		List<News> news = query.getResultList();
    	
		return news;
    }
    
}
