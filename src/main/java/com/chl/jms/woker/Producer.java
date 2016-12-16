package com.chl.jms.woker;

import java.util.concurrent.ConcurrentHashMap;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Component;

/**
 * 
 * <p>Title: Producer</p>
 * <p>Description: 消息生产者</p>
 * @author Harry Chang
 * @Email cxiaolng@qq.com
 * @date 2016-12-16 13:47:12
 */
@Component
public class Producer {

	private static ConcurrentHashMap<String, ActiveMQTopic> topicPool = new ConcurrentHashMap<String, ActiveMQTopic>();

	private static ConcurrentHashMap<String, ActiveMQQueue> queuePool = new ConcurrentHashMap<String, ActiveMQQueue>();

	@Autowired
	private JmsTemplate jmsTemplate;
	/**
	 * 发送Topic消息
	 * 
	 * @param topicName
	 *            Topic队列名
	 * @param message
	 *            消息体
	 * @return
	 * @throws JMSException
	 */
	public String sendAndReceiveTopicMessage(final String topicName, final String message) throws JMSException {

		Message replyMsg = jmsTemplate.sendAndReceive(topicFactory(topicName), new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
		TextMessage textMessage = (TextMessage) replyMsg;
		return textMessage.getText();
	}

	/**
	 * 发送Topic消息
	 * 
	 * @param topicName
	 *            Topic队列名
	 * @param message
	 *            消息体
	 * @return
	 * @throws JMSException
	 */
	public void sendTopicMessage(final String topicName, final String message) throws JMSException {

		jmsTemplate.send(topicFactory(topicName), new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

	/**
	 * 发送Queue消息
	 * 
	 * @param queueName
	 *            queue队列名
	 * @param message
	 *            消息体
	 * @return
	 * @throws JMSException
	 */
	public String sendAndReceiveQueueMessage(final String queueName, final String message) throws JMSException {
		Message replyMsg = jmsTemplate.sendAndReceive(queueFactory(queueName), new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
		TextMessage textMessage = (TextMessage) replyMsg;
		return textMessage.getText();
	}

	/**
	 * 发送Queue消息
	 * 
	 * @param queueName
	 *            queue队列名
	 * @param message
	 *            消息体
	 * @return
	 * @throws JMSException
	 */
	public void sendQueueMessage(final String queueName, final String message) throws JMSException {
		jmsTemplate.send(queueFactory(queueName), new MessageCreator() {
			@Override
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage(message);
			}
		});
	}

	private ActiveMQTopic topicFactory(String topicName) {
		if (!topicPool.containsKey(topicName)) {
			topicPool.put(topicName, new ActiveMQTopic(topicName));
		}
		return topicPool.get(topicName);
	}

	private ActiveMQQueue queueFactory(String queueName) {
		if (!queuePool.containsKey(queueName)) {
			queuePool.put(queueName, new ActiveMQQueue(queueName));
		}
		return queuePool.get(queueName);
	}

}