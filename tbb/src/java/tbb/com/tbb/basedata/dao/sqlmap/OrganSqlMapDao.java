package com.tbb.basedata.dao.sqlmap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseSqlMapDao;
import com.newbee.tmf.core.PageList;
import com.tbb.basedata.dao.OrganDao;
import com.tbb.basedata.domain.Organ;
import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;

/**
 * Organ SqlMapDao
 */
public class OrganSqlMapDao extends BaseSqlMapDao implements OrganDao {

	public OrganSqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	public void create(Organ domain) throws DaoException {
		try {
			getSqlMapExecutor().insert("Organ.create", domain);
		} catch (SQLException e) {
			throw new DaoException("Create domain error. cause:" + e, e);
		}
	}

	public int delete(java.lang.String domainPK) throws DaoException {
		try {
			return getSqlMapExecutor().delete("Organ.delete", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Delete domain error. cause:" + e, e);
		}
	}

	public List queryForList(Map params) throws DaoException {
		this.prepareQuery(params);
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("Organ.query_list", params);
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
			list = queryForPageList("Organ.query", params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new DaoException("Query domain error. cause:", e);
		}
		return list;
	}

	public Organ retrieve(java.lang.String domainPK) throws DaoException {
		Organ object = new Organ();
		try {
			object = (Organ) getSqlMapExecutor().queryForObject(
					"Organ.retrieve", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Retrieve domain error. cause:" + e, e);
		}
		return object;
	}

	public int update(Organ domain) throws DaoException {
		try {
			return getSqlMapExecutor().update("Organ.update", domain);
		} catch (SQLException e) {
			throw new DaoException("Update domain error. cause:" + e, e);
		}
	}

	/**
	 * 得到组织机构列表，根据父ID
	 * 
	 * @param parent_id
	 *            父ID
	 * @return 包装为List的领域对象集合
	 * @throws DaoException
	 */
	@SuppressWarnings("unchecked")
	public List getOrganListByParentID(String parent_id) throws DaoException {
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList(
					"Organ.get_child_organ_list", parent_id);
		} catch (Exception e) {
			throw new DaoException("Query domain error" + e, e);
		}
		return list;
	}

}
