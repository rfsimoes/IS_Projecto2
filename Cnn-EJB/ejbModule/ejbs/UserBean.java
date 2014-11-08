package ejbs;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import common.User;

/**
 * Session Bean implementation class UserBean
 */
@Stateless
public class UserBean implements UserBeanRemote {

	@PersistenceContext(name = "TestPersistence")
	private EntityManager em;
	
	private User user = null;
	
    /**
     * Default constructor. 
     */
    public UserBean() {
        // TODO Auto-generated constructor stub
    }
    
    
    /**
     * M�todo para verificar se o login (username+password) existe
     * @param username
     * @param password
     * @return true se existir, false caso contr�rio
     */
    public boolean login(String username, String password){
    	Query query = em.createQuery("SELECT s FROM User s WHERE username=':u' AND password=':p'");
    	query.setParameter("u", username);
    	query.setParameter("p", password);
    	
    	@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		if (users.size() > 0) {
			user = users.get(0);
			return true;
		}
		return false;
    }
    
    
    public boolean register(String username, String password, String name, String email){
    	if(checkUsername(username)==false && checkEmail(email)==false){
    		User novo = new User(username, password, name, email);
    		
    		em.persist(novo);
    		return true;
    	}
    	return false;
    }


	private boolean checkUsername(String username) {
		Query query = em.createQuery("SELECT s FROM User s WHERE username=':u'");
    	query.setParameter("u", username);
		
    	@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		if (users.size() > 0) {
			return true;
		}
		return false;
	}
	
	
	private boolean checkEmail(String email) {
		Query query = em.createQuery("SELECT s FROM User s WHERE email=':e'");
    	query.setParameter("e", email);
		
    	@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		if (users.size() > 0) {
			return true;
		}
		return false;
	}

}
