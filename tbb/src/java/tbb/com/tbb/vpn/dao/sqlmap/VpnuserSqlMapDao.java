package com.tbb.vpn.dao.sqlmap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseSqlMapDao;
import com.newbee.tmf.core.PageList;
import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;
import com.tbb.vpn.dao.VpnuserDao;
import com.tbb.vpn.domain.Vpnuser;

/**
 * Vpnuser SqlMapDao
 */
public class VpnuserSqlMapDao extends BaseSqlMapDao implements VpnuserDao {

	public VpnuserSqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	public void create(Vpnuser domain) throws DaoException {
		try {
			getSqlMapExecutor().insert("Vpnuser.create", domain);
		} catch (SQLException e) {
			throw new DaoException("Create domain error. cause:" + e, e);
		}
	}

	public int delete(java.lang.String domainPK) throws DaoException {
		try {
			return getSqlMapExecutor().delete("Vpnuser.delete", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Delete domain error. cause:" + e, e);
		}
	}

	public List queryForList(Map params) throws DaoException {
		this.prepareQuery(params);
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("Vpnuser.query_list", params);
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
			list = queryForPageList("Vpnuser.query", params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new DaoException("Query domain error. cause:", e);
		}
		return list;
	}

	public Vpnuser retrieve(java.lang.String domainPK) throws DaoException {
		Vpnuser object = new Vpnuser();
		try {
			object = (Vpnuser) getSqlMapExecutor().queryForObject(
					"Vpnuser.retrieve", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Retrieve domain error. cause:" + e, e);
		}
		return object;
	}

	public int update(Vpnuser domain) throws DaoException {
		try {
			return getSqlMapExecutor().update("Vpnuser.update", domain);
		} catch (SQLException e) {
			throw new DaoException("Update domain error. cause:" + e, e);
		}
	}


}

