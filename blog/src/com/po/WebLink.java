package com.po;

public class WebLink extends BasePO{
	
	private Integer webLinkId;
	
	private String name;
	
	private String link;
	
	private Integer webLinktypeId;
	
	private Integer userId;
	
	private Integer hit;
	
	private String host;
	
	private String description;
	
	private String icon;
	
	
	public Integer getWebLinkId() {
		return webLinkId;
	}
	
	public String getName() {
		return name;
	}
	
	public String getLink() {
		return link;
	}
	
	public Integer getWebLinktypeId() {
		return webLinktypeId;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public Integer getHit() {
		return hit;
	}
	
	public String getHost() {
		return host;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getIcon() {
		return icon;
	}
	
	
	public void setWebLinkId(Integer webLinkId) {
		this.webLinkId = webLinkId;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setLink(String link) {
		this.link = link;
	}
	
	public void setWebLinktypeId(Integer webLinktypeId) {
		this.webLinktypeId = webLinktypeId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	public void setHit(Integer hit) {
		this.hit = hit;
	}
	
	public void setHost(String host) {
		this.host = host;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	public String toString(){
		return "WebLink";
	}
}
