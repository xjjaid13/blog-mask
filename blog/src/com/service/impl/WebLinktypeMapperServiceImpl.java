package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.impl.BaseServiceImpl;
import com.dao.WebLinktypeMapperDao;
import com.po.WebLinktype;
import com.service.WebLinktypeMapperService;

@Service("webLinktypeMapperService")
public class WebLinktypeMapperServiceImpl extends BaseServiceImpl<WebLinktype> implements WebLinktypeMapperService{

	@Autowired
	WebLinktypeMapperDao webLinktypeMapperDao;

}
