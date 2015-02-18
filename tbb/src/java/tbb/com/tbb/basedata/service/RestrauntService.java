package com.tbb.basedata.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.newbee.tmf.core.PageList;
import com.tbb.basedata.dao.RestrauntDao;
import com.tbb.basedata.domain.Restraunt;

/**
 * Restraunt Service
 */
public class RestrauntService extends BaseService
{
	private static RestrauntService instance = new RestrauntService();

	private RestrauntService()
	{
		// empty
		// 防止直接创建对象
	}

	public static RestrauntService getInstance()
	{
		return instance;
	}

	/**
	 * 创建Restraunt对象
	 * 
	 * @param restraunt
	 * @throws Exception
	 */
	public void createRestraunt(Restraunt restraunt) throws Exception
	{
		try
		{
			RestrauntDao dao = (RestrauntDao)getDao(RestrauntDao.class);
			dao.create(restraunt);
		}
		catch (Exception e)
		{
			throw new BaseException("创建新Restraunt失败！", e);
		}
	}

	/**
	 * 根据Restraunt主关键字获取Restraunt信息
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public Restraunt retrieveRestraunt(java.lang.String domainPK) throws Exception
	{
		try
		{
			RestrauntDao dao = (RestrauntDao)getDao(RestrauntDao.class);
			return dao.retrieve(domainPK);
		}
		catch (Exception e)
		{
			throw new BaseException("获取Restraunt信息失败！", e);
		}
	}

	/**
	 * 更新Restraunt信息
	 * 
	 * @param restraunt
	 * @return
	 * @throws Exception
	 */
	public int updateRestraunt(Restraunt restraunt) throws Exception
	{
		int effectRows = 0;

		try
		{
			RestrauntDao dao = (RestrauntDao)getDao(RestrauntDao.class);
			effectRows = dao.update(restraunt);
		}
		catch (Exception e)
		{
			throw new BaseException("修改Restraunt信息失败！", e);
		}

		return effectRows;
	}

	/**
	 * 根据Restraunt主关键字删除Restraunt
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public int deleteRestraunt(java.lang.String domainPK) throws Exception
	{
		int effectRows = 0;

		try
		{
			RestrauntDao dao = (RestrauntDao)getDao(RestrauntDao.class);
			effectRows = dao.delete(domainPK);
		}
		catch (Exception e)
		{
			throw new BaseException("删除Restraunt失败！", e);
		}

		return effectRows;
	}

	/**
	 * 查询Restraunt
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List queryRestrauntForList(Map params) throws Exception
	{
		try
		{
			RestrauntDao dao = (RestrauntDao)getDao(RestrauntDao.class);
			return dao.queryForList(params);
		}
		catch (Exception e)
		{
			throw new BaseException("查询Restraunt失败!", e);
		}
	}

	/**
	 * 查询Restraunt
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList queryRestrauntForPageList(Map params, int pageIndex,
			int pageSize) throws Exception
	{
		try
		{
			RestrauntDao dao = (RestrauntDao)getDao(RestrauntDao.class);
			return dao.queryForPageList(params, pageIndex, pageSize);
		}
		catch (Exception e)
		{
			throw new BaseException("查询Restraunt失败!", e);
		}
	}

}

