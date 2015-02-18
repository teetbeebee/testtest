package com.tbb.sys.dao;

import java.util.List;

import com.tbb.sys.domain.SysPermit;
import com.ibatis.dao.client.DaoException;

public interface SysGrantDao {
	
	/**
	 * 用户添加角色
	 * @param user_id 用户id
	 * @param role_id 角色id
	 * @throws DaoException
	 */
	public void sysUserAddSysRole(String user_id, String role_id) throws DaoException;
	
	/**
	 * 用户删除角色
	 * @param user_id 用户id
	 * @param role_id 角色id
	 * @throws DaoException
	 */
	public void sysUserDeleteSysRole(String user_id, String role_id) throws DaoException;
	
	/**
	 * 用户删除所有角色
	 * @param user_id 用户id
	 * @throws DaoException
	 */
	public void sysUserDeleteAllSysRoles(String user_id) throws DaoException;
	
	/**
	 * 角色删除所有用户
	 * @param role_id 角色id
	 * @throws DaoException
	 */
	public void sysRoleDeleteAllSysUsers(String role_id) throws DaoException;
	
	/**
	 * 用户拥有的角色
	 * @param user_id 用户id
	 * @throws DaoException
	 */
	public List sysUserHaveSysRoles(String user_id) throws DaoException;
	
	/**
	 * 用户没有拥有的角色
	 * @param user_id 用户id
	 * @throws DaoException
	 */
	public List sysUserHaveNotSysRoles(String user_id) throws DaoException;
	
	/**
	 * 角色拥有的用户
	 * @param role_id 角色id
	 * @throws DaoException
	 */
	public List sysRoleHaveSysUsers(String role_id) throws DaoException;
	
	/**
	 * 角色没有拥有的用户
	 * @param role_id 角色id
	 * @throws DaoException
	 */
	public List sysRoleHaveNotSysUsers(String role_id) throws DaoException;
	
	public SysPermit sysUserSysPermitCheck(String user_id,String permit_id) throws DaoException;
	/**
	 * 角色添加Action
	 * @param role_id 角色id
	 * @param permit_id actionId
	 * @throws DaoException
	 */
	public void sysRoleAddSysPermit(String role_id, String permit_id) throws DaoException;
	
	/**
	 * 角色删除Action
	 * @param role_id 角色id
	 * @param permit_id actionId
	 * @throws DaoException
	 */
	public void sysRoleDeleteSysPermit(String role_id, String permit_id) throws DaoException;
	
	/**
	 * 角色删除所有Action
	 * @param role_id 角色id
	 * @throws DaoException
	 */
	public void sysRoleDeleteAllSysPermits(String role_id) throws DaoException;
	
	/**
	 * Action删除所有角色
	 * @param permit_id actionId
	 * @throws DaoException
	 */
	public void sysPermitDeleteAllSysRoles(String permit_id) throws DaoException;
	
	/**
	 * 角色拥有的Action
	 * @param role_id 角色id
	 * @throws DaoException
	 */
	public List sysRoleHaveSysPermits(String role_id) throws DaoException;
	
	/**
	 * 角色没有拥有的Action
	 * @param role_id 角色id
	 * @throws DaoException
	 */
	public List sysRoleHaveNotSysPermits(String role_id) throws DaoException;
	
	/**
	 * Action拥有的角色
	 * @param permit_id actionId
	 * @throws DaoException
	 */
	public List sysPermitHaveSysRoles(String permit_id) throws DaoException;
	
	/**
	 * Action没有拥有的角色
	 * @param permit_id actionId
	 * @throws DaoException
	 */
	public List sysPermitHaveNotSysRoles(String permit_id) throws DaoException;
}
