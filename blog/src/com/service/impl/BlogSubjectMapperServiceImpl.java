package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BlogSubjectMapperDao;
import com.po.BlogSubject;
import com.service.BlogSubjectMapperService;

@Service("blogSubjectMapperService")
public class BlogSubjectMapperServiceImpl extends BaseServiceImpl<BlogSubject> implements BlogSubjectMapperService{

	@Autowired
	BlogSubjectMapperDao blogSubjectMapperDao;
	
	public List<BlogSubject> selectSubjectByType(BlogSubject blogSubject){
		return blogSubjectMapperDao.selectSubjectByType(blogSubject);
	}

}
