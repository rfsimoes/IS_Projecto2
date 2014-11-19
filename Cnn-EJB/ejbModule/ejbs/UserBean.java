package ejbs;

import java.util.Collections;
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

	/*
	*  Método para efetuar login. Verifica se o username e a password existem e combinam.
	*  Recebe o username e a password
	*  Devolve true se existirem e combinarem, false caso contrário
	*
	*  NOTA: Este método serve também para garantir o acesso seguro aos beans.
	* 		 Em cada método que seja necessário estar logado para efectuar a acção correspondente, haverá a seguinte condição:
	*			 if(!login(username, password)){ return [null/false]; }
	*		 Isto permite verificar se um utilizador tem permissão para aceder ao método.
	*		 Caso não tenha, será informado do acesso indevido e redirecionado para a página de login.
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

	
	// Método para efetuar registo. Verifica se o username e o email já existem.
	// Caso já existam, o registo não é feito. 
	// Recebe os dados para o registo (username, password, nome e email).
	// Devolve true se o registo for feito com sucesso, false caso contrário.
	public boolean register(String username, String password, String name, String email) {
		if (checkUsername(username) == false && checkEmail(email) == false) {
			User novo = new User(username, password, name, email);

			em.persist(novo);
			return true;
		}
		return false;
	}

	// Método para verificar se um username existe
	// Recebe o username
	// Devolve true caso exista, false caso contrário
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

	// Método para verificar se um email existe
	// Recebe o email
	// Devolve true caso exista, false caso contrário
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

	
	// Método para ir buscar um User através do username
	// Recebe o username
	// Devovle o utilizador caso exista, null caso contário
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
	
	// Método para ir buscar todos os utilizadores (à excepção do admin)
	// Devolve lista de utilizadores, lista vazia caso não existam utilizadores ou null se for feito um acesso indevido ao método
	public List<User> getAllUsers(String username, String password) {
		if(!login(username, password))
			return null;
		
		Query query = em.createQuery("FROM User s WHERE s.username NOT LIKE 'admin'");

		@SuppressWarnings("unchecked")
		List<User> users = query.getResultList();
		if (users.size() > 0) {
			return users;
		}
		List<User> emptyList = Collections.emptyList();
		return emptyList;
	}

	
	// Método para editar a conta de um utilizador
	// Recebe o utilizador, a password, o nome, o email atual e o novo email
	// Devolve o utilizador caso a edição seja feita com sucesso, 
	// 		   um utilizador com o username vazio caso falhe por email já existente, 
	//		   null caso falhe por acesso indevido ao método
	public User editAccount(User user, String password, String name, String currEmail, String newEmail, String usernameSec, String passwordSec) {		
		if(!login(usernameSec, passwordSec))
			return null;
		
		// Se o email atual for diferente no novo email
		if (currEmail.toLowerCase().compareTo(newEmail.toLowerCase()) != 0) {
			// Se o novo email já existir
			if (checkEmail(newEmail) == true){
				User erro = new User();
				erro.setUsername("");
				return erro;
			}
		}

		user.setPassword(password);
		user.setName(name);
		user.setEmail(newEmail);
		em.merge(user);
		return user;
	}
	
	
	// Método para apagar uma conta
	// Recebe o utilizador a apagar
	// Devolve 1 se apagar o utilizador, -1 caso se verifique acesso indevido ao método
	public int deleteAccount(User user, String username, String password)
	{
		if(!login(username, password))
			return -1;
		
		// Se a BD tiver o user, apaga-o. Se não tiver, tem de fazer o merge desse user para o apagar
		em.remove(em.contains(user) ? user : em.merge(user));
		
		return 1;
	}

}
