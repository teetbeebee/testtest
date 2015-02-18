package com.tbb.member.service;

import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.newbee.tmf.core.PageList;
import com.tbb.member.dao.Web_memberDao;
import com.tbb.member.domain.Web_member;

/**
 * Web_member Service
 */
public class Web_memberService extends BaseService
{
	private static Web_memberService instance = new Web_memberService();

	private Web_memberService()
	{
		// empty
		// 防止直接创建对象
	}

	public static Web_memberService getInstance()
	{
		return instance;
	}

	/**
	 * 创建Web_member对象
	 * 
	 * @param web_member
	 * @throws Exception
	 */
	public void createWeb_member(Web_member web_member) throws Exception
	{
		try
		{
			Web_memberDao dao = (Web_memberDao)getDao(Web_memberDao.class);
			dao.create(web_member);
		}
		catch (Exception e)
		{
			throw new BaseException("创建新Web_member失败！", e);
		}
	}

	/**
	 * 根据Web_member主关键字获取Web_member信息
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public Web_member retrieveWeb_member(java.lang.String domainPK) throws Exception
	{
		try
		{
			Web_memberDao dao = (Web_memberDao)getDao(Web_memberDao.class);
			return dao.retrieve(domainPK);
		}
		catch (Exception e)
		{
			throw new BaseException("获取Web_member信息失败！", e);
		}
	}
	
	public Web_member retrieveByUid(java.lang.String domainPK) throws Exception
	{
		try
		{
			Web_memberDao dao = (Web_memberDao)getDao(Web_memberDao.class);
			return dao.retrieveByUid(domainPK);
		}
		catch (Exception e)
		{
			throw new BaseException("获取Web_member信息失败！", e);
		}
	}

	/**
	 * 更新Web_member信息
	 * 
	 * @param web_member
	 * @return
	 * @throws Exception
	 */
	public int updateWeb_member(Web_member web_member) throws Exception
	{
		int effectRows = 0;

		try
		{
			Web_memberDao dao = (Web_memberDao)getDao(Web_memberDao.class);
			effectRows = dao.update(web_member);
		}
		catch (Exception e)
		{
			throw new BaseException("修改Web_member信息失败！", e);
		}

		return effectRows;
	}

	/**
	 * 根据Web_member主关键字删除Web_member
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public int deleteWeb_member(java.lang.String domainPK) throws Exception
	{
		int effectRows = 0;

		try
		{
			Web_memberDao dao = (Web_memberDao)getDao(Web_memberDao.class);
			effectRows = dao.delete(domainPK);
		}
		catch (Exception e)
		{
			throw new BaseException("删除Web_member失败！", e);
		}

		return effectRows;
	}

	/**
	 * 查询Web_member
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List queryWeb_memberForList(Map params) throws Exception
	{
		try
		{
			Web_memberDao dao = (Web_memberDao)getDao(Web_memberDao.class);
			return dao.queryForList(params);
		}
		catch (Exception e)
		{
			throw new BaseException("查询Web_member失败!", e);
		}
	}

	/**
	 * 查询Web_member
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList queryWeb_memberForPageList(Map params, int pageIndex,
			int pageSize) throws Exception
	{
		try
		{
			Web_memberDao dao = (Web_memberDao)getDao(Web_memberDao.class);
			return dao.queryForPageList(params, pageIndex, pageSize);
		}
		catch (Exception e)
		{
			throw new BaseException("查询Web_member失败!", e);
		}
	}

}



