package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dao.RssCrawlMapperDao;
import com.po.RssCrawl;

@Repository
public class RssCrawlMapperDaoImpl extends BaseDaoImpl<RssCrawl> implements RssCrawlMapperDao {

	@Override
	public List<RssCrawl> selectView(RssCrawl rssCrawl) {
		return sqlSessionTemplate.selectList("com.dao."+rssCrawl.toString()+"MapperDao.selectView", rssCrawl);
	}

	
}
