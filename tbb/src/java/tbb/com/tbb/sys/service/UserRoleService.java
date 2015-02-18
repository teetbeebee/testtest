package com.tbb.sys.service;

import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.newbee.tmf.core.PageList;
import com.tbb.sys.dao.UserRoleDao;
import com.tbb.sys.domain.UserRole;

/**
 * UserRole Service
 */
public class UserRoleService extends BaseService {
	private static UserRoleService instance = new UserRoleService();

	private UserRoleService() {
		// empty
		// 防止直接创建对象
	}

	public static UserRoleService getInstance() {
		return instance;
	}

	/**
	 * 创建UserRole对象
	 * 
	 * @param userRole
	 * @throws Exception
	 */
	public void createUserRole(UserRole userRole) throws Exception {
		try {
			UserRoleDao dao = (UserRoleDao) getDao(UserRoleDao.class);
			dao.create(userRole);
		} catch (Exception e) {
			throw new BaseException("创建新UserRole失败！", e);
		}
	}

	/**
	 * 根据UserRole主关键字获取UserRole信息
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public UserRole retrieveUserRole(java.util.HashMap domainPK) throws Exception {
		try {
			UserRoleDao dao = (UserRoleDao) getDao(UserRoleDao.class);
			return dao.retrieve(domainPK);
		} catch (Exception e) {
			throw new BaseException("获取UserRole信息失败！", e);
		}
	}

	/**
	 * 更新UserRole信息
	 * 
	 * @param userRole
	 * @return
	 * @throws Exception
	 */
	public int updateUserRole(UserRole userRole) throws Exception {
		int effectRows = 0;

		try {
			UserRoleDao dao = (UserRoleDao) getDao(UserRoleDao.class);
			effectRows = dao.update(userRole);
		} catch (Exception e) {
			throw new BaseException("修改UserRole信息失败！", e);
		}

		return effectRows;
	}

	/**
	 * 根据UserRole主关键字删除UserRole
	 * 
	 * @param domainPK
	 * @return
	 * @throws Exception
	 */
	public int deleteUserRole(java.util.HashMap domainPK) throws Exception {
		int effectRows = 0;

		try {
			UserRoleDao dao = (UserRoleDao) getDao(UserRoleDao.class);
			effectRows = dao.delete(domainPK);
		} catch (Exception e) {
			throw new BaseException("删除UserRole失败！", e);
		}

		return effectRows;
	}

	/**
	 * 查询UserRole
	 * 
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public List queryUserRoleForList(Map params) throws Exception {
		try {
			UserRoleDao dao = (UserRoleDao) getDao(UserRoleDao.class);
			return dao.queryForList(params);
		} catch (Exception e) {
			throw new BaseException("查询UserRole失败!", e);
		}
	}

	/**
	 * 查询UserRole
	 * 
	 * @param params
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public PageList queryUserRoleForPageList(Map params, int pageIndex, int pageSize)
			throws Exception {
		try {
			UserRoleDao dao = (UserRoleDao) getDao(UserRoleDao.class);
			return dao.queryForPageList(params, pageIndex, pageSize);
		} catch (Exception e) {
			throw new BaseException("查询UserRole失败!", e);
		}
	}

	

	
}

