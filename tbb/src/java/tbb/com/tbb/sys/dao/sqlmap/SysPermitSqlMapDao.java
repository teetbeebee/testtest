package com.tbb.sys.dao.sqlmap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseSqlMapDao;
import com.newbee.tmf.core.PageList;
import com.tbb.sys.dao.SysPermitDao;
import com.tbb.sys.domain.SysPermit;
import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;

/**
 * SysPermit SqlMapDao
 */
public class SysPermitSqlMapDao extends BaseSqlMapDao implements SysPermitDao {

	public SysPermitSqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	public void create(SysPermit domain) throws DaoException {
		try {
			getSqlMapExecutor().insert("SysPermit.create", domain);
		} catch (SQLException e) {
			throw new DaoException("Create domain error. cause:" + e, e);
		}
	}

	public int delete(java.lang.String domainPK) throws DaoException {
		try {
			return getSqlMapExecutor().delete("SysPermit.delete", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Delete domain error. cause:" + e, e);
		}
	}

	public List queryForList(Map params) throws DaoException {
		this.prepareQuery(params);
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("SysPermit.query_list", params);
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
			list = queryForPageList("SysPermit.query", params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new DaoException("Query domain error. cause:", e);
		}
		return list;
	}

	public SysPermit retrieve(java.lang.String domainPK) throws DaoException {
		SysPermit object = new SysPermit();
		try {
			object = (SysPermit) getSqlMapExecutor().queryForObject("SysPermit.retrieve",
					domainPK);
		} catch (SQLException e) {
			throw new DaoException("Retrieve domain error. cause:" + e, e);
		}
		return object;
	}

	public int update(SysPermit domain) throws DaoException {
		try {
			return getSqlMapExecutor().update("SysPermit.update", domain);
		} catch (SQLException e) {
			throw new DaoException("Update domain error. cause:" + e, e);
		}
	}

	

	
}

