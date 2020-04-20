package com.fintech.becarsfintech.dto;

import com.fintech.becarsfintech.models.RoleNameEnum;

/**
 * 
 * @author NamanSisodia
 * user register dto
 */
public class UserRegisterDto {
	
	private String name;
	private String userName;
	private String password;
	private RoleNameEnum userType;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public RoleNameEnum getUserType() {
		return userType;
	}
	
	public void setUserType(RoleNameEnum userType) {
		this.userType = userType;
	}
	
}
