package com.gtg.processor.manager.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UserDTO   implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String firstName;
	private String lastName;
	private String email;
	private String username;
	private String password;
	private String apiKey;
	private Date created;
	private Date updated;
	private String createdBy;
	private String updatedBy;
	private String roleName;
	private Long roleId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date apikeyExpireTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public Date getApikeyExpireTime() {
		return apikeyExpireTime;
	}

	public void setApikeyExpireTime(Date apikeyExpireTime) {
		this.apikeyExpireTime = apikeyExpireTime;
	}

	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", username=" + username + ", password=" + password + ", apiKey=" + apiKey + ", created=" + created
				+ ", updated=" + updated + ", createdBy=" + createdBy + ", updatedBy=" + updatedBy + ", roleName="
				+ roleName + ", roleId=" + roleId + ", apikeyExpireTime=" + apikeyExpireTime + "]";
	}
	
	
	
}
