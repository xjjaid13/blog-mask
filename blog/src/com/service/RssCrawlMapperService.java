package com.service;

import java.util.List;

import com.po.RssCrawl;

public interface RssCrawlMapperService extends BaseService<RssCrawl>{
	
	public List<RssCrawl> selectView(RssCrawl rssCrawl);
	
}
