package com.service;

import java.util.List;

import com.po.Rss;
import com.po.RssSubscribe;
import com.po.RssType;

public interface RssSubscribeMapperService extends BaseService<RssSubscribe>{
	
	public List<Rss> selectTypeSubscribe(RssSubscribe rssSubscribe);
	
	/**
	 * 获得用户或类别下的rss列表
	 * @param rssType 使用rssTypeId和userId过滤
	 * @return
	 */
	public List<Rss> selectRssCrawlList(RssType rssType);
	
}
