package com.po;

public class NoteTime extends BasePO{
	
	private Integer noteTimeId;
	
	private Integer type;
	
	private String value;
	
	private Integer parentId;
	
	private Integer userId;
	
	private String displayValue;
	
	
	public Integer getNoteTimeId() {
		return noteTimeId;
	}
	
	public Integer getType() {
		return type;
	}
	
	public String getValue() {
		return value;
	}
	
	public Integer getParentId() {
		return parentId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public String getDisplayValue() {
		return displayValue;
	}
	
	
	public void setNoteTimeId(Integer noteTimeId) {
		this.noteTimeId = noteTimeId;
	}
	
	public void setType(Integer type) {
		this.type = type;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public void setDisplayValue(String displayValue) {
		this.displayValue = displayValue;
	}
	
	public String toString(){
		return "NoteTime";
	}
}
