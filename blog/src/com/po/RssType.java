package com.po;

public class RssType extends BasePO{
	
	private Integer rssTypeId;
	
	private String typeName;
	
	private Integer userId;
	
	private Integer parentId;
	
	private String parentString;
	
	
	public Integer getRssTypeId() {
		return rssTypeId;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public Integer getParentId() {
		return parentId;
	}
	
	public String getParentString() {
		return parentString;
	}
	
	
	public void setRssTypeId(Integer rssTypeId) {
		this.rssTypeId = rssTypeId;
	}
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public void setParentString(String parentString) {
		this.parentString = parentString;
	}
	
	public String toString(){
		return "RssType";
	}
}
