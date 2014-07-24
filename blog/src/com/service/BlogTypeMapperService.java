package com.service;

import com.po.BlogType;

public interface BlogTypeMapperService extends BaseService<BlogType>{
	
	public boolean isChildren(int parentId,int userId);
	
}
