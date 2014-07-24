package com.service;

import java.util.List;

import com.po.BlogSubject;

public interface BlogSubjectMapperService extends BaseService<BlogSubject>{
	
	public List<BlogSubject> selectSubjectByType(BlogSubject blogSubject);
	
}
