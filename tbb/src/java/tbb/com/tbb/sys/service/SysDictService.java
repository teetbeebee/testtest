package com.tbb.sys.service;

import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.newbee.tmf.core.PageList;
import com.tbb.sys.dao.SysDictDao;
import com.tbb.sys.domain.SysDict;

/**
 * SysDict Service
 */
public class SysDictService extends BaseService {
	private static SysDictService instance = new SysDictService();

	private SysDictService() {
		// empty
		// 防止直接创建对象
	}

	public static SysDictService getInstance() {
		return instance;
	}

	/**
	 * 创建SysDict对象
	 * 
	 * @param sysDict
	 * @throws Exception
	 */
	public void createSysDict(SysDict sysDict) throws Exception {
		try {
			SysDictDao dao = (SysDictDao) getDao(SysDictDao.class);
			dao.create(sysDict);
		} catch (Exception e) {
			throw new BaseException("创建新SysDict失败！", e);
		}
	}

	/**
	 * 根据SysDict主关键字获取SysDict信息
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public SysDict retrieveSysDict(java.util.HashMap domainPK) throws Exception {
		try {
			SysDictDao dao = (SysDictDao) getDao(SysDictDao.class);
			return dao.retrieve(domainPK);
		} catch (Exception e) {
			throw new BaseException("获取SysDict信息失败！", e);
		}
	}

	/**
	 * 更新SysDict信息
	 * 
	 * @param sysDict
	 * @return
	 * @throws Exception
	 */
	public int updateSysDict(SysDict sysDict) throws Exception {
		int effectRows = 0;

		try {
			SysDictDao dao = (SysDictDao) getDao(SysDictDao.class);
			effectRows = dao.update(sysDict);
		} catch (Exception e) {
			throw new BaseException("修改SysDict信息失败！", e);
		}

		return effectRows;
	}

	/**
	 * 根据SysDict主关键字删除SysDict
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public int deleteSysDict(java.util.HashMap domainPK) throws Exception {
		int effectRows = 0;

		try {
			SysDictDao dao = (SysDictDao) getDao(SysDictDao.class);
			effectRows = dao.delete(domainPK);
		} catch (Exception e) {
			throw new BaseException("删除SysDict失败！", e);
		}

		return effectRows;
	}

	/**
	 * 查询SysDict
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List querySysDictForList(Map params) throws Exception {
		try {
			SysDictDao dao = (SysDictDao) getDao(SysDictDao.class);
			return dao.queryForList(params);
		} catch (Exception e) {
			throw new BaseException("查询SysDict失败!", e);
		}
	}

	/**
	 * 查询SysDict
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList querySysDictForPageList(Map params, int pageIndex, int pageSize)
			throws Exception {
		try {
			SysDictDao dao = (SysDictDao) getDao(SysDictDao.class);
			return dao.queryForPageList(params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new BaseException("查询SysDict失败!", e);
		}
	}

	

	
}

