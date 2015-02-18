package com.tbb.sys.dao.sqlmap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseSqlMapDao;
import com.newbee.tmf.core.PageList;
import com.tbb.sys.dao.SysDictDao;
import com.tbb.sys.domain.SysDict;
import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;

/**
 * SysDict SqlMapDao
 */
public class SysDictSqlMapDao extends BaseSqlMapDao implements SysDictDao {

	public SysDictSqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	public void create(SysDict domain) throws DaoException {
		try {
			getSqlMapExecutor().insert("SysDict.create", domain);
		} catch (SQLException e) {
			throw new DaoException("Create domain error. cause:" + e, e);
		}
	}

	public int delete(java.util.HashMap domainPK) throws DaoException {
		try {
			return getSqlMapExecutor().delete("SysDict.delete", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Delete domain error. cause:" + e, e);
		}
	}

	public List queryForList(Map params) throws DaoException {
		this.prepareQuery(params);
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("SysDict.query_list", params);
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
			list = queryForPageList("SysDict.query", params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new DaoException("Query domain error. cause:", e);
		}
		return list;
	}

	public SysDict retrieve(java.util.HashMap domainPK) throws DaoException {
		SysDict object = new SysDict();
		try {
			object = (SysDict) getSqlMapExecutor().queryForObject("SysDict.retrieve",
					domainPK);
		} catch (SQLException e) {
			throw new DaoException("Retrieve domain error. cause:" + e, e);
		}
		return object;
	}

	public int update(SysDict domain) throws DaoException {
		try {
			return getSqlMapExecutor().update("SysDict.update", domain);
		} catch (SQLException e) {
			throw new DaoException("Update domain error. cause:" + e, e);
		}
	}

	

	
}

