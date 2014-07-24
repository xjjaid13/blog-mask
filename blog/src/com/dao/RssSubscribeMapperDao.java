package com.dao;

import java.util.List;

import com.po.Rss;
import com.po.RssSubscribe;
import com.po.RssType;

public interface RssSubscribeMapperDao extends BaseDao<RssSubscribe>{
	
	public List<Rss> selectTypeSubscribe(RssSubscribe rssSubscribe);
	
	public List<Rss> selectListByIds(RssSubscribe rssSubscribe);
	
	public List<RssSubscribe> selectListJoin(RssType rssType);
	
}
