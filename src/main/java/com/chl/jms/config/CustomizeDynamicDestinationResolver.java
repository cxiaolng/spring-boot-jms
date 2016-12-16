package com.chl.jms.config;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

import org.springframework.jms.support.destination.DestinationResolver;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class CustomizeDynamicDestinationResolver implements DestinationResolver{

	/**
	 * Resolve the specified destination name as a dynamic destination.
	 * @param session the current JMS Session
	 * @param destinationName the name of the destination
	 * @param pubSubDomain {@code true} if the domain is pub-sub, {@code false} if P2P
	 * @return the JMS destination (either a topic or a queue)
	 * @throws javax.jms.JMSException if resolution failed
	 * @see #resolveTopic(javax.jms.Session, String)
	 * @see #resolveQueue(javax.jms.Session, String)
	 */
	@Override
	public Destination resolveDestinationName(Session session, String destinationName, boolean pubSubDomain)
			throws JMSException {

		Assert.notNull(session, "Session must not be null");
		Assert.notNull(destinationName, "Destination name must not be null");
		
		if (destinationName.toLowerCase().indexOf("topic") != -1) {
			return resolveTopic(session, destinationName);
		}
		else {
			return resolveQueue(session, destinationName);
		}
	}


	/**
	 * Resolve the given destination name to a {@link Topic}.
	 * @param session the current JMS Session
	 * @param topicName the name of the desired {@link Topic}
	 * @return the JMS {@link Topic}
	 * @throws javax.jms.JMSException if resolution failed
	 * @see Session#createTopic(String)
	 */
	protected Topic resolveTopic(Session session, String topicName) throws JMSException {
		return session.createTopic(topicName);
	}

	/**
	 * Resolve the given destination name to a {@link Queue}.
	 * @param session the current JMS Session
	 * @param queueName the name of the desired {@link Queue}
	 * @return the JMS {@link Queue}
	 * @throws javax.jms.JMSException if resolution failed
	 * @see Session#createQueue(String)
	 */
	protected Queue resolveQueue(Session session, String queueName) throws JMSException {
		return session.createQueue(queueName);
	}
	

}
