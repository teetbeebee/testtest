package com.tbb.basedata.dao.sqlmap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseSqlMapDao;
import com.newbee.tmf.core.PageList;
import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;
import com.tbb.basedata.dao.RestrauntDao;
import com.tbb.basedata.domain.Restraunt;

/**
 * Restraunt SqlMapDao
 */
public class RestrauntSqlMapDao extends BaseSqlMapDao implements RestrauntDao {

	public RestrauntSqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	public void create(Restraunt domain) throws DaoException {
		try {
			getSqlMapExecutor().insert("Restraunt.create", domain);
		} catch (SQLException e) {
			throw new DaoException("Create domain error. cause:" + e, e);
		}
	}

	public int delete(java.lang.String domainPK) throws DaoException {
		try {
			return getSqlMapExecutor().delete("Restraunt.delete", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Delete domain error. cause:" + e, e);
		}
	}

	public List queryForList(Map params) throws DaoException {
		this.prepareQuery(params);
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("Restraunt.query_list", params);
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
			list = queryForPageList("Restraunt.query", params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new DaoException("Query domain error. cause:", e);
		}
		return list;
	}

	public Restraunt retrieve(java.lang.String domainPK) throws DaoException {
		Restraunt object = new Restraunt();
		try {
			object = (Restraunt) getSqlMapExecutor().queryForObject(
					"Restraunt.retrieve", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Retrieve domain error. cause:" + e, e);
		}
		return object;
	}

	public int update(Restraunt domain) throws DaoException {
		try {
			return getSqlMapExecutor().update("Restraunt.update", domain);
		} catch (SQLException e) {
			throw new DaoException("Update domain error. cause:" + e, e);
		}
	}


}

