package ejbs;

import java.util.List;

import javax.ejb.Remote;

import common.User;

@Remote
public interface UserBeanRemote {
	public boolean login(String username, String password);
	public boolean register(String username, String password, String name, String email);
	public User getUser(String username);
	public List<User> getAllUsers();
	public User editAccount(User user, String password, String name, String currEmail, String newEmail);
	public void deleteAccount(User user);
}
