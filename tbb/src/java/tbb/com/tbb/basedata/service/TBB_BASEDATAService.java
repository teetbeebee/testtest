package com.tbb.basedata.service;

import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.newbee.tmf.core.PageList;
import com.tbb.basedata.dao.TBB_BASEDATADao;
import com.tbb.basedata.domain.TBB_BASEDATA;

/**
 * TBB_BASEDATA Service
 */
public class TBB_BASEDATAService extends BaseService {
	private static TBB_BASEDATAService instance = new TBB_BASEDATAService();

	private TBB_BASEDATAService() {
		// empty
		// 防止直接创建对象
	}

	public static TBB_BASEDATAService getInstance() {
		return instance;
	}

	/**
	 * 创建TBB_BASEDATA对象
	 * 
	 * @param TBB_BASEDATA
	 * @throws Exception
	 */
	public void createTBB_BASEDATA(TBB_BASEDATA TBB_BASEDATA) throws Exception {
		try {
			TBB_BASEDATADao dao = (TBB_BASEDATADao) getDao(TBB_BASEDATADao.class);
			dao.create(TBB_BASEDATA);
		} catch (Exception e) {
			throw new BaseException("创建新TBB_BASEDATA失败！", e);
		}
	}

	/**
	 * 根据TBB_BASEDATA主关键字获取TBB_BASEDATA信息
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public TBB_BASEDATA retrieveTBB_BASEDATA(java.lang.Integer domainPK) throws Exception {
		try {
			TBB_BASEDATADao dao = (TBB_BASEDATADao) getDao(TBB_BASEDATADao.class);
			return dao.retrieve(domainPK);
		} catch (Exception e) {
			throw new BaseException("获取TBB_BASEDATA信息失败！", e);
		}
	}

	/**
	 * 更新TBB_BASEDATA信息
	 * 
	 * @param TBB_BASEDATA
	 * @return
	 * @throws Exception
	 */
	public int updateTBB_BASEDATA(TBB_BASEDATA TBB_BASEDATA) throws Exception {
		int effectRows = 0;

		try {
			TBB_BASEDATADao dao = (TBB_BASEDATADao) getDao(TBB_BASEDATADao.class);
			effectRows = dao.update(TBB_BASEDATA);
		} catch (Exception e) {
			throw new BaseException("修改TBB_BASEDATA信息失败！", e);
		}

		return effectRows;
	}

	/**
	 * 根据TBB_BASEDATA主关键字删除TBB_BASEDATA
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public int deleteTBB_BASEDATA(java.lang.Integer domainPK) throws Exception {
		int effectRows = 0;

		try {
			TBB_BASEDATADao dao = (TBB_BASEDATADao) getDao(TBB_BASEDATADao.class);
			effectRows = dao.delete(domainPK);
		} catch (Exception e) {
			throw new BaseException("删除TBB_BASEDATA失败！", e);
		}

		return effectRows;
	}

	/**
	 * 查询TBB_BASEDATA
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List queryTBB_BASEDATAForList(Map params) throws Exception {
		try {
			TBB_BASEDATADao dao = (TBB_BASEDATADao) getDao(TBB_BASEDATADao.class);
			return dao.queryForList(params);
		} catch (Exception e) {
			throw new BaseException("查询TBB_BASEDATA失败!", e);
		}
	}

	/**
	 * 查询TBB_BASEDATA
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList queryTBB_BASEDATAForPageList(Map params, int pageIndex, int pageSize)
			throws Exception {
		try {
			TBB_BASEDATADao dao = (TBB_BASEDATADao) getDao(TBB_BASEDATADao.class);
			return dao.queryForPageList(params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new BaseException("查询TBB_BASEDATA失败!", e);
		}
	}

	

	
}



