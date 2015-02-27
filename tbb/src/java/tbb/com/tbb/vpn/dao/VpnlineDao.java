package com.tbb.vpn.dao;

import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.PageList;
import com.ibatis.dao.client.DaoException;
import com.tbb.vpn.domain.Vpnline;

/**
 * Vpnline Dao
 */
public interface VpnlineDao {
	
	public PageList queryForPageList(Map params, int pageIndex, int pageSize)
			throws DaoException;
	
	public Vpnline retrieve(java.lang.String domainPK) throws DaoException;
	
	public void create(Vpnline domain) throws DaoException;
	
	public int update(Vpnline domain) throws DaoException;
	
	public int delete(java.lang.String domainPK) throws DaoException;
	
	public List queryForList(Map params) throws DaoException;
	
}

