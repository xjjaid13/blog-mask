package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.impl.BaseServiceImpl;
import com.dao.BlogMapperDao;
import com.po.Blog;
import com.service.BlogMapperService;

@Service("blogMapperService")
public class BlogMapperServiceImpl extends BaseServiceImpl<Blog> implements BlogMapperService{

	@Autowired
	BlogMapperDao blogMapperDao;

}
