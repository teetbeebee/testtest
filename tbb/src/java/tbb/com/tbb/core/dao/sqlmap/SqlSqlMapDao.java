package com.tbb.core.dao.sqlmap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbb.core.dao.SqlDao;
import com.tbb.core.domain.Sql;
import com.newbee.tmf.core.BaseSqlMapDao;
import com.newbee.tmf.core.PageList;
import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;

/**
 * 通用模块SqlMapDao
 * 
 *  
 * @version 1.0
 * @date 2007-2-10
 */
public class SqlSqlMapDao extends BaseSqlMapDao implements SqlDao {

	public SqlSqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	public void create(String sqlString) throws DaoException {
		try {
			getSqlMapExecutor().insert("Sql.create", sqlString);
		} catch (SQLException e) {
			throw new DaoException("Create domain error. cause:" + e, e);
		}
	}

	public int delete(String sqlString) throws DaoException {
		try {
			return getSqlMapExecutor().delete("Sql.delete", sqlString);
		} catch (SQLException e) {
			throw new DaoException("Delete domain error. cause:" + e, e);
		}
	}

	public List queryForList(String sqlString) throws DaoException {
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("Sql.query_list", sqlString);
		} catch (Exception e) {
			throw new DaoException("Query domain error" + e, e);
		}
		return list;
	}

	public PageList queryForPageList(String sqlString, int pageIndex, int pageSize)
			throws DaoException {
		PageList list = null;
		try {
			list = queryForPageList("Sql.query", sqlString, pageIndex, pageSize);
		} catch (Exception e) {
			throw new DaoException("Query domain error. cause:", e);
		}
		return list;
	}

	public Map retrieve(String sqlString) throws DaoException {
		Map map = new HashMap();
		try {
			map = (Map) getSqlMapExecutor().queryForObject("Sql.retrieve",
					sqlString);
		} catch (SQLException e) {
			throw new DaoException("Retrieve domain error. cause:" + e, e);
		}
		return map;
	}
	
	public Object retrieveClob(String sqlString) throws DaoException {
		Object obj = null;
		try {
			obj = getSqlMapExecutor().queryForObject("Sql.retrieve_clob",
					sqlString);
		} catch (SQLException e) {
			throw new DaoException("Retrieve clob error. cause:" + e, e);
		}
		return obj;
	}
	
	public Object retrieveBlob(String sqlString) throws DaoException {
		Object obj = null;
		try {
			obj = getSqlMapExecutor().queryForObject("Sql.retrieve_blob",
					sqlString);
		} catch (SQLException e) {
			throw new DaoException("Retrieve blob error. cause:" + e, e);
		}
		return obj;
	}

	public int update(String sqlString) throws DaoException {
		try {
			return getSqlMapExecutor().update("Sql.update", sqlString);
		} catch (SQLException e) {
			throw new DaoException("Update domain error. cause:" + e, e);
		}
	}

	public int queryAccount(String sqlString) throws DaoException {
		Integer accountValue = new Integer(0);
		try {
			accountValue = (Integer)getSqlMapExecutor().queryForObject("Sql.queryAccount",
					sqlString);
		} catch (SQLException e) {
			throw new DaoException("queryAccount error. cause:" + e, e);
		}
		return accountValue.intValue();
	}

	public int updateBlob(Sql sql) throws DaoException {
		try {
			return getSqlMapExecutor().update("Sql.update_blob", sql);
		} catch (SQLException e) {
			throw new DaoException("Update blob error. cause:" + e, e);
		}
	}

}
