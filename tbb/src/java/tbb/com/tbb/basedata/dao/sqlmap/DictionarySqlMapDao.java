package com.tbb.basedata.dao.sqlmap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseSqlMapDao;
import com.newbee.tmf.core.PageList;
import com.tbb.basedata.dao.DictionaryDao;
import com.tbb.basedata.domain.Dictionary;
import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;

/**
 * Dictionary SqlMapDao
 */
public class DictionarySqlMapDao extends BaseSqlMapDao implements DictionaryDao {

	public DictionarySqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	public void create(Dictionary domain) throws DaoException {
		try {
			getSqlMapExecutor().insert("Dictionary.create", domain);
		} catch (SQLException e) {
			throw new DaoException("Create domain error. cause:" + e, e);
		}
	}

	public int delete(java.lang.Integer domainPK) throws DaoException {
		try {
			return getSqlMapExecutor().delete("Dictionary.delete", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Delete domain error. cause:" + e, e);
		}
	}

	public List queryForList(Map params) throws DaoException {
		this.prepareQuery(params);
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("Dictionary.query_list", params);
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
			list = queryForPageList("Dictionary.query", params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new DaoException("Query domain error. cause:", e);
		}
		return list;
	}

	public Dictionary retrieve(java.lang.Integer domainPK) throws DaoException {
		Dictionary object = new Dictionary();
		try {
			object = (Dictionary) getSqlMapExecutor().queryForObject("Dictionary.retrieve",
					domainPK);
		} catch (SQLException e) {
			throw new DaoException("Retrieve domain error. cause:" + e, e);
		}
		return object;
	}

	public int update(Dictionary domain) throws DaoException {
		try {
			return getSqlMapExecutor().update("Dictionary.update", domain);
		} catch (SQLException e) {
			throw new DaoException("Update domain error. cause:" + e, e);
		}
	}

	

	
}

