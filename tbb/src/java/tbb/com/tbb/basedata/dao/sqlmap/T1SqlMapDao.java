package com.tbb.basedata.dao.sqlmap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseSqlMapDao;
import com.newbee.tmf.core.PageList;
import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;
import com.tbb.basedata.dao.T1Dao;
import com.tbb.basedata.domain.T1;

/**
 * T1 SqlMapDao
 */
public class T1SqlMapDao extends BaseSqlMapDao implements T1Dao {

	public T1SqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	public void create(T1 domain) throws DaoException {
		try {
			getSqlMapExecutor().insert("T1.create", domain);
		} catch (SQLException e) {
			throw new DaoException("Create domain error. cause:" + e, e);
		}
	}

	public int delete(java.lang.String domainPK) throws DaoException {
		try {
			return getSqlMapExecutor().delete("T1.delete", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Delete domain error. cause:" + e, e);
		}
	}

	public List queryForList(Map params) throws DaoException {
		this.prepareQuery(params);
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("T1.query_list", params);
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
			list = queryForPageList("T1.query", params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new DaoException("Query domain error. cause:", e);
		}
		return list;
	}

	public T1 retrieve(java.lang.String domainPK) throws DaoException {
		T1 object = new T1();
		try {
			object = (T1) getSqlMapExecutor().queryForObject(
					"T1.retrieve", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Retrieve domain error. cause:" + e, e);
		}
		return object;
	}

	public int update(T1 domain) throws DaoException {
		try {
			return getSqlMapExecutor().update("T1.update", domain);
		} catch (SQLException e) {
			throw new DaoException("Update domain error. cause:" + e, e);
		}
	}


}
