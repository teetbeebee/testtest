package com.tbb.sys.dao.sqlmap;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.newbee.tmf.core.BaseSqlMapDao;
import com.tbb.sys.dao.SysGrantDao;
import com.tbb.sys.domain.SysPermit;
import com.ibatis.dao.client.DaoException;
import com.ibatis.dao.client.DaoManager;

public class SysGrantSqlMapDao extends BaseSqlMapDao implements SysGrantDao {

	public SysGrantSqlMapDao(DaoManager daoManager) {
		super(daoManager);
	}
	
	public List sysRoleHaveNotSysUsers(String role_id) throws DaoException {
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("SysGrant.sys_role_have_not_sys_users", role_id);
		} catch (SQLException e) {
			throw new DaoException("SysGrant.sys_role_have_not_sys_users error. cause:" + e, e);
		}
		return list;
	}

	public List sysRoleHaveSysUsers(String role_id) throws DaoException {
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("SysGrant.sys_role_have_sys_users", role_id);
		} catch (SQLException e) {
			throw new DaoException("SysGrant.sys_role_have_sys_users error. cause:" + e, e);
		}
		return list;
	}

	public void sysUserAddSysRole(String user_id, String role_id) throws DaoException {
		Map<String,String> param = new HashMap<String,String>();
		param.put("user_id", user_id);
		param.put("role_id", role_id);
		try {
			getSqlMapExecutor().insert("SysGrant.sys_user_add_sys_role", param);
		} catch (SQLException e) {
			throw new DaoException("SysGrant.sys_user_add_sys_role error. cause:" + e, e);
		}
	}

	public void sysUserDeleteSysRole(String user_id, String role_id) throws DaoException {
		Map<String,String> param = new HashMap<String,String>();
		param.put("user_id", user_id);
		param.put("role_id", role_id);
		try {
			getSqlMapExecutor().insert("SysGrant.sys_user_delete_sys_role", param);
		} catch (SQLException e) {
			throw new DaoException("SysGrant.sys_user_delete_sys_role error. cause:" + e, e);
		}
	}

	public List sysUserHaveNotSysRoles(String user_id) throws DaoException {
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("SysGrant.sys_user_have_not_sys_roles", user_id);
		} catch (SQLException e) {
			throw new DaoException("SysGrant.sys_user_have_not_sys_roles error. cause:" + e, e);
		}
		return list;
	}

	public List sysUserHaveSysRoles(String user_id) throws DaoException {
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("SysGrant.sys_user_have_sys_roles", user_id);
		} catch (SQLException e) {
			throw new DaoException("SysGrant.sys_user_have_sys_roles error. cause:" + e, e);
		}
		return list;
	}

	public void sysRoleDeleteAllSysUsers(String role_id) throws DaoException {
		try {
			getSqlMapExecutor().delete("SysGrant.sys_role_delete_all_sys_users", role_id);
		} catch (SQLException e) {
			throw new DaoException("SysGrant.sys_role_delete_all_sys_users error. cause:" + e, e);
		}
	}

	public void sysUserDeleteAllSysRoles(String user_id) throws DaoException {
		try {
			getSqlMapExecutor().delete("SysGrant.sys_user_delete_all_sys_roles", user_id);
		} catch (SQLException e) {
			throw new DaoException("SysGrant.sys_user_delete_all_sys_roles error. cause:" + e, e);
		}
	}

	public List sysPermitHaveNotSysRoles(String permit_id) throws DaoException {
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("SysGrant.sys_permit_have_not_sys_roles", permit_id);
		} catch (SQLException e) {
			throw new DaoException("SysGrant.sys_permit_have_not_sys_roles error. cause:" + e, e);
		}
		return list;
	}

	public List sysPermitHaveSysRoles(String permit_id) throws DaoException {
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("SysGrant.sys_permit_have_sys_roles", permit_id);
		} catch (SQLException e) {
			throw new DaoException("SysGrant.sys_permit_have_sys_roles error. cause:" + e, e);
		}
		return list;
	}

	public void sysRoleAddSysPermit(String role_id, String permit_id) throws DaoException {
		Map<String,String> param = new HashMap<String,String>();
		param.put("role_id", role_id);
		param.put("permit_id", permit_id);
		try {
			getSqlMapExecutor().insert("SysGrant.sys_role_add_sys_permit", param);
		} catch (SQLException e) {
			throw new DaoException("SysGrant.sys_role_add_sys_permit error. cause:" + e, e);
		}
	}

	public void sysRoleDeleteSysPermit(String role_id, String permit_id) throws DaoException {
		Map<String,String> param = new HashMap<String,String>();
		param.put("role_id", role_id);
		param.put("permit_id", permit_id);
		try {
			getSqlMapExecutor().insert("SysGrant.sys_role_delete_sys_permit", param);
		} catch (SQLException e) {
			throw new DaoException("SysGrant.sys_role_delete_sys_permit error. cause:" + e, e);
		}
	}

	public List sysRoleHaveNotSysPermits(String role_id) throws DaoException {
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("SysGrant.sys_role_have_not_sys_permits", role_id);
		} catch (SQLException e) {
			throw new DaoException("SysGrant.sys_role_have_not_sys_permits error. cause:" + e, e);
		}
		return list;
	}

	public List sysRoleHaveSysPermits(String role_id) throws DaoException {
		List list = new ArrayList();
		try {
			list = getSqlMapExecutor().queryForList("SysGrant.sys_role_have_sys_permits", role_id);
		} catch (SQLException e) {
			throw new DaoException("SysGrant.sys_role_have_sys_permits error. cause:" + e, e);
		}
		return list;
	}

	public void sysPermitDeleteAllSysRoles(String permit_id) throws DaoException {
		try {
			getSqlMapExecutor().delete("SysGrant.sys_permit_delete_all_sys_roles", permit_id);
		} catch (SQLException e) {
			throw new DaoException("SysGrant.sys_permit_delete_all_sys_roles error. cause:" + e, e);
		}
	}

	public void sysRoleDeleteAllSysPermits(String role_id) throws DaoException {
		try {
			getSqlMapExecutor().delete("SysGrant.sys_role_delete_all_sys_permits", role_id);
		} catch (SQLException e) {
			throw new DaoException("SysGrant.sys_role_delete_all_sys_permits error. cause:" + e, e);
		}
	}

	public SysPermit sysUserSysPermitCheck(String user_id, String permit_id) throws DaoException {
		SysPermit object = new SysPermit();
		  Map<String,String> map = new HashMap<String,String>();
		    map.put("user_id", user_id);
		    map.put("permit_id", permit_id);
		    
		try {
			object = (SysPermit) getSqlMapExecutor().queryForObject("SysGrant.sys_user_sys_permit_check", map);
		} catch (SQLException e) {
			throw new DaoException("Retrieve SysGrant.sys_user_sys_permit_check error. cause:" + e, e);
		}
		return object;
	}
}
