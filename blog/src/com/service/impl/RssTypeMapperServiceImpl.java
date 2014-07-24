package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.impl.BaseServiceImpl;
import com.dao.RssTypeMapperDao;
import com.po.RssType;
import com.service.RssTypeMapperService;

@Service("rssTypeMapperService")
public class RssTypeMapperServiceImpl extends BaseServiceImpl<RssType> implements RssTypeMapperService{

	@Autowired
	RssTypeMapperDao rssTypeMapperDao;

	@Override
	public boolean isChildren(int parentId,int userId) {
		RssType rssType = new RssType();
		if(parentId != -1){
			rssType.setParentId(parentId);
		}
		if(userId != -1){
			rssType.setUserId(userId);
		}
		int count = rssTypeMapperDao.count(rssType);
		if(count > 0){
			return true;
		}else{
			return false;
		}
	}

}
