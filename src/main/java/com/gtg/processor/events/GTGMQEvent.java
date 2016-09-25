package com.gtg.processor.events;

import java.io.Serializable;

import com.gtg.processor.manager.dto.UserDTO;

public class GTGMQEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private String asyncCallType;

	public String getAsyncCallType() {
		return asyncCallType;
	}

	public void setAsyncCallType(String asyncCallType) {
		this.asyncCallType = asyncCallType;
	}

	private UserDTO userDTO;

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	@Override
	public String toString() {
		return "GTGMQEvent [asyncCallType=" + asyncCallType + ", userDTO=" + userDTO + "]";
	}

	
}
