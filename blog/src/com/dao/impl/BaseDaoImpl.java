package com.dao.impl;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dao.BaseDao;

/**
 * 基础类，封装了数据库基本操作
 * 
 * @author taylor
 * */
@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;

	public int insert(T t) {
		return sqlSessionTemplate.insert("com.dao."+t.toString()+"MapperDao.insert", t);
	}

	public int update(T t) {
		return sqlSessionTemplate.update("com.dao."+t.toString()+"MapperDao.update", t);
	}

	public int delete(T t) {
		return sqlSessionTemplate.delete("com.dao."+t.toString()+"MapperDao.delete", t);
	}

	public T select(T t) {
		return sqlSessionTemplate.selectOne("com.dao."+t.toString()+"MapperDao.select", t);
	}

	public int count(T t) {
		return sqlSessionTemplate.selectOne("com.dao."+t.toString()+"MapperDao.selectCount", t);
	}

	public List<T> selectList(T t) {
		return sqlSessionTemplate.selectList("com.dao."+t.toString()+"MapperDao.selectList", t);
	}
	
	public int maxId(T t) {
		return sqlSessionTemplate.selectOne("com.dao."+t.toString()+"MapperDao.maxId");
		
	}
	
	public int deleteByIds(T t){
		return sqlSessionTemplate.delete("com.dao."+t.toString()+"MapperDao.deleteByIds", t);
	}

    @Override
    public List<T> selectList(String sql, Object param) {
        return sqlSessionTemplate.selectList(sql, param);
    }

	@Override
	public int insertAndReturnId(T t) {
		insert(t);
		return maxId(t);
	}
	
}
