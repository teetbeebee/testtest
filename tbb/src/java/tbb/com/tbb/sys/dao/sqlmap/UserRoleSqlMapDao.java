package com.tbb.sys.dao.sqlmap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseSqlMapDao;
import com.newbee.tmf.core.PageList;
import com.tbb.sys.dao.UserRoleDao;
import com.tbb.sys.domain.UserRole;
import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;

/**
 * UserRole SqlMapDao
 */
public class UserRoleSqlMapDao extends BaseSqlMapDao implements UserRoleDao {

	public UserRoleSqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	public void create(UserRole domain) throws DaoException {
		try {
			getSqlMapExecutor().insert("UserRole.create", domain);
		} catch (SQLException e) {
			throw new DaoException("Create domain error. cause:" + e, e);
		}
	}

	public int delete(java.util.HashMap domainPK) throws DaoException {
		try {
			return getSqlMapExecutor().delete("UserRole.delete", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Delete domain error. cause:" + e, e);
		}
	}

	public List queryForList(Map params) throws DaoException {
		this.prepareQuery(params);
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("UserRole.query_list", params);
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
			list = queryForPageList("UserRole.query", params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new DaoException("Query domain error. cause:", e);
		}
		return list;
	}

	public UserRole retrieve(java.util.HashMap domainPK) throws DaoException {
		UserRole object = new UserRole();
		try {
			object = (UserRole) getSqlMapExecutor().queryForObject("UserRole.retrieve",
					domainPK);
		} catch (SQLException e) {
			throw new DaoException("Retrieve domain error. cause:" + e, e);
		}
		return object;
	}

	public int update(UserRole domain) throws DaoException {
		try {
			return getSqlMapExecutor().update("UserRole.update", domain);
		} catch (SQLException e) {
			throw new DaoException("Update domain error. cause:" + e, e);
		}
	}

	

	
}

