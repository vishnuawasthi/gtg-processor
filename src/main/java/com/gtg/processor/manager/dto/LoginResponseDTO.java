package com.gtg.processor.manager.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class LoginResponseDTO  implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private String username;
	private String password;
	private String apiKey;
	private String status;
	private String message;
	private Integer statusCode; 
	private String userMessage;
	private String developerMessage;
	
	//@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	  @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date apikeyExpireTime;
	
	private Date created;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(String userMessage) {
		this.userMessage = userMessage;
	}

	public String getDeveloperMessage() {
		return developerMessage;
	}

	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}

	public Date getApikeyExpireTime() {
		return apikeyExpireTime;
	}

	public void setApikeyExpireTime(Date apikeyExpireTime) {
		this.apikeyExpireTime = apikeyExpireTime;
	}
	
	
	
}
