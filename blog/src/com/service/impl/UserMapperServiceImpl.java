package com.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.service.impl.BaseServiceImpl;
import com.dao.UserMapperDao;
import com.po.User;
import com.service.UserMapperService;
import com.util.Md5Util;

@Service("userMapperService")
public class UserMapperServiceImpl extends BaseServiceImpl<User> implements UserMapperService{

	@Autowired
	UserMapperDao userMapperDao;

	@Override
	public User validUser(User user) {
		String password = user.getPassword();
		password = Md5Util.getMD5(password.getBytes());
		user.setPassword(password);
		user = userMapperDao.select(user);
		return user;
	}

}
