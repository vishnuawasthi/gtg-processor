package com.gtg.processor.events;

import java.io.Serializable;

import com.gtg.processor.manager.dto.UserDTO;

public class GTGMQEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private UserDTO userDTO;

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
	
}
