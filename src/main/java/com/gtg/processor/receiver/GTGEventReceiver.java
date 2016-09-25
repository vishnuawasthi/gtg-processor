package com.gtg.processor.receiver;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.gtg.core.constants.GTGAsyncCallsType;
import com.gtg.processor.events.GTGMQEvent;
import com.gtg.processor.exception.InvalidUserException;
import com.gtg.processor.manager.service.LoginService;

@Component
public class GTGEventReceiver {
	public static final Logger log = Logger.getLogger(GTGEventReceiver.class);
	
	@Autowired
	private LoginService loginService;
	
	public void receiveGTGEvent(GTGMQEvent event) {
		log.info("receiveGTGEvent() - start");

		if (GTGAsyncCallsType.REGISTRATION.name().equalsIgnoreCase(event.getAsyncCallType())) {
			// TODO - SERVICE CALL
			if (event.getUserDTO() != null) {
				try {
					loginService.save(event.getUserDTO());
				} catch (InvalidUserException e) {
					log.error("Exception occured while processing  registration : ", e);
				}
			}
		}

		if (GTGAsyncCallsType.FORGOTPASSWORD.name().equalsIgnoreCase(event.getAsyncCallType())) {
			if (event.getUserDTO() != null) {
				try {
					loginService.forgotPasswordAsync(event.getUserDTO());
					System.out.println("forgotPassword done");
				} catch (InvalidUserException e) {
					log.error("Exception occured while processing  forgotPassword : ", e);
				}
			}
		}
		///log.info("event : " + event);
		log.info("receiveGTGEvent() - end");
	}
	
}
