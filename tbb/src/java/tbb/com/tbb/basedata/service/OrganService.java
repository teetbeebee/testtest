package com.tbb.basedata.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.newbee.tmf.core.PageList;
import com.tbb.basedata.dao.OrganDao;
import com.tbb.basedata.domain.Organ;
import com.tbb.sys.domain.SysUser;

/**
 * Organ Service
 */
public class OrganService extends BaseService
{
	private static OrganService instance = new OrganService();

	private OrganService()
	{
		// empty
		// 防止直接创建对象
	}

	public static OrganService getInstance()
	{
		return instance;
	}

	/**
	 * 创建Organ对象
	 * 
	 * @param organ
	 * @throws Exception
	 */
	public void createOrgan(Organ organ) throws Exception
	{
		try
		{
			OrganDao dao = (OrganDao)getDao(OrganDao.class);
			dao.create(organ);
		}
		catch (Exception e)
		{
			throw new BaseException("创建新Organ失败！", e);
		}
	}

	/**
	 * 根据Organ主关键字获取Organ信息
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public Organ retrieveOrgan(java.lang.String domainPK) throws Exception
	{
		try
		{
			OrganDao dao = (OrganDao)getDao(OrganDao.class);
			return dao.retrieve(domainPK);
		}
		catch (Exception e)
		{
			throw new BaseException("获取Organ信息失败！", e);
		}
	}

	/**
	 * 更新Organ信息
	 * 
	 * @param organ
	 * @return
	 * @throws Exception
	 */
	public int updateOrgan(Organ organ) throws Exception
	{
		int effectRows = 0;

		try
		{
			OrganDao dao = (OrganDao)getDao(OrganDao.class);
			effectRows = dao.update(organ);
		}
		catch (Exception e)
		{
			throw new BaseException("修改Organ信息失败！", e);
		}

		return effectRows;
	}

	/**
	 * 根据Organ主关键字删除Organ
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public int deleteOrgan(java.lang.String domainPK) throws Exception
	{
		int effectRows = 0;

		try
		{
			OrganDao dao = (OrganDao)getDao(OrganDao.class);
			effectRows = dao.delete(domainPK);
		}
		catch (Exception e)
		{
			throw new BaseException("删除Organ失败！", e);
		}

		return effectRows;
	}

	/**
	 * 查询Organ
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List queryOrganForList(Map params) throws Exception
	{
		try
		{
			OrganDao dao = (OrganDao)getDao(OrganDao.class);
			return dao.queryForList(params);
		}
		catch (Exception e)
		{
			throw new BaseException("查询Organ失败!", e);
		}
	}

	/**
	 * 查询Organ
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList queryOrganForPageList(Map params, int pageIndex,
			int pageSize) throws Exception
			{
		try
		{
			OrganDao dao = (OrganDao)getDao(OrganDao.class);
			return dao.queryForPageList(params, pageIndex, pageSize);
		}
		catch (Exception e)
		{
			throw new BaseException("查询Organ失败!", e);
		}
			}

	List<Map<String, Object>> org_list = new ArrayList<Map<String, Object>>();

	/**
	 * 获取组织机构树，根据登录用户的信息
	 * 
	 * @param request
	 * @return
	 * @throws BaseException
	 */
	public List getOrganTree(HttpServletRequest request) throws BaseException
	{
		// 取操作人账号
		SysUser user = (SysUser)request.getSession(true).getAttribute("user");
		String organ_id = user.getOrgan_id();

		if (null == organ_id || "".equals(organ_id.trim()))
		{
			throw new BaseException("用户组织机构编号为空！");
		}

		return getOrganTree(organ_id);
	}

