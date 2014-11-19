package ejbs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.interceptor.InvocationContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import common.News;
import common.User;

@Singleton
@Startup
public class EmailSenderBean implements EmailSenderBeanRemote {

	@Resource
	private TimerService timerService;

	@PersistenceContext(name = "TestPersistence")
	private EntityManager em;

	@Timeout
	public void scheduler(Timer timer) {
		System.out.println("scheduler=" + timer.getInfo());
		sendEmail();
	}

	@PostConstruct
	public void initialize(InvocationContext ctx) {
		ScheduleExpression se = new ScheduleExpression();
		se.hour("0/24").minute("0").second("0");
		timerService.createCalendarTimer(se, new TimerConfig("TimerConfig!", false));
	}

	@PreDestroy
	public void stop() {
		System.out.println("Stop all existing timers");
		for (Timer timer : timerService.getTimers()) {
			System.out.println("Stopping timer: " + timer.getInfo());
			timer.cancel();
		}
	}

	public void sendEmail() {
		System.out.println("A enviar ...");
		String email = "";

		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				// return new PasswordAuthentication("isproject14@gmail.com",
				// "isproject2014");
				return new PasswordAuthentication("systemsintegration14@gmail.com", "1234567892014");
			}
		});

		try {

			Query query = em.createQuery("SELECT u FROM User u");
			@SuppressWarnings("unchecked")
			List<User> users = query.getResultList();
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Calendar cal = Calendar.getInstance();
			String today = dateFormat.format(cal.getTime());
			// System.out.println("TODAY -> " +
			// dateFormat.format(cal.getTime()));
			cal.add(Calendar.DAY_OF_MONTH, -1);
			String yesterday = dateFormat.format(cal.getTime());
			// System.out.println("YESTERDAY -> " +
			// dateFormat.format(cal.getTime()));
			query = em.createQuery("SELECT n FROM News n WHERE n.date > '" + yesterday + "' and n.date < '" + today + "'");
			@SuppressWarnings("unchecked")
			List<News> news = query.getResultList();

			for (News n : news) {
				email += n.toStringForEmail();
			}


			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("isproject14@gmail.com"));
			message.setSubject("Your daily digest");
			/*
			 * message.setText("Dear Mail Crawler," +
			 * "\n\n No spam to my email, please!");
			 */
			for (User u : users) {
				String toSend = "<p>Dear " + u.getName() + ", here goes your daily digest:</p><br>" + email;
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(u.getEmail()));
				message.setContent(toSend, "text/html");
				Transport.send(message);
			}

			System.out.println("Sent emails"+news.size());

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}

}
