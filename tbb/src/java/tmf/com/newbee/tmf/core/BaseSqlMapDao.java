package com.newbee.tmf.core;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;
import com.ibatis.dao.client.template.SqlMapDaoTemplate;
import com.ibatis.dao.engine.transaction.sqlmap.SqlMapDaoTransaction;
import com.ibatis.sqlmap.client.SqlMapExecutor;

public class BaseSqlMapDao extends SqlMapDaoTemplate {

	/**
	 * 所有子类必须提供一个带DaoManager参数的构造器，用于
	 * 
	 * @param daoManager DaoManager
	 */
	public BaseSqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	protected Connection getConnection() {
		SqlMapDaoTransaction trans = (SqlMapDaoTransaction) daoManager
				.getTransaction(this);
		return trans.getConnection();
	}
    
	@SuppressWarnings("unchecked")
	public Map prepareQuery(Map param){
		Map<String,Object> newMap = new HashMap<String,Object>();
		if(param != null){
			newMap.putAll(param);
			Set set = newMap.keySet();
			for (Iterator iter = set.iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				Object obj = newMap.get(key);
				if (obj instanceof String) { // 如果值对象是字符串型
					String value = (String) obj;
					value = value.replace("'", "''"); // 替换单撇号'
					newMap.put(key, value);
				}
			}
		}
		return newMap;
	}
	
	/**
	 * 用于分页查询大量数据的一页数据,注意结果中将只包含该页的数据，不含全部数据
	 * 
	 * @param id String sqlmap的id
	 * @param parameterObject Object 条件参数
	 * @param pageIndex int 第几页
	 * @param pageSize int 每页记录数
	 * @throws DaoException
	 * @return PageList 结果列表
	 */
	public PageList queryForPageList(String id, Object parameterObject,
			int pageIndex, int pageSize) throws DaoException {

		String cnt_id = id + "_cnt";
		String list_id = id + "_list";

		return queryForPageList(list_id, parameterObject, cnt_id,
				parameterObject, pageIndex, pageSize);

	}

	/**
	 * 用于分页查询大量数据的一页数据,注意结果中将只包含该页的数据，不含全部数据
	 * 
	 * @param list_id String 查询实际数据的sql id
	 * @param list_parameterObject Object 查询实际数据的参数
	 * @param cnt_id String 查询数据记录总数的sql id
	 * @param cnt_parameterObject Object 查询数据记录总数的参数
	 * @param pageIndex int 第几页
	 * @param pageSize int 每页记录数
	 * @throws DaoException
	 * @return PageList 分页列表
	 */
	public PageList queryForPageList(String list_id,
			Object list_parameterObject, String cnt_id,
			Object cnt_parameterObject, int pageIndex, int pageSize)
			throws DaoException {
		if (list_parameterObject == null) {
			return PageList.emptyList(pageSize);
		}
		if (cnt_parameterObject == null) {
			cnt_parameterObject = list_parameterObject;
		}
		
		list_parameterObject = this.prepareQuery((Map)list_parameterObject);
		cnt_parameterObject = this.prepareQuery((Map)cnt_parameterObject);		

		SqlMapExecutor sql = this.getSqlMapExecutor();
		int count = 0;
		Integer cnt = null;
		try {
			cnt = (Integer) sql.queryForObject(cnt_id, cnt_parameterObject);
			if (cnt != null) {
				count = cnt.intValue();
			}

			if (count == 0) {
				return PageList.emptyList(pageSize);
			}
		} catch (Exception ex) {
			throw new DaoException("Failed to queryForPageList - id [" + cnt_id
					+ "], parameterObject [" + cnt_parameterObject
					+ "].  Cause: " + ex, ex);
		}

		if (pageSize <= 0) {
			pageSize = QueryParams.defaultPageSize;
		}

		int pageCount = count / pageSize;
		if (count % pageSize != 0) {
			pageCount++;
		}
		pageIndex = pageIndex < 1 ? 1 : pageIndex;
		pageIndex = pageIndex > pageCount ? pageCount : pageIndex;
		int skip = pageSize * (pageIndex - 1);
		int max = pageSize;
		try {
			java.util.List list = sql.queryForList(list_id,
					list_parameterObject, skip, max);
			return new PageList(list, pageCount, count, pageIndex, pageSize);
		} catch (Exception ex) {
			throw new DaoException("Failed to queryForPageList - id ["
					+ list_id + "], parameterObject [" + list_parameterObject
					+ "].  Cause: " + ex, ex);
		}
	}

}
