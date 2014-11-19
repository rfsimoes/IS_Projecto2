package ejbs;

import java.util.List;

import javax.ejb.Remote;

import common.User;

@Remote
public interface UserBeanRemote {
	public boolean login(String username, String password);
	public boolean register(String username, String password, String name, String email);
	public User getUser(String username);
	public List<User> getAllUsers(String username, String password);
	public User editAccount(User user, String password, String name, String currEmail, String newEmail, String usernameSec, String passwordSec);
	public int deleteAccount(User user, String username, String password);
}
