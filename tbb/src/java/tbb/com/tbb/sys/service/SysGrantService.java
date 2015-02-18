package com.tbb.sys.service;

import java.util.ArrayList;
import java.util.List;

import com.newbee.tmf.core.BaseException;
import com.newbee.tmf.core.BaseService;
import com.tbb.sys.dao.SysGrantDao;
import com.tbb.sys.domain.SysPermit;

/**
 * 角色ActionService
 */
public class SysGrantService extends BaseService {
	private static SysGrantService instance = new SysGrantService();

	private SysGrantService() {
		// empty
		// 防止直接创建对象
	}

	public static SysGrantService getInstance() {
		return instance;
	}

	/**
	 * 用户添加角色
	 * @param user_id
	 * @param role_id
	 * @throws Exception
	 */
	public void sysUserAddSysRole(String user_id,String role_id) throws Exception {
		try {
			SysGrantDao dao = (SysGrantDao) getDao(SysGrantDao.class);
			dao.sysUserAddSysRole(user_id, role_id);
		} catch (Exception e) {
			throw new BaseException("用户添加角色失败！", e);
		}
	}
	
	/**
	 * 用户删除角色
	 * @param user_id
	 * @param role_id
	 * @throws Exception
	 */
	public void sysUserDeleteSysRole(String user_id,String role_id) throws Exception {
		try {
			SysGrantDao dao = (SysGrantDao) getDao(SysGrantDao.class);
			dao.sysUserDeleteSysRole(user_id, role_id);
		} catch (Exception e) {
			throw new BaseException("用户删除角色失败！", e);
		}
	}
	
	/**
	 * 用户删除所有角色
	 * @param user_id
	 * @param role_id
	 * @throws Exception
	 */
	public void sysUserDeleteAllSysRoles(String user_id) throws Exception {
		try {
			SysGrantDao dao = (SysGrantDao) getDao(SysGrantDao.class);
			dao.sysUserDeleteAllSysRoles(user_id);
		} catch (Exception e) {
			throw new BaseException("用户删除所有角色失败！", e);
		}
	}
	
	/**
	 * 角色删除所有用户
	 * @param role_id
	 * @param role_id
	 * @throws Exception
	 */
	public void sysRoleDeleteAllSysUsers(String role_id) throws Exception {
		try {
			SysGrantDao dao = (SysGrantDao) getDao(SysGrantDao.class);
			dao.sysRoleDeleteAllSysUsers(role_id);
		} catch (Exception e) {
			throw new BaseException("角色删除所有用户失败！", e);
		}
	}

	
	/**
	 * 用户拥有的角色
	 * @param user_id
	 * @return
	 * @throws Exception
	 */
	public List sysUserHaveSysRoles(String user_id) throws Exception {
		List list = new ArrayList();

		try {
			SysGrantDao dao = (SysGrantDao) getDao(SysGrantDao.class);
			list = dao.sysUserHaveSysRoles(user_id);
		} catch (Exception e) {
			throw new BaseException("获取用户拥有的角色失败！", e);
		}

		return list;
	}

	/**
	 *  用户没有拥有的角色
	 * @param user_id
	 * @return
	 * @throws Exception
	 */
	public List sysUserHaveNotSysRoles(String user_id) throws Exception {
		List list = new ArrayList();

		try {
			SysGrantDao dao = (SysGrantDao) getDao(SysGrantDao.class);
			list = dao.sysUserHaveNotSysRoles(user_id);
		} catch (Exception e) {
			throw new BaseException("获取用户没有拥有的角色失败！", e);
		}

		return list;
	}


	/**
	 * 角色拥有的用户
	 * @param user_id
	 * @return
	 * @throws Exception
	 */
	public List sysRoleHaveSysUsers(String role_id) throws Exception {
		List list = new ArrayList();

		try {
			SysGrantDao dao = (SysGrantDao) getDao(SysGrantDao.class);
			list = dao.sysRoleHaveSysUsers(role_id);
		} catch (Exception e) {
			throw new BaseException("获取角色拥有的用户失败！", e);
		}

		return list;
	}
	
	/**
	 * 角色没有拥有的用户
	 * @param user_id
	 * @return
	 * @throws Exception
	 */
	public List sysRoleHaveNotSysUsers(String role_id) throws Exception {
		List list = new ArrayList();

		try {
			SysGrantDao dao = (SysGrantDao) getDao(SysGrantDao.class);
			list = dao.sysRoleHaveNotSysUsers(role_id);
		} catch (Exception e) {
			throw new BaseException("获取角色没有拥有的用户失败！", e);
		}

		return list;
	}
	
	
	/**
	 * 为用户添加一批角色
	 * 
	 * @param user_id 用户id
	 * @param role_ids 角色ids
	 * @throws DaoException
	 */
	public void sysUserAddSysRoles(String user_id, String[] role_ids) throws Exception {
			
		try {
			this.startTransaction();
			this.sysUserDeleteAllSysRoles(user_id);
			if (role_ids != null) {
				for (int i = 0; i < role_ids.length; i++) {
					String role_id = role_ids[i];
					this.sysUserAddSysRole(user_id, role_id);
				}
			}
			this.commitTransaction();
		} catch (Exception e) {
			throw new BaseException("为用户添加角色失败！", e);
		} finally {
			this.endTransaction();
		}
	}
	
