package com.po;

import com.alibaba.fastjson.annotation.JSONField;

public class BasePO {

	/** 开始页数 */
	private int startPage = -1;
	
	/** 每页显示数量 */
	private int page = 10;
	
	/** 批量处理id */
	private String ids;
	
	/** 条件 */
	private String condition;

	@JSONField(serialize=false)
	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	@JSONField(serialize=false)
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	@JSONField(serialize=false)
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	@JSONField(serialize=false)
    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }
	
}
