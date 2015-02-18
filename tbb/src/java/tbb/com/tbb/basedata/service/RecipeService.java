package com.tbb.basedata.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.newbee.tmf.core.PageList;
import com.tbb.basedata.dao.RecipeDao;
import com.tbb.basedata.domain.Recipe;

/**
 * Recipe Service
 */
public class RecipeService extends BaseService
{
	private static RecipeService instance = new RecipeService();

	private RecipeService()
	{
		// empty
		// 防止直接创建对象
	}

	public static RecipeService getInstance()
	{
		return instance;
	}

	/**
	 * 创建Recipe对象
	 * 
	 * @param recipe
	 * @throws Exception
	 */
	public void createRecipe(Recipe recipe) throws Exception
	{
		try
		{
			RecipeDao dao = (RecipeDao)getDao(RecipeDao.class);
			dao.create(recipe);
		}
		catch (Exception e)
		{
			throw new BaseException("创建新Recipe失败！", e);
		}
	}

	/**
	 * 根据Recipe主关键字获取Recipe信息
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public Recipe retrieveRecipe(java.lang.String domainPK) throws Exception
	{
		try
		{
			RecipeDao dao = (RecipeDao)getDao(RecipeDao.class);
			return dao.retrieve(domainPK);
		}
		catch (Exception e)
		{
			throw new BaseException("获取Recipe信息失败！", e);
		}
	}

	/**
	 * 更新Recipe信息
	 * 
	 * @param recipe
	 * @return
	 * @throws Exception
	 */
	public int updateRecipe(Recipe recipe) throws Exception
	{
		int effectRows = 0;

		try
		{
			RecipeDao dao = (RecipeDao)getDao(RecipeDao.class);
			effectRows = dao.update(recipe);
		}
		catch (Exception e)
		{
			throw new BaseException("修改Recipe信息失败！", e);
		}

		return effectRows;
	}

	/**
	 * 根据Recipe主关键字删除Recipe
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public int deleteRecipe(java.lang.String domainPK) throws Exception
	{
		int effectRows = 0;

		try
		{
			RecipeDao dao = (RecipeDao)getDao(RecipeDao.class);
			effectRows = dao.delete(domainPK);
		}
		catch (Exception e)
		{
			throw new BaseException("删除Recipe失败！", e);
		}

		return effectRows;
	}

	/**
	 * 查询Recipe
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List queryRecipeForList(Map params) throws Exception
	{
		try
		{
			RecipeDao dao = (RecipeDao)getDao(RecipeDao.class);
			return dao.queryForList(params);
		}
		catch (Exception e)
		{
			throw new BaseException("查询Recipe失败!", e);
		}
	}

	/**
	 * 查询Recipe
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList queryRecipeForPageList(Map params, int pageIndex,
			int pageSize) throws Exception
	{
		try
		{
			RecipeDao dao = (RecipeDao)getDao(RecipeDao.class);
			return dao.queryForPageList(params, pageIndex, pageSize);
		}
		catch (Exception e)
		{
			throw new BaseException("查询Recipe失败!", e);
		}
	}

}

