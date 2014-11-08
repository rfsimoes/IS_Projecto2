package message;

import generated.Cnn;
import generated.Region;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.ActivationConfigProperty;
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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import common.Author;
import common.News;
import common.User;

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
	
	/**
	 * Default constructor.
	 */
	public MyMessageBean() {
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		System.out.println("Recebeu stuff");
		
		try {
			JAXBContext context = JAXBContext.newInstance(Cnn.class);
			
			Unmarshaller u = context.createUnmarshaller();
			String msg = ((TextMessage) message).getText();
			
			Cnn cnn = (Cnn) u.unmarshal(new StringReader(msg));
			
			// Percorrer as regiões
			for(Region r:cnn.getRegion()){
				// Percorrer as notícias
				for(generated.News n:r.getNews()){
					// Lista de autores para cada notícia
					List<Author> authors = new ArrayList<Author>();
					// Percorrer cada autor para o adicionar à BD e à lista
					for(String name:n.getAuthor()){
						// Verificar se já existe um autor com o mesmo nome
						Author existe = em.find(Author.class, name);
						// Existindo ou não, é adicionado à lista de autores da notícia
						Author a = new Author(name);
						authors.add(a);
						// Só é adicionado à tabela Author se ainda não existir
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
					java.sql.Date jsqlDate = new java.sql.Date(cal.getTime().getTime());
					// Criar nova notícia com a estrutura correspondente à da BD
					News nova = new News(r.getName(), n.getTitle(), n.getUrl(), n.getHighlights(), jsqlDate, authors, n.getText(), n.getPhotoURL(), n.getVideoURL());
					// Adicionar notícia à BD
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
		
		/*// Adicionar ao XML a referência ao XSLT

		String msg = ((TextMessage) message).getText();
		String aux = (msg.split("\\?>"))[0]
				+ "?><?xml-stylesheet type=\"text/xsl\" href=\"Stylesheet.xsl\"?>"
				+ msg.split("\\?>")[1];
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DocumentBuilderFactory factory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();
		Document doc = builder
				.parse(new InputSource(new StringReader(aux)));
		DOMSource source = new DOMSource(doc);
		String ficheiroXML = "HTML_" + System.currentTimeMillis() + ".xml";
		StreamResult result = new StreamResult(new File(ficheiroXML));
		transformer.transform(source, result);

		System.out.println("Ficheiro '" + ficheiroXML + "' criado.\n");*/
	}

}