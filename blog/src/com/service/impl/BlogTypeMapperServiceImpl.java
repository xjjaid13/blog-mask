package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BlogTypeMapperDao;
import com.po.BlogType;
import com.service.BlogTypeMapperService;

@Service("blogTypeMapperService")
public class BlogTypeMapperServiceImpl extends BaseServiceImpl<BlogType> implements BlogTypeMapperService{

	@Autowired
	BlogTypeMapperDao blogTypeMapperDao;

	@Override
	public boolean isChildren(int parentId,int userId) {
		BlogType blogType = new BlogType();
		if(parentId != -1){
			blogType.setParentId(parentId);
		}
		if(userId != -1){
			blogType.setUserId(userId);
		}
		int count = blogTypeMapperDao.count(blogType);
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}

}
