/**
 * <p>Company: DM</p>
 *  
 * @version 2.0 2006-09-04
 */

package com.tbb.common.dao;

import com.ibatis.dao.client.DaoException;

public interface CommonDao {

	/**
	 * 取序列当前的值
	 * @param sequence 序列名称
	 * @return 序列当前的值
	 * @throws DaoException
	 */
	public Integer getCurrentSequence(String sequenceName) throws DaoException;

	/**
	 * 取序列的下一个值
	 * @param sequenceName 序列名称
	 * @return 序列的下一个值
	 * @throws DaoException
	 */
	public Integer getNextSequence(String sequenceName) throws DaoException;
}
