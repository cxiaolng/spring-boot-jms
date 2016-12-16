package com.chl.jms.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Description;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.stereotype.Component;

/**
 * 
 * <p>Title: ActiveMQConfig</p>
 * <p>Description:配置类 </p>
 * @author Harry Chang
 * @Email cxiaolng@qq.com
 * @date 2016-12-16 13:50:41
 */
@Component
@Configuration
@Description(value = "ActiveMQ Configuration")
public class ActiveMQConfig {
	
	@Autowired
	private CustomizeDynamicDestinationResolver customizeDynamicDestinationResolver;
	
	public PooledConnectionFactory connectionFactory(ActiveMQConnectionFactory connectionFactory) {
		PooledConnectionFactory pool = new PooledConnectionFactory(connectionFactory);
		pool.setMaxConnections(5);
		return pool;
	}
	
	@Bean(name="jmsTemplate")
	public JmsTemplate jmsTemplate(ActiveMQConnectionFactory connectionFactory) {
		JmsTemplate template = new JmsTemplate(connectionFactory(connectionFactory));
		template.setDestinationResolver(customizeDynamicDestinationResolver);
		template.setMessageConverter(new SimpleMessageConverter());
		return template;
	}
	
}
