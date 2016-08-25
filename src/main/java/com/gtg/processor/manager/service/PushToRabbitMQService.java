package com.gtg.processor.manager.service;

import static com.gtg.processor.constants.RabbitMQConstants.CREATE_GTG_DATA_ROUTING_KEY;
import static com.gtg.processor.constants.RabbitMQConstants.GTG_EXCHANGE_NAME;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gtg.processor.events.GTGMQEvent;

public interface PushToRabbitMQService {

	public void pushUserToMQ(GTGMQEvent event);

	@Service
	public class Impl implements PushToRabbitMQService {
		public static final Logger log = Logger.getLogger(Impl.class);

		@Autowired
		private RabbitTemplate rabbitTemplate;

		@Override
		public void pushUserToMQ(GTGMQEvent event) {
			rabbitTemplate.convertAndSend(GTG_EXCHANGE_NAME, CREATE_GTG_DATA_ROUTING_KEY, event);

		}

	}

}
