package com.gtg.processor.manager.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.gtg.processor.events.GTGRedisEvent;
import com.gtg.processor.manager.dto.UserDTO;

public interface RedisService {

	public void saveToRedish(String key, GTGRedisEvent event);

	public GTGRedisEvent getAll(String key, int start, int end);

	public GTGRedisEvent getOne(String key);

	@Service
	public class Impl implements RedisService {

		@Autowired
		@Qualifier(value="redisTemplate")
		private RedisTemplate<String, Object> redisTemplate;

		@Resource(name = "redisTemplate")
		private ListOperations<String, Object> listOps;

		@Override
		public void saveToRedish(String key, GTGRedisEvent event) {
			System.out.println("saveToRedish() - start");
			UserDTO userDTO = event.getUserDTO();
			listOps.leftPush(key, userDTO);
			System.out.println("saveToRedish() - end");

		}

		@Override
		public GTGRedisEvent getAll(String key, int start, int end) {
			List<UserDTO> users = new ArrayList<UserDTO>();
			List<Object> lists = listOps.range(key, start, end);
			System.out.println("lists   : " + lists);
			if (lists != null) {
				for (Object obj : lists) {
					users.add((UserDTO) obj);
				}
				GTGRedisEvent event = new GTGRedisEvent();
				event.setUsers(users);
				return event;
			}

			return null;
		}

		@Override
		public GTGRedisEvent getOne(String key) {
			// TODO Auto-generated method stub
			return null;
		}

	}

}
