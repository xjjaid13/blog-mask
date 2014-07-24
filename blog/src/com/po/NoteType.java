package com.po;

public class NoteType extends BasePO{
	
	private Integer noteTypeId;
	
	private String noteTypeName;
	
	
	public Integer getNoteTypeId() {
		return noteTypeId;
	}
	
	public String getNoteTypeName() {
		return noteTypeName;
	}
	
	
	public void setNoteTypeId(Integer noteTypeId) {
		this.noteTypeId = noteTypeId;
	}
	
	public void setNoteTypeName(String noteTypeName) {
		this.noteTypeName = noteTypeName;
	}
	
	public String toString(){
		return "NoteType";
	}
}
