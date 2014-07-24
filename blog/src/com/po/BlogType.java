package com.po;

public class BlogType extends BasePO{
	
	private Integer blogTypeId;
	
	private Integer userId;
	
	private String typeName;
	
	private Integer parentId;
	
	private String parentString;
	
	
	public Integer getBlogTypeId() {
		return blogTypeId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public Integer getParentId() {
		return parentId;
	}
	
	public String getParentString() {
		return parentString;
	}
	
	
	public void setBlogTypeId(Integer blogTypeId) {
		this.blogTypeId = blogTypeId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public void setParentString(String parentString) {
		this.parentString = parentString;
	}
	
	public String toString(){
		return "BlogType";
	}
}
