package com.tbb.member.dao.sqlmap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseSqlMapDao;
import com.newbee.tmf.core.PageList;
import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;
import com.tbb.member.dao.Web_memberDao;
import com.tbb.member.domain.Web_member;

/**
 * Web_member SqlMapDao
 */
public class Web_memberSqlMapDao extends BaseSqlMapDao implements Web_memberDao {

	public Web_memberSqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	public void create(Web_member domain) throws DaoException {
		try {
			getSqlMapExecutor().insert("Member.create", domain);
		} catch (SQLException e) {
			throw new DaoException("Create domain error. cause:" + e, e);
		}
	}

	public int delete(java.lang.String domainPK) throws DaoException {
		try {
			return getSqlMapExecutor().delete("Member.delete", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Delete domain error. cause:" + e, e);
		}
	}

	public List queryForList(Map params) throws DaoException {
		this.prepareQuery(params);
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("Member.query_list", params);
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
			list = queryForPageList("Member.query", params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new DaoException("Query domain error. cause:", e);
		}
		return list;
	}

	public Web_member retrieve(java.lang.String domainPK) throws DaoException {
		Web_member object = new Web_member();
		try {
			object = (Web_member) getSqlMapExecutor().queryForObject(
					"Member.retrieve", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Retrieve domain error. cause:" + e, e);
		}
		return object;
	}
	
	public Web_member retrieveByUid(java.lang.String domainPK) throws DaoException {
		Web_member object = new Web_member();
		try {
			object = (Web_member) getSqlMapExecutor().queryForObject(
					"Member.retrieveByUid", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Retrieve domain error. cause:" + e, e);
		}
		return object;
	}

	public int update(Web_member domain) throws DaoException {
		try {
			return getSqlMapExecutor().update("Member.update", domain);
		} catch (SQLException e) {
			throw new DaoException("Update domain error. cause:" + e, e);
		}
	}


}

