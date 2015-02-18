package com.tbb.sys.service;

import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.newbee.tmf.core.PageList;
import com.tbb.sys.dao.SysPermitDao;
import com.tbb.sys.domain.SysPermit;

/**
 * SysPermit Service
 */
public class SysPermitService extends BaseService {
	private static SysPermitService instance = new SysPermitService();

	private SysPermitService() {
		// empty
		// 防止直接创建对象
	}

	public static SysPermitService getInstance() {
		return instance;
	}

	/**
	 * 创建SysPermit对象
	 * 
	 * @param sysPermit
	 * @throws Exception
	 */
	public void createSysPermit(SysPermit sysPermit) throws Exception {
		try {
			SysPermitDao dao = (SysPermitDao) getDao(SysPermitDao.class);
			dao.create(sysPermit);
		} catch (Exception e) {
			throw new BaseException("创建新SysPermit失败！", e);
		}
	}

	/**
	 * 根据SysPermit主关键字获取SysPermit信息
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public SysPermit retrieveSysPermit(java.lang.String domainPK) throws Exception {
		try {
			SysPermitDao dao = (SysPermitDao) getDao(SysPermitDao.class);
			return dao.retrieve(domainPK);
		} catch (Exception e) {
			throw new BaseException("获取SysPermit信息失败！", e);
		}
	}

	/**
	 * 更新SysPermit信息
	 * 
	 * @param sysPermit
	 * @return
	 * @throws Exception
	 */
	public int updateSysPermit(SysPermit sysPermit) throws Exception {
		int effectRows = 0;

		try {
			SysPermitDao dao = (SysPermitDao) getDao(SysPermitDao.class);
			effectRows = dao.update(sysPermit);
		} catch (Exception e) {
			throw new BaseException("修改SysPermit信息失败！", e);
		}

		return effectRows;
	}

	/**
	 * 根据SysPermit主关键字删除SysPermit
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public int deleteSysPermit(java.lang.String domainPK) throws Exception {
		int effectRows = 0;

		try {
			SysPermitDao dao = (SysPermitDao) getDao(SysPermitDao.class);
			effectRows = dao.delete(domainPK);
		} catch (Exception e) {
			throw new BaseException("删除SysPermit失败！", e);
		}

		return effectRows;
	}

	/**
	 * 查询SysPermit
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List querySysPermitForList(Map params) throws Exception {
		try {
			SysPermitDao dao = (SysPermitDao) getDao(SysPermitDao.class);
			return dao.queryForList(params);
		} catch (Exception e) {
			throw new BaseException("查询SysPermit失败!", e);
		}
	}

	/**
	 * 查询SysPermit
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList querySysPermitForPageList(Map params, int pageIndex, int pageSize)
			throws Exception {
		try {
			SysPermitDao dao = (SysPermitDao) getDao(SysPermitDao.class);
			return dao.queryForPageList(params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new BaseException("查询SysPermit失败!", e);
		}
	}

	

	
}

