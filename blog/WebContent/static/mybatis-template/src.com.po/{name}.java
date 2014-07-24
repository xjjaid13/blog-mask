package com.po;

public class {TableName} extends BasePO{
	{for}
	private {type} {columnname};
	{endfor}
	{for}
	public {type} get{Columnname}() {
		return {columnname};
	}
	{endfor}
	{for}
	public void set{Columnname}({type} {columnname}) {
		this.{columnname} = {columnname};
	}
	{endfor}
	public String toString(){
		return "{TableName}";
	}
}
