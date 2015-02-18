package com.tbb.basedata.service;

import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.newbee.tmf.core.PageList;
import com.tbb.basedata.dao.DictionaryDao;
import com.tbb.basedata.domain.Dictionary;

/**
 * Dictionary Service
 */
public class DictionaryService extends BaseService {
	private static DictionaryService instance = new DictionaryService();

	private DictionaryService() {
		// empty
		// 防止直接创建对象
	}

	public static DictionaryService getInstance() {
		return instance;
	}

	/**
	 * 创建Dictionary对象
	 * 
	 * @param dictionary
	 * @throws Exception
	 */
	public void createDictionary(Dictionary dictionary) throws Exception {
		try {
			DictionaryDao dao = (DictionaryDao) getDao(DictionaryDao.class);
			dao.create(dictionary);
		} catch (Exception e) {
			throw new BaseException("创建新Dictionary失败！", e);
		}
	}

	/**
	 * 根据Dictionary主关键字获取Dictionary信息
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public Dictionary retrieveDictionary(java.lang.Integer domainPK) throws Exception {
		try {
			DictionaryDao dao = (DictionaryDao) getDao(DictionaryDao.class);
			return dao.retrieve(domainPK);
		} catch (Exception e) {
			throw new BaseException("获取Dictionary信息失败！", e);
		}
	}

	/**
	 * 更新Dictionary信息
	 * 
	 * @param dictionary
	 * @return
	 * @throws Exception
	 */
	public int updateDictionary(Dictionary dictionary) throws Exception {
		int effectRows = 0;

		try {
			DictionaryDao dao = (DictionaryDao) getDao(DictionaryDao.class);
			effectRows = dao.update(dictionary);
		} catch (Exception e) {
			throw new BaseException("修改Dictionary信息失败！", e);
		}

		return effectRows;
	}

	/**
	 * 根据Dictionary主关键字删除Dictionary
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public int deleteDictionary(java.lang.Integer domainPK) throws Exception {
		int effectRows = 0;

		try {
			DictionaryDao dao = (DictionaryDao) getDao(DictionaryDao.class);
			effectRows = dao.delete(domainPK);
		} catch (Exception e) {
			throw new BaseException("删除Dictionary失败！", e);
		}

		return effectRows;
	}

	/**
	 * 查询Dictionary
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List queryDictionaryForList(Map params) throws Exception {
		try {
			DictionaryDao dao = (DictionaryDao) getDao(DictionaryDao.class);
			return dao.queryForList(params);
		} catch (Exception e) {
			throw new BaseException("查询Dictionary失败!", e);
		}
	}

	/**
	 * 查询Dictionary
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList queryDictionaryForPageList(Map params, int pageIndex, int pageSize)
			throws Exception {
		try {
			DictionaryDao dao = (DictionaryDao) getDao(DictionaryDao.class);
			return dao.queryForPageList(params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new BaseException("查询Dictionary失败!", e);
		}
	}

	

	
}

