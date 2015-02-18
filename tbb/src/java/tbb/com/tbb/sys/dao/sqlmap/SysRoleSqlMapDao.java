package com.tbb.sys.dao.sqlmap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseSqlMapDao;
import com.newbee.tmf.core.PageList;
import com.tbb.sys.dao.SysRoleDao;
import com.tbb.sys.domain.SysRole;
import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;

/**
 * SysRole SqlMapDao
 */
public class SysRoleSqlMapDao extends BaseSqlMapDao implements SysRoleDao {

	public SysRoleSqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	public void create(SysRole domain) throws DaoException {
		try {
			getSqlMapExecutor().insert("SysRole.create", domain);
		} catch (SQLException e) {
			throw new DaoException("Create domain error. cause:" + e, e);
		}
	}

	public int delete(java.lang.String domainPK) throws DaoException {
		try {
			return getSqlMapExecutor().delete("SysRole.delete", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Delete domain error. cause:" + e, e);
		}
	}

	public List queryForList(Map params) throws DaoException {
		this.prepareQuery(params);
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("SysRole.query_list", params);
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
			list = queryForPageList("SysRole.query", params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new DaoException("Query domain error. cause:", e);
		}
		return list;
	}

	public SysRole retrieve(java.lang.String domainPK) throws DaoException {
		SysRole object = new SysRole();
		try {
			object = (SysRole) getSqlMapExecutor().queryForObject("SysRole.retrieve",
					domainPK);
		} catch (SQLException e) {
			throw new DaoException("Retrieve domain error. cause:" + e, e);
		}
		return object;
	}

	public int update(SysRole domain) throws DaoException {
		try {
			return getSqlMapExecutor().update("SysRole.update", domain);
		} catch (SQLException e) {
			throw new DaoException("Update domain error. cause:" + e, e);
		}
	}

	

	
}

