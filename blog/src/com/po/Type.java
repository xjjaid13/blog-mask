package com.po;

public class Type extends BasePO{
	
	private Integer typeId;
	
	private String typeName;
	
	private Integer userId;
	
	
	public Integer getTypeId() {
		return typeId;
	}
	
	public String getTypeName() {
		return typeName;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	
	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}
	
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public String toString(){
		return "Type";
	}
}
