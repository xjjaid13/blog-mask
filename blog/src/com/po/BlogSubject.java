package com.po;

public class BlogSubject extends BasePO{
	
	private Integer blogSubjectId;
	
	private String subjectTitle;
	
	private Integer blogTypeId;
	
	
	public Integer getBlogSubjectId() {
		return blogSubjectId;
	}
	
	public String getSubjectTitle() {
		return subjectTitle;
	}
	
	public Integer getBlogTypeId() {
		return blogTypeId;
	}
	
	
	public void setBlogSubjectId(Integer blogSubjectId) {
		this.blogSubjectId = blogSubjectId;
	}
	
	public void setSubjectTitle(String subjectTitle) {
		this.subjectTitle = subjectTitle;
	}
	
	public void setBlogTypeId(Integer blogTypeId) {
		this.blogTypeId = blogTypeId;
	}
	
	public String toString(){
		return "BlogSubject";
	}
}
