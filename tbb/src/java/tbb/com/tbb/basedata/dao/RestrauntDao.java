package com.tbb.basedata.dao;


import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.PageList;
import com.ibatis.dao.client.DaoException;
import com.tbb.basedata.domain.Restraunt;

/**
 * Organ Dao
 */
public interface RestrauntDao {
	
	public PageList queryForPageList(Map params, int pageIndex, int pageSize)
			throws DaoException;
	
	public Restraunt retrieve(java.lang.String domainPK) throws DaoException;
	
	public void create(Restraunt domain) throws DaoException;
	
	public int update(Restraunt domain) throws DaoException;
	
	public int delete(java.lang.String domainPK) throws DaoException;
	
	public List queryForList(Map params) throws DaoException;
	
}


