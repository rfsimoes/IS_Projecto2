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
	 * M�todo para efetuar login. Verifica se o username e a password existem e
	 * combinam.
	 * 
	 * @param username
	 * @param password
	 * @return true se existir, false caso contr�rio
	 */
	public boolean login(String username, String password) {
		Query query = em.createQuery("SELECT s FROM User s WHERE username= :u AND password= :p");
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

	/**
	 * M�todo para efetuar registo. Verifica se o username e o email j� existem.
	 * Caso j� existam, o registo n�o � feito. true se o registo for feito com
	 * sucesso, false caso contr�rio.
	 * 
	 */
	public boolean register(String username, String password, String name, String email) {
		if (checkUsername(username) == false && checkEmail(email) == false) {
			User novo = new User(username, password, name, email);

			em.persist(novo);
			return true;
		}
		return false;
	}

	private boolean checkUsername(String username) {
		Query query = em.createQuery("FROM User s WHERE s.username= :u");
		query.setParameter("u", username);

		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		if (users.size() > 0) {
			return true;
		}
		return false;
	}

	private boolean checkEmail(String email) {
		Query query = em.createQuery("SELECT s FROM User s WHERE email= :e");
		query.setParameter("e", email);

		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		if (users.size() > 0) {
			return true;
		}
		return false;
	}

	/*
	 * Ir buscar um user (Poss�vel duplicado de checkUsername)
	 */
	public User getUser(String username) {
		Query query = em.createQuery("FROM User s WHERE s.username= :u");
		query.setParameter("u", username);

		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		if (users.size() > 0) {
			return users.get(0);
		}
		return null;
	}
	
	/*
	 * Ir buscar todos os utilizadores (� excep��o do admin)
	 */
	public List<User> getAllUsers() {
		Query query = em.createQuery("FROM User s WHERE s.username NOT LIKE 'admin'");

		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		if (users.size() > 0) {
			return users;
		}
		return null;
	}

	public User editAccount(User user, String password, String name, String currEmail, String newEmail) {
		
		if (currEmail.toLowerCase().compareTo(newEmail.toLowerCase()) != 0) {
			if (checkEmail(newEmail) == true)
				return null;
		}

		user.setPassword(password);
		user.setName(name);
		user.setEmail(newEmail);
		em.merge(user);
		return user;
	}
	
	public void deleteAccount(User user)
	{
		em.remove(em.contains(user) ? user : em.merge(user));
	}

}
