package com.dao;

import java.util.List;

import com.po.BlogSubject;

public interface BlogSubjectMapperDao extends BaseDao<BlogSubject>{
	
	public List<BlogSubject> selectSubjectByType(BlogSubject blogSubject);
	
}
