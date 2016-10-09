package com.gtg.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.gtg.services.service.PushToRabbitMQService;

@EnableWebMvc
@EnableScheduling
@EnableAutoConfiguration
@EntityScan(basePackages = "com.gtg.core.entity")
@EnableJpaRepositories(basePackages = "com.gtg.core.repository")
@ComponentScan(basePackages = {
		"com.gtg.services", 
		"com.gtg.services.jobs",
		"com.gtg.services.service",
		"com.gtg.services.receiver", 
		"com.gtg.email.service" })
public class Application implements CommandLineRunner {

	@Autowired
	PushToRabbitMQService pushToRabbitMQService;

	public void run(String... arg0) throws Exception {

	}

	public static void main(String... args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
	}

}
