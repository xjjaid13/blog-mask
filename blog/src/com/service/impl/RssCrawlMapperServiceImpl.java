package com.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.RssCrawlMapperDao;
import com.po.RssCrawl;
import com.service.RssCrawlMapperService;

@Service("rssCrawlMapperService")
public class RssCrawlMapperServiceImpl extends BaseServiceImpl<RssCrawl> implements RssCrawlMapperService{

	@Autowired
	RssCrawlMapperDao rssCrawlMapperDao;

	@Override
	public List<RssCrawl> selectView(RssCrawl rssCrawl) {
		return rssCrawlMapperDao.selectView(rssCrawl);
	}
	
}
