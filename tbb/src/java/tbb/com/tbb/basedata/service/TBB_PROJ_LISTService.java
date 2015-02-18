package com.tbb.basedata.service;

import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.newbee.tmf.core.PageList;
import com.tbb.basedata.dao.TBB_PROJ_LISTDao;
import com.tbb.basedata.domain.TBB_PROJ_LIST;

/**
 * TBB_PROJ_LIST Service
 */
public class TBB_PROJ_LISTService extends BaseService {
	private static TBB_PROJ_LISTService instance = new TBB_PROJ_LISTService();

	private TBB_PROJ_LISTService() {
		// empty
		// 防止直接创建对象
	}

	public static TBB_PROJ_LISTService getInstance() {
		return instance;
	}

	/**
	 * 创建TBB_PROJ_LIST对象
	 * 
	 * @param TBB_PROJ_LIST
	 * @throws Exception
	 */
	public void createTBB_PROJ_LIST(TBB_PROJ_LIST TBB_PROJ_LIST) throws Exception {
		try {
			TBB_PROJ_LISTDao dao = (TBB_PROJ_LISTDao) getDao(TBB_PROJ_LISTDao.class);
			dao.create(TBB_PROJ_LIST);
		} catch (Exception e) {
			throw new BaseException("创建新TBB_PROJ_LIST失败！", e);
		}
	}

	/**
	 * 根据TBB_PROJ_LIST主关键字获取TBB_PROJ_LIST信息
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public TBB_PROJ_LIST retrieveTBB_PROJ_LIST(java.lang.Integer domainPK) throws Exception {
		try {
			TBB_PROJ_LISTDao dao = (TBB_PROJ_LISTDao) getDao(TBB_PROJ_LISTDao.class);
			return dao.retrieve(domainPK);
		} catch (Exception e) {
			throw new BaseException("获取TBB_PROJ_LIST信息失败！", e);
		}
	}

	/**
	 * 更新TBB_PROJ_LIST信息
	 * 
	 * @param TBB_PROJ_LIST
	 * @return
	 * @throws Exception
	 */
	public int updateTBB_PROJ_LIST(TBB_PROJ_LIST TBB_PROJ_LIST) throws Exception {
		int effectRows = 0;

		try {
			TBB_PROJ_LISTDao dao = (TBB_PROJ_LISTDao) getDao(TBB_PROJ_LISTDao.class);
			effectRows = dao.update(TBB_PROJ_LIST);
		} catch (Exception e) {
			throw new BaseException("修改TBB_PROJ_LIST信息失败！", e);
		}

		return effectRows;
	}

	/**
	 * 根据TBB_PROJ_LIST主关键字删除TBB_PROJ_LIST
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public int deleteTBB_PROJ_LIST(java.lang.Integer domainPK) throws Exception {
		int effectRows = 0;

		try {
			TBB_PROJ_LISTDao dao = (TBB_PROJ_LISTDao) getDao(TBB_PROJ_LISTDao.class);
			effectRows = dao.delete(domainPK);
		} catch (Exception e) {
			throw new BaseException("删除TBB_PROJ_LIST失败！", e);
		}

		return effectRows;
	}

	/**
	 * 查询TBB_PROJ_LIST
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List queryTBB_PROJ_LISTForList(Map params) throws Exception {
		try {
			TBB_PROJ_LISTDao dao = (TBB_PROJ_LISTDao) getDao(TBB_PROJ_LISTDao.class);
			return dao.queryForList(params);
		} catch (Exception e) {
			throw new BaseException("查询TBB_PROJ_LIST失败!", e);
		}
	}

	/**
	 * 查询TBB_PROJ_LIST
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList queryTBB_PROJ_LISTForPageList(Map params, int pageIndex, int pageSize)
			throws Exception {
		try {
			TBB_PROJ_LISTDao dao = (TBB_PROJ_LISTDao) getDao(TBB_PROJ_LISTDao.class);
			return dao.queryForPageList(params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new BaseException("查询TBB_PROJ_LIST失败!", e);
		}
	}

	

	
}



