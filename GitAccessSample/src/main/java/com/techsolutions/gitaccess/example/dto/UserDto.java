package com.techsolutions.gitaccess.example.dto;

import javax.validation.constraints.NotNull;

public class UserDto {
	
	@NotNull(message = "UserName cannot be empty")
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

}
