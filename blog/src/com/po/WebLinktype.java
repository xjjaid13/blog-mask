package com.po;

public class WebLinktype extends BasePO{
	
	private Integer webLinktypeId;
	
	private String name;
	
	private Integer userId;
	
	
	public Integer getWebLinktypeId() {
		return webLinktypeId;
	}
	
	public String getName() {
		return name;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	
	public void setWebLinktypeId(Integer webLinktypeId) {
		this.webLinktypeId = webLinktypeId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String toString(){
		return "WebLinktype";
	}
}
