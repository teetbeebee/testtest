package com.tbb.sys.dao.sqlmap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseSqlMapDao;
import com.newbee.tmf.core.PageList;
import com.tbb.sys.dao.SysUserDao;
import com.tbb.sys.domain.SysUser;
import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;

/**
 * SysUser SqlMapDao
 */
public class SysUserSqlMapDao extends BaseSqlMapDao implements SysUserDao {

	public SysUserSqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	public void create(SysUser domain) throws DaoException {
		try {
			getSqlMapExecutor().insert("SysUser.create", domain);
		} catch (SQLException e) {
			throw new DaoException("Create domain error. cause:" + e, e);
		}
	}

	public int delete(java.lang.String domainPK) throws DaoException {
		try {
			return getSqlMapExecutor().delete("SysUser.delete", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Delete domain error. cause:" + e, e);
		}
	}

	public List queryForList(Map params) throws DaoException {
		this.prepareQuery(params);
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("SysUser.query_list", params);
		} catch (Exception e) {
			throw new DaoException("Query domain error" + e, e);
		}
		return list;
	}
	
	public List querySysUserByEmail(Map params) throws DaoException {
		this.prepareQuery(params);
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("SysUser.checkEmail_list", params);
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
			list = queryForPageList("SysUser.query", params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new DaoException("Query domain error. cause:", e);
		}
		return list;
	}

	public SysUser retrieve(java.lang.String domainPK) throws DaoException {
		SysUser object = new SysUser();
		try {
			object = (SysUser) getSqlMapExecutor().queryForObject("SysUser.retrieve",
					domainPK);
		} catch (SQLException e) {
			throw new DaoException("Retrieve domain error. cause:" + e, e);
		}
		return object;
	}

	public int update(SysUser domain) throws DaoException {
		try {
			return getSqlMapExecutor().update("SysUser.update", domain);
		} catch (SQLException e) {
			throw new DaoException("Update domain error. cause:" + e, e);
		}
	}

	@Override
	public SysUser retrieveBySid(String session_id) throws DaoException {
		SysUser object = new SysUser();
		try {
			object = (SysUser) getSqlMapExecutor().queryForObject("SysUser.retrieveBySid",
					session_id);
		} catch (SQLException e) {
			throw new DaoException("Retrieve domain error. cause:" + e, e);
		}
		return object;
	}

	

	
}

