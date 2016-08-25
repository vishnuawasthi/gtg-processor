package com.gtg.processor.config;

import static com.gtg.processor.constants.RabbitMQConstants.GTG_REDIS_TOPIC;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.session.data.redis.config.ConfigureRedisAction;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

import com.gtg.processor.receiver.RedisReceiver;

/**
 * 
 * @author Vishnu Awasthi
 *         <p>
 *         Spring Data Redis, part of the larger Spring Data family, provides
 *         easy configuration and access to Redis from Spring applications. It
 *         offers both low-level and high-level abstractions for interacting
 *         with the store, freeing the user from infrastructural concerns.
 *         </p>
 *         <p>
 *         Note : The Data-base simply serializes and stores the PoJo against
 *         the Key passed. That is why it is important to implement the
 *         Serializable interface. Not implementing Serializable interface leads
 *         to silly Serialization Exceptions at the time of persisting to the
 *         Database.
 *         </p>
 *
 */
@EnableRedisHttpSession
@Configuration
public class RedisConfiguration {

	@Bean
	RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			MessageListenerAdapter messageListenerAdapter) {
		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(messageListenerAdapter, new PatternTopic(GTG_REDIS_TOPIC));
		return container;
	}

	@Bean(name = "gtgRedisMessageListenerAdapter")
	MessageListenerAdapter gtgRedisMessageListenerAdapter(RedisReceiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveGTGRedisEvent");
	}

	@Bean(name = "stringRedisTemplate")
	public StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
		return new StringRedisTemplate(connectionFactory);
	}

	@Bean(name = "redisTemplate")
	public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(connectionFactory);
		return redisTemplate;
	}

	@Bean
	public static ConfigureRedisAction configureRedisAction() {
		return ConfigureRedisAction.NO_OP;
	}

	@Bean
	public RedisReceiver RedisReceiver() {
		return new RedisReceiver();
	}
}
