package com.tbb.sys.service;

import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.newbee.tmf.core.PageList;
import com.tbb.sys.dao.SysRoleDao;
import com.tbb.sys.domain.SysRole;

/**
 * SysRole Service
 */
public class SysRoleService extends BaseService {
	private static SysRoleService instance = new SysRoleService();

	private SysRoleService() {
		// empty
		// 防止直接创建对象
	}

	public static SysRoleService getInstance() {
		return instance;
	}

	/**
	 * 创建SysRole对象
	 * 
	 * @param sysRole
	 * @throws Exception
	 */
	public void createSysRole(SysRole sysRole) throws Exception {
		try {
			SysRoleDao dao = (SysRoleDao) getDao(SysRoleDao.class);
			dao.create(sysRole);
		} catch (Exception e) {
			throw new BaseException("创建新SysRole失败！", e);
		}
	}

	/**
	 * 根据SysRole主关键字获取SysRole信息
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public SysRole retrieveSysRole(java.lang.String domainPK) throws Exception {
		try {
			SysRoleDao dao = (SysRoleDao) getDao(SysRoleDao.class);
			return dao.retrieve(domainPK);
		} catch (Exception e) {
			throw new BaseException("获取SysRole信息失败！", e);
		}
	}

	/**
	 * 更新SysRole信息
	 * 
	 * @param sysRole
	 * @return
	 * @throws Exception
	 */
	public int updateSysRole(SysRole sysRole) throws Exception {
		int effectRows = 0;

		try {
			SysRoleDao dao = (SysRoleDao) getDao(SysRoleDao.class);
			effectRows = dao.update(sysRole);
		} catch (Exception e) {
			throw new BaseException("修改SysRole信息失败！", e);
		}

		return effectRows;
	}

	/**
	 * 根据SysRole主关键字删除SysRole
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public int deleteSysRole(java.lang.String domainPK) throws Exception {
		int effectRows = 0;

		try {
			SysRoleDao dao = (SysRoleDao) getDao(SysRoleDao.class);
			effectRows = dao.delete(domainPK);
		} catch (Exception e) {
			throw new BaseException("删除SysRole失败！", e);
		}

		return effectRows;
	}

	/**
	 * 查询SysRole
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List querySysRoleForList(Map params) throws Exception {
		try {
			SysRoleDao dao = (SysRoleDao) getDao(SysRoleDao.class);
			return dao.queryForList(params);
		} catch (Exception e) {
			throw new BaseException("查询SysRole失败!", e);
		}
	}

	/**
	 * 查询SysRole
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList querySysRoleForPageList(Map params, int pageIndex, int pageSize)
			throws Exception {
		try {
			SysRoleDao dao = (SysRoleDao) getDao(SysRoleDao.class);
			return dao.queryForPageList(params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new BaseException("查询SysRole失败!", e);
		}
	}

	

	
}

