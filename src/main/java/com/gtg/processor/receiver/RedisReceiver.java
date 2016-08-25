package com.gtg.processor.receiver;

import com.gtg.processor.events.GTGRedisEvent;
import com.gtg.processor.manager.dto.UserDTO;

public class RedisReceiver {

	public void receiveGTGRedisEvent(GTGRedisEvent event) {
		System.out.println("receiveGTGRedisEvent() - start");
		UserDTO dto = event.getUserDTO();

		System.out.println("DTO  : " + dto);

		System.out.println("receiveGTGRedisEvent() - end");

	}

}
