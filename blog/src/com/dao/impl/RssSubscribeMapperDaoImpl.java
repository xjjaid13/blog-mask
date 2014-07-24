package com.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.dao.RssSubscribeMapperDao;
import com.po.Rss;
import com.po.RssSubscribe;
import com.po.RssType;

@Repository
public class RssSubscribeMapperDaoImpl extends BaseDaoImpl<RssSubscribe> implements RssSubscribeMapperDao {

	@Override
	public List<Rss> selectTypeSubscribe(RssSubscribe rssSubscribe) {
		return sqlSessionTemplate.selectList("com.dao."+rssSubscribe.toString()+"MapperDao.selectTypeSubscribe", rssSubscribe);
	}

	@Override
	public List<Rss> selectListByIds(RssSubscribe rssSubscribe) {
		return sqlSessionTemplate.selectList("com.dao."+rssSubscribe.toString()+"MapperDao.selectListByIds", rssSubscribe);
	}

	@Override
	public List<RssSubscribe> selectListJoin(RssType rssType) {
		return sqlSessionTemplate.selectList("com.dao.RssSubscribeMapperDao.selectListJoin", rssType);
	}

}
