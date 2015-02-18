package com.tbb.basedata.dao;


import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.PageList;
import com.ibatis.dao.client.DaoException;
import com.tbb.basedata.domain.T1;

/**
 * Organ Dao
 */
public interface T1Dao {
	
	public PageList queryForPageList(Map params, int pageIndex, int pageSize)
			throws DaoException;
	
	public T1 retrieve(java.lang.String domainPK) throws DaoException;
	
	public void create(T1 domain) throws DaoException;
	
	public int update(T1 domain) throws DaoException;
	
	public int delete(java.lang.String domainPK) throws DaoException;
	
	public List queryForList(Map params) throws DaoException;
	
}

