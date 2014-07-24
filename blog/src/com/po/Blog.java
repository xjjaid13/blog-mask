package com.po;

public class Blog extends BasePO{
	
	private Integer blogId;
	
	private String title;
	
	private String content;
	
	private String createDate;
	
	private Integer hit;
	
	private Integer userId;
	
	private String shortContent;
	
	private Integer commentnum;
	
	private Integer subjectId;
	
	private String keywords;
	
	
	public Integer getBlogId() {
		return blogId;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getContent() {
		return content;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	
	public Integer getHit() {
		return hit;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public String getShortContent() {
		return shortContent;
	}
	
	public Integer getCommentnum() {
		return commentnum;
	}
	
	public Integer getSubjectId() {
		return subjectId;
	}
	
	public String getKeywords() {
		return keywords;
	}
	
	
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public void setHit(Integer hit) {
		this.hit = hit;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public void setShortContent(String shortContent) {
		this.shortContent = shortContent;
	}
	
	public void setCommentnum(Integer commentnum) {
		this.commentnum = commentnum;
	}
	
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	public String toString(){
		return "Blog";
	}
}
