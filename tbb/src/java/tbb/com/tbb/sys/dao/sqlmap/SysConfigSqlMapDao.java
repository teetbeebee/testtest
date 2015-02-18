package com.tbb.sys.dao.sqlmap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseSqlMapDao;
import com.newbee.tmf.core.PageList;
import com.tbb.sys.dao.SysConfigDao;
import com.tbb.sys.domain.SysConfig;
import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;

/**
 * SysConfig SqlMapDao
 */
public class SysConfigSqlMapDao extends BaseSqlMapDao implements SysConfigDao {

	public SysConfigSqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	public void create(SysConfig domain) throws DaoException {
		try {
			getSqlMapExecutor().insert("SysConfig.create", domain);
		} catch (SQLException e) {
			throw new DaoException("Create domain error. cause:" + e, e);
		}
	}

	public int delete(java.util.HashMap domainPK) throws DaoException {
		try {
			return getSqlMapExecutor().delete("SysConfig.delete", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Delete domain error. cause:" + e, e);
		}
	}

	public List queryForList(Map params) throws DaoException {
		this.prepareQuery(params);
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("SysConfig.query_list", params);
		} catch (Exception e) {
			throw new DaoException("Query domain error" + e, e);
		}
		return list;
	}

	public PageList queryForPageList(Map params, int pageIndex, int pageSize)
			throws DaoException {
		this.prepareQuery(params);
		PageList list = null;
		try {
			list = queryForPageList("SysConfig.query", params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new DaoException("Query domain error. cause:", e);
		}
		return list;
	}

	public SysConfig retrieve(java.util.HashMap domainPK) throws DaoException {
		SysConfig object = new SysConfig();
		try {
			object = (SysConfig) getSqlMapExecutor().queryForObject("SysConfig.retrieve",
					domainPK);
		} catch (SQLException e) {
			throw new DaoException("Retrieve domain error. cause:" + e, e);
		}
		return object;
	}

	public int update(SysConfig domain) throws DaoException {
		try {
			return getSqlMapExecutor().update("SysConfig.update", domain);
		} catch (SQLException e) {
			throw new DaoException("Update domain error. cause:" + e, e);
		}
	}

	

	
}

