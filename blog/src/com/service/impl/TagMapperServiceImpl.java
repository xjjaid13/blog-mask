package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.impl.BaseServiceImpl;
import com.dao.TagMapperDao;
import com.po.Tag;
import com.service.TagMapperService;

@Service("tagMapperService")
public class TagMapperServiceImpl extends BaseServiceImpl<Tag> implements TagMapperService{

	@Autowired
	TagMapperDao tagMapperDao;

}
