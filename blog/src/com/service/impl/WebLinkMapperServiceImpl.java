package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.impl.BaseServiceImpl;
import com.dao.WebLinkMapperDao;
import com.po.WebLink;
import com.service.WebLinkMapperService;

@Service("webLinkMapperService")
public class WebLinkMapperServiceImpl extends BaseServiceImpl<WebLink> implements WebLinkMapperService{

	@Autowired
	WebLinkMapperDao webLinkMapperDao;

}
