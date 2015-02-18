package com.tbb.sys.service;

import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.newbee.tmf.core.PageList;
import com.tbb.sys.dao.SysRolePermitDao;
import com.tbb.sys.domain.SysRolePermit;

/**
 * SysRolePermit Service
 */
public class SysRolePermitService extends BaseService {
	private static SysRolePermitService instance = new SysRolePermitService();

	private SysRolePermitService() {
		// empty
		// 防止直接创建对象
	}

	public static SysRolePermitService getInstance() {
		return instance;
	}

	/**
	 * 创建SysRolePermit对象
	 * 
	 * @param sysRolePermit
	 * @throws Exception
	 */
	public void createSysRolePermit(SysRolePermit sysRolePermit) throws Exception {
		try {
			SysRolePermitDao dao = (SysRolePermitDao) getDao(SysRolePermitDao.class);
			dao.create(sysRolePermit);
		} catch (Exception e) {
			throw new BaseException("创建新SysRolePermit失败！", e);
		}
	}

	/**
	 * 根据SysRolePermit主关键字获取SysRolePermit信息
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public SysRolePermit retrieveSysRolePermit(java.util.HashMap domainPK) throws Exception {
		try {
			SysRolePermitDao dao = (SysRolePermitDao) getDao(SysRolePermitDao.class);
			return dao.retrieve(domainPK);
		} catch (Exception e) {
			throw new BaseException("获取SysRolePermit信息失败！", e);
		}
	}

	/**
	 * 更新SysRolePermit信息
	 * 
	 * @param sysRolePermit
	 * @return
	 * @throws Exception
	 */
	public int updateSysRolePermit(SysRolePermit sysRolePermit) throws Exception {
		int effectRows = 0;

		try {
			SysRolePermitDao dao = (SysRolePermitDao) getDao(SysRolePermitDao.class);
			effectRows = dao.update(sysRolePermit);
		} catch (Exception e) {
			throw new BaseException("修改SysRolePermit信息失败！", e);
		}

		return effectRows;
	}

	/**
	 * 根据SysRolePermit主关键字删除SysRolePermit
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public int deleteSysRolePermit(java.util.HashMap domainPK) throws Exception {
		int effectRows = 0;

		try {
			SysRolePermitDao dao = (SysRolePermitDao) getDao(SysRolePermitDao.class);
			effectRows = dao.delete(domainPK);
		} catch (Exception e) {
			throw new BaseException("删除SysRolePermit失败！", e);
		}

		return effectRows;
	}

	/**
	 * 查询SysRolePermit
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List querySysRolePermitForList(Map params) throws Exception {
		try {
			SysRolePermitDao dao = (SysRolePermitDao) getDao(SysRolePermitDao.class);
			return dao.queryForList(params);
		} catch (Exception e) {
			throw new BaseException("查询SysRolePermit失败!", e);
		}
	}

	/**
	 * 查询SysRolePermit
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList querySysRolePermitForPageList(Map params, int pageIndex, int pageSize)
			throws Exception {
		try {
			SysRolePermitDao dao = (SysRolePermitDao) getDao(SysRolePermitDao.class);
			return dao.queryForPageList(params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new BaseException("查询SysRolePermit失败!", e);
		}
	}

	

	
}

