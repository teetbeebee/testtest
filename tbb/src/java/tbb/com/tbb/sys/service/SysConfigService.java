package com.tbb.sys.service;

import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.newbee.tmf.core.PageList;
import com.tbb.sys.dao.SysConfigDao;
import com.tbb.sys.domain.SysConfig;

/**
 * SysConfig Service
 */
public class SysConfigService extends BaseService {
	private static SysConfigService instance = new SysConfigService();

	private SysConfigService() {
		// empty
		// 防止直接创建对象
	}

	public static SysConfigService getInstance() {
		return instance;
	}

	/**
	 * 创建SysConfig对象
	 * 
	 * @param sysConfig
	 * @throws Exception
	 */
	public void createSysConfig(SysConfig sysConfig) throws Exception {
		try {
			SysConfigDao dao = (SysConfigDao) getDao(SysConfigDao.class);
			dao.create(sysConfig);
		} catch (Exception e) {
			throw new BaseException("创建新SysConfig失败！", e);
		}
	}

	/**
	 * 根据SysConfig主关键字获取SysConfig信息
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public SysConfig retrieveSysConfig(java.util.HashMap domainPK)
			throws Exception {
		try {
			SysConfigDao dao = (SysConfigDao) getDao(SysConfigDao.class);
			return dao.retrieve(domainPK);
		} catch (Exception e) {
			throw new BaseException("获取SysConfig信息失败！", e);
		}
	}

	/**
	 * 更新SysConfig信息
	 * 
	 * @param sysConfig
	 * @return
	 * @throws Exception
	 */
	public int updateSysConfig(SysConfig sysConfig) throws Exception {
		int effectRows = 0;

		try {
			SysConfigDao dao = (SysConfigDao) getDao(SysConfigDao.class);
			effectRows = dao.update(sysConfig);
		} catch (Exception e) {
			throw new BaseException("修改SysConfig信息失败！", e);
		}

		return effectRows;
	}

	/**
	 * 根据SysConfig主关键字删除SysConfig
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public int deleteSysConfig(java.util.HashMap domainPK) throws Exception {
		int effectRows = 0;

		try {
			SysConfigDao dao = (SysConfigDao) getDao(SysConfigDao.class);
			effectRows = dao.delete(domainPK);
		} catch (Exception e) {
			throw new BaseException("删除SysConfig失败！", e);
		}

		return effectRows;
	}

	/**
	 * 查询SysConfig
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List querySysConfigForList(Map params) throws Exception {
		try {
			SysConfigDao dao = (SysConfigDao) getDao(SysConfigDao.class);
			return dao.queryForList(params);
		} catch (Exception e) {
			throw new BaseException("查询SysConfig失败!", e);
		}
	}

	/**
	 * 查询SysConfig
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList querySysConfigForPageList(Map params, int pageIndex,
			int pageSize) throws Exception {
		try {
			SysConfigDao dao = (SysConfigDao) getDao(SysConfigDao.class);
			return dao.queryForPageList(params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new BaseException("查询SysConfig失败!", e);
		}
	}

}
