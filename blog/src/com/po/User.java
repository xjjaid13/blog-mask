package com.po;

public class User extends BasePO{
	
	private Integer userId;
	
	private String username;
	
	private String password;
	
	
	public Integer getUserId() {
		return userId;
	}
	
	public String getUsername() {
		return username;
	}
	
	public String getPassword() {
		return password;
	}
	
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String toString(){
		return "User";
	}
}
