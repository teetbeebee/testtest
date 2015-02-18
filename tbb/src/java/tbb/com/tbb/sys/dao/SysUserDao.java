package com.tbb.sys.dao;


import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.PageList;
import com.tbb.sys.domain.SysUser;
import com.ibatis.dao.client.DaoException;

/**
 * SysUser Dao
 */
public interface SysUserDao {
	/**
	 * 创建领域对象
	 * 
	 * @param domain
	 *            领域对象
	 * @throws DaoException
	 */
	public void create(SysUser domain) throws DaoException;

	/**
	 * 获取领域对象
	 * 
	 * @param domainPK
	 *            领域对象主关键字
	 * @return 领域对象
	 * @throws DaoException
	 */
	public SysUser retrieve(java.lang.String domainPK) throws DaoException;
	
	public SysUser retrieveBySid(java.lang.String session_id) throws DaoException;

	/**
	 * 更新领域对象
	 * 
	 * @param domain
	 *            领域对象
	 * @return 成功更新的记录数
	 * @throws DaoException
	 */
	public int update(SysUser domain) throws DaoException;

	/**
	 * 删除领域对象
	 * 
	 * @param domainPK
	 *            领域对象主关键字
	 * @return 成功删除的记录数
	 * @throws DaoException
	 */
	public int delete(java.lang.String domainPK) throws DaoException;

	/**
	 * 查询领域对象
	 * 
	 * @param params
	 *            查询条件
	 * @return 包装为List的领域对象集合
	 * @throws DaoException
	 */
	public List queryForList(Map params) throws DaoException;
	
	public List querySysUserByEmail(Map params) throws DaoException;

	/**
	 * 查询领域对象
	 * 
	 * @param params
	 *            查询条件
	 * @param pageIndex
	 *            第几页
	 * @param pageSize
	 *            每页的最大记录数
	 * @return 包装为PageList的领域对象集合
	 * @throws DaoException
	 */
	public PageList queryForPageList(Map params, int pageIndex, int pageSize)
			throws DaoException;
	

	

	
}

