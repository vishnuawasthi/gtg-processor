package com.gtg.processor.receiver;

import com.gtg.processor.events.GTGMQEvent;

public class GTGEventReceiver {

	public void receiveGTGEvent(GTGMQEvent event) {
		System.out.println("receiveGTGEvent() - start");
		System.out.println("event : "+event.getUserDTO());
		System.out.println("receiveGTGEvent() - end");
	}
	
	
}
