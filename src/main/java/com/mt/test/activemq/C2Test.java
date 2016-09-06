package com.mt.test.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

public class C2Test {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory = new ActiveMQConnectionFactory("system", "manager", "tcp://120.25.204.152:61616");
        Connection connection = (Connection) factory.createConnection();
        connection.start();
        Session session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
        // Destination destination = session.createQueue("Consumer.B.VirtualTopic.TEST");
        // Destination destination = session.createTopic("tt1");
        Queue queue = new ActiveMQQueue("Consumer.A.VirtualTopic.TEST");
        MessageConsumer consumer = session.createConsumer(queue);
        /*
         * Message message = consumer.receive(1000); if (message instanceof TextMessage) { TextMessage textMessage =
         * (TextMessage) message; String text = textMessage.getText(); System.out.println("text=-=" + text); } else {
         * System.out.println(); }
         */
        consumer.setMessageListener(new MessageListener() {

            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    System.out.println(textMessage.getText() + "==");
                } catch (JMSException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
