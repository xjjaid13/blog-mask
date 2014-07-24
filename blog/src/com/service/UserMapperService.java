package com.service;

import com.po.User;

public interface UserMapperService extends BaseService<User>{
	
	public User validUser(User user);
	
}
