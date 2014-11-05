package message;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
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

/**
 * Message-Driven Bean implementation class for: MyBean
 *
 */
@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
		@ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/testTopic") }, mappedName = "topic/testTopic")
public class MyMessageBean implements MessageListener {

	/**
	 * Default constructor.
	 */
	public MyMessageBean() {
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	public void onMessage(Message message) {
		try {
			System.out.println("Recebeu stuff");
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

			System.out.println("Ficheiro '" + ficheiroXML + "' criado.\n");
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}

}