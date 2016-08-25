package com.gtg.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.gtg.processor.manager.service.PushToRabbitMQService;
import com.gtg.processor.manager.service.RedisService;

@EnableWebMvc
@EnableScheduling
//@EnableRedisRepositories
@EnableAutoConfiguration
@EntityScan(basePackages = "com.gtg.core.entity")
@EnableJpaRepositories(basePackages = "com.gtg.core.repository")
@ComponentScan(basePackages = { 
		"com.gtg.processor", 
		"com.gtg.processor.jobs", 
		"com.gtg.processor.manager.service",
		"com.gtg.processor.marketplace.service", 
		"com.gtg.processor.receiver" })
public class Application implements CommandLineRunner {

	@Autowired
	PushToRabbitMQService pushToRabbitMQService;

	@Autowired
	// StringRedisTemplate stringRedisTemplate;
	@Qualifier(value = "redisTemplate")
	RedisTemplate redisTemplate;

	@Autowired
	RedisService redisService;

	public void run(String... arg0) throws Exception {


	}

	public static void main(String... args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, args);
	}

}
