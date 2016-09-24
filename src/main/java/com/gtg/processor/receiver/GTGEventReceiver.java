package com.gtg.processor.receiver;

import com.gtg.core.constants.GTGAsyncCallsType;
import com.gtg.processor.events.GTGMQEvent;

public class GTGEventReceiver {

	public void receiveGTGEvent(GTGMQEvent event) {
		System.out.println("receiveGTGEvent() - start");

		if (GTGAsyncCallsType.REGISTRATION.name().equalsIgnoreCase(event.getAsyncCallType())) {
			// TODO - SERVICE CALL
			
			System.out.println("event inside : " + event.getUserDTO());
		}

		if (GTGAsyncCallsType.FORGOTPASSWORD.name().equalsIgnoreCase(event.getAsyncCallType())) {
			// TODO - SERVICE CALL
		}

		System.out.println("event : " + event.getUserDTO());
		System.out.println("receiveGTGEvent() - end");
	}

}
