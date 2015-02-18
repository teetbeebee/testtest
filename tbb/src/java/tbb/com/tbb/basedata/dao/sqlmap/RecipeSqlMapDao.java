package com.tbb.basedata.dao.sqlmap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseSqlMapDao;
import com.newbee.tmf.core.PageList;
import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;
import com.tbb.basedata.dao.RecipeDao;
import com.tbb.basedata.domain.Recipe;

/**
 * Recipe SqlMapDao
 */
public class RecipeSqlMapDao extends BaseSqlMapDao implements RecipeDao {

	public RecipeSqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	public void create(Recipe domain) throws DaoException {
		try {
			getSqlMapExecutor().insert("Recipe.create", domain);
		} catch (SQLException e) {
			throw new DaoException("Create domain error. cause:" + e, e);
		}
	}

	public int delete(java.lang.String domainPK) throws DaoException {
		try {
			return getSqlMapExecutor().delete("Recipe.delete", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Delete domain error. cause:" + e, e);
		}
	}

	public List queryForList(Map params) throws DaoException {
		this.prepareQuery(params);
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("Recipe.query_list", params);
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
			list = queryForPageList("Recipe.query", params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new DaoException("Query domain error. cause:", e);
		}
		return list;
	}

	public Recipe retrieve(java.lang.String domainPK) throws DaoException {
		Recipe object = new Recipe();
		try {
			object = (Recipe) getSqlMapExecutor().queryForObject(
					"Recipe.retrieve", domainPK);
		} catch (SQLException e) {
			throw new DaoException("Retrieve domain error. cause:" + e, e);
		}
		return object;
	}

	public int update(Recipe domain) throws DaoException {
		try {
			return getSqlMapExecutor().update("Recipe.update", domain);
		} catch (SQLException e) {
			throw new DaoException("Update domain error. cause:" + e, e);
		}
	}


}

