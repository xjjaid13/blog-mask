package com.dao;

import java.util.List;

import com.po.RssCrawl;

public interface RssCrawlMapperDao extends BaseDao<RssCrawl>{
	
	public List<RssCrawl> selectView(RssCrawl rssCrawl);
	
}
