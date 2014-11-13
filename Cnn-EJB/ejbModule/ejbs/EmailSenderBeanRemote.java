package ejbs;

import javax.ejb.Remote;

@Remote
public interface EmailSenderBeanRemote {

	public void sendEmail();

}
