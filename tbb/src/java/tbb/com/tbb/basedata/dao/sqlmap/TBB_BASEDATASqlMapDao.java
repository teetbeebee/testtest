package com.tbb.basedata.dao.sqlmap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseSqlMapDao;
import com.newbee.tmf.core.PageList;
import com.tbb.basedata.dao.TBB_BASEDATADao;
import com.tbb.basedata.domain.TBB_BASEDATA;
import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;

/**
 * TBB_BASEDATA SqlMapDao
 */
public class TBB_BASEDATASqlMapDao extends BaseSqlMapDao implements TBB_BASEDATADao {

	public TBB_BASEDATASqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	public void create(TBB_BASEDATA domain) throws DaoException {
		try {
			getSqlMapExecutor().insert("TBB_BASEDATA.create", domain);
		} catch (SQLException e) {
			throw new DaoException("Create domain error. cause:" + e, e);
		}
	}

	public int delete(java.lang.Integer domainPK) throws DaoException {
		try {
			return getSqlMapExecutor().delete("TBB_BASEDATA.delete", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Delete domain error. cause:" + e, e);
		}
	}

	public List queryForList(Map params) throws DaoException {
		this.prepareQuery(params);
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("TBB_BASEDATA.query_list", params);
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
			list = queryForPageList("TBB_BASEDATA.query", params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new DaoException("Query domain error. cause:", e);
		}
		return list;
	}

	public TBB_BASEDATA retrieve(java.lang.Integer domainPK) throws DaoException {
		TBB_BASEDATA object = new TBB_BASEDATA();
		try {
			object = (TBB_BASEDATA) getSqlMapExecutor().queryForObject("TBB_BASEDATA.retrieve",
					domainPK);
		} catch (SQLException e) {
			throw new DaoException("Retrieve domain error. cause:" + e, e);
		}
		return object;
	}

	public int update(TBB_BASEDATA domain) throws DaoException {
		try {
			return getSqlMapExecutor().update("TBB_BASEDATA.update", domain);
		} catch (SQLException e) {
			throw new DaoException("Update domain error. cause:" + e, e);
		}
	}

	

	
}



