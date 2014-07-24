package com.dao;

import com.po.Rss;

public interface RssMapperDao extends BaseDao<Rss>{
	
	public Rss selectRssTopCrawl(Rss rss);
	
}
