package com.tbb.core.service;

/**
 *  
 * 直接执行SQL脚本
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tbb.core.dao.SqlDao;
import com.tbb.core.domain.Sql;
import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.newbee.tmf.core.PageList;

/**
 * 自定义sql语句操作Service
 */
public class SqlService extends BaseService {
	private static SqlService instance = new SqlService();

	private SqlService() {
		// empty
		// 防止直接创建对象
	}

	public static SqlService getInstance() {
		return instance;
	}

	/**
	 * 根据自定义sql语句新建对象
	 * 
	 * @param sql
	 * @throws Exception
	 */
	public void createSql(String sql) throws Exception {
		try {
			SqlDao dao = (SqlDao) getDao(SqlDao.class);
			dao.create(sql);
		} catch (Exception e) {
			throw new BaseException("创建对象失败！", e);
		}
	}

	/**
	 * 根据自定义sql语句获取一个对象
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public Map retrieveSql(String sql,String context_id) throws Exception {
		Map map = new HashMap();
		try {
			//System.out.println("service retrieve--------------");
			SqlDao dao = (SqlDao) getDao(SqlDao.class,context_id);
			map = dao.retrieve(sql);
		} catch (Exception e) {
			System.out.println(" service   获取对象信息失败！");
			throw new BaseException("获取对象信息失败！", e);
		}
		return map;
	}
	
	/**
	 * 根据自定义sql语句获取一个Clob大字段对象
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public String retrieveClobSql(String sql) throws Exception {
		Object obj = null;
		try {
			//System.out.println("service retrieve--------------");
			SqlDao dao = (SqlDao) getDao(SqlDao.class);
			obj = dao.retrieveClob(sql);
		} catch (Exception e) {
			System.out.println(" service   获取Clob对象信息失败！");
			throw new BaseException("获取Clob对象信息失败！", e);
		}
		if(obj == null){
			obj = new String("");
		}
		return obj.toString();
	}
	
	/**
	 * 根据自定义sql语句获取一个Blob大字段对象
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public Sql retrieveBlobSql(String sql) throws Exception {
		Sql obj = null;
		try {
			//System.out.println("service retrieve--------------");
			SqlDao dao = (SqlDao) getDao(SqlDao.class);
			obj = (Sql)dao.retrieveBlob(sql);
		} catch (Exception e) {
			System.out.println(" service   获取Blob对象信息失败！");
			throw new BaseException("获取Blob对象信息失败！", e);
		}
		return obj;
	}
	
	/**
	 * 根据自定义sql语句更新对象
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int updateSql(String sql) throws Exception {
		int effectRows = 0;

		try {
			SqlDao dao = (SqlDao) getDao(SqlDao.class);
			effectRows = dao.update(sql);
		} catch (Exception e) {
			throw new BaseException("修改对象信息失败！", e);
		}
                                                                                                                                          
		return effectRows;
	}
	
	/**
	 * 根据自定义sql语句更新对象
	 * 
	 * @param sql
	 * @return
	 * @throws Exception
	 */
	public int updateBlob(Sql sql) throws Exception {
		int effectRows = 0;

		try {
			SqlDao dao = (SqlDao) getDao(SqlDao.class);
			effectRows = dao.updateBlob(sql);
		} catch (Exception e) {
			throw new BaseException("修改对象blob信息失败！", e);
		}
                                                                                                                                          
		return effectRows;
	}


	/**
	 * 根据自定义sql语句删除对象
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public int deleteSql(String sql) throws Exception {
		int effectRows = 0;
		try {
			SqlDao dao = (SqlDao) getDao(SqlDao.class);
			effectRows = dao.delete(sql);
		} catch (Exception e) {
			throw new BaseException("删除对象失败!", e);
		}

		return effectRows;
	}

	/**
	 * 根据自定义sql语句查询一个对象列表
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List querySqlForList(String sql) throws Exception {
		try {
			SqlDao dao = (SqlDao) getDao(SqlDao.class);
			return dao.queryForList(sql);
		} catch (Exception e) {
			throw new BaseException("查询对象失败!", e);
		}
	}

	/**
	 * 根据自定义sql语句查询一个对象分页列表
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList querySqlForPageList(String sql, int pageIndex, int pageSize)
			throws Exception {
		try {
			SqlDao dao = (SqlDao) getDao(SqlDao.class);
			return dao.queryForPageList(sql, pageIndex, pageSize);
		} catch (Exception e) {
			throw new BaseException("查询对象失败!", e);
		}
	}
	
	/**
	 * 查询计算
	 * 
	 * @param sql SQL语句，如：select max(id) from tableName
	 * @return
	 * @throws Exception
	 */
	public int queryAccount(String sql) throws Exception{
		try {
			SqlDao dao = (SqlDao) getDao(SqlDao.class);
			return dao.queryAccount(sql);
		} catch (Exception e) {
			throw new BaseException("SQL语句计算失败!", e);
		}
	}

}