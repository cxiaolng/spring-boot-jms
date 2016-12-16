package com.chl.jms.woker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * 
 * <p>Title: Receiver</p>
 * <p>Description: 消息消费者</p>
 * @author Harry Chang
 * @Email cxiaolng@qq.com
 * @date 2016-12-16 13:47:52
 */
@Component
public class Receiver {
	
	private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

	@JmsListener(destination = "test.springboot.topic")
	public void topic1HandleMessage(String message) {
		logger.error("########### topic 1 Recerved Message: {}", message);
	}
	
	@JmsListener(destination = "test.springboot.topic")
	public void topic2HandleMessage(String message) {
		logger.error("########### topic 2 Recerved Message: {}", message);
	}
	
	@JmsListener(destination = "test.springboot.queue")
	public void queue1HandleMessage(String message) {
		logger.error("########### queue 1 Recerved Message: {}", message);
	}
}
