package ejbs;

import javax.ejb.Remote;

@Remote
public interface UserBeanRemote {
	public boolean login(String username, String password);
	public boolean register(String username, String password, String name, String email);
}
