package com.tbb.sys.dao.sqlmap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseSqlMapDao;
import com.newbee.tmf.core.PageList;
import com.tbb.sys.dao.SysRolePermitDao;
import com.tbb.sys.domain.SysRolePermit;
import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;

/**
 * SysRolePermit SqlMapDao
 */
public class SysRolePermitSqlMapDao extends BaseSqlMapDao implements SysRolePermitDao {

	public SysRolePermitSqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	public void create(SysRolePermit domain) throws DaoException {
		try {
			getSqlMapExecutor().insert("SysRolePermit.create", domain);
		} catch (SQLException e) {
			throw new DaoException("Create domain error. cause:" + e, e);
		}
	}

	public int delete(java.util.HashMap domainPK) throws DaoException {
		try {
			return getSqlMapExecutor().delete("SysRolePermit.delete", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Delete domain error. cause:" + e, e);
		}
	}

	public List queryForList(Map params) throws DaoException {
		this.prepareQuery(params);
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("SysRolePermit.query_list", params);
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
			list = queryForPageList("SysRolePermit.query", params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new DaoException("Query domain error. cause:", e);
		}
		return list;
	}

	public SysRolePermit retrieve(java.util.HashMap domainPK) throws DaoException {
		SysRolePermit object = new SysRolePermit();
		try {
			object = (SysRolePermit) getSqlMapExecutor().queryForObject("SysRolePermit.retrieve",
					domainPK);
		} catch (SQLException e) {
			throw new DaoException("Retrieve domain error. cause:" + e, e);
		}
		return object;
	}

	public int update(SysRolePermit domain) throws DaoException {
		try {
			return getSqlMapExecutor().update("SysRolePermit.update", domain);
		} catch (SQLException e) {
			throw new DaoException("Update domain error. cause:" + e, e);
		}
	}

	

	
}

