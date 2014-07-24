package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dao.BlogSubjectMapperDao;
import com.po.BlogSubject;

@Repository
public class BlogSubjectMapperDaoImpl extends BaseDaoImpl<BlogSubject> implements BlogSubjectMapperDao {

	public List<BlogSubject> selectSubjectByType(BlogSubject blogSubject){
		return sqlSessionTemplate.selectList("com.dao."+blogSubject.toString()+"MapperDao.selectSubjectByType", blogSubject);
	}
	
}
