package com.tbb.basedata.dao;


import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.PageList;
import com.ibatis.dao.client.DaoException;
import com.tbb.basedata.domain.Recipe;

/**
 * Organ Dao
 */
public interface RecipeDao {
	
	public PageList queryForPageList(Map params, int pageIndex, int pageSize)
			throws DaoException;
	
	public Recipe retrieve(java.lang.String domainPK) throws DaoException;
	
	public void create(Recipe domain) throws DaoException;
	
	public int update(Recipe domain) throws DaoException;
	
	public int delete(java.lang.String domainPK) throws DaoException;
	
	public List queryForList(Map params) throws DaoException;
	
}


