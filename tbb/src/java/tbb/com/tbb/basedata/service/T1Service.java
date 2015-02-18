package com.tbb.basedata.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.newbee.tmf.core.PageList;
import com.tbb.basedata.dao.T1Dao;
import com.tbb.basedata.domain.T1;

/**
 * T1 Service
 */
public class T1Service extends BaseService
{
	private static T1Service instance = new T1Service();

	private T1Service()
	{
		// empty
		// 防止直接创建对象
	}

	public static T1Service getInstance()
	{
		return instance;
	}

	/**
	 * 创建T1对象
	 * 
	 * @param t1
	 * @throws Exception
	 */
	public void createT1(T1 t1) throws Exception
	{
		try
		{
			T1Dao dao = (T1Dao)getDao(T1Dao.class);
			dao.create(t1);
		}
		catch (Exception e)
		{
			throw new BaseException("创建新T1失败！", e);
		}
	}

	/**
	 * 根据T1主关键字获取T1信息
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public T1 retrieveT1(java.lang.String domainPK) throws Exception
	{
		try
		{
			T1Dao dao = (T1Dao)getDao(T1Dao.class);
			return dao.retrieve(domainPK);
		}
		catch (Exception e)
		{
			throw new BaseException("获取T1信息失败！", e);
		}
	}

	/**
	 * 更新T1信息
	 * 
	 * @param t1
	 * @return
	 * @throws Exception
	 */
	public int updateT1(T1 t1) throws Exception
	{
		int effectRows = 0;

		try
		{
			T1Dao dao = (T1Dao)getDao(T1Dao.class);
			effectRows = dao.update(t1);
		}
		catch (Exception e)
		{
			throw new BaseException("修改T1信息失败！", e);
		}

		return effectRows;
	}

	/**
	 * 根据T1主关键字删除T1
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public int deleteT1(java.lang.String domainPK) throws Exception
	{
		int effectRows = 0;

		try
		{
			T1Dao dao = (T1Dao)getDao(T1Dao.class);
			effectRows = dao.delete(domainPK);
		}
		catch (Exception e)
		{
			throw new BaseException("删除T1失败！", e);
		}

		return effectRows;
	}

	/**
	 * 查询T1
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List queryT1ForList(Map params) throws Exception
	{
		try
		{
			T1Dao dao = (T1Dao)getDao(T1Dao.class);
			return dao.queryForList(params);
		}
		catch (Exception e)
		{
			throw new BaseException("查询T1失败!", e);
		}
	}

	/**
	 * 查询T1
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList queryT1ForPageList(Map params, int pageIndex,
			int pageSize) throws Exception
	{
		try
		{
			T1Dao dao = (T1Dao)getDao(T1Dao.class);
			return dao.queryForPageList(params, pageIndex, pageSize);
		}
		catch (Exception e)
		{
			throw new BaseException("查询T1失败!", e);
		}
	}

}
