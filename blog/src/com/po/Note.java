package com.po;

public class Note extends BasePO{
	
	private Integer noteId;
	
	private String content;
	
	private Integer noteTypeId;
	
	private String createDate;
	
	private Integer userId;
	
	
	public Integer getNoteId() {
		return noteId;
	}
	
	public String getContent() {
		return content;
	}
	
	public Integer getNoteTypeId() {
		return noteTypeId;
	}
	
	public String getCreateDate() {
		return createDate;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	
	public void setNoteId(Integer noteId) {
		this.noteId = noteId;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public void setNoteTypeId(Integer noteTypeId) {
		this.noteTypeId = noteTypeId;
	}
	
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String toString(){
		return "Note";
	}
}
