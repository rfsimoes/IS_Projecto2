package message;

import ejbs.EmailSenderBeanRemote;
import generated.Cnn;
import generated.Region;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import common.Author;
import common.News;

/**
 * Message-Driven Bean implementation class for: MyBean
 *
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/testTopic") }, mappedName = "topic/testTopic")
public class MyMessageBean implements MessageListener {

	@PersistenceContext(name="TestPersistence")
	private EntityManager em;
	
	@EJB
	private EmailSenderBeanRemote esb;
	
	/**
	 * Default constructor.
	 */
	public MyMessageBean() {
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		System.out.println("Recebeu not�cias");
		//esb.sendEmail();
		
		try {
			JAXBContext context = JAXBContext.newInstance(Cnn.class);
			
			Unmarshaller u = context.createUnmarshaller();
			String msg = ((TextMessage) message).getText();
			
			Cnn cnn = (Cnn) u.unmarshal(new StringReader(msg));
			
			// Percorrer as regi�es
			for(Region r:cnn.getRegion()){
				// Percorrer as not�cias
				for(generated.News n:r.getNews()){
					// Lista de autores para cada not�cia
					List<Author> authors = new ArrayList<Author>();
					// Percorrer cada autor para o adicionar � BD e � lista
					for(String name:n.getAuthor()){
						// Verificar se j� existe um autor com o mesmo nome
						Author existe = em.find(Author.class, name);
						// Existindo ou n�o, � adicionado � lista de autores da not�cia
						Author a = new Author(name);
						authors.add(a);
						// S� � adicionado � tabela Author se ainda n�o existir
						if(existe==null){
							em.persist(a);
						}
					}
					// Preparar a data
					Calendar cal = Calendar.getInstance();
					cal.set(Calendar.YEAR, n.getDate().getYear());
					cal.set(Calendar.MONTH, n.getDate().getMonth()-1);
					cal.set(Calendar.DATE, n.getDate().getDay());
					cal.set(Calendar.HOUR_OF_DAY, n.getDate().getHour()/100);
					cal.set(Calendar.MINUTE, n.getDate().getHour()%100);
					cal.set(Calendar.SECOND, 0);
					cal.set(Calendar.MILLISECOND, 0);
					java.sql.Timestamp jsqlTs = new java.sql.Timestamp(cal.getTime().getTime());
					// Criar nova not�cia com a estrutura correspondente � da BD
					News nova = new News(r.getName(), n.getTitle(), n.getUrl(), n.getHighlights(), jsqlTs, authors, n.getText(), n.getPhotoURL(), n.getVideoURL());
					// Adicionar not�cia � BD
					em.persist(nova);
				}
			}
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}