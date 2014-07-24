package com.service;

import com.po.RssType;

public interface RssTypeMapperService extends BaseService<RssType>{
	
	public boolean isChildren(int parentId,int userId);
	
}
