package za.co.polygon.repository;

import javax.jms.JMSException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Repository;

import za.co.polygon.domain.Notification;

@Repository
public class MessageRepository {

    @Autowired
    private JmsTemplate jmsTemplate;

   
    public void publish(final Notification notification, String queue){

        jmsTemplate.send(queue, new MessageCreator() {
            @Override
            public javax.jms.Message createMessage(javax.jms.Session session) throws JMSException {
                return session.createObjectMessage(notification);
            }
        });

    }

}
