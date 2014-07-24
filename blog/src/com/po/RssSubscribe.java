package com.po;

public class RssSubscribe extends BasePO{
	
	private Integer rssSubscribeId;
	
	private Integer rssId;
	
	private Integer rssTypeId;
	
	
	public Integer getRssSubscribeId() {
		return rssSubscribeId;
	}
	
	public Integer getRssId() {
		return rssId;
	}
	
	public Integer getRssTypeId() {
		return rssTypeId;
	}
	
	
	public void setRssSubscribeId(Integer rssSubscribeId) {
		this.rssSubscribeId = rssSubscribeId;
	}
	
	public void setRssId(Integer rssId) {
		this.rssId = rssId;
	}
	
	public void setRssTypeId(Integer rssTypeId) {
		this.rssTypeId = rssTypeId;
	}
	
	public String toString(){
		return "RssSubscribe";
	}
}
