package com.po;

public class Tag extends BasePO{
	
	private Integer tagId;
	
	private String tagName;
	
	private Integer userId;
	
	
	public Integer getTagId() {
		return tagId;
	}
	
	public String getTagName() {
		return tagName;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	
	public void setTagId(Integer tagId) {
		this.tagId = tagId;
	}
	
	public void setTagName(String tagName) {
		this.tagName = tagName;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String toString(){
		return "Tag";
	}
}
