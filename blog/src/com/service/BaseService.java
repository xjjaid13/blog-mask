package com.service;

import java.util.List;

public interface BaseService<T> {

	int insert(T t);
	
	int update(T t);
	
	int delete(T t);
	
	T select(T t);
	
	int count(T t);
	
	List<T> selectList(T t);
	
	int maxId(T t);
	
	int deleteByIds(T t);
	
	List<T> selectList(String sql,Object param);
	
	int insertAndReturnId(T t);
	
}
