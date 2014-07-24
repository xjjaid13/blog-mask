package com.dao.impl;

import org.springframework.stereotype.Repository;
import com.dao.RssMapperDao;
import com.po.Rss;

@Repository
public class RssMapperDaoImpl extends BaseDaoImpl<Rss> implements RssMapperDao {
	
	public Rss selectRssTopCrawl(Rss rss){
		return sqlSessionTemplate.selectOne("com.dao."+rss.toString()+"MapperDao.selectRssTopCrawl", rss);
	}
	
}
