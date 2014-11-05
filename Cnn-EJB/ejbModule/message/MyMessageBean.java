package message;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Message-Driven Bean implementation class for: MyBean
 *
 */
@MessageDriven(
  activationConfig = {
   @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
   @ActivationConfigProperty(propertyName = "destination", propertyValue = "topic/testTopic")
   },
  mappedName = "topic/testTopic")
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
   System.out.println("Recebi: \n" + ((TextMessage) message).getText());
  } catch (JMSException e) {
   e.printStackTrace();
  }
    }

}