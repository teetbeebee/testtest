package com.tbb.member.dao;


import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.PageList;
import com.ibatis.dao.client.DaoException;
import com.tbb.member.domain.Web_member;

/**
 * Web_member Dao
 */
public interface Web_memberDao {
	
	public PageList queryForPageList(Map params, int pageIndex, int pageSize)
			throws DaoException;
	
	public Web_member retrieve(java.lang.String domainPK) throws DaoException;
	
	public Web_member retrieveByUid(java.lang.String uid) throws DaoException;
	
	public void create(Web_member domain) throws DaoException;
	
	public int update(Web_member domain) throws DaoException;
	
	public int delete(java.lang.String domainPK) throws DaoException;
	
	public List queryForList(Map params) throws DaoException;
	
}

