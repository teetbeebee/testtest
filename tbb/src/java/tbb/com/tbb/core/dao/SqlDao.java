package com.tbb.core.dao;


import java.util.List;
import java.util.Map;

import com.tbb.core.domain.Sql;
import com.newbee.tmf.core.PageList;
import com.ibatis.dao.client.DaoException;

/**
 * 通用模板Dao
 * 
 *  
 * @version 1.0
 * @date 2007-2-10
 */
public interface SqlDao {
	/**
	 * 新建领域对象
	 * 
	 * @param sqlString
	 *            sql语句
	 * @throws DaoException
	 */
	public void create(String sqlString) throws DaoException;

	/**
	 * 获取领域对象
	 * 
	 * @param sqlString
	 *            sql语句
	 * @return Map对象
	 * @throws DaoException
	 */
	public Map retrieve(String sqlString) throws DaoException;

	/**
	 * 获取Clob大字段
	 * 
	 * @param sqlString
	 *            sql语句
	 * @return Map对象
	 * @throws DaoException
	 */
	public Object retrieveClob(String sqlString) throws DaoException;

	/**
	 * 获取Blob大字段
	 * 
	 * @param sqlString
	 *            sql语句
	 * @return Map对象
	 * @throws DaoException
	 */
	public Object retrieveBlob(String sqlString) throws DaoException;

	/**
	 * 更新领域对象
	 * 
	 * @param sqlString
	 *            sql语句
	 * @return 成功更新的记录数
	 * @throws DaoException
	 */
	public int update(String sqlString) throws DaoException;

	/**
	 * 更新Blob大字段
	 * 
	 * @param sql类
	 * @return Map对象
	 * @throws DaoException
	 */
	public int updateBlob(Sql sql) throws DaoException;

	/**
	 * 删除领域对象
	 * 
	 * @param sqlString
	 *            sql语句
	 * @return 成功删除的记录数
	 * @throws DaoException
	 */
	public int delete(String sqlString) throws DaoException;

	/**
	 * 查询领域对象
	 * 
	 * @param sqlString
	 *            sql语句
	 * @return 包装为List的领域对象集合
	 * @throws DaoException
	 */
	public List queryForList(String sqlString) throws DaoException;

	/**
	 * 查询领域对象
	 * 
	 * @param sqlString
	 *            sql语句
	 * @param pageIndex
	 *            第几页
	 * @param pageSize
	 *            每页的最大记录数
	 * @return 包装为PageList的领域对象集合
	 * @throws DaoException
	 */
	public PageList queryForPageList(String sqlString, int pageIndex,
			int pageSize) throws DaoException;

	/**
	 * 通过查询取运算结果
	 * 
	 * @param sqlString
	 *            SQL语句，如：select max(id) from tableName
	 * @return
	 * @throws DaoException
	 */
	public int queryAccount(String sqlString) throws DaoException;
}