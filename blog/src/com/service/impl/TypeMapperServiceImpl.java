package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.impl.BaseServiceImpl;
import com.dao.TypeMapperDao;
import com.po.Type;
import com.service.TypeMapperService;

@Service("typeMapperService")
public class TypeMapperServiceImpl extends BaseServiceImpl<Type> implements TypeMapperService{

	@Autowired
	TypeMapperDao typeMapperDao;

}
