package com.tbb.vpn.service;

import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.newbee.tmf.core.PageList;
import com.tbb.vpn.dao.VpnlineDao;
import com.tbb.vpn.domain.Vpnline;

/**
 * Vpnline Service
 */
public class VpnlineService extends BaseService
{
	private static VpnlineService instance = new VpnlineService();

	private VpnlineService()
	{
		// empty
		// 防止直接创建对象
	}

	public static VpnlineService getInstance()
	{
		return instance;
	}

	/**
	 * 创建Vpnline对象
	 * 
	 * @param vpnline
	 * @throws Exception
	 */
	public void createVpnline(Vpnline vpnline) throws Exception
	{
		try
		{
			VpnlineDao dao = (VpnlineDao)getDao(VpnlineDao.class);
			dao.create(vpnline);
		}
		catch (Exception e)
		{
			throw new BaseException("创建新Vpnline失败！", e);
		}
	}

	/**
	 * 根据Vpnline主关键字获取Vpnline信息
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public Vpnline retrieveVpnline(java.lang.String domainPK) throws Exception
	{
		try
		{
			VpnlineDao dao = (VpnlineDao)getDao(VpnlineDao.class);
			return dao.retrieve(domainPK);
		}
		catch (Exception e)
		{
			throw new BaseException("获取Vpnline信息失败！", e);
		}
	}

	/**
	 * 更新Vpnline信息
	 * 
	 * @param vpnline
	 * @return
	 * @throws Exception
	 */
	public int updateVpnline(Vpnline vpnline) throws Exception
	{
		int effectRows = 0;

		try
		{
			VpnlineDao dao = (VpnlineDao)getDao(VpnlineDao.class);
			effectRows = dao.update(vpnline);
		}
		catch (Exception e)
		{
			throw new BaseException("修改Vpnline信息失败！", e);
		}

		return effectRows;
	}

	/**
	 * 根据Vpnline主关键字删除Vpnline
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public int deleteVpnline(java.lang.String domainPK) throws Exception
	{
		int effectRows = 0;

		try
		{
			VpnlineDao dao = (VpnlineDao)getDao(VpnlineDao.class);
			effectRows = dao.delete(domainPK);
		}
		catch (Exception e)
		{
			throw new BaseException("删除Vpnline失败！", e);
		}

		return effectRows;
	}

	/**
	 * 查询Vpnline
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List queryVpnlineForList(Map params) throws Exception
	{
		try
		{
			VpnlineDao dao = (VpnlineDao)getDao(VpnlineDao.class);
			return dao.queryForList(params);
		}
		catch (Exception e)
		{
			throw new BaseException("查询Vpnline失败!", e);
		}
	}

	/**
	 * 查询Vpnline
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList queryVpnlineForPageList(Map params, int pageIndex,
			int pageSize) throws Exception
	{
		try
		{
			VpnlineDao dao = (VpnlineDao)getDao(VpnlineDao.class);
			return dao.queryForPageList(params, pageIndex, pageSize);
		}
		catch (Exception e)
		{
			throw new BaseException("查询Vpnline失败!", e);
		}
	}

}



