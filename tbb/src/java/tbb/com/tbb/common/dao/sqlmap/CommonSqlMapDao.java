/**
 * <p>Company: DM</p>
 *  
 * @version 2.0 2006-09-04
 */

package com.tbb.common.dao.sqlmap;

import com.newbee.tmf.core.BaseSqlMapDao;
import com.tbb.common.dao.CommonDao;
import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;

public class CommonSqlMapDao extends BaseSqlMapDao implements CommonDao {

	public CommonSqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}

	public Integer getCurrentSequence(String sequenceName) throws DaoException {
		Integer seq = null;
		try {
			seq = (Integer) this.getSqlMapExecutor().queryForObject(
					"Common.get_current_sequence", sequenceName);
		} catch (Exception e) {
			throw new DaoException("Get current sequence value error. cause:", e);
		}
		return seq;
	}

	public Integer getNextSequence(String sequenceName) throws DaoException {
		Integer seq = null;
		try {
			seq = (Integer) this.getSqlMapExecutor().queryForObject(
					"Common.get_next_sequence", sequenceName);
		} catch (Exception e) {
			throw new DaoException("Get next sequence value error. cause:", e);
		}
		return seq;
	}
}
