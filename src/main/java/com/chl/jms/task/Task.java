package com.chl.jms.task;

import javax.annotation.Resource;
import javax.jms.JMSException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.chl.jms.woker.Producer;

@Component
public class Task {
	
	private static final Logger logger = LoggerFactory.getLogger(Task.class);
	
	@Resource(name="producer")
	private Producer producer;
	
	@Scheduled(fixedRate = 3000)
	public void producer1() {
		logger.error("producer1 send.");
		try {
			producer.sendTopicMessage("test.springboot.topic", "hello topic.");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Scheduled(fixedRate = 3000)
	public void producer2() {
		logger.error("producer2 send.");
		try {
			producer.sendQueueMessage("test.springboot.queue", "hello queue.");
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
