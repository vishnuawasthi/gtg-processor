package com.gtg.processor.jobs;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobRunner {

	@Scheduled(fixedRate = 100000)
	public void run() {
		
		System.out.println("run() - start");
		
		System.out.println("run() - end");
	}
	
	
}
