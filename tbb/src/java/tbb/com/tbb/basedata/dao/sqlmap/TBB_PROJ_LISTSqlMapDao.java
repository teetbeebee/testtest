package com.tbb.basedata.dao.sqlmap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseSqlMapDao;
import com.newbee.tmf.core.PageList;
import com.tbb.basedata.dao.TBB_PROJ_LISTDao;
import com.tbb.basedata.domain.TBB_PROJ_LIST;
import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;

/**
 * TBB_PROJ_LIST SqlMapDao
 */
public class TBB_PROJ_LISTSqlMapDao extends BaseSqlMapDao implements TBB_PROJ_LISTDao {

	public TBB_PROJ_LISTSqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	public void create(TBB_PROJ_LIST domain) throws DaoException {
		try {
			getSqlMapExecutor().insert("TBB_PROJ_LIST.create", domain);
		} catch (SQLException e) {
			throw new DaoException("Create domain error. cause:" + e, e);
		}
	}

	public int delete(java.lang.Integer domainPK) throws DaoException {
		try {
			return getSqlMapExecutor().delete("TBB_PROJ_LIST.delete", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Delete domain error. cause:" + e, e);
		}
	}

	public List queryForList(Map params) throws DaoException {
		this.prepareQuery(params);
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("TBB_PROJ_LIST.query_list", params);
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
			list = queryForPageList("TBB_PROJ_LIST.query", params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new DaoException("Query domain error. cause:", e);
		}
		return list;
	}

	public TBB_PROJ_LIST retrieve(java.lang.Integer domainPK) throws DaoException {
		TBB_PROJ_LIST object = new TBB_PROJ_LIST();
		try {
			object = (TBB_PROJ_LIST) getSqlMapExecutor().queryForObject("TBB_PROJ_LIST.retrieve",
					domainPK);
		} catch (SQLException e) {
			throw new DaoException("Retrieve domain error. cause:" + e, e);
		}
		return object;
	}

	public int update(TBB_PROJ_LIST domain) throws DaoException {
		try {
			return getSqlMapExecutor().update("TBB_PROJ_LIST.update", domain);
		} catch (SQLException e) {
			throw new DaoException("Update domain error. cause:" + e, e);
		}
	}

	

	
}