	/**
	 * 获取组织机构树
	 * 
	 * @param parent_id 根结点ID
	 * @return 封装为List的组织机构集合
	 */
	public List getOrganTree(String parent_id)
	{

		org_list.clear();

		OrganDao dao = (OrganDao)getDao(OrganDao.class);

		if (parent_id.equals("0"))
		{ // 顶级节点

			if (isParentNode(parent_id))
			{
				List<Organ> organ_arr = dao.getOrganListByParentID(parent_id);
				for (Organ organ : organ_arr)
				{
					if (isParentNode(organ.getOrgan_id()))
					{
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("level", new Integer(0));
						map.put("organ", organ);
						map.put("isparent", true);

						org_list.add(map);

						getChildNode(organ.getOrgan_id(), 0);
					}
					else
					{
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("level", new Integer(0));
						map.put("organ", organ);
						map.put("isparent", false);

						org_list.add(map);
					}

				}

			}
			else
			{
				return null;
			}
		}
		else
		{ // 不是顶级结点
			Organ orgnode = dao.retrieve(parent_id);
			if (orgnode != null)
			{

				if (isParentNode(parent_id))
				{
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("level", new Integer(0));
					map.put("organ", orgnode);
					map.put("isparent", true);

					org_list.add(map);

					getChildNode(parent_id, 0);
				}
				else
				{
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("level", new Integer(0));
					map.put("organ", orgnode);
					map.put("isparent", false);

					org_list.add(map);
				}
			}
			else
			{
				return null;
			}
		}

		return org_list;
	}

	/**
	 * 是否是父节点
	 */
	public Boolean isParentNode(String parent_id)
	{

		Boolean isParent = false;

		OrganDao dao = (OrganDao)getDao(OrganDao.class);
		List orgList = dao.getOrganListByParentID(parent_id);
		if (orgList.size() > 0)
		{
			isParent = true;
		}

		return isParent;
	}

	/**
	 * 剃归子节点
	 * 
	 * @param parent_id 父节点ID
	 * @param level 节点级数
	 */
	private void getChildNode(String parent_id, int level)
	{

		OrganDao dao = (OrganDao)getDao(OrganDao.class);

		if (isParentNode(parent_id))
		{
			List<Organ> org_arr = dao.getOrganListByParentID(parent_id);
			for (Organ organ : org_arr)
			{
				if (isParentNode(organ.getOrgan_id()))
				{
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("level", new Integer(level + 1));
					map.put("organ", organ);
					map.put("isparent", true);

					org_list.add(map);

					getChildNode(organ.getOrgan_id(), level + 1);
				}
				else
				{
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("level", new Integer(level + 1));
					map.put("organ", organ);
					map.put("isparent", false);

					org_list.add(map);
				}

			}
		}
	}

	public String getMaskByUser(SysUser user){
		OrganDao dao = (OrganDao)getDao(OrganDao.class);
		Organ organ = dao.retrieve(user.getOrgan_id());		
		return organ.getOrgan_id().substring(0, 2 * Integer.parseInt(organ.getOrgan_level()));
	}

	public String getMaskByOrgan(Organ organ){
		if(organ == null)
			return "";
		else
			return organ.getOrgan_id().substring(0, 2 * Integer.parseInt(organ.getOrgan_level()));
	}

	public Organ getOrganByOrganID(String organID){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("organ_id", organID);

		OrganDao dao = (OrganDao)getDao(OrganDao.class);
		List organList = dao.queryForList(map);

		if(organList.size() > 0)
			return (Organ)organList.get(0);
		else
			return null;
	}

	public List getChildList(String organID){
		if(!this.isParentNode(organID))
			return null;

		Organ parent = this.getOrganByOrganID(organID);
		String idMask = this.getMaskByOrgan(parent);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("organ_id", idMask);
		map.put("organ_level", Integer.parseInt(parent.getOrgan_level()) + 1);

		OrganDao dao = (OrganDao)getDao(OrganDao.class);
		List organList = dao.queryForList(map);

		return organList;
	}
	
	public String getNameByID(String organID){
		String organ_name = "";
		Organ organ = this.getOrganByOrganID(organID);
		if(organ != null)
			organ_name = organ.getOrgan_name();
		
		return organ_name;
	}

}