	/**
	 * 角色添加Action
	 * @param role_id
	 * @param permit_id
	 * @throws Exception
	 */
	public void sysRoleAddSysPermit(String role_id,String permit_id) throws Exception {
		try {
			SysGrantDao dao = (SysGrantDao) getDao(SysGrantDao.class);
			dao.sysRoleAddSysPermit(role_id, permit_id);
		} catch (Exception e) {
			throw new BaseException("角色添加Action失败！", e);
		}
	}
	
	/**
	 * 角色删除Action
	 * @param role_id
	 * @param permit_id
	 * @throws Exception
	 */
	public void sysRoleDeleteSysPermit(String role_id,String permit_id) throws Exception {
		try {
			SysGrantDao dao = (SysGrantDao) getDao(SysGrantDao.class);
			dao.sysRoleDeleteSysPermit(role_id, permit_id);
		} catch (Exception e) {
			throw new BaseException("角色删除Action失败！", e);
		}
	}
	
	/**
	 * 角色删除所有Action
	 * @param role_id
	 * @param permit_id
	 * @throws Exception
	 */
	public void sysRoleDeleteAllSysPermits(String role_id) throws Exception {
		try {
			SysGrantDao dao = (SysGrantDao) getDao(SysGrantDao.class);
			dao.sysRoleDeleteAllSysPermits(role_id);
		} catch (Exception e) {
			throw new BaseException("角色删除所有Action失败！", e);
		}
	}
	
	/**
	 * Action删除所有角色
	 * @param permit_id
	 * @param permit_id
	 * @throws Exception
	 */
	public void sysPermitDeleteAllSysRoles(String permit_id) throws Exception {
		try {
			SysGrantDao dao = (SysGrantDao) getDao(SysGrantDao.class);
			dao.sysPermitDeleteAllSysRoles(permit_id);
		} catch (Exception e) {
			throw new BaseException("Action删除所有角色失败！", e);
		}
	}

	
	/**
	 * 角色拥有的Action
	 * @param role_id
	 * @return
	 * @throws Exception
	 */
	public List sysRoleHaveSysPermits(String role_id) throws Exception {
		List list = new ArrayList();

		try {
			SysGrantDao dao = (SysGrantDao) getDao(SysGrantDao.class);
			list = dao.sysRoleHaveSysPermits(role_id);
		} catch (Exception e) {
			throw new BaseException("获取角色拥有的Action失败！", e);
		}

		return list;
	}

	/**
	 *  角色没有拥有的Action
	 * @param role_id
	 * @return
	 * @throws Exception
	 */
	public List sysRoleHaveNotSysPermits(String role_id) throws Exception {
		List list = new ArrayList();

		try {
			SysGrantDao dao = (SysGrantDao) getDao(SysGrantDao.class);
			list = dao.sysRoleHaveNotSysPermits(role_id);
		} catch (Exception e) {
			throw new BaseException("获取角色没有拥有的Action失败！", e);
		}

		return list;
	}


	/**
	 * Action拥有的角色
	 * @param role_id
	 * @return
	 * @throws Exception
	 */
	public List sysPermitHaveSysRoles(String permit_id) throws Exception {
		List list = new ArrayList();

		try {
			SysGrantDao dao = (SysGrantDao) getDao(SysGrantDao.class);
			list = dao.sysPermitHaveSysRoles(permit_id);
		} catch (Exception e) {
			throw new BaseException("获取Action拥有的角色失败！", e);
		}

		return list;
	}
	
	/**
	 * Action没有拥有的角色
	 * @param role_id
	 * @return
	 * @throws Exception
	 */
	public List sysPermitHaveNotSysRoles(String permit_id) throws Exception {
		List list = new ArrayList();

		try {
			SysGrantDao dao = (SysGrantDao) getDao(SysGrantDao.class);
			list = dao.sysPermitHaveNotSysRoles(permit_id);
		} catch (Exception e) {
			throw new BaseException("获取Action没有拥有的角色失败！", e);
		}

		return list;
	}
	
	
	/**
	 * 为角色添加一批Action
	 * 
	 * @param role_id 角色id
	 * @param permit_ids Actionids
	 * @throws DaoException
	 */
	public void sysRoleAddSysPermits(String role_id, String[] permit_ids) throws Exception {
			
		try {
			this.startTransaction();
			this.sysRoleDeleteAllSysPermits(role_id);
			if (permit_ids != null) {
				for (int i = 0; i < permit_ids.length; i++) {
					String permit_id = permit_ids[i];
					this.sysRoleAddSysPermit(role_id, permit_id);
				}
			}
			this.commitTransaction();
		} catch (Exception e) {
			throw new BaseException("为角色添加Action失败！", e);
		} finally {
			this.endTransaction();
		}
	}
	/**
	 * 根据用户权限检测
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public SysPermit sysUserSysPermitCheck(String user_id,String permit_id) throws Exception {
		try {
			SysGrantDao dao = (SysGrantDao) getDao(SysGrantDao.class);
			return dao.sysUserSysPermitCheck(user_id, permit_id);
		} catch (Exception e) {
			throw new BaseException("检测用户权限信息失败！", e);
		}
	}

}
